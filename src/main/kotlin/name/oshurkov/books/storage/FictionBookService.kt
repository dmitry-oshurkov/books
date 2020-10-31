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
            .map { file ->

                val bytes = ZipFile(file).use {
                    val entry = it.entries().toList().first()
                    it.getInputStream(entry).use { stream -> stream.readAllBytes() }
                }

                FictionBook(null, bytes) to file and FBZ
            }

        val fb2plain = files[FB2].orEmpty()
            .map { FictionBook(it, null) to it and FB2 }

        fb2plain + fbz
    }
}
