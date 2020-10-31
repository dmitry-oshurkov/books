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

        bookService.import(files, target) { title, authors, seq, seqNo, file ->

            val ext = if (file.name.endsWith(".fb2.zip")) "fb2.zip" else file.extension
            val authorsDir = authors.joinToString { it.toStringForList() }
            val newFileName = if (seq != null) "[$seqNo] $title.$ext" else "$title.$ext"
            val newFilePath = if (seq != null) Path.of(authorsDir, seq.name, newFileName) else Path.of(authorsDir, newFileName)

            val target = File(target, newFilePath.toString())
            val result = file.copyTo(target, true)

            Files.move(file.toPath(), File(processed, file.name).toPath(), REPLACE_EXISTING)
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
