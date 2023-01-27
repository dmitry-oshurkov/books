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

    log.info("Import: reading files")

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

    log.info("Import started")

    val monotonic = TimeSource.Monotonic
    val start = monotonic.markNow()

    val books = parseFb2(files)

    insertBooksMetadata(books)

    val authors = selectAuthors()
    val genres = selectGenres()
    val sequences = selectSequences()

    books.forEach { insertBook(it, authors, genres, sequences, afterSaveFile) }

    val stop = monotonic.markNow()

    log.info("Import finished: ${stop - start}")
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
            // todo check filename size 255 bytes
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


private val log by logger()
