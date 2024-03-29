package name.oshurkov.books.sequence

import name.oshurkov.books.author.*
import name.oshurkov.books.core.*
import name.oshurkov.books.core.data.*
import name.oshurkov.books.core.plugins.*
import org.ktorm.dsl.*
import java.time.*


object Sequences : NamedBksTable("sequence")


class Sequence(
    val id: Int,
    val updated: OffsetDateTime,
    val name: String,
) {
    companion object {

        val example = Sequence(
            id = 67,
            updated = OffsetDateTime.now(),
            name = "Властелин колец",
        )
    }
}


fun selectSequences() = db
    .from(Sequences)
    .select(Sequences.columns)
    .map {
        Sequence(
            id = it[Sequences.id]!!,
            updated = it[Sequences.updated]!!.atMoscowOffset(),
            name = it[Sequences.name]!!,
        )
    }


fun selectSequences(authorId: Int) = db
    .from(Sequences)
    .innerJoin(AuthorSequences, on = AuthorSequences.sequence_id eq Sequences.id)
    .select(Sequences.columns)
    .where { AuthorSequences.author_id eq authorId }
    .map {
        Sequence(
            id = it[Sequences.id]!!,
            updated = it[Sequences.updated]!!.atMoscowOffset(),
            name = it[Sequences.name]!!,
        )
    }
