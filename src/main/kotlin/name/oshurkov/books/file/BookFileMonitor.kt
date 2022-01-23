package name.oshurkov.books.file

import name.oshurkov.books.Properties.Companion.fileMonitorProcessed
import name.oshurkov.books.Properties.Companion.fileMonitorSource
import name.oshurkov.books.book.*
import org.springframework.scheduling.annotation.*
import org.springframework.stereotype.*
import java.io.*
import java.nio.file.*
import java.nio.file.StandardCopyOption.*

@Component
class BookFileMonitor {

    init {
        fileMonitorSource.mkdirs()
        fileMonitorProcessed.mkdirs()
    }

    @Scheduled(fixedDelay = 1_000)
    fun import() {

        val files = fileMonitorSource
            .listFiles(FileFilter { it.extension in listOf("fb2", "epub") || it.name.endsWith(".fb2.zip") })
            .orEmpty()
            .toList()

        if (files.isNotEmpty())
            importBooks(files) { Files.move(it.toPath(), File(fileMonitorProcessed, it.name).toPath(), REPLACE_EXISTING) }
    }
}
