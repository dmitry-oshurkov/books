package name.oshurkov.books.catalog

import name.oshurkov.books.storage.*
import nl.siegmann.epublib.epub.*
import org.springframework.beans.factory.annotation.*
import org.springframework.data.domain.*
import org.springframework.http.MediaType.*
import org.springframework.web.bind.annotation.*
import java.io.*
import java.nio.file.*
import java.util.*

@RestControllerAdvice
@RequestMapping("/catalog")
class CatalogController {

    @GetMapping(produces = [APPLICATION_XML_VALUE])
    fun root() = run {

        Feed(
            id = "tag:root",
            title = "Каталог книг",
            updated = Date(),
            links = listOf(
                Link(rel = "self", href = "catalog", type = "application/atom+xml;profile=opds-catalog;kind=navigation"),
                Link(rel = "start", href = "catalog", type = "application/atom+xml;profile=opds-catalog;kind=navigation"),
                Link(rel = "http://opds-spec.org/crawlable", href = "catalog/featured", type = "application/atom+xml;profile=opds-catalog;kind=acquisition")
            ),
            entries = listOf(
                Entry(
                    id = "tag:root:featured",
                    title = "Рекомендуемые",
                    updated = Date(),
                    content = Content("Рекомендуемые книги"),
                    links = listOf(Link(href = "catalog/featured", type = "application/atom+xml;profile=opds-catalog;kind=acquisition"))
                ),
                Entry(
                    id = "tag:root:authors",
                    title = "По авторам",
                    updated = Date(),
                    content = Content("Поиск книг по авторам"),
                    links = listOf(Link(href = "catalog/authors", type = "application/atom+xml;profile=opds-catalog;kind=navigation"))
                ),
                Entry(
                    id = "tag:root:genre",
                    title = "По жанрам",
                    updated = Date(),
                    content = Content("Поиск книг по жанрам"),
                    links = listOf(Link(href = "catalog/genres", type = "application/atom+xml;profile=opds-catalog;kind=navigation"))
                )
            )
        )
    }

    @GetMapping("featured", produces = [APPLICATION_XML_VALUE])
    fun featured() = run {

        val bookEntries = bookRepository.findAll(PageRequest.of(1, 100))
            .map { it.toEntry() }

        val epub = EpubReader().readEpub(FileInputStream("/home/dmitry/Загрузки/qq.epub"))
//        val epub = EpubReader().readEpub(FileInputStream("/home/dmitry/yandex.disk/Книги/Брэдбери, Рэй/451 градус по Фаренгейту.epub"))
        Files.write(Path.of("/home/dmitry/Загрузки/qq.epub${epub.coverImage.mediaType.defaultExtension}"), epub.coverImage.data)

        Feed(
            id = "tag:featured",
            title = "Рекомендуемые книги",
            updated = Date(),
            links = listOf(
                Link(rel = "self", href = "catalog/featured", type = "application/atom+xml;profile=opds-catalog;kind=acquisition"),
                Link(rel = "start", href = "catalog", type = "application/atom+xml;profile=opds-catalog;kind=navigation"),
                Link(rel = "up", href = "catalog", type = "application/atom+xml;profile=opds-catalog;kind=navigation"),
                Link(rel = "http://opds-spec.org/crawlable", href = "catalog/featured", type = "application/atom+xml;profile=opds-catalog;kind=acquisition"),
            ),
            entries = bookEntries + listOf(
                Entry(
                    id = "2",
                    title = epub.metadata.titles[0],
                    updated = Date(),
                    summary = Summary(epub.metadata.descriptions.firstOrNull()),
                    authors = epub.metadata.authors.map {
                        Author(
                            name = "${it.firstname} ${it.lastname}",
                            uri = null
                        )
                    },
                    categories = epub.metadata.subjects.map {
                        Category(
                            scheme = "http://purl.org/dc/terms/LCSH",
                            term = it
                        )
                    },
                    rights = epub.metadata.rights.firstOrNull(),
                    language = epub.metadata.language,
//                    issued = book.metadata.dates.firstOrNull()?.let { ZonedDateTime.parse(it.value, DateTimeFormatter.ISO_INSTANT)  },
                    publisher = epub.metadata.publishers.firstOrNull(),
                    sources = null,
                    content = Content(
                        content = "<p>\n" +
                            "            <a href=\"https://standardebooks.org/ebooks/h-g-wells\">\n" +
                            "               <abbr>H. G.</abbr>\n" +
                            "               Wells’\n" +
                            "            </a>\n" +
                            "            classic tale of alien invasion has to this day never been out of print. Like many works of the era, it was originally published as a serial—though the publisher,\n" +
                            "            <i>Pearson’s Magazine</i>\n" +
                            "            , demanded to know the ending before committing to publication.\n" +
                            "         </p>\n" +
                            "         <p>\n" +
                            "            <i>The War of the Worlds</i>\n" +
                            "            , with its matter-of-fact narrative style and deft mixture of contemporary science and fictionalized interstellar war machines, became an instant hit. Its themes of colonialism, social Darwinism, good and evil, and total war still resonate with modern-day readers, so much so that it’s been continuously adapted for screen, radio, television, comics, and print.\n" +
                            "         </p>",
                        type = "text/html"
                    ),
                    links = listOf(
                        Link(rel = "http://opds-spec.org/image", href = "file:///home/dmitry/Загрузки/qq.epub.jpg", type = "image/jpeg"),
                        Link(rel = "http://opds-spec.org/image/thumbnail", href = "file:///home/dmitry/Загрузки/qq.epub.jpg", type = "image/jpeg"),
                        Link(rel = "http://opds-spec.org/acquisition/open-access", href = "rowland-smith.epub", type = "application/epub+zip", title = "Recommended compatible epub")
                    )
                )
            )
        )
    }

    @GetMapping("authors", produces = [APPLICATION_XML_VALUE])
    fun authors() = run {

        val authorsEntries = authorRepository.findAll().map {
            Entry(
                id = "tag:authors:${it.id}",
                title = it.toString(),
                updated = it.updated,
                content = null,
                summary = null,
                authors = null,
                rights = null,
                language = null,
                issued = null,
                publisher = null,
                sources = listOf(),
                links = listOf(
                    Link(rel = "subsection", href = "/catalog/authors/${it.id}/books", type = "application/atom+xml;profile=opds-catalog;kind=navigation"),
                )
            )
        }

        Feed(
            id = "tag:authors",
            title = "По авторам",
            updated = Date(),
            links = listOf(
                Link(rel = "self", href = "catalog/authors", type = "application/atom+xml;profile=opds-catalog;kind=acquisition"),
                Link(rel = "start", href = "catalog", type = "application/atom+xml;profile=opds-catalog;kind=navigation"),
                Link(rel = "up", href = "catalog", type = "application/atom+xml;profile=opds-catalog;kind=navigation"),
            ),
            entries = authorsEntries
        )
    }

    @GetMapping("authors/{authorId}/books", produces = [APPLICATION_XML_VALUE])
    fun authorBooks(@PathVariable authorId: Int) = run {

        val bookEntries = bookRepository.findBooksByAuthorsId(authorId)
            .map { it.toEntry() }
            .toList()

        Feed(
            id = "tag:authors:${authorId}",
            title = "author $authorId",
            updated = Date(),
            links = listOf(
            ),
            entries = bookEntries
        )
    }

    private fun Book.toEntry() = Entry(
        id = "tag:books:$id",
        title = title,
        updated = updated,
        content = Content(content),
        summary = Summary(summary),
        authors = authors.map { a -> Author(name = a.toString(), uri = null) },
        categories = genres.map { g -> Category(term = g.value, scheme = null) },
        rights = rights,
        language = language,
        issued = issued,
        publisher = publisher,
        sources = listOf(),
        links = listOf(
            Link(rel = "http://opds-spec.org/image", href = "/catalog/image/$id", type = coverContentType ?: ""),
            Link(rel = "http://opds-spec.org/image/thumbnail", href = "/catalog/image/thumbnail/$id", type = coverContentType ?: ""),
            Link(rel = "http://opds-spec.org/acquisition/open-access", href = "/catalog/file/$id", type = fileContentType, title = title)
        )
    )

    @GetMapping("genres", produces = [APPLICATION_XML_VALUE])
    fun genres() = run {
    }

    @Autowired
    private lateinit var bookRepository: BookRepository

    @Autowired
    private lateinit var authorRepository: AuthorRepository
}
