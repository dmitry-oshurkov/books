package name.oshurkov.books.storage

import org.springframework.data.jpa.repository.*
import javax.persistence.*
import javax.persistence.CascadeType.*
import javax.persistence.GenerationType.*

@Entity
class Author(
    @Id
    @GeneratedValue(strategy = AUTO)
    val id: Int? = null,
    val firstName: String?,
    val middleName: String?,
    val lastName: String,

    @ManyToMany(cascade = [ALL])
    val books: Set<Book>,
) {
    override fun toString() = "$firstName${if (middleName != null) " $middleName" else ""} $lastName"
}

interface AuthorRepository : JpaRepository<Author, Int>
