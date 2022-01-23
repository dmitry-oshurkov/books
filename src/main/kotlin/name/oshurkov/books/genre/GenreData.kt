package name.oshurkov.books.genre

import name.oshurkov.books.*
import org.springframework.data.jpa.repository.*
import javax.persistence.*

@Entity
class Genre(
    @Column(nullable = false)
    val name: String,
) : EntityBase()

interface GenreRepository : JpaRepository<Genre, Int> {
    fun findByOrderByName(): List<Genre>
}
