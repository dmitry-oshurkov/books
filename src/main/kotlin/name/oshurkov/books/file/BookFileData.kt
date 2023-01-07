package name.oshurkov.books.file

import name.oshurkov.books.core.*
import name.oshurkov.books.core.data.*
import name.oshurkov.books.core.plugins.*
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
    .select(BookFiles.columns)
    .map {
        it[BookFiles.book_id]!! to BookFile(
            id = it[BookFiles.id]!!,
            bookId = it[BookFiles.book_id]!!,
            updated = it[BookFiles.updated]!!.atMoscowOffset(),
            content = it[BookFiles.content]!!,
            hash = it[BookFiles.hash]!!,
            type = it[BookFiles.type]!!
        )
    }
    .groupedValues()


fun selectBookFile(id: Int) = db
    .from(BookFiles)
    .select(BookFiles.columns)
    .where { BookFiles.id eq id }
    .map {
        BookFile(
            id = it[BookFiles.id]!!,
            bookId = it[BookFiles.book_id]!!,
            updated = it[BookFiles.updated]!!.atMoscowOffset(),
            content = it[BookFiles.content]!!,
            hash = it[BookFiles.hash]!!,
            type = it[BookFiles.type]!!
        )
    }
    .singleOrNull()
