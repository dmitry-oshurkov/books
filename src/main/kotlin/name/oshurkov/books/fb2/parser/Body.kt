package name.oshurkov.books.fb2.parser

import org.w3c.dom.*
import java.util.*

//http://www.fictionbook.org/index.php/Элемент_body
class Body {
    var lang: String? = null
    var name: String? = null
    var title: Title? = null
    var image: Image? = null
    var sections = ArrayList<Section>()
    var epigraphs: ArrayList<Epigraph>? = null

    constructor() {}
    internal constructor(body: Node) {
        val attrs = body.attributes
        for (index in 0 until attrs.length) {
            val attr = attrs.item(index)
            if (attr.nodeName == "name") {
                name = attr.nodeValue
            }
            if (attr.nodeName == "xml:lang") {
                lang = attr.nodeValue
            }
        }
        val map = body.childNodes
        for (index in 0 until map.length) {
            val node = map.item(index)
            when (node.nodeName) {
                "section" -> sections.add(Section(node))
                "title" -> title = Title(node)
                "name" -> name = node.textContent
                "image" -> image = Image(node)
                "epigraph" -> {
                    if (epigraphs == null) epigraphs = ArrayList()
                    epigraphs!!.add(Epigraph(node))
                }
            }
        }
    }
}
