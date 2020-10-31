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
                FB2 -> if (forceCompress) "fb2.zip" else "fb2"
                else -> file.extension
            }
            val authorsDir = authors.joinToString { it.toStringForList() }

            val (newFileName, newFileDir) = if (seq != null)
                "[$seqNo] $title.$ext" to Path.of(target.absolutePath, authorsDir, seq.name).toFile()
            else
                "$title.$ext" to Path.of(target.absolutePath, authorsDir).toFile()

            newFileDir.mkdirs()

            val target = File(newFileDir, newFileName)

            when (bookExt) {
                FBZ -> {

                    val bytes = ZipFile(file).use {
                        val entry = it.entries().toList().first()
                        it.getInputStream(entry).use { stream -> stream.readAllBytes() }
                    }

                    zip(target, seq, seqNo, title, bytes)
                }
                FB2 -> if (forceCompress)
                    zip(target, seq, seqNo, title, file.readBytes())
                else
                    file.copyTo(target, true)
                else -> file.copyTo(target, true)
            }

            Files.move(file.toPath(), File(processed, file.name).toPath(), REPLACE_EXISTING)
            target
        }
    }

    private fun zip(target: File, seq: Sequence?, seqNo: Int?, title: String, bytes: ByteArray) =
        ZipOutputStream(FileOutputStream(target)).use { stream ->
            stream.setLevel(BEST_COMPRESSION)
            stream.putNextEntry(ZipEntry(if (seq != null) "[$seqNo] $title.fb2" else "$title.fb2"))
            stream.write(bytes, 0, bytes.size)
            stream.closeEntry()
        }

    @Value("\${books.importer.source}")
    private lateinit var source: File

    @Value("\${books.importer.target}")
    private lateinit var target: File

    @Value("\${books.importer.processed}")
    private lateinit var processed: File

    @Value("\${books.importer.forceCompress}")
    private var forceCompress: Boolean = true

    @Autowired
    private lateinit var bookService: BookService
}
