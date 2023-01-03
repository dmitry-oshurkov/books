// ktlint-disable filename
package name.oshurkov.books.file


enum class FileType(val contentType: String, val extension: String) {
    FB2("application/fb2", "fb2"),
    FBZ("application/fb2+zip", "fb2.zip"),
    EPUB("application/epub+zip", "epub")
}
