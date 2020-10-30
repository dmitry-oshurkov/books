package name.oshurkov.books.storage

import org.springframework.data.jpa.repository.*
import javax.persistence.*

@Entity
class Author(
    val firstName: String?,
    val middleName: String?,
    val lastName: String,
) : EntityBase() {
    override fun toString() = "$firstName$middle $lastName"
    fun toStringForList() = "$lastName$first"

    private val first get() = if (firstName != null) ", $firstName$middle" else ""
    private val middle get() = if (middleName != null) " $middleName" else ""
}

interface AuthorRepository : JpaRepository<Author, Int>
