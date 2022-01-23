package name.oshurkov.books.file.fb2.parser

import org.w3c.dom.*

// http://www.fictionbook.org/index.php/Элемент_binary
class Binary : IdElement {
    var contentType: String? = null
    var binary: String? = null

    constructor() {}
    internal constructor(node: Node) : super(node) {
        binary = node.textContent
        val map = node.attributes
        for (index in 0 until map.length) {
            val attr = map.item(index)
            when (attr.nodeName) {
                "content-type" -> contentType = attr.nodeValue
            }
        }
    }

    override var id: String?
        get() = super.id
        set(id) {
            super.id = id
        }
}
