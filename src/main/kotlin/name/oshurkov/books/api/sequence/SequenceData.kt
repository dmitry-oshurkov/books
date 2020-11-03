package name.oshurkov.books.api.sequence

import name.oshurkov.books.api.*
import org.springframework.data.jpa.repository.*
import javax.persistence.*

@Entity
data class Sequence(
    val name: String,
) : EntityBase()

interface SequenceRepository : JpaRepository<Sequence, Int>
