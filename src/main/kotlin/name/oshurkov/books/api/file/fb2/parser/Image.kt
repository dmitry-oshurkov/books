package name.oshurkov.books.api.file.fb2.parser

import org.w3c.dom.*

class Image {
    var name: String? = null
        protected set
    var value: String? = null
        protected set

    internal constructor(node: Node) {
        name = node.attributes.item(0).nodeName
        value = node.attributes.item(0).nodeValue
    }

    constructor() : super() {}
}
