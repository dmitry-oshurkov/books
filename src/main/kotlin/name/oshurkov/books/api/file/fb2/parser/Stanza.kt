package name.oshurkov.books.api.file.fb2.parser

import org.w3c.dom.*

class Stanza internal constructor(node: Node) {
    var title: ArrayList<Title>? = null
    var stanza: ArrayList<Element>? = null

    init {
        val nodeList = node.childNodes
        for (i in 0 until nodeList.length) {
            val paragraph = nodeList.item(i)
            when (paragraph.nodeName) {
                "title" -> {
                    if (title == null) title = ArrayList()
                    title!!.add(Title(paragraph))
                }
                "subtitle" -> {
                    if (stanza == null) stanza = ArrayList()
                    stanza!!.add(Subtitle(paragraph))
                }
                "v" -> {
                    if (stanza == null) stanza = ArrayList()
                    stanza!!.add(V(paragraph))
                }
            }
        }
    }
}
