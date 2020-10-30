package name.oshurkov.books.fb2.parser

import org.w3c.dom.*

open class IdElement {
    open var id: String? = null

    protected constructor() {}
    protected constructor(node: Node) {
        val map = node.attributes
        for (index in 0 until map.length) {
            val attr = map.item(index)
            if (attr.nodeName == "id") {
                id = attr.nodeValue
            }
        }
    }
}
