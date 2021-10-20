package name.oshurkov.books.api.book

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import name.oshurkov.books.*
import name.oshurkov.books.Properties.Companion.forceFb2CompressForStore
import name.oshurkov.books.Repositories.Companion.authorsRep
import name.oshurkov.books.Repositories.Companion.bookFilesRep
import name.oshurkov.books.Repositories.Companion.booksRep
import name.oshurkov.books.Repositories.Companion.genresRep
import name.oshurkov.books.Repositories.Companion.sequencesRep
import name.oshurkov.books.api.author.*
import name.oshurkov.books.api.file.*
import name.oshurkov.books.api.file.FileType.*
import name.oshurkov.books.api.file.fb2.parser.*
import name.oshurkov.books.api.genre.*
import name.oshurkov.books.api.sequence.Sequence
import org.apache.commons.compress.archivers.sevenz.*
import org.slf4j.*
import java.io.*
import java.nio.file.*
import java.text.*
import java.util.Date
import java.util.zip.*
import java.util.zip.Deflater.*
import kotlin.io.path.*

fun importBooksBatch(urls: List<String>) {

    val files = urls
        .filter { it.isNotBlank() }
        .pmap {
            val response = HttpClient().get<HttpResponse>(it)
            val bytes = response.receive<ByteArray>()
            val disposition = response.headers["Content-Disposition"] ?: ""
            val filename = disposition.substringAfter("filename=")
            val suffix = filename.substring(filename.length - 8, filename.length)

            bytes to suffix
        }
        .map { (bytes, suffix) ->
            createTempFile("books", suffix)
                .apply { writeBytes(bytes) }
                .toFile()
        }

    try {
        importBooks(files)
    } finally {
        files.forEach { it.delete() }
    }
}

fun importBooks(files: List<File>, afterSaveFile: (File) -> Unit = {}) {

    val filesMap = files
        .groupBy {
            when {
                it.extension == "fb2" -> FB2
                it.name.endsWith(".fb2.zip") -> FBZ
                it.extension == "epub" -> EPUB
                else -> null
            }
        }
        .filter { it.key != null }
        .mapKeys { it.key!! }

    val fb2 = parseFb2(filesMap)
    val epub = parseEpub(filesMap)

    val authors = extractAuthors(fb2, epub)
    val genres = extractGenres(fb2, epub)
    val sequences = extractSequences(fb2)

    val fb2Books = fb2ToBooks(fb2, authors, genres, ::bookFile, afterSaveFile, sequences)
    val epubBooks = epubToBooks(epub, authors, genres, ::bookFile, afterSaveFile)
    val books = fb2Books + epubBooks

    genresRep.saveAll(genres)
    sequencesRep.saveAll(sequences)
    authorsRep.saveAll(authors)
    booksRep.saveAll(books).onEach {
        bookFilesRep.saveAll(it.files)
        log.info("Imported: ${it.title}")
    }
}

fun exportBooks(targetDir: String) {

    val root = File(targetDir)

    if (root.exists())
        root.deleteRecursively()

    booksRep.findAll().forEach {

        val authorsDir = it.authors.joinToString { a -> a.toStringForList() }

        val newFileDir = if (it.sequence != null && it.sequenceNumber != null)
            Path.of(root.absolutePath, authorsDir, it.sequence.name).toFile()
        else
            Path.of(root.absolutePath, authorsDir).toFile()

        newFileDir.mkdirs()

        it.files.forEach { f ->

            val baseName = "${it.title}.${f.type.extension}"

            val newFileName = if (it.sequence != null && it.sequenceNumber != null)
                "[${it.sequenceNumber}${if (it.recommended) "*" else ""}] $baseName"
            else
                "${if (it.recommended) "* " else ""}$baseName"

            File(newFileDir, newFileName).writeBytes(f.content)
        }
    }
}

fun backupBooks(targetDir: String) {

    val root = File(targetDir)
    val backupName = "books-${SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(Date())}"
    val exported = File(root, backupName)
    exportBooks(exported.absolutePath)

    createSevenZipFile(File(root, "$backupName.7z"), exported)
    exported.deleteRecursively()
}


private fun bookFile(book: Book, file: File, type: FileType, title: String, seqNo: Int?) = run {

    val (content, hash) = when (type) {

        FBZ -> {
            val bytes = unzipFile(file)
            zip(seqNo, title, bytes) to uuid(bytes)
        }

        FB2 -> if (forceFb2CompressForStore)
            file.readBytes().let { zip(seqNo, title, it) to uuid(it) }
        else
            file.readBytes().let { it to uuid(it) }

        else -> file.readBytes().let { it to uuid(it) }
    }

    val newType = if (type == FB2 && forceFb2CompressForStore)
        FBZ
    else
        type

    BookFile(book, content, hash, newType)
}

private fun extractSequences(fb2: List<Triple<FictionBook, File, FileType>>) = run {

    val fb2Sequences = fb2
        .mapNotNull { (fb, _, _) -> fb.description.titleInfo.sequence?.name }

    val epubSequences = emptyList<String>()

    (
        sequencesRep.findAll() + (fb2Sequences + epubSequences)
            .map { Sequence(name = it) }
        )
        .distinctBy { it.name }
}

private fun extractGenres(fb2: List<Triple<FictionBook, File, FileType>>, epub: List<Triple<EpubBook, File, FileType>>) = run {

    val fb2Genres = fb2
        .flatMap { (fb, _, _) -> fb.description.titleInfo.genres }
        .map { fb2GenreToString(it) }

    val epubGenres = epub.flatMap { (ep, _, _) -> ep.metadata.subjects }

    (
        genresRep.findAll() + (fb2Genres + epubGenres)
            .map { Genre(name = it) }
        )
        .distinctBy { it.name }
}

private fun extractAuthors(fb2: List<Triple<FictionBook, File, FileType>>, epub: List<Triple<EpubBook, File, FileType>>) = run {

    val fb2Authors = fb2
        .flatMap { (fb, _, _) -> fb.description.titleInfo.authors }
        .map { it.firstName to it.middleName and (it.lastName ?: "?") }

    val epubAuthors = epub
        .flatMap { (ep, _, _) -> ep.metadata.authors }
        .map { it.firstname to null and it.lastname }

    (
        authorsRep.findAll() + (fb2Authors + epubAuthors)
            .map { (firstName, middleName, lastName) ->
                Author(
                    firstName = firstName?.trim()?.ifEmpty { null },
                    middleName = middleName?.trim(),
                    lastName = lastName.trim(),
                )
            }
        )
        .distinctBy { listOf(it.lastName, it.middleName, it.firstName) }
}

private fun createSevenZipFile(sevenZ: File, folder: File) {

    runCatching {

        SevenZOutputFile(sevenZ).use { out ->

            Files.walk(folder.toPath())
                .map { it.toFile() }
                .filter { !it.isDirectory }
                .forEach { file ->
                    FileInputStream(file).use {
                        val entry = out.createArchiveEntry(file, file.toRelativeString(folder))
                        out.putArchiveEntry(entry)
                        out.write(file.readBytes())
                        out.closeArchiveEntry()
                    }
                }

            out.finish()
        }
    }
}

private fun zip(seqNo: Int?, title: String, bytes: ByteArray) =
    ByteArrayOutputStream().use {
        ZipOutputStream(it).use { stream ->
            stream.setLevel(BEST_COMPRESSION)
            stream.putNextEntry(ZipEntry(if (seqNo != null) "[$seqNo] $title.fb2" else "$title.fb2"))
            stream.write(bytes, 0, bytes.size)
            stream.closeEntry()
        }
        it.toByteArray()
    }


private val log = LoggerFactory.getLogger(::importBooks::class.java)
