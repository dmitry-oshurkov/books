package name.oshurkov.books.file.fb2.parser.fonts

open class Font protected constructor(emphasis: String, p: String) {
    val startIndex: Int
    val finishIndex: Int

    init {
        startIndex = p.indexOf(emphasis)
        finishIndex = startIndex + emphasis.length
    }
}
