package name.oshurkov.books.fb2.parser

import org.w3c.dom.*

class Sequence {
    var name: String? = null
        protected set
    var number: String? = null
        protected set

    constructor() {}
    internal constructor(node: Node) {
        val map = node.attributes
        for (index in 0 until map.length) {
            val attr = map.item(index)
            if (attr.nodeName == "name") {
                name = attr.nodeValue
            } else if (attr.nodeName == "number") {
                number = attr.nodeValue
            }
        }
    }
}
