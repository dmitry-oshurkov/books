package name.oshurkov.books.book

import name.oshurkov.books.*
import name.oshurkov.books.author.*
import name.oshurkov.books.core.*
import name.oshurkov.books.file.*
import name.oshurkov.books.file.FileType.*
import name.oshurkov.books.genre.*
import name.oshurkov.books.sequence.*
import org.apache.commons.compress.archivers.sevenz.*
import org.ktorm.support.postgresql.*
import org.slf4j.*
import java.io.*
import java.nio.file.*
import java.text.*
import java.util.*
import kotlin.io.path.*
import kotlin.time.*


/**
 * Выполняет импорт книг из указанного каталога файловой системы.
 * Файлы книг могут быть находиться во вложенных каталогах.
 *
 * @param rootDir каталог файловой системы
 * @param afterSaveFile функция, выполняемая после импорта каждой книги
 */
fun importBooks(rootDir: String, afterSaveFile: (File) -> Unit = {}) {

    val files = Files.find(Path(rootDir), 10, { path, _ -> path.extension in listOf("fb2", "epub") || path.name.endsWith(".fb2.zip") })
        .map { it.toFile() }
        .toList()

    importBooks(files, afterSaveFile)
}


/**
 * Выполняет импорт книг из указанных файлов.
 *
 * @param files файлы книг
 * @param afterSaveFile функция, выполняемая после импорта каждой книги
 */
@OptIn(ExperimentalTime::class)
fun importBooks(files: List<File>, afterSaveFile: (File) -> Unit = {}) {

    val monotonic = TimeSource.Monotonic
    val start = monotonic.markNow()

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

    val fb2Books = fb2ToBooks(fb2, afterSaveFile)
    val epubBooks = epubToBooks(epub, afterSaveFile)
    val books = fb2Books + epubBooks

    insertBooksMetadata(books)

    val authors = selectAuthors()
    val genres = selectGenres()
    val sequences = selectSequences()

    books.forEach { insertBook(it, authors, genres, sequences) }

    val stop = monotonic.markNow()

    log.info("Imports finished: ${stop - start}")
}


/**
 * Выполняет экспорт книг в указанный каталог файловой системы.
 *
 * @param targetDir каталог файловой системы
 */
fun exportBooks(targetDir: String) {

    val root = File(targetDir)

    if (root.exists())
        root.deleteRecursively()

    val books = selectBooks()
    val bookAuthors = selectBookAuthors()
    val sequences = selectSequences().associateBy { it.id }
    val bookFiles = selectBookFiles()

    books.forEach {

        val authorsDir = bookAuthors[it.id]!!.joinToString { a -> a.toStringForList() }
        val sequence = sequences[it.sequenceId]

        val newFileDir = if (sequence != null && it.sequenceNumber != null)
            Path.of(root.absolutePath, authorsDir, sequence.name).toFile()
        else
            Path.of(root.absolutePath, authorsDir).toFile()

        newFileDir.mkdirs()

        bookFiles[it.id]?.forEach { file ->

            val prefix = prefix(it, sequence)
            val newFileName = "$prefix${it.title}.${file.type.extension}"

            File(newFileDir, newFileName).writeBytes(file.content)
        }
    }
}


/**
 * Выполняет архивирование книг в указанный каталог файловой системы. Архив 7Z.
 *
 * @param targetDir каталог файловой системы
 */
fun backupBooks(targetDir: String) {

    val root = File(targetDir)
    val backupName = "books-${SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(Date())}"
    val exported = File(root, backupName)
    exportBooks(exported.absolutePath)

    createSevenZipFile(File(root, "$backupName.7z"), exported)
    exported.deleteRecursively()
}


fun downloadBook(fileId: Int, fullFilename: Boolean) = run {

    val file = selectBookFile(fileId)

    if (file != null) {

        val book = selectBook(file.bookId)!!
        val authors = selectBookAuthors(file.bookId)

        val name = if (fullFilename)
            "[${authors.joinToString()}] - ${book.title}"
        else
            "${file.bookId}-$fileId"

        "$name.${file.type.extension}" to file.type.contentType and file.content
    } else
        "" to "" and null
}


fun bookImage(bookId: Int) = run {

    val book = selectBook(bookId)

    if (book?.cover != null)
        (book.coverContentType ?: "image/jpeg") to book.cover
    else
        "" to null
}


fun bookThumbnail(bookId: Int) = bookImage(bookId) // todo not implemented yet


private fun insertBooksMetadata(books: List<ImportedBook>) {

    val importedAuthors = books.flatMap { it.authors }.distinct()
    val importedGenres = books.flatMap { it.genres }.distinct()
    val importedSequences = books.mapNotNull { it.sequence }.distinct()


    db.bulkInsertOrUpdate(Authors) {
        importedAuthors.map { author ->
            item {
                set(it.last_name, author.lastName)
                set(it.first_name, author.firstName)
                set(it.middle_name, author.middleName)
            }
        }

        onConflict(it.last_name, it.first_name) { doNothing() }
    }

    db.bulkInsertOrUpdate(Genres) {
        importedGenres.map { genre ->
            item {
                set(it.name, genre)
            }
        }
        onConflict(it.name) { doNothing() }
    }

    db.bulkInsertOrUpdate(Sequences) {
        importedSequences.map { sequence ->
            item {
                set(it.name, sequence)
            }
        }
        onConflict(it.name) { doNothing() }
    }
}


private fun insertBook(book: ImportedBook, authors: List<Author>, genres: List<Genre>, sequences: List<Sequence>) {

    try {
        db.useTransaction {

        val sequence = sequences.find { g -> g.name == book.sequence }!!

            val id = db.insertOrUpdateReturning(Books, Books.id) {
                set(it.title, book.title)
                set(it.summary, book.summary)
                set(it.recommended, book.recommended)
                set(it.verified, book.verified)
                set(it.unread, book.unread)
                set(it.cover, book.cover)
                set(it.cover_content_type, book.coverContentType)
                set(it.hash, book.hash)
                set(it.issued, book.issued)
                set(it.language, book.language)
                set(it.publisher, book.publisher)
                set(it.sequence_id, sequence?.id)
                set(it.sequence_number, book.sequenceNumber)
                set(it.summary_content_type, book.summaryContentType)
                onConflict(it.hash) { doNothing() }
            }


            if (id != null) {

                db.bulkInsert(BookAuthors) {
                    authors
                        .filter { author -> book.authors.singleOrNull { author.lastName == it.lastName && author.firstName == it.firstName } != null }
                        .onEach { author ->
                            if (sequence != null)
                                db.insertOrUpdate(AuthorSequences) {
                                    set(it.author_id, author.id)
                                    set(it.sequences_id, sequence.id)
                                    onConflict { doNothing() }
                                }
                        }
                        .map { author ->
                            item {
                                set(it.book_id, id)
                                set(it.authors_id, author.id)
                            }
                        }
                }


                db.bulkInsert(BookGenres) {
                    genres
                        .filter { genre -> book.genres.singleOrNull { genre.name == it } != null }
                        .map { genre ->
                            item {
                                set(it.book_id, id)
                                set(it.genres_id, genre.id)
                            }
                        }
                }


                db.bulkInsert(BookFiles) {
                    book.files.map { file ->
                        item {
                            set(it.book_id, id)
                            set(it.content, file.content)
                            set(it.hash, file.hash)
                            set(it.type, file.type)
                        }
                    }
                }

                log.info("Imported: ${book.title}")
            }
        }
    } catch (e: Exception) {
        log.error("Error import: ${book.title}")
    }
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


private val log = LoggerFactory.getLogger(::createSevenZipFile::class.java)
