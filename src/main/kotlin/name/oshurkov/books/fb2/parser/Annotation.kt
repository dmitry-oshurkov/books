package name.oshurkov.books.fb2.parser

import org.w3c.dom.*
import java.util.*

//http://www.fictionbook.org/index.php/Элемент_annotation
open class Annotation : IdElement {
    var lang: String? = null
    var elements: ArrayList<Element>? = null

    constructor() {}
    internal constructor(node: Node) : super(node) {
        val map = node.attributes
        for (index in 0 until map.length) {
            val attr = map.item(index)
            if (attr.nodeName == "xml:lang") {
                lang = attr.nodeValue
            }
        }
        val nodeList = node.childNodes
        for (i in 0 until nodeList.length) {
            val paragraph = nodeList.item(i)
            if (elements == null) elements = ArrayList()
            when (paragraph.nodeName) {
                "p" -> elements!!.add(P(paragraph))
                "poem" -> elements!!.add(Poem(paragraph))
                "cite" -> elements!!.add(Cite(paragraph))
                "subtitle" -> elements!!.add(Subtitle(paragraph))
                "empty-line" -> elements!!.add(EmptyLine())
                "table" -> elements!!.add(Table())
            }
        }
    }

    val annotations: ArrayList<Element>
        get() = if (elements == null) ArrayList() else elements!!
}
