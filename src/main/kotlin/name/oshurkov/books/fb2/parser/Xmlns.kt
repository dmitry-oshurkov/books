package name.oshurkov.books.fb2.parser

import org.w3c.dom.*

class Xmlns {
    var name: String? = null
        protected set
    var value: String? = null
        protected set

    constructor() {}
    internal constructor(node: Node) {
        name = node.nodeName
        value = node.nodeValue
    }
}
