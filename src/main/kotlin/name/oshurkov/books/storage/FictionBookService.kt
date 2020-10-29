package name.oshurkov.books.storage

import com.kursx.parser.fb2.*
import name.oshurkov.books.storage.BookExt.*
import org.springframework.stereotype.*
import java.io.*
import java.util.zip.*

@Component
class FictionBookService {

    fun parse(files: Map<BookExt, List<File>>) = run {

        val fbz = files[FBZ].orEmpty()
            .mapNotNull { file ->

                try {
                    ZipFile(file.absolutePath).use {
                        val entry = it.entries().toList().first()
                        it.getInputStream(entry).use {

                            val bytes = it.readAllBytes()
                            val tmp = File.createTempFile("temp", null)
                            try {
                                tmp.writeBytes(bytes)
                                FictionBook(tmp) to file
                            } catch (e: Exception) {
                                null
                            } finally {
                                tmp.delete()
                            }
                        }
                    }
                } catch (e: Exception) {
                    null
                }
            }

        val fb2plain = files[FB2].orEmpty()
            .map { FictionBook(it) to it }

        fb2plain + fbz
    }
}
