package name.oshurkov.books.fb2.parser

import org.w3c.dom.*

class Sequence internal constructor(node: Node) {
    lateinit var name: String
        private set
    lateinit var number: String
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
