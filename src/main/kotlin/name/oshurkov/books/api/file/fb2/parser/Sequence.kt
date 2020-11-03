package name.oshurkov.books.api.file.fb2.parser

import org.w3c.dom.*

class Sequence internal constructor(node: Node) {
    var name: String? = null
        private set
    var number: String? = null
        private set

    init {
        (0 until node.attributes.length)
            .map { node.attributes.item(it) }
            .forEach {
                when (it.nodeName) {
                    "name" -> name = it.nodeValue
                    "number" -> number = it.nodeValue
                }
            }
    }
}
