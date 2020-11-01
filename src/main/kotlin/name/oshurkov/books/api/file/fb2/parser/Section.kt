package name.oshurkov.books.api.file.fb2.parser

import org.w3c.dom.*
import java.util.*

class Section internal constructor(root: Node) : IdElement(root) {
    var image: Image? = null
    var annotation: Annotation? = null
    var epigraphs = arrayListOf<Epigraph>()
    var sections = arrayListOf<Section>()
    var elements = arrayListOf<Element>()
    var title: Title? = null

    init {
        val body = root.childNodes
        for (item in 0 until body.length) {
            val node = body.item(item)
            when (node.nodeName) {
                "title" -> title = Title(node)
                "elements" -> annotation = Annotation(node)
                "image" -> elements.add(P(Image(node)))
                "epigraph" -> epigraphs.add(Epigraph(node))
                "section" -> sections.add(Section(node))
                "poem" -> elements.add(Poem(node))
                "subtitle" -> elements.add(Subtitle(node))
                "p" -> elements.add(P(node))
                "empty-line" -> elements.add(EmptyLine())
                "cite" -> elements.add(Cite(node))
            }
        }
    }

    private fun getTitleString(): String {
        val title1 = title ?: return ""
        val builder = StringBuilder()
        val list = ArrayList<Element?>(title1.paragraphs)
        val outerDivider = "\n"
        builder.append(Element.getText(list, ". ")).append(outerDivider)
        return builder.substring(0, builder.length - outerDivider.length)
    }

    override fun toString(): String {
        var data = getTitleString()
        if (elements.isNotEmpty())
            data += " p: ${elements.size}"
        if (elements.isNotEmpty())
            data += " section: ${sections.size}"
        return data.trim { it <= ' ' }
    }
}
