package name.oshurkov.books.storage

import org.springframework.data.jpa.repository.*
import java.util.*
import javax.persistence.*
import javax.persistence.CascadeType.*
import javax.persistence.GenerationType.*

@Entity
class Book(
    @Id
    @GeneratedValue(strategy = AUTO)
    val id: Int = 0,
    val title: String,
    val updated: Date = Date(),
    val content: String?,
    val summary: String?,

    @ManyToMany(cascade = [ALL])
    val authors: Set<Author>,

    val rights: String?,
    val language: String?,
    val issued: String?,
    val publisher: String?,
    @Lob
    val cover: ByteArray?,
    val coverContentType: String?,
    val file: String,
)

interface BookRepository : JpaRepository<Book, Int>

enum class BookExt { FB2, FBZ, EPUB }
