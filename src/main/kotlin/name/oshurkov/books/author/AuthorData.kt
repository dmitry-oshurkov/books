package name.oshurkov.books.author

import name.oshurkov.books.core.*
import name.oshurkov.books.core.data.*
import name.oshurkov.books.core.plugins.*
import org.ktorm.dsl.*
import org.ktorm.schema.*


object Authors : BksTable("author") {
    val last_name = varchar("last_name")
    val first_name = varchar("first_name")
    val middle_name = varchar("middle_name")
}


object BookAuthors : SimpleTable("book_author") {
    val book_id = int("book_id").primaryKey()
    val author_id = int("author_id").primaryKey()
}


object AuthorSequences : SimpleTable("author_sequence") {
    val author_id = int("author_id").primaryKey()
    val sequence_id = int("sequence_id").primaryKey()
}


fun selectBookAuthors() = db
    .from(BookAuthors)
    .innerJoin(Authors, on = Authors.id eq BookAuthors.author_id)
    .select(BookAuthors.book_id, *Authors.columns.toTypedArray())
    .map {
        it[BookAuthors.book_id]!! to Author(
            id = it[Authors.id]!!,
            updated = it[Authors.updated]!!.atMoscowOffset(),
            lastName = it[Authors.last_name]!!,
            firstName = it[Authors.first_name]!!,
            middleName = it[Authors.middle_name],
        )
    }
    .groupedValues()


fun selectBookAuthors(bookId: Int) = db
    .from(BookAuthors)
    .innerJoin(Authors, on = Authors.id eq BookAuthors.author_id)
    .select(Authors.columns)
    .where { BookAuthors.book_id eq bookId }
    .map {
        Author(
            id = it[Authors.id]!!,
            updated = it[Authors.updated]!!.atMoscowOffset(),
            lastName = it[Authors.last_name]!!,
            firstName = it[Authors.first_name]!!,
            middleName = it[Authors.middle_name],
        )
    }


fun selectAuthors() = db
    .from(Authors)
    .select(Authors.columns)
    .orderBy(Authors.last_name.asc())
    .map {
        Author(
            id = it[Authors.id]!!,
            updated = it[Authors.updated]!!.atMoscowOffset(),
            lastName = it[Authors.last_name]!!,
            firstName = it[Authors.first_name]!!,
            middleName = it[Authors.middle_name],
        )
    }


fun selectAuthor(id: Int) = db
    .from(Authors)
    .select(Authors.columns)
    .where { Authors.id eq id }
    .map {
        Author(
            id = it[Authors.id]!!,
            updated = it[Authors.updated]!!.atMoscowOffset(),
            lastName = it[Authors.last_name]!!,
            firstName = it[Authors.first_name]!!,
            middleName = it[Authors.middle_name],
        )
    }
    .singleOrNull()


fun selectAuthorsCount() = Authors.count(db)
