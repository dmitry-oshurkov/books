package name.oshurkov.books.catalog

import com.fasterxml.jackson.dataformat.xml.annotation.*

@JacksonXmlRootElement(localName = "feed")
data class Feed(
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
