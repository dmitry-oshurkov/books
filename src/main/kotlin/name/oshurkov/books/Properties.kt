package name.oshurkov.books

import org.springframework.beans.factory.annotation.*
import org.springframework.stereotype.*
import java.io.*

class Properties {

    @Component
    companion object {

        var forceFb2CompressForStore: Boolean = true
            @Value("\${books.import.forceFb2CompressForStore}") set

        lateinit var fileMonitorSource: File
            @Value("\${books.fileMonitor.source}") set

        lateinit var fileMonitorProcessed: File
            @Value("\${books.fileMonitor.processed}") set
    }
}
