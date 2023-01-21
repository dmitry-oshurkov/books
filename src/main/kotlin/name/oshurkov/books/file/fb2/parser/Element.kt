package name.oshurkov.books.file.fb2.parser

import org.w3c.dom.*

open class Element {
    open var text: String?
        protected set

    constructor() {
        text = null
    }

    constructor(p: Node) {
        text = p.textContent
    }

    constructor(p: String?) {
        text = p
    }

    companion object {
        fun getText(list: ArrayList<Element?>, divider: String): String {
            val text = StringBuilder()
            for (p in list) {
                text.append(p!!.text).append(divider)
            }
            return if (text.length <= divider.length) "" else text.substring(0, text.length - divider.length).trim { it <= ' ' }
        }
    }
}
