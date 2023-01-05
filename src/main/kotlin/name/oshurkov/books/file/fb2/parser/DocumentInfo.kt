package name.oshurkov.books.file.fb2.parser

import org.w3c.dom.*

class DocumentInfo {
    var authors = ArrayList<Person>()
        protected set
    protected var publishers: ArrayList<Person>? = null
    var programUsed: String? = null
        protected set
    var srcUrl: String? = null
        protected set
    var srcOcr: String? = null
        protected set
    var email: String? = null
        protected set
    var id: String? = null
        protected set
    var version: String? = null
        protected set
    var history: History? = null
        protected set
    var date: Date? = null
        protected set

    constructor() {}
    internal constructor(document: Document) {
        val description = document.getElementsByTagName("document-info")
        for (item in 0 until description.length) {
            val map = description.item(item).childNodes
            for (index in 0 until map.length) {
                val node = map.item(index)
                when (node.nodeName) {
                    "author" -> authors.add(Person(node))
                    "publisher" -> {
                        if (publishers == null) publishers = ArrayList()
                        publishers!!.add(Person(node))
                    }

                    "program-used" -> programUsed = node.textContent
                    "email" -> email = node.textContent
                    "src-url" -> srcUrl = node.textContent
                    "src-ocr" -> srcOcr = node.textContent
                    "id" -> id = node.textContent
                    "version" -> version = node.textContent
                    "history" -> history = History(node)
                    "date" -> date = Date(node)
                }
            }
        }
    }
}
