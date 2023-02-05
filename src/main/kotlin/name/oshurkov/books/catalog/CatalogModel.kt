package name.oshurkov.books.catalog

import com.fasterxml.jackson.dataformat.xml.annotation.*
import name.oshurkov.books.book.*
import name.oshurkov.books.catalog.CatalogPath.Companion.authors
import name.oshurkov.books.catalog.CatalogPath.Companion.catalog
import name.oshurkov.books.catalog.CatalogPath.Companion.genres
import name.oshurkov.books.catalog.CatalogPath.Companion.recent
import name.oshurkov.books.catalog.CatalogPath.Companion.recommended
import name.oshurkov.books.catalog.CatalogPath.Companion.unread
import name.oshurkov.books.catalog.CatalogPath.Companion.unverified
import name.oshurkov.books.genre.*
import java.time.*
import java.util.*


@JacksonXmlRootElement(localName = "feed")
data class Feed(

    @JacksonXmlProperty(isAttribute = true, localName = "xmlns:dc") val dcterms: String = "http://purl.org/dc/terms/",
    @JacksonXmlProperty(isAttribute = true, localName = "xmlns:thr") val thr: String = "http://purl.org/syndication/thread/1.0",
    @JacksonXmlProperty(isAttribute = true, localName = "xmlns:opds") val opds: String = "http://opds-spec.org/2010/catalog",
    @JacksonXmlProperty(isAttribute = true, localName = "xmlns:opensearch") val opensearch: String = "http://a9.com/-/spec/opensearch/1.1/",
    @JacksonXmlProperty(isAttribute = true, localName = "xmlns:xsi") val xsi: String = "http://www.w3.org/2001/XMLSchema-instance",
    @JacksonXmlProperty(isAttribute = true) val xmlns: String = "http://www.w3.org/2005/Atom",
    @JacksonXmlProperty(isAttribute = true, localName = "xml:lang") val lang: String = "ru",

    val id: String,
    val title: String,
    val updated: Date = Date(),
    val author: Author? = null,

    @JacksonXmlProperty(localName = "link")
    @JacksonXmlElementWrapper(useWrapping = false)
    val links: List<CatalogLink>?,

    @JacksonXmlProperty(localName = "entry")
    @JacksonXmlElementWrapper(useWrapping = false)
    val entries: List<Entry>?
) {
    companion object {

        val rootExample = Feed(
            id = "tag:root",
            title = "Книжный каталог",
            links = listOf(
                Navigation(rel = "self", href = catalog.path),
                Navigation(rel = "start", href = catalog.path),
            ),
            entries = listOf(
                Entry(
                    id = "tag:root:unread",
                    title = "Непрочитанные",
                    updated = OffsetDateTime.now(),
                    content = Content("Непрочитанные книги"),
                    links = linksAcqImgThumb(subsection = unread.path, img = "/abstract.png")
                ),
            )
        )

        val recommendedExample = Feed(
            id = "tag:recommended",
            title = "Рекомендуемые книги",
            links = linksAcqNavNav(self = recommended.path, up = catalog.path),
            entries = bookEntries({ listOf(Book.example) })
        )

        val unreadExample = Feed(
            id = "tag:unread",
            title = "Непрочитанные книги",
            links = linksAcqNavNav(self = unread.path, up = catalog.path),
            entries = bookEntries({ listOf(Book.example) })
        )

        val recentExample = Feed(
            id = "tag:recent",
            title = "Недавно добавленные книги",
            links = linksAcqNavNav(self = recent.path, up = catalog.path),
            entries = bookEntries({ listOf(Book.example) }, includeSequenceNumber = true, sequenceNumberForRecent = true)
        )

        val unverifiedExample = Feed(
            id = "tag:unverified",
            title = "Непроверенные книги",
            links = linksAcqNavNav(self = unverified.path, up = catalog.path),
            entries = bookEntries({ listOf(Book.example) })
        )

        val authorsExample = Feed(
            id = "tag:authors",
            title = "По авторам",
            links = linksAcqNavNav(self = authors.path, up = catalog.path),
            entries = listOf(name.oshurkov.books.author.Author.example).map {
                Entry(
                    id = "tag:authors:${it.id}",
                    title = it.toStringForList(),
                    updated = it.updated,
                    links = linksNavImgThumb(subsection = "${authors.path}/${it.id}/books", img = "/author.png")
                )
            }
        )

        val authorBooksExample = Feed(
            id = "tag:authors:${name.oshurkov.books.author.Author.example.id}:books",
            title = "Все книги автора ${name.oshurkov.books.author.Author.example}",
            links = listOf(Acquisition(rel = "http://opds-spec.org/facet", href = "${authors.path}/${name.oshurkov.books.author.Author.example.id}/sequences", title = "По сериям", facetGroup = "Серии", activeFacet = true)),
            entries = bookEntries({ listOf(Book.example) })
        )

        val authorBooksBySequenceExample = Feed(
            id = "tag:authors",
            title = "По сериям",
            links = linksAcqNavNav(self = "${authors.path}/${name.oshurkov.books.author.Author.example.id}/sequences", up = authors.path),
            entries = listOf(name.oshurkov.books.sequence.Sequence.example).map {
                Entry(
                    id = "tag:sequences:${it.id}",
                    title = it.name,
                    updated = it.updated,
                    links = listOf(
                        Navigation(rel = "subsection", href = "${CatalogPath.sequences.path}/${it.id}/books"),
                        Navigation(rel = "up", href = "${authors.path}/${name.oshurkov.books.author.Author.example.id}/sequences"),
                    )
                )
            }
        )

        val sequencesExample = Feed(
            id = "tag:sequences:${name.oshurkov.books.sequence.Sequence.example.id}",
            title = "Все книги серии",
            links = listOf(),
            entries = bookEntries({ listOf(Book.example) })
        )

        val genresExample = Feed(
            id = "tag:genres",
            title = "По жанрам",
            links = linksAcqNavNav(self = genres.path, up = catalog.path),
            entries = listOf(Genre.example).map {
                Entry(
                    id = "tag:genres:${it.id}",
                    title = it.name,
                    updated = it.updated,
                    links = linksNavImgThumb(subsection = "${genres.path}/${it.id}/books", img = "/genre.png")
                )
            }
        )

        val booksByGenreExample = Feed(
            id = "tag:genre:${Genre.example.id}",
            title = "Все книги жанра",
            links = listOf(),
            entries = bookEntries({ listOf(Book.example) })
        )
    }
}


data class Author(
    val name: String,
    val uri: String?
)


interface CatalogLink


open class ImageThumbnail(
    @JacksonXmlProperty(isAttribute = true)
    val type: String = "image/png",
    @JacksonXmlProperty(isAttribute = true)
    val rel: String = "http://opds-spec.org/image/thumbnail",
    @JacksonXmlProperty(isAttribute = true)
    val href: String
) : CatalogLink


open class Thumbnail(
    @JacksonXmlProperty(isAttribute = true)
    val type: String = "image/png",
    @JacksonXmlProperty(isAttribute = true)
    val rel: String = "http://opds-spec.org/thumbnail",
    @JacksonXmlProperty(isAttribute = true)
    val href: String
) : CatalogLink


open class Link(
    @JacksonXmlProperty(isAttribute = true)
    val type: String = "application/atom+xml;profile=opds-catalog;",
    @JacksonXmlProperty(isAttribute = true)
    val title: String? = null,
    @JacksonXmlProperty(isAttribute = true)
    val rel: String? = null,
    @JacksonXmlProperty(isAttribute = true)
    val href: String
) : CatalogLink


class Acquisition(
    @JacksonXmlProperty(isAttribute = true)
    val type: String = "application/atom+xml;profile=opds-catalog;kind=acquisition",
    @JacksonXmlProperty(isAttribute = true)
    val title: String? = null,
    @JacksonXmlProperty(isAttribute = true)
    val rel: String? = null,
    @JacksonXmlProperty(isAttribute = true)
    val href: String,
    @JacksonXmlProperty(isAttribute = true, localName = "opds:facetGroup")
    val facetGroup: String? = null,
    @JacksonXmlProperty(isAttribute = true, localName = "opds:activeFacet")
    val activeFacet: Boolean = false,
) : CatalogLink


class Navigation(
    @JacksonXmlProperty(isAttribute = true)
    val type: String = "application/atom+xml;profile=opds-catalog;kind=navigation",
    @JacksonXmlProperty(isAttribute = true)
    val title: String? = null,
    @JacksonXmlProperty(isAttribute = true)
    val rel: String? = null,
    @JacksonXmlProperty(isAttribute = true)
    val href: String
) : CatalogLink


data class Entry(
    val id: String,
    val title: String,
    val updated: OffsetDateTime,
    val content: Content? = null,
    val summary: Summary? = null,

    @JacksonXmlProperty(localName = "author")
    @JacksonXmlElementWrapper(useWrapping = false)
    val authors: List<Author>? = null,

    @JacksonXmlProperty(localName = "category")
    @JacksonXmlElementWrapper(useWrapping = false)
    val categories: List<Category>? = null,

    val rights: String? = null,
    @JacksonXmlProperty(localName = "dc:language")
    val language: String? = null,
    @JacksonXmlProperty(localName = "dc:issued")
    val issued: String? = null,
    @JacksonXmlProperty(localName = "dc:publisher")
    val publisher: String? = null,

    @JacksonXmlProperty(localName = "dc:source")
    @JacksonXmlElementWrapper(useWrapping = false)
    val sources: List<String>? = null,

    @JacksonXmlProperty(localName = "link")
    @JacksonXmlElementWrapper(useWrapping = false)
    val links: List<CatalogLink>?,
)


data class Category(
    @JacksonXmlProperty(isAttribute = true)
    val scheme: String?,
    @JacksonXmlProperty(isAttribute = true)
    val term: String?
)


data class Content(
    @JacksonXmlText
    val content: String?,
    @JacksonXmlProperty(isAttribute = true)
    val type: String? = "text"
)


data class Summary(
    @JacksonXmlText
    val content: String?,
    @JacksonXmlProperty(isAttribute = true)
    val type: String? = "text"
)


class CatalogPath private constructor(
    private val root: String,
    val value: String,
) {
    val path get() = "$root$value"

    companion object {
        val catalog = CatalogPath("", "/catalog")
        val recommended = CatalogPath(catalog.path, "/recommended")
        val unread = CatalogPath(catalog.path, "/unread")
        val recent = CatalogPath(catalog.path, "/recent")
        val authors = CatalogPath(catalog.path, "/authors")
        val sequences = CatalogPath(catalog.path, "/sequences")
        val genres = CatalogPath(catalog.path, "/genres")
        val unverified = CatalogPath(catalog.path, "/unverified")
    }
}
