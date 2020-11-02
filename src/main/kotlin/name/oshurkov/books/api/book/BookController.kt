package name.oshurkov.books.api.book

import org.apache.tomcat.util.http.fileupload.FileUploadBase.*
import org.aspectj.util.*
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

    @GetMapping("{id}/file/{fileId}")
    @ResponseBody
    fun download(
        @RequestHeader("user-agent") userAgent: String,
        @PathVariable id: Int,
        @PathVariable fileId: Int,
    ) = run {

        val book = bookRepository.getOne(id)

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
            ResponseEntity.notFound()
    }!!

    @PostMapping("import")
    fun importAll(@RequestBody rootDir: String) = run {
        val files = FileUtil.listFiles(File(rootDir)) { it.extension in listOf("fb2", "epub") || it.name.endsWith(".fb2.zip") }
        bookService.import(files)
    }

    @PostMapping("export")
    fun exportAll(@RequestBody targetDir: String) = bookService.export(targetDir)

    @PostMapping("backup")
    fun backupAll(@RequestBody targetDir: String) = bookService.backup(targetDir)

    @Autowired
    private lateinit var bookService: BookService

    @Autowired
    private lateinit var bookRepository: BookRepository
}