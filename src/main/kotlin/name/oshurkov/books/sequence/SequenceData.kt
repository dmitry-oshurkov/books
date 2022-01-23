package name.oshurkov.books.sequence

import name.oshurkov.books.*
import org.springframework.data.jpa.repository.*
import javax.persistence.*

@Entity
data class Sequence(
    @Column(nullable = false)
    val name: String,
) : EntityBase()

interface SequenceRepository : JpaRepository<Sequence, Int>
