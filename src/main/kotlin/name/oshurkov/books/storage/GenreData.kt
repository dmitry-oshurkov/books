package name.oshurkov.books.storage

import javax.persistence.*

@Entity
class Genre(
    val name: String,
) : EntityBase()
