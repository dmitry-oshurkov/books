package name.oshurkov.books.api.file

import name.oshurkov.books.*
import name.oshurkov.books.api.author.*
import name.oshurkov.books.api.book.*
import name.oshurkov.books.api.file.FileType.*
import name.oshurkov.books.api.genre.*
import nl.siegmann.epublib.epub.*
import org.slf4j.*
import java.io.*
import javax.xml.namespace.*

fun parseEpub(files: Map<FileType, List<File>>) = files[EPUB].orEmpty().map { EpubReader().readEpub(it.inputStream()) to it and EPUB }

fun epubToBooks(
    epub: List<Triple<EpubBook, File, FileType>>,
    authors: List<Author>,
    genres: List<Genre>,
    bookFileFn: (Book, File, FileType, String, Int?) -> BookFile,
    afterSaveFile: (File) -> Unit
) =
    epub
        .mapNotNull { (ep, file, type) ->

            runCatching {
                val bookAuthors = ep.metadata.authors
                    .mapNotNull { authors.find { a -> a.firstName == it.firstname && a.lastName == it.lastname } }
                    .toSet()

                val bookGenres = ep.metadata.subjects
                    .mapNotNull { genres.find { g -> g.name == it } }
                    .toSet()

                val summary = ep.metadata.descriptions.firstOrNull()

                Book(
                    title = ep.title,
                    content = ep.metadata.otherProperties[QName("se:long-description")],
                    summary = summary,
                    summaryContentType = summaryType(summary),
                    rights = ep.metadata.rights.firstOrNull(),
                    language = lang(ep.metadata.language),
                    issued = ep.metadata.dates.firstOrNull()?.value,
                    publisher = ep.metadata.publishers.firstOrNull(),
                    cover = ep.coverImage?.data,
                    coverContentType = ep.coverImage?.mediaType?.name,
                    recommended = file.name.contains("*]") || file.name.startsWith("*"),
                    sequence = null,
                    sequenceNumber = null,
                    hash = bookHash(bookAuthors, ep.title),
                    authors = bookAuthors,
                    genres = bookGenres
                )
                    .also {
                        val bookFile = bookFileFn(it, file, type, it.title, null)
                        it.files += bookFile
                    }
            }
                .onSuccess {
                    afterSaveFile(file)
                    log.info("Parsed: [${file.absolutePath}]")
                }
                .onFailure {
                    afterSaveFile(file)
                    log.error("Error import: [${file.absolutePath}] - message: ${it.message}")
                }
                .getOrNull()
        }

typealias EpubBook = nl.siegmann.epublib.domain.Book


private val log = LoggerFactory.getLogger(::parseEpub::class.java)
