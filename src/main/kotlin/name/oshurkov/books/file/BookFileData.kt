package name.oshurkov.books.file

import name.oshurkov.books.*
import name.oshurkov.books.core.data.*
import org.ktorm.dsl.*
import org.ktorm.schema.*


object BookFiles : BksTable("book_file") {
    val book_id = int("book_id")
    val content = bytes("content")
    val hash = uuid("hash")
    val type = enumOrd<FileType>("type")
}


fun selectBookFiles() = db
    .from(BookFiles)
    .select(BookFiles.book_id, BookFiles.id, BookFiles.type)
    .map { it[BookFiles.book_id]!! to (it[BookFiles.id]!! to it[BookFiles.type]!!) }
    .groupedValues()
