package name.oshurkov.books.storage

import org.springframework.data.jpa.repository.*
import javax.persistence.*
import javax.persistence.CascadeType.*

@Entity
class Book(
    val title: String,
    @Column(length = 3000)
    val content: String?,
    @Column(length = 1000)
    val summary: String?,
    val summaryContentType: String?,
    @Column(length = 500)
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
) : EntityBase()

interface BookRepository : JpaRepository<Book, Int> {

    fun findBooksByAuthorsId(id: Int): List<Book>
    fun findBooksByGenresId(id: Int): List<Book>
}

enum class BookExt { FB2, FBZ, EPUB, UNSUPPORTED }
