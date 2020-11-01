package name.oshurkov.books.api.file.fb2.parser

import org.w3c.dom.*
import java.util.*

class Epigraph : IdElement {
    var elements = ArrayList<Element>()
    var textAuthor: ArrayList<TextAuthor>? = ArrayList()

    constructor() {}
    internal constructor(root: Node) {
        val map = root.attributes
        for (index in 0 until map.length) {
            val attr = map.item(index)
            if (attr.nodeName == "id") {
                id = attr.nodeValue
            }
        }
        val body = root.childNodes
        for (item in 0 until body.length) {
            val node = body.item(item)
            when (node.nodeName) {
                "text-author" -> {
                    if (textAuthor == null) textAuthor = ArrayList()
                    textAuthor!!.add(TextAuthor(node))
                }
                "poem" -> elements.add(Poem(node))
                "cite" -> elements.add(Cite(node))
                "p" -> elements.add(P(node))
            }
        }
    }
}
