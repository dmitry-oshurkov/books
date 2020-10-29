package name.oshurkov.books.catalog

import name.oshurkov.books.storage.*
import org.springframework.beans.factory.annotation.*
import org.springframework.data.domain.*
import org.springframework.http.MediaType.*
import org.springframework.web.bind.annotation.*
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
                Link(rel = "self", href = "/catalog", type = "application/atom+xml;profile=opds-catalog;kind=navigation"),
                Link(rel = "start", href = "/catalog", type = "application/atom+xml;profile=opds-catalog;kind=navigation"),
                Link(rel = "http://opds-spec.org/crawlable", href = "/catalog/featured", type = "application/atom+xml;profile=opds-catalog;kind=acquisition")
            ),
            entries = listOf(
                Entry(
                    id = "tag:root:featured",
                    title = "Рекомендуемые",
                    updated = Date(),
                    content = Content("Рекомендуемые книги"),
                    links = listOf(Link(href = "/catalog/featured", type = "application/atom+xml;profile=opds-catalog;kind=acquisition"))
                ),
                Entry(
                    id = "tag:root:authors",
                    title = "По авторам",
                    updated = Date(),
                    content = Content("Поиск книг по авторам"),
                    links = listOf(Link(href = "/catalog/authors", type = "application/atom+xml;profile=opds-catalog;kind=navigation"))
                ),
                Entry(
                    id = "tag:root:genre",
                    title = "По жанрам",
                    updated = Date(),
                    content = Content("Поиск книг по жанрам"),
                    links = listOf(Link(href = "/catalog/genres", type = "application/atom+xml;profile=opds-catalog;kind=navigation"))
                )
            )
        )
    }

    @GetMapping("featured", produces = [APPLICATION_XML_VALUE])
    fun featured() = run {

        val bookEntries = bookRepository.findAll(PageRequest.of(1, 100))
            .map { it.toEntry() }
            .toList()

        Feed(
            id = "tag:featured",
            title = "Рекомендуемые книги",
            updated = Date(),
            links = listOf(
                Link(rel = "self", href = "/catalog/featured", type = "application/atom+xml;profile=opds-catalog;kind=acquisition"),
                Link(rel = "start", href = "/catalog", type = "application/atom+xml;profile=opds-catalog;kind=navigation"),
                Link(rel = "up", href = "/catalog", type = "application/atom+xml;profile=opds-catalog;kind=navigation"),
                Link(rel = "http://opds-spec.org/crawlable", href = "/catalog/featured", type = "application/atom+xml;profile=opds-catalog;kind=acquisition"),
            ),
            entries = bookEntries
        )
    }

    @GetMapping("authors", produces = [APPLICATION_XML_VALUE])
    fun authors() = run {

        val authorsEntries = authorRepository.findAll(Sort.by("lastName")).map {
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
                    Link(rel = "subsection", href = "/catalog/author/${it.id}/book", type = "application/atom+xml;profile=opds-catalog;kind=navigation"),
                )
            )
        }

        Feed(
            id = "tag:authors",
            title = "По авторам",
            updated = Date(),
            links = listOf(
                Link(rel = "self", href = "/catalog/authors", type = "application/atom+xml;profile=opds-catalog;kind=acquisition"),
                Link(rel = "start", href = "/catalog", type = "application/atom+xml;profile=opds-catalog;kind=navigation"),
                Link(rel = "up", href = "/catalog", type = "application/atom+xml;profile=opds-catalog;kind=navigation"),
            ),
            entries = authorsEntries
        )
    }

    @GetMapping("author/{authorId}/book", produces = [APPLICATION_XML_VALUE])
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
        summary = Summary(summary, summaryContentType),
        authors = authors.map { a -> Author(name = a.toString(), uri = null) },
        categories = genres.map { g -> Category(term = g.value, scheme = null) },
        rights = rights,
        language = language,
        issued = issued,
        publisher = publisher,
        sources = listOf(),
        links = listOf(
            Link(rel = "http://opds-spec.org/image", href = "/api/book/$id/image", type = coverContentType ?: ""),
            Link(rel = "http://opds-spec.org/image/thumbnail", href = "/api/book/$id/image/thumbnail", type = coverContentType ?: ""),
            Link(rel = "http://opds-spec.org/acquisition/open-access", href = "/api/book/$id/file", type = fileContentType, title = title)
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
