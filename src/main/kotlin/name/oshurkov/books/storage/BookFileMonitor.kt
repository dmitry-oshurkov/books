package name.oshurkov.books.storage

import name.oshurkov.books.storage.BookExt.*
import org.springframework.beans.factory.annotation.*
import org.springframework.scheduling.annotation.*
import org.springframework.stereotype.*
import java.io.*
import java.nio.file.*
import java.nio.file.StandardCopyOption.*
import java.util.zip.*
import java.util.zip.Deflater.*

@Component
class BookFileMonitor {

    @Scheduled(fixedDelay = 1_000)
    fun import() {

        val files = source
            .listFiles(FileFilter { it.extension in listOf("fb2", "epub") || it.name.endsWith(".fb2.zip") })
            .orEmpty()

        processed.mkdirs()

        bookService.import(files, target) { title, authors, seq, seqNo, bookExt, file ->

            val ext = when (bookExt) {
                FBZ -> "fb2.zip"
                else -> file.extension
            }
            val authorsDir = authors.joinToString { it.toStringForList() }
            val newFileName = if (seq != null) "[$seqNo] $title.$ext" else "$title.$ext"
            val newFilePath = if (seq != null) Path.of(authorsDir, seq.name, newFileName) else Path.of(authorsDir, newFileName)
            val target = File(target, newFilePath.toString())

            when (bookExt) {
                FBZ -> {

                    val bytes = ZipFile(file).use {
                        val entry = it.entries().toList().first()
                        it.getInputStream(entry).use { stream -> stream.readAllBytes() }
                    }

                    ZipOutputStream(FileOutputStream(target)).use { stream ->
                        stream.setLevel(BEST_COMPRESSION)
                        stream.putNextEntry(ZipEntry(if (seq != null) "[$seqNo] $title.fb2" else "$title.fb2"))
                        stream.write(bytes, 0, bytes.size)
                        stream.closeEntry()
                    }
                }
                else -> file.copyTo(target, true)
            }

            Files.move(file.toPath(), File(processed, file.name).toPath(), REPLACE_EXISTING)
            target
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
