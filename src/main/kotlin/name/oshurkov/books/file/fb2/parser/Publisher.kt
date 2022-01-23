package name.oshurkov.books.file.fb2.parser

import org.w3c.dom.*

class Publisher : Person {
    var lang: String? = null

    constructor() {}
    constructor(node: Node) : super(node) {
        val map = node.attributes
        for (index in 0 until map.length) {
            val attr = map.item(index)
            if (attr.nodeName == "lang") {
                id = attr.nodeValue
            }
        }
    }
}
