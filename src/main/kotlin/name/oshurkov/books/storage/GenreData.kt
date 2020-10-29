package name.oshurkov.books.storage

import javax.persistence.*
import javax.persistence.GenerationType.*

@Entity
class Genre(
    @Id
    @GeneratedValue(strategy = AUTO)
    val id: Int = 0,
    val value: String,
)
