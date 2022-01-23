package name.oshurkov.books.file.fb2.parser

import org.w3c.dom.*

open class TitleInfo internal constructor(document: Document) {
    var genres = ArrayList<String>()
        protected set

    //    set
    //  TODO http://www.fictionbook.org/index.php/Жанры_FictionBook_2.1
    var keywords = ArrayList<String>()
        protected set
    var bookTitle: String? = null
        protected set
    var date: String? = null
        protected set
    var lang: String? = null
    var srcLang: String? = null
        protected set
    var authors = arrayListOf<Person>()
        protected set
    var translators = arrayListOf<Person>()
        protected set
    var annotation: Annotation? = null
        protected set
    var coverPage = arrayListOf<Image>()
        protected set
    var sequence: Sequence? = null
        protected set

    init {
        val description = document.getElementsByTagName("title-info")
        for (item in 0 until description.length) {
            val map = description.item(item).childNodes
            for (index in 0 until map.length) {
                val node = map.item(index)
                when (node.nodeName) {
                    "sequence" -> sequence = Sequence(node)
                    "coverpage" -> {
                        val images = node.childNodes
                        var image = 0
                        while (image < images.length) {
                            if (images.item(image).nodeName == "image") {
                                coverPage.add(Image(images.item(image)))
                            }
                            image++
                        }
                    }
                    "annotation" -> annotation = Annotation(node)
                    "date" -> date = node.textContent
                    "author" -> authors.add(Person(node))
                    "translator" -> translators.add(Person(node))
                    "keywords" -> keywords.add(node.textContent)
                    "genre" -> genres.add(node.textContent)
                    "book-title" -> bookTitle = node.textContent
                    "lang" -> lang = node.textContent
                    "src-lang" -> srcLang = node.textContent
                }
            }
        }
    }
}
