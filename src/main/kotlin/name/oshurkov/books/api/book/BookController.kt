package name.oshurkov.books.api.book

import org.apache.tomcat.util.http.fileupload.FileUploadBase.*
import org.springframework.beans.factory.annotation.*
import org.springframework.core.io.*
import org.springframework.http.*
import org.springframework.http.MediaType.*
import org.springframework.web.bind.annotation.*
import java.io.*
import kotlin.text.Charsets.UTF_8

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
    fun download(@RequestHeader("user-agent") userAgent: String, @PathVariable id: Int): ResponseEntity<ByteArrayResource> = run {

        val book = bookRepository.getOne(id)
        val file = File(root, book.file)

        val filename = if (userAgent.contains("Chrome") || userAgent.contains("Mozilla") || userAgent.contains("Safari"))
            "[${book.authors.joinToString()}] - ${file.name}"
        else
            book.id.toString()

        val headers = HttpHeaders()
        headers.contentDisposition = ContentDisposition.builder(ATTACHMENT)
            .filename(filename, UTF_8)
            .build()

        ResponseEntity.ok()
            .contentType(parseMediaType(book.fileContentType))
            .headers(headers)
            .body(ByteArrayResource(file.readBytes()))
    }

    @Value("\${books.root}")
    private lateinit var root: File

    @Autowired
    private lateinit var bookRepository: BookRepository
}
