package name.oshurkov.books.catalog

import name.oshurkov.books.api.author.*
import name.oshurkov.books.api.book.*
import name.oshurkov.books.api.genre.*
import org.springframework.beans.factory.annotation.*
import org.springframework.data.domain.*
import org.springframework.data.repository.*
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
                Navigation(rel = "self", href = root),
                Navigation(rel = "start", href = root),
            ),
            entries = listOf(
                Entry(
                    id = "tag:root:recommended",
                    title = "Рекомендуемые",
                    updated = Date(),
                    content = Content("Рекомендуемые книги"),
                    links = listOf(Acquisition(rel = "subsection", href = recommended))
                ),
                Entry(
                    id = "tag:root:authors",
                    title = "По авторам",
                    updated = Date(),
                    content = Content("Поиск книг по авторам"),
                    links = listOf(Navigation(rel = "subsection", href = authors))
                ),
                Entry(
                    id = "tag:root:genre",
                    title = "По жанрам",
                    updated = Date(),
                    content = Content("Поиск книг по жанрам"),
                    links = listOf(Navigation(rel = "subsection", href = genres))
                )
            )
        )
    }

    @GetMapping("recommended", produces = [APPLICATION_XML_VALUE])
    fun recommended() = run {

        val bookEntries = bookRepository.findBooksByRecommendedTrue()
            .map { it.toEntry() }
            .toList()

        Feed(
            id = "tag:recommended",
            title = "Рекомендуемые книги",
            links = listOf(
                Acquisition(rel = "self", href = recommended),
                Navigation(rel = "start", href = root),
                Navigation(rel = "up", href = root),
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
                links = listOf(
                    Navigation(rel = "subsection", href = "/catalog/author/${it.id}/book"),
                )
            )
        }

        Feed(
            id = "tag:authors",
            title = "По авторам",
            links = listOf(
                Acquisition(rel = "self", href = authors),
                Navigation(rel = "start", href = root),
                Navigation(rel = "up", href = root),
            ),
            entries = authorsEntries
        )
    }

    @GetMapping("author/{id}/book", produces = [APPLICATION_XML_VALUE])
    fun authorBooks(@PathVariable id: Int) = run {

        val author = authorRepository.findByIdOrNull(id)
        val bookEntries = bookRepository.findBooksByAuthorsId(id)
            .map { it.toEntry() }
            .toList()

        Feed(
            id = "tag:authors:${id}",
            title = "Все книги автора ${author?.toString()}",
            links = if (author?.sequences?.isNotEmpty() == true)
                listOf(Acquisition(rel = "http://opds-spec.org/facet", href = "/catalog/author/$id/sequence", title = "По сериям", facetGroup = "Серии", activeFacet = true))
            else
                emptyList(),
            entries = bookEntries
        )
    }

    @GetMapping("author/{id}/sequence", produces = [APPLICATION_XML_VALUE])
    fun authorSequences(@PathVariable id: Int) = run {

        val author = authorRepository.findByIdOrNull(id)

        val sequencesEntries = author?.sequences.orEmpty().map {
            Entry(
                id = "tag:sequences:${it.id}",
                title = it.name,
                updated = it.updated,
                links = listOf(
                    Navigation(rel = "subsection", href = "/catalog/sequence/${it.id}/book"),
                    Navigation(rel = "up", href = "/catalog/author/$id/sequence"),
                )
            )
        }

        Feed(
            id = "tag:authors",
            title = "По сериям",
            links = listOf(
                Acquisition(rel = "self", href = "/catalog/author/$id/sequence"),
                Navigation(rel = "start", href = root),
                Navigation(rel = "up", href = authors),
            ),
            entries = sequencesEntries
        )
    }

    @GetMapping("sequence/{id}/book", produces = [APPLICATION_XML_VALUE])
    fun authorSequenceBooks(@PathVariable id: Int) = run {

        val bookEntries = bookRepository.findBooksBySequenceIdOrderBySequenceNumber(id)
            .map { it.toEntry(includeSequenceNumber = true) }
            .toList()

        Feed(
            id = "tag:sequences:${id}",
            title = "Все книги серии",
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
                links = listOf(
                    Navigation(rel = "subsection", href = "/catalog/genre/${it.id}/book"),
                )
            )
        }

        Feed(
            id = "tag:genres",
            title = "По жанрам",
            links = listOf(
                Acquisition(rel = "self", href = genres),
                Navigation(rel = "start", href = root),
                Navigation(rel = "up", href = root),
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


    private fun Book.toEntry(includeSequenceNumber: Boolean = false) = Entry(
        id = "tag:books:$id",
        title = if (includeSequenceNumber && sequenceNumber != null)
            "$sequenceNumber. $title"
        else
            title,
        updated = updated,
        content = Content(content).toPlainText(),
        summary = Summary(summary, summaryContentType).toPlainText(),
        authors = authors.map { Author(name = it.toString(), uri = "http://opds-spec.org/authors/$id") },
        categories = genres.map { Category(term = it.name, scheme = null) },
        rights = rights,
        language = language,
        issued = issued,
        publisher = publisher,
        sources = listOf(),
        links = listOf(
            *authors.map { Navigation(rel = "related", href = "/catalog/author/${it.id}/book", title = "Все книги автора $it") }.toTypedArray(),
            *coverContentType?.let {
                listOf(
                    Link(rel = "http://opds-spec.org/image", href = "/api/book/$id/image", type = coverContentType),
                    Link(rel = "http://opds-spec.org/image/thumbnail", href = "/api/book/$id/image/thumbnail", type = coverContentType),
                )
            }.orEmpty().toTypedArray(),
            *files.map { Link(rel = "http://opds-spec.org/acquisition/open-access", href = "/api/book/$id/file/${it.id}", type = it.type.contentType, title = it.type.contentType) }.toTypedArray()
        )
    )

    private fun Content.toPlainText() = Content(content?.replace("<[^>]*>".toRegex(), ""), "text") // todo need to disable jackson html escaping
    private fun Summary.toPlainText() = Summary(content?.replace("<[^>]*>".toRegex(), ""), "text") // todo need to disable jackson html escaping

    private val root = "/catalog"
    private val recommended = "$root/recommended"
    private val authors = "$root/authors"
    private val genres = "$root/genres"

    @Autowired
    private lateinit var bookRepository: BookRepository

    @Autowired
    private lateinit var authorRepository: AuthorRepository

    @Autowired
    private lateinit var genreRepository: GenreRepository
}
