package name.oshurkov.books.storage

import org.springframework.data.jpa.repository.*
import javax.persistence.*

@Entity
class Genre(
    val name: String,
) : EntityBase()

interface GenreRepository : JpaRepository<Genre, Int>
