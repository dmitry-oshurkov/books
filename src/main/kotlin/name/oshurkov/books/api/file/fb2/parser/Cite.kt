package name.oshurkov.books.api.file.fb2.parser

import org.w3c.dom.*
import java.util.*

//http://www.fictionbook.org/index.php/Элемент_cite
class Cite : Element {
    var id: String? = null
    var lang: String? = null
    var elements: ArrayList<Element>? = null
    var textAuthor: ArrayList<TextAuthor>? = null

    constructor() {}
    internal constructor(node: Node) {
        val attrs = node.attributes
        for (index in 0 until attrs.length) {
            val attr = attrs.item(index)
            if (attr.nodeName == "id") {
                id = attr.nodeValue
            }
            if (attr.nodeName == "xml:lang") {
                lang = attr.nodeValue
            }
        }
        val nodeList = node.childNodes
        for (i in 0 until nodeList.length) {
            val paragraph = nodeList.item(i)
            when (paragraph.nodeName) {
                "text-author" -> {
                    if (textAuthor == null) textAuthor = ArrayList()
                    textAuthor!!.add(TextAuthor(paragraph))
                }
                "poem" -> {
                    if (elements == null) elements = ArrayList()
                    elements!!.add(Poem(paragraph))
                }
                "subtitle" -> {
                    if (elements == null) elements = ArrayList()
                    elements!!.add(Subtitle(paragraph))
                }
                "p" -> {
                    if (elements == null) elements = ArrayList()
                    elements!!.add(P(paragraph))
                }
                "empty-line" -> {
                    if (elements == null) elements = ArrayList()
                    elements!!.add(EmptyLine())
                }
            }
        }
    }

    override var text: String?
        get() {
            val list = ArrayList(elements)
            if (textAuthor != null) list.addAll(textAuthor!!)
            return getText(list, "\n")
        }
        set(text) {
            super.text = text
        }
}
