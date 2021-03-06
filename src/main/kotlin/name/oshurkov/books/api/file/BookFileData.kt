package name.oshurkov.books.api.file

import name.oshurkov.books.api.*
import name.oshurkov.books.api.book.*
import org.springframework.data.jpa.repository.*
import java.util.*
import javax.persistence.*

@Entity
class BookFile(
    @ManyToOne(optional = false)
    val book: Book,
    @Lob
    @Column(nullable = false)
    val content: ByteArray,
    @Column(nullable = false, unique = true)
    val hash: UUID,
    @Column(nullable = false)
    val type: FileType,
) : EntityBase()

enum class FileType(val contentType: String, val extension: String) {
    FB2("application/fb2", "fb2"),
    FBZ("application/fb2+zip", "fb2.zip"),
    EPUB("application/epub+zip", "epub")
}

interface BookFileRepository : JpaRepository<BookFile, Int>
