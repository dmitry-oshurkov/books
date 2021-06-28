package name.oshurkov.books.api.author

import name.oshurkov.books.api.*
import name.oshurkov.books.api.sequence.*
import org.springframework.data.jpa.repository.*
import javax.persistence.*
import javax.persistence.CascadeType.*
import javax.persistence.FetchType.*

@Entity
class Author(
    val firstName: String?,
    val middleName: String?,
    @Column(nullable = false)
    val lastName: String,

    @ManyToMany(cascade = [MERGE, REMOVE, REFRESH, DETACH], fetch = EAGER)
    var sequences: MutableSet<Sequence> = mutableSetOf()
) : EntityBase() {
    override fun toString() = "$firstName$middle $lastName"
    fun toStringForList() = "$lastName$first"

    private val first get() = if (firstName != null) ", $firstName$middle" else ""
    private val middle get() = if (middleName != null) " $middleName" else ""
}

interface AuthorRepository : JpaRepository<Author, Int> {
    fun findByOrderByLastName(): List<Author>
}
