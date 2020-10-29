package name.oshurkov.books.storage

import org.hibernate.annotations.*
import org.springframework.data.jpa.repository.*
import java.util.*
import javax.persistence.*
import javax.persistence.CascadeType.*
import javax.persistence.Entity
import javax.persistence.GenerationType.*

@Entity
class Book(
    @Id
    @GeneratedValue(strategy = AUTO)
    val id: Int = 0,
    val title: String,
    @UpdateTimestamp
    val updated: Date = Date(0),
    val content: String?,
    val summary: String?,
    val rights: String?,
    val language: String?,
    val issued: String?,
    val publisher: String?,
    @Lob
    val cover: ByteArray?,
    val coverContentType: String?,
    @Column(unique = true)
    val file: String,
    val fileContentType: String,

    @ManyToMany(cascade = [ALL])
    val authors: Set<Author>,

    @ManyToMany(cascade = [ALL])
    val genres: Set<Genre>,
)

interface BookRepository : JpaRepository<Book, Int>

enum class BookExt { FB2, FBZ, EPUB }
