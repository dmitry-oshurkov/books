package name.oshurkov.books.book

import name.oshurkov.books.*
import name.oshurkov.books.author.*
import name.oshurkov.books.genre.*
import name.oshurkov.books.sequence.*
import org.hibernate.annotations.*
import org.hibernate.annotations.OnDeleteAction.*
import org.springframework.data.jpa.repository.*
import java.util.*
import javax.persistence.*
import javax.persistence.CascadeType.*
import javax.persistence.Entity

@Entity
class Book(
    @Column(nullable = false)
    val title: String,
    @Column(length = 6000)
    val content: String?,
    @Column(length = 4000)
    val summary: String?,
    val summaryContentType: String?,
    @Column(length = 1000)
    val rights: String?,
    val language: String?,
    val issued: String?,
    val publisher: String?,
    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    val cover: ByteArray?,
    val coverContentType: String?,
    var recommended: Boolean = false,
    val unread: Boolean = true,
    val verified: Boolean = false,

    @ManyToOne(cascade = [MERGE, REFRESH, DETACH])
    val sequence: Sequence?,
    val sequenceNumber: Int?,
    @Column(nullable = false, unique = true)
    val hash: UUID,

    @ManyToMany(cascade = [MERGE, REFRESH, DETACH])
    val authors: Set<Author>, // todo order is important

    @ManyToMany(cascade = [MERGE, REFRESH, DETACH])
    val genres: Set<Genre>,

    @OneToMany(mappedBy = "book", orphanRemoval = true)
    @OnDelete(action = CASCADE)
    val files: MutableSet<name.oshurkov.books.file.BookFile> = mutableSetOf(),
) : EntityBase()

interface BookRepository : JpaRepository<Book, Int> {
    fun findByAuthorsIdOrderBySequenceAscSequenceNumber(id: Int): List<Book>
    fun findByGenresIdOrderBySequenceAscSequenceNumber(id: Int): List<Book>
    fun findBySequenceIdOrderBySequenceNumber(id: Int): List<Book>
    fun findByRecommendedTrue(): List<Book>
    fun findByUnreadTrueOrderByAuthorsAscSequenceAscSequenceNumber(): List<Book>
    fun findByVerifiedFalseOrderByAuthors(): List<Book>
    fun findTop10ByOrderByUpdatedDesc(): List<Book>
}
