package name.oshurkov.books.api.file

import name.oshurkov.books.api.book.*
import org.springframework.beans.factory.annotation.*
import org.springframework.scheduling.annotation.*
import org.springframework.stereotype.*
import java.io.*
import java.nio.file.*
import java.nio.file.StandardCopyOption.*

@Component
class BookFileMonitor {

    @Scheduled(fixedDelay = 1_000)
    fun import() {

        val files = source
            .listFiles(FileFilter { it.extension in listOf("fb2", "epub") || it.name.endsWith(".fb2.zip") })
            .orEmpty()

        processed.mkdirs()
        bookService.import(files) { Files.move(it.toPath(), File(processed, it.name).toPath(), REPLACE_EXISTING) }
    }

    @Value("\${books.fileMonitor.source}")
    private lateinit var source: File

    @Value("\${books.fileMonitor.processed}")
    private lateinit var processed: File

    @Autowired
    private lateinit var bookService: BookService
}