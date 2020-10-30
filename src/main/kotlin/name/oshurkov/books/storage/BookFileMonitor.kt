package name.oshurkov.books.storage

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

        bookService.import(files, target) { title, authors, file ->

            val extension = if (file.name.endsWith(".fb2.zip")) "fb2.zip" else file.extension
            val path = Paths.get(authors.joinToString { it.toStringForList() }, "$title.$extension")
            val target = File(target, path.toString())

            val result = file.copyTo(target, true)
            val file1 = File(processed, file.name)
            Files.move(file.toPath(), file1.toPath(), REPLACE_EXISTING)
            result
        }
    }

    @Value("\${books.importer.source}")
    private lateinit var source: File

    @Value("\${books.importer.target}")
    private lateinit var target: File

    @Value("\${books.importer.processed}")
    private lateinit var processed: File

    @Autowired
    private lateinit var bookService: BookService
}
