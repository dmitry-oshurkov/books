package name.oshurkov.books.file.fb2.parser

import org.w3c.dom.*

class Date {
    var value: String? = null
        protected set
    var date: String? = null
        protected set

    constructor() {}
    internal constructor(node: Node) {
        val map = node.attributes
        for (index in 0 until map.length) {
            val attr = map.item(index)
            if (attr.nodeName == "value") {
                value = attr.nodeValue
            }
        }
        date = node.textContent
    }
}
