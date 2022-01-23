package name.oshurkov.books.file.fb2.parser

import org.w3c.dom.*

class Poem : Element {
    var title: Title? = null
        protected set
    var epigraphs: ArrayList<Epigraph>? = null
        protected set
    protected var stanza = ArrayList<Stanza>()
    var textAuthor: String? = null
        protected set
    var date: String? = null
        protected set

    constructor() {}
    internal constructor(node: Node) {
        val nodeList = node.childNodes
        for (i in 0 until nodeList.length) {
            val paragraph = nodeList.item(i)
            when (paragraph.nodeName) {
                "text-author" -> textAuthor = paragraph.textContent
                "title" -> title = Title(paragraph)
                "epigraph" -> {
                    if (epigraphs == null) epigraphs = ArrayList()
                    epigraphs!!.add(Epigraph(paragraph))
                }
                "date" -> date = paragraph.textContent
                "stanza" -> stanza.add(Stanza(paragraph))
            }
        }
    }

    override var text: String?
        get() {
            val list = ArrayList<Element?>()
            val title2 = title
            if (title2 != null) list.addAll(title2.paragraphs)
            for (stanza1 in stanza) {
                if (stanza1.title != null) {
                    for (title1 in stanza1.title!!) {
                        list.addAll(title1.paragraphs)
                    }
                }
                list.addAll(stanza1.stanza!!.toList())
            }
            return getText(list, "\n")
        }
        set(text) {
            super.text = text
        }
}
