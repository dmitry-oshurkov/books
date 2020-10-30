package name.oshurkov.books.storage

import name.oshurkov.books.*
import name.oshurkov.books.storage.BookExt.*
import nl.siegmann.epublib.epub.*
import org.springframework.stereotype.*
import java.io.*

@Component
class EpubService {

    fun parse(files: Map<BookExt, List<File>>) = files[EPUB].orEmpty().map { EpubReader().readEpub(FileInputStream(it)) to it and EPUB }
}
