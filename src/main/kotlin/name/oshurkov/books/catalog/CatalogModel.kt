package name.oshurkov.books.catalog

import com.fasterxml.jackson.dataformat.xml.annotation.*

@JacksonXmlRootElement(localName = "feed")
data class Feed(

    @JacksonXmlProperty(isAttribute = true, localName = "xmlns:dcterms") val dcterms: String = "http://purl.org/dc/terms/",
    @JacksonXmlProperty(isAttribute = true, localName = "xmlns:thr") val thr: String = "http://purl.org/syndication/thread/1.0",
    @JacksonXmlProperty(isAttribute = true, localName = "xmlns:opds") val opds: String = "http://opds-spec.org/2010/catalog",
    @JacksonXmlProperty(isAttribute = true, localName = "xmlns:opensearch") val opensearch: String = "http://a9.com/-/spec/opensearch/1.1/",
    @JacksonXmlProperty(isAttribute = true, localName = "xmlns:xsi") val xsi: String = "http://www.w3.org/2001/XMLSchema-instance",
    @JacksonXmlProperty(isAttribute = true) val xmlns: String = "http://www.w3.org/2005/Atom",
    @JacksonXmlProperty(isAttribute = true, localName = "xml:lang") val lang: String = "en",

    val id: String?,
    val title: String?,
    val updated: String?,
    val author: Author?,
    val link: List<Link>?,
    val entry: List<Entry>?
)

data class Author(
    val name: String?,
    val uri: String?
)

data class Link(
    val type: String?,
    val title: String?,
    val rel: String?,
    val href: String?
)

data class Entry(
    val title: String?,
    val id: String?,
    val updated: String?,
    val content: String?,
    val link: Link?
)
