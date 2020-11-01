package name.oshurkov.books.api.genre

import name.oshurkov.books.api.*
import org.springframework.data.jpa.repository.*
import javax.persistence.*

@Entity
class Genre(
    val name: String,
) : EntityBase()

interface GenreRepository : JpaRepository<Genre, Int>
