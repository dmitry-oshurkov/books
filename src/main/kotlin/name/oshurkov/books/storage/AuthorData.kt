package name.oshurkov.books.storage

import org.hibernate.annotations.*
import org.springframework.data.jpa.repository.*
import java.util.*
import javax.persistence.*
import javax.persistence.Entity
import javax.persistence.GenerationType.*

@Entity
class Author(
    @Id
    @GeneratedValue(strategy = AUTO)
    val id: Int? = null,
    val firstName: String?,
    val middleName: String?,
    val lastName: String,
    @UpdateTimestamp
    val updated: Date = Date(0),
) {
    override fun toString() = "$firstName$middle $lastName"
    fun toStringForList() = "$lastName$first"

    private val first get() = if (firstName != null) ", $firstName$middle" else ""
    private val middle get() = if (middleName != null) " $middleName" else ""
}

interface AuthorRepository : JpaRepository<Author, Int>
