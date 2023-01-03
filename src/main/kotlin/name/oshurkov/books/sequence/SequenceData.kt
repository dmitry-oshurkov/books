package name.oshurkov.books.sequence

import name.oshurkov.books.*
import name.oshurkov.books.author.*
import name.oshurkov.books.core.*
import name.oshurkov.books.core.data.*
import org.ktorm.dsl.*
import org.ktorm.schema.*
import java.time.*


object Sequences : BksTable("sequence") {
    val name = varchar("name")
}


class Sequence(
    val id: Int,
    val updated: OffsetDateTime,
    val name: String,
)


fun selectSequences(authorId: Int) = db
    .from(Sequences)
    .innerJoin(AuthorSequences, on = AuthorSequences.sequences_id eq Sequences.id)
    .select(Sequences.columns)
    .where { AuthorSequences.author_id eq authorId }
    .map {
        Sequence(
            id = it[Sequences.id]!!,
            updated = it[Sequences.updated]!!.atMoscowOffset(),
            name = it[Sequences.name]!!,
        )
    }
