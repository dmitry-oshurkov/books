package name.oshurkov.books.api

import org.hibernate.annotations.*
import java.util.*
import javax.persistence.*
import javax.persistence.GenerationType.*

@MappedSuperclass
abstract class EntityBase(
    @Id
    @GeneratedValue(strategy = AUTO)
    val id: Int = 0,
    @UpdateTimestamp
    @Column(nullable = false)
    val updated: Date = Date(0),
)
