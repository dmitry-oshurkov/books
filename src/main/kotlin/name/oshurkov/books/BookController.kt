package name.oshurkov.books

import name.oshurkov.books.storage.*
import org.springframework.beans.factory.annotation.*
import org.springframework.core.io.*
import org.springframework.http.*
import org.springframework.http.MediaType.*
import org.springframework.web.bind.annotation.*
import java.io.*

@RestControllerAdvice
@RequestMapping("/api/book")
class BookController {

    @GetMapping("{id}/image", produces = [IMAGE_JPEG_VALUE])
    @ResponseBody
    fun image(@PathVariable id: Int) = bookRepository.getOne(id).cover

    @GetMapping("{id}/image/thumbnail", produces = [IMAGE_JPEG_VALUE])
    @ResponseBody
    fun thumbnail(@PathVariable id: Int) = bookRepository.getOne(id).cover

    @GetMapping("{id}/file")
    @ResponseBody
    fun download(@PathVariable id: Int): ResponseEntity<ByteArrayResource> = run {

        val book = bookRepository.getOne(id)
        val file = File(booksDir, book.file)

        val headers = HttpHeaders()
        headers.contentDisposition = ContentDisposition.builder("attachment")
            .filename("[${book.authors.joinToString()}] - ${file.name}", Charsets.UTF_8)
            .build()

        ResponseEntity.ok()
            .contentType(parseMediaType(book.fileContentType))
            .headers(headers)
            .body(ByteArrayResource(file.readBytes()))
    }

    @Autowired
    private lateinit var bookRepository: BookRepository
}
