package name.oshurkov.books.api.book

import kotlinx.coroutines.reactor.*
import org.apache.tomcat.util.http.fileupload.FileUploadBase.*
import org.aspectj.util.*
import org.springframework.core.io.*
import org.springframework.data.repository.*
import org.springframework.http.*
import org.springframework.http.MediaType.*
import org.springframework.web.bind.annotation.*
import java.io.*
import kotlin.text.Charsets.UTF_8

@RestControllerAdvice
@RequestMapping("/api/book")
class BookController(
    val bookService: BookService,
    val bookRepository: BookRepository
) {

    @GetMapping("{id}/image/thumbnail", produces = [IMAGE_JPEG_VALUE])
    fun thumbnail(@PathVariable id: Int) = mono {
        bookRepository.getById(id).cover
    }

    @PostMapping("{id}/recommended")
    fun recommended(@PathVariable id: Int): ResponseEntity<Unit> = run {

        val book = bookRepository.findByIdOrNull(id)

        if (book != null) {
            book.recommended = true
            bookRepository.save(book)
            ResponseEntity.ok().build()
        } else
            ResponseEntity.notFound().build()
    }

    @GetMapping("{id}/file/{fileId}")
    fun download(
        @RequestHeader("user-agent") userAgent: String,
        @PathVariable id: Int,
        @PathVariable fileId: Int,
    ): ResponseEntity<*> = run {

        val book = bookRepository.findByIdOrNull(id)

        if (book != null) {

            val filename = if (userAgent.contains("Chrome") || userAgent.contains("Mozilla") || userAgent.contains("Safari"))
                "[${book.authors.joinToString()}] - ${book.title}"
            else
                book.id.toString()

            val headers = HttpHeaders()
            headers.contentDisposition = ContentDisposition.builder(ATTACHMENT)
                .filename(filename, UTF_8)
                .build()

            val bookFile = book.files.find { it.id == fileId }

            if (bookFile != null)
                ResponseEntity.ok()
                    .contentType(parseMediaType(bookFile.type.contentType))
                    .headers(headers)
                    .body(ByteArrayResource(bookFile.content))
            else
                ResponseEntity.notFound().build()
        } else
            ResponseEntity.notFound().build()
    }

    @PostMapping("import")
    fun importAll(@RequestBody rootDir: String) = run {
        val files = FileUtil
            .listFiles(File(rootDir)) { it.extension in listOf("fb2", "epub") || it.name.endsWith(".fb2.zip") }
            .toList()
        bookService.import(files)
    }

    @PostMapping("export")
    fun exportAll(@RequestBody targetDir: String) = bookService.export(targetDir)

    @PostMapping("backup")
    fun backupAll(@RequestBody targetDir: String) = bookService.backup(targetDir)
}
