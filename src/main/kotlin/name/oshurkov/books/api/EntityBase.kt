package name.oshurkov.books.api

import org.hibernate.annotations.*
import java.util.*
import javax.persistence.*

@MappedSuperclass
abstract class EntityBase(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int = 0,
    @UpdateTimestamp
    val updated: Date = Date(0),
)
