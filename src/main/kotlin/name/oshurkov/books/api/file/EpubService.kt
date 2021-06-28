package name.oshurkov.books.api.file

import name.oshurkov.books.*
import name.oshurkov.books.api.file.FileType.*
import nl.siegmann.epublib.epub.*
import java.io.*

fun parseEpub(files: Map<FileType, List<File>>) = files[EPUB].orEmpty().map { EpubReader().readEpub(it.inputStream()) to it and EPUB }
