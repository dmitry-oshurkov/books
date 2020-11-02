package name.oshurkov.books.api.file

import name.oshurkov.books.*
import name.oshurkov.books.api.file.FileType.*
import nl.siegmann.epublib.epub.*
import org.springframework.stereotype.*
import java.io.*

@Component
class EpubService {

    fun parse(files: Map<FileType?, List<File>>) = files[EPUB].orEmpty().map { EpubReader().readEpub(FileInputStream(it)) to it and EPUB }
}
