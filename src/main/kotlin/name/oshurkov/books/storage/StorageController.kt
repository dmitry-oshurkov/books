package name.oshurkov.books.storage

import org.aspectj.util.*
import org.springframework.beans.factory.annotation.*
import org.springframework.web.bind.annotation.*
import java.io.*

@RestControllerAdvice
@RequestMapping("/storage")
class StorageController {

    @PostMapping("rebuild")
    fun rebuild() {

        val files = FileUtil.listFiles(root) { it.extension in listOf("fb2", "epub") || it.name.endsWith(".fb2.zip") }

        bookService.import(files, root) { _, _, _, _, _, file -> file }
    }

    @Value("\${books.root}")
    private lateinit var root: File

    @Autowired
    private lateinit var bookService: BookService
}
