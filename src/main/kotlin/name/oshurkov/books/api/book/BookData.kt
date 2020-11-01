package name.oshurkov.books.api.book

import name.oshurkov.books.api.*
import name.oshurkov.books.api.author.*
import name.oshurkov.books.api.genre.*
import name.oshurkov.books.api.sequence.*
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

    @OneToOne(cascade = [MERGE, REMOVE, REFRESH, DETACH])
    val sequence: Sequence?,
    val sequenceNumber: Int?,

    @ManyToMany(cascade = [MERGE, REMOVE, REFRESH, DETACH])
    val authors: Set<Author>,

    @ManyToMany(cascade = [MERGE, REMOVE, REFRESH, DETACH])
    val genres: Set<Genre>,
) : EntityBase()

interface BookRepository : JpaRepository<Book, Int> {

    fun findBooksByAuthorsId(id: Int): List<Book>
    fun findBooksByGenresId(id: Int): List<Book>
}

enum class BookExt { FB2, FBZ, EPUB, UNSUPPORTED }
