package name.oshurkov.books.file

import name.oshurkov.books.author.*
import name.oshurkov.books.book.*
import name.oshurkov.books.core.*
import name.oshurkov.books.file.FileType.*
import nl.siegmann.epublib.epub.*
import java.io.*


fun parseEpub(files: Map<FileType, List<File>>) = files[EPUB].orEmpty().map { EpubReader().readEpub(it.inputStream()) to it and EPUB }


fun epubToBooks(epub: List<Triple<EpubBook, File, FileType>>, afterSaveFile: (File) -> Unit) = epub.mapNotNull { (ep, file, type) ->

    runCatching {
        val bookAuthors = ep.metadata.authors.map { ImportedAuthor(lastName = it.lastname, firstName = it.firstname, middleName = null) }.distinct()
        val summary = ep.metadata.descriptions.firstOrNull()
        val attrs = file.name.parseAttrs()

        ImportedBook(
            title = ep.title,
            summary = summary,
            summaryContentType = summaryType(summary),
            language = lang(ep.metadata.language),
            issued = ep.metadata.dates.firstOrNull()?.value,
            publisher = ep.metadata.publishers.firstOrNull(),
            cover = ep.coverImage?.data,
            coverContentType = ep.coverImage?.mediaType?.name,
            recommended = attrs.isRecommended(),
            verified = !attrs.isNotVerified(),
            unread = attrs.isUnread(),
            sequence = null,
            sequenceNumber = null,
            hash = bookHash(bookAuthors, ep.title),
            authors = bookAuthors,
            genres = ep.metadata.subjects.distinct(),
            files = listOf(bookFile(file, type, ep.title, null)),
        )
    }
        .onSuccess {
            afterSaveFile(file)
            log.info("Parsed: [${file.absolutePath}]")
        }
        .onFailure {
            afterSaveFile(file)
            log.error("Error import: [${file.absolutePath}] - message: ${it.message}")
        }
        .getOrNull()
}


typealias EpubBook = nl.siegmann.epublib.domain.Book


private val log by logger()
