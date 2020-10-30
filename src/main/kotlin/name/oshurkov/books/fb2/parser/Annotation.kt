package name.oshurkov.books.fb2.parser

import org.w3c.dom.*

/**
 * http://www.fictionbook.org/index.php/Элемент_annotation
 */
open class Annotation internal constructor(node: Node) : IdElement(node) {

    var lang: String? = null
    var annotations = arrayListOf<Element>()

    init {

        val map = node.attributes
        (0 until map.length)
            .map { map.item(it) }
            .filter { it.nodeName == "xml:lang" }
            .forEach { lang = it.nodeValue }

        val nodeList = node.childNodes

        (0 until nodeList.length)
            .map { nodeList.item(it) }
            .forEach {
                when (it.nodeName) {
                    "p" -> annotations.add(P(it))
                    "poem" -> annotations.add(Poem(it))
                    "cite" -> annotations.add(Cite(it))
                    "subtitle" -> annotations.add(Subtitle(it))
                    "empty-line" -> annotations.add(EmptyLine())
                    "table" -> annotations.add(Table())
                }
            }
    }
}
