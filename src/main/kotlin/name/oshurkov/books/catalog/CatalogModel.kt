package name.oshurkov.books.catalog

import com.fasterxml.jackson.dataformat.xml.annotation.*
import java.util.*

@JacksonXmlRootElement(localName = "feed")
data class Feed(

    @JacksonXmlProperty(isAttribute = true, localName = "xmlns:dc") val dcterms: String = "http://purl.org/dc/terms/",
    @JacksonXmlProperty(isAttribute = true, localName = "xmlns:thr") val thr: String = "http://purl.org/syndication/thread/1.0",
    @JacksonXmlProperty(isAttribute = true, localName = "xmlns:opds") val opds: String = "http://opds-spec.org/2010/catalog",
    @JacksonXmlProperty(isAttribute = true, localName = "xmlns:opensearch") val opensearch: String = "http://a9.com/-/spec/opensearch/1.1/",
    @JacksonXmlProperty(isAttribute = true, localName = "xmlns:xsi") val xsi: String = "http://www.w3.org/2001/XMLSchema-instance",
    @JacksonXmlProperty(isAttribute = true) val xmlns: String = "http://www.w3.org/2005/Atom",
    @JacksonXmlProperty(isAttribute = true, localName = "xml:lang") val lang: String = "en",

    val id: String,
    val title: String,
    val updated: Date?,
    val author: Author? = null,

    @JacksonXmlProperty(localName = "link")
    @JacksonXmlElementWrapper(useWrapping = false)
    val links: List<Link>?,

    @JacksonXmlProperty(localName = "entry")
    @JacksonXmlElementWrapper(useWrapping = false)
    val entries: List<Entry>?
)

data class Author(
    val name: String,
    val uri: String?
)

data class Link(
    @JacksonXmlProperty(isAttribute = true)
    val type: String = "application/atom+xml;profile=opds-catalog;",
    @JacksonXmlProperty(isAttribute = true)
    val title: String? = null,
    @JacksonXmlProperty(isAttribute = true)
    val rel: String? = null,
    @JacksonXmlProperty(isAttribute = true)
    val href: String
)

data class Entry(
    val id: String,
    val title: String,
    val updated: Date,
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
    val links: List<Link>?,
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
