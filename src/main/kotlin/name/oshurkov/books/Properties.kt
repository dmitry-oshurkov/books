package name.oshurkov.books

import org.springframework.beans.factory.annotation.*
import org.springframework.stereotype.*
import java.io.*

@Component
class Properties {

    @Component
    companion object {

        @JvmStatic
        var forceCompress: Boolean = true
            @Value("\${books.fileMonitor.forceCompress}") set

        @JvmStatic
        lateinit var fileMonitorSource: File
            @Value("\${books.fileMonitor.source}") set

        @JvmStatic
        lateinit var fileMonitorProcessed: File
            @Value("\${books.fileMonitor.processed}") set
    }
}
