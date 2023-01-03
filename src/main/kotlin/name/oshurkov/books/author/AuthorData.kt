package name.oshurkov.books.author

import name.oshurkov.books.*
import name.oshurkov.books.core.*
import name.oshurkov.books.core.data.*
import org.ktorm.dsl.*
import org.ktorm.schema.*
import java.time.*


object Authors : BksTable("author") {
    val last_name = varchar("last_name")
    val first_name = varchar("first_name")
    val middle_name = varchar("middle_name")
}


object BookAuthors : SimpleTable("book_authors") {
    val book_id = int("book_id")
    val authors_id = int("authors_id")
}


object AuthorSequences : SimpleTable("author_sequences") {
    val author_id = int("author_id")
    val sequences_id = int("sequences_id")
}


class Author(
    val id: Int,
    val updated: OffsetDateTime,
    val lastName: String,
    val firstName: String?,
    val middleName: String?,
) {
    override fun toString() = "$firstName$middle $lastName"
    fun toStringForList() = "$lastName$first"

    private val first get() = if (firstName != null) ", $firstName$middle" else ""
    private val middle get() = if (middleName != null) " $middleName" else ""
}


fun selectBookAuthors() = db
    .from(BookAuthors)
    .innerJoin(Authors, on = Authors.id eq BookAuthors.authors_id)
    .select(BookAuthors.book_id, *Authors.columns.toTypedArray())
    .map {
        it[BookAuthors.book_id]!! to Author(
            id = it[Authors.id]!!,
            updated = it[Authors.updated]!!.atMoscowOffset(),
            lastName = it[Authors.last_name]!!,
            firstName = it[Authors.first_name],
            middleName = it[Authors.middle_name],
        )
    }
    .groupedValues()


fun selectAuthors() = db
    .from(Authors)
    .select(Authors.columns)
    .orderBy(Authors.last_name.asc())
    .map {
        Author(
            id = it[Authors.id]!!,
            updated = it[Authors.updated]!!.atMoscowOffset(),
            lastName = it[Authors.last_name]!!,
            firstName = it[Authors.first_name],
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
            firstName = it[Authors.first_name],
            middleName = it[Authors.middle_name],
        )
    }
    .singleOrNull()
