package name.oshurkov.books.catalog

import name.oshurkov.books.*
import name.oshurkov.books.storage.*
import org.springframework.beans.factory.annotation.*
import org.springframework.core.io.*
import org.springframework.http.*
import org.springframework.http.MediaType.*
import org.springframework.web.bind.annotation.*
import java.io.*
import kotlin.text.Charsets.UTF_8

@RestControllerAdvice
@RequestMapping("/catalog/file")
class FileController {

    @GetMapping("{bookId}")
    @ResponseBody
    fun download(@PathVariable bookId: Int): ResponseEntity<ByteArrayResource> = run {

        val book = bookRepository.getOne(bookId)
        val file = File(booksDir, book.file)

        val headers = HttpHeaders()
        headers.contentDisposition = ContentDisposition.builder("attachment")
            .filename("[${book.authors.joinToString()}] - ${file.name}", UTF_8)
            .build()

        ResponseEntity.ok()
            .contentType(parseMediaType(book.fileContentType))
            .headers(headers)
            .body(ByteArrayResource(file.readBytes()))
    }

    @Autowired
    private lateinit var bookRepository: BookRepository
}
