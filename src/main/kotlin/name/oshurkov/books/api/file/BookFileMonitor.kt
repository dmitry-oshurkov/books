package name.oshurkov.books.api.file

import name.oshurkov.books.Properties.Companion.fileMonitorProcessed
import name.oshurkov.books.Properties.Companion.fileMonitorSource
import name.oshurkov.books.api.book.*
import org.springframework.scheduling.annotation.*
import org.springframework.stereotype.*
import java.io.*
import java.nio.file.*
import java.nio.file.StandardCopyOption.*

@Component
class BookFileMonitor {

    @Scheduled(fixedDelay = 1_000)
    fun import() {

        if (!fileMonitorSource.exists())
            fileMonitorSource.mkdirs()

        val files = fileMonitorSource
            .listFiles(FileFilter { it.extension in listOf("fb2", "epub") || it.name.endsWith(".fb2.zip") })
            .orEmpty()
            .toList()

        if (files.isNotEmpty())
            fileMonitorProcessed.mkdirs()

        importBooks(files) { Files.move(it.toPath(), File(fileMonitorProcessed, it.name).toPath(), REPLACE_EXISTING) }
    }
}
