package name.oshurkov.books.genre

import name.oshurkov.books.core.*
import name.oshurkov.books.core.data.*
import name.oshurkov.books.core.plugins.*
import org.ktorm.dsl.*
import org.ktorm.schema.*
import java.time.*


object Genres : NamedBksTable("genre")


object BookGenres : SimpleTable("book_genre") {
    val book_id = int("book_id").primaryKey()
    val genre_id = int("genre_id").primaryKey()
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
    .innerJoin(Genres, on = Genres.id eq BookGenres.genre_id)
    .select(BookGenres.book_id, Genres.name)
    .map { it[BookGenres.book_id]!! to it[Genres.name]!! }
    .groupedValues()
