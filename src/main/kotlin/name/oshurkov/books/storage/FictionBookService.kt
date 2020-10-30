package name.oshurkov.books.storage

import name.oshurkov.books.*
import name.oshurkov.books.fb2.parser.*
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
                        it.getInputStream(entry).use { stream ->

                            // todo repack fbz with zip entry renaming
                            val bytes = stream.readAllBytes()
                            val tmp = File.createTempFile("temp", null)
                            try {
                                tmp.writeBytes(bytes)
                                FictionBook(tmp) to file and FBZ
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
            .map { FictionBook(it) to it and FB2 }

        fb2plain + fbz
    }
}
