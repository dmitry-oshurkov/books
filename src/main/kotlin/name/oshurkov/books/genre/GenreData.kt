package name.oshurkov.books.genre

import name.oshurkov.books.*
import name.oshurkov.books.core.*
import name.oshurkov.books.core.data.*
import org.ktorm.dsl.*
import org.ktorm.schema.*
import java.time.*


object Genres : BksTable("genre") {
    val name = varchar("name")
}


object BookGenres : SimpleTable("book_genres") {
    val book_id = int("book_id")
    val genres_id = int("genres_id")
}


class Genre(
    val id: Int,
    val updated: OffsetDateTime,
    val name: String,
)


fun selectGenres() = db
    .from(Genres)
    .select(Genres.columns)
    .map {
        Genre(
            id = it[Genres.id]!!,
            updated = it[Genres.updated]!!.atMoscowOffset(),
            name = it[Genres.name]!!,
        )
    }


fun selectBookGenres() = db
    .from(BookGenres)
    .innerJoin(Genres, on = Genres.id eq BookGenres.genres_id)
    .select(BookGenres.book_id, Genres.name)
    .map { it[BookGenres.book_id]!! to it[Genres.name]!! }
    .groupedValues()
