package name.oshurkov.books.storage

import name.oshurkov.books.*
import name.oshurkov.books.fb2.parser.*
import name.oshurkov.books.storage.BookExt.*
import org.apache.tomcat.util.codec.binary.*
import org.slf4j.*
import org.springframework.beans.factory.annotation.*
import org.springframework.stereotype.*
import java.io.*
import javax.xml.namespace.*

@Component
class BookService {

    fun import(arrayOfFiles: Array<out File>, bookRootDir: File, preprocessFile: (String, Set<Author>, File) -> File) = run {

        if (arrayOfFiles.isEmpty())
            return@run

        val files = arrayOfFiles
            .groupBy {
                when {
                    it.extension == "fb2" -> FB2
                    it.name.endsWith(".fb2.zip") -> FBZ
                    it.extension == "epub" -> EPUB
                    else -> UNSUPPORTED
                }
            }

        val fb2 = fictionBookService.parse(files)
        val epub = epubService.parse(files)

        val authors = authors(fb2, epub)
        val genres = genres(fb2, epub)

        val fb2Books = fb2
            .map { (fb, f, ext) ->

                val bookAuthors = fb.description.titleInfo.authors
                    .mapNotNull { authors.find { a -> a.firstName == it.firstName && a.middleName == it.middleName && a.lastName == it.lastName } }
                    .toSet()

                val bookGenres = fb.description.titleInfo.genres
                    .mapNotNull { genres.find { g -> g.name == it } }
                    .toSet()

                val binary = fb.binaries[fb.description.titleInfo.coverPage.firstOrNull()?.value?.trimStart('#')]

                val file = preprocessFile(fb.title, bookAuthors, f)
                val summary = fb.description.titleInfo.annotation?.annotations?.firstOrNull()?.text

                Book(
                    title = fb.title,
                    content = null,
                    summary = summary,
                    summaryContentType = summaryType(summary),
                    rights = null,
                    language = lang(fb.lang),
                    issued = fb.description.titleInfo.date,
                    publisher = null,
                    cover = binary?.binary?.let { Base64.decodeBase64(it) },
                    coverContentType = binary?.contentType,
                    file = file.relativeTo(bookRootDir).path,
                    fileContentType = fileType(ext),
                    authors = bookAuthors,
                    genres = bookGenres
                )
            }

        val epubBooks = epub
            .map { (ep, f, ext) ->

                val bookAuthors = ep.metadata.authors
                    .mapNotNull { authors.find { a -> a.firstName == it.firstname && a.lastName == it.lastname } }
                    .toSet()

                val bookGenres = ep.metadata.subjects
                    .mapNotNull { genres.find { g -> g.name == it } }
                    .toSet()

                val file = preprocessFile(ep.metadata.titles[0], bookAuthors, f)
                val summary = ep.metadata.descriptions.firstOrNull()

                Book(
                    title = ep.metadata.titles[0],
                    content = ep.metadata.otherProperties[QName("se:long-description")],
                    summary = summary,
                    summaryContentType = summaryType(summary),
                    rights = ep.metadata.rights.firstOrNull(),
                    language = lang(ep.metadata.language),
                    issued = ep.metadata.dates.firstOrNull()?.value,
                    publisher = ep.metadata.publishers.firstOrNull(),
                    cover = ep.coverImage?.data,
                    coverContentType = ep.coverImage?.mediaType?.name,
                    file = file.relativeTo(bookRootDir).path,
                    fileContentType = fileType(ext),
                    authors = bookAuthors,
                    genres = bookGenres
                )
            }

        bookRepository.saveAll(fb2Books + epubBooks).onEach {
            log.info("Imported: ${it.title} [${it.file}]")
        }
    }


    private fun genres(fb2: List<Triple<FictionBook, File, BookExt>>, epub: List<Triple<nl.siegmann.epublib.domain.Book, File, BookExt>>) = run {

        val fb2Genres = fb2.flatMap { (fb, _, _) -> fb.description.titleInfo.genres }
        val epubGenres = epub.flatMap { (ep, _, _) -> ep.metadata.subjects }

        val entities = (
            genreRepository.findAll() + (fb2Genres + epubGenres)
                .map { Genre(name = it) }
            )
            .distinctBy { it.name }

        genreRepository.saveAll(entities)
    }

    private fun authors(fb2: List<Triple<FictionBook, File, BookExt>>, epub: List<Triple<nl.siegmann.epublib.domain.Book, File, BookExt>>) = run {

        val fb2Authors = fb2
            .flatMap { (fb, _, _) -> fb.description.titleInfo.authors }
            .map { it.firstName to it.middleName and (it.lastName ?: "?") }

        val epubAuthors = epub
            .flatMap { (ep, _, _) -> ep.metadata.authors }
            .map { it.firstname to null and it.lastname }

        val entities = (
            authorRepository.findAll() + (fb2Authors + epubAuthors)
                .map { (firstName, middleName, lastName) ->
                    Author(
                        firstName = firstName?.trim()?.ifEmpty { null },
                        middleName = middleName?.trim(),
                        lastName = lastName.trim(),
                    )
                }
            )
            .distinctBy { listOf(it.lastName, it.middleName, it.firstName) }

        authorRepository.saveAll(entities)
    }

    private fun summaryType(value: String?) = if (value?.contains("</p>") == true)
        "text/html"
    else
        "text"

    private fun fileType(ext: BookExt) = when (ext) {
        FB2 -> "application/xml"
        FBZ -> "application/zip"
        EPUB -> "application/epub+zip"
        UNSUPPORTED -> ""
    }

    private fun lang(value: String) = when (value.toLowerCase()) {
        "ru" -> "ru-RU"
        "en" -> "en-US"
        else -> value
    }

    @Autowired
    private lateinit var fictionBookService: FictionBookService

    @Autowired
    private lateinit var epubService: EpubService

    @Autowired
    private lateinit var bookRepository: BookRepository

    @Autowired
    private lateinit var authorRepository: AuthorRepository

    @Autowired
    private lateinit var genreRepository: GenreRepository

    private val log = LoggerFactory.getLogger(BookService::class.java)
}
