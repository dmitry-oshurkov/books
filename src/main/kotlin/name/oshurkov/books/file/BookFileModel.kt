package name.oshurkov.books.file

import java.time.*
import java.util.*


class BookFile(
    val id: Int,
    val bookId: Int,
    val updated: OffsetDateTime,
    val content: ByteArray,
    val hash: UUID,
    val type: FileType,
)


enum class FileType(val contentType: String, val extension: String) {
    FB2("application/fb2", "fb2"),
    FBZ("application/fb2+zip", "fb2.zip"),
}


class ImportedBookFile(
    val content: ByteArray,
    val hash: UUID,
    val type: FileType
)
