package name.oshurkov.books.storage

import org.springframework.data.jpa.repository.*
import javax.persistence.*

@Entity
class Sequence(
    val name: String,
) : EntityBase()

interface SequenceRepository : JpaRepository<Sequence, Int>
