package name.oshurkov.books.api.file.fb2.parser

import org.w3c.dom.*
import java.util.*

//http://www.fictionbook.org/index.php/Элемент_author
open class Person {
    var id: String? = null
    var firstName: String? = null
    var middleName: String? = null
    var lastName: String? = null
    var nickname: String? = null
    var homePages: ArrayList<String>? = null
    var emails: ArrayList<String>? = null

    constructor() {}
    internal constructor(node: Node) {
        val nodeList = node.childNodes
        for (i in 0 until nodeList.length) {
            val author = nodeList.item(i)
            when (author.nodeName) {
                "id" -> id = author.textContent
                "home-page" -> {
                    if (homePages == null) homePages = ArrayList()
                    homePages!!.add(author.textContent)
                }
                "email" -> {
                    if (emails == null) emails = ArrayList()
                    emails!!.add(author.textContent)
                }
                "nickname" -> nickname = author.textContent
                "first-name" -> firstName = author.textContent
                "middle-name" -> middleName = author.textContent
                "last-name" -> lastName = author.textContent
            }
        }
    }

    val fullName: String
        get() = ((if (firstName == null) "" else "$firstName ")
            + (if (middleName == null) "" else "$middleName ")
            + if (lastName == null) "" else lastName)
}
