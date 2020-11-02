package name.oshurkov.books.catalog

import name.oshurkov.books.api.author.*
import name.oshurkov.books.api.book.*
import name.oshurkov.books.api.genre.*
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
            title = "Книжный каталог",
            links = listOf(
                Navigation(rel = "self", href = "/catalog"),
                Navigation(rel = "start", href = "/catalog"),
                Acquisition(rel = "http://opds-spec.org/featured", href = "/catalog/featured")
            ),
            entries = listOf(
                Entry(
                    id = "tag:root:featured",
                    title = "Рекомендуемые",
                    updated = Date(),
                    content = Content("Рекомендуемые книги"),
                    links = listOf(Acquisition(rel = "subsection", href = "/catalog/featured"))
                ),
                Entry(
                    id = "tag:root:authors",
                    title = "По авторам",
                    updated = Date(),
                    content = Content("Поиск книг по авторам"),
                    links = listOf(Navigation(rel = "subsection", href = "/catalog/authors"))
                ),
                Entry(
                    id = "tag:root:genre",
                    title = "По жанрам",
                    updated = Date(),
                    content = Content("Поиск книг по жанрам"),
                    links = listOf(Navigation(rel = "subsection", href = "/catalog/genres"))
                )
            )
        )
    }

    @GetMapping("featured", produces = [APPLICATION_XML_VALUE])
    fun featured() = run {

        val bookEntries = bookRepository.findAll()
            .map { it.toEntry() }
            .toList()

        Feed(
            id = "tag:featured",
            title = "Рекомендуемые книги",
            links = listOf(
                Acquisition(rel = "self", href = "/catalog/featured"),
                Navigation(rel = "start", href = "/catalog"),
                Navigation(rel = "up", href = "/catalog"),
            ),
            entries = bookEntries
        )
    }

    @GetMapping("authors", produces = [APPLICATION_XML_VALUE])
    fun authors() = run {

        val authorsEntries = authorRepository.findAll(Sort.by("lastName")).map {
            Entry(
                id = "tag:authors:${it.id}",
                title = it.toStringForList(),
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
                    Navigation(rel = "subsection", href = "/catalog/author/${it.id}/book"),
                )
            )
        }

        Feed(
            id = "tag:authors",
            title = "По авторам",
            links = listOf(
                Acquisition(rel = "self", href = "/catalog/authors"),
                Navigation(rel = "start", href = "/catalog"),
                Navigation(rel = "up", href = "/catalog"),
            ),
            entries = authorsEntries
        )
    }

    @GetMapping("author/{id}/book", produces = [APPLICATION_XML_VALUE])
    fun authorBooks(@PathVariable id: Int) = run {

        val bookEntries = bookRepository.findBooksByAuthorsId(id)
            .map { it.toEntry() }
            .toList()

        Feed(
            id = "tag:authors:${id}",
            title = "author $id",
            links = listOf(),
            entries = bookEntries
        )
    }

    @GetMapping("genres", produces = [APPLICATION_XML_VALUE])
    fun genres() = run {

        val genresEntries = genreRepository.findAll(Sort.by("name")).map {
            Entry(
                id = "tag:genres:${it.id}",
                title = it.name,
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
                    Navigation(rel = "subsection", href = "/catalog/genre/${it.id}/book"),
                )
            )
        }

        Feed(
            id = "tag:genres",
            title = "По жанрам",
            links = listOf(
                Acquisition(rel = "self", href = "/catalog/genres"),
                Navigation(rel = "start", href = "/catalog"),
                Navigation(rel = "up", href = "/catalog"),
            ),
            entries = genresEntries
        )
    }

    @GetMapping("genre/{id}/book", produces = [APPLICATION_XML_VALUE])
    fun genreBooks(@PathVariable id: Int) = run {

        val bookEntries = bookRepository.findBooksByGenresId(id)
            .map { it.toEntry() }
            .toList()

        Feed(
            id = "tag:genre:${id}",
            title = "genre $id",
            links = listOf(),
            entries = bookEntries
        )
    }


    private fun Book.toEntry() = Entry(
        id = "tag:books:$id",
        title = title,
        updated = updated,
        content = Content(content).toPlainText(),
        summary = Summary(summary, summaryContentType).toPlainText(),
        authors = authors.map { a -> Author(name = a.toString(), uri = null) },
        categories = genres.map { g -> Category(term = g.name, scheme = null) },
        rights = rights,
        language = language,
        issued = issued,
        publisher = publisher,
        sources = listOf(),
        links = listOf(
            *authors.map { a -> Navigation(rel = "related", href = "/catalog/author/${a.id}/book", title = "Все книги автора $a") }.toTypedArray(),
            *coverContentType?.let {
                listOf(
                    Link(rel = "http://opds-spec.org/image", href = "/api/book/$id/image", type = coverContentType),
                    Link(rel = "http://opds-spec.org/image/thumbnail", href = "/api/book/$id/image/thumbnail", type = coverContentType),
                )
            }.orEmpty().toTypedArray(),
            Link(rel = "http://opds-spec.org/acquisition/open-access", href = "/api/book/$id/file", type = fileContentType, title = "fb2.zip")
        )
    )

    private fun Content.toPlainText() = Content(content?.replace("<[^>]*>".toRegex(), ""), "text") // todo need to disable jackson html escaping
    private fun Summary.toPlainText() = Summary(content?.replace("<[^>]*>".toRegex(), ""), "text") // todo need to disable jackson html escaping

    @Autowired
    private lateinit var bookRepository: BookRepository

    @Autowired
    private lateinit var authorRepository: AuthorRepository

    @Autowired
    private lateinit var genreRepository: GenreRepository
}
