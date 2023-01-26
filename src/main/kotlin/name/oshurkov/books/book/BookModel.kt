package name.oshurkov.books.book

import name.oshurkov.books.author.*
import name.oshurkov.books.file.*
import java.io.*
import java.time.*
import java.util.*


class Book(
    val id: Int,
    val updated: OffsetDateTime,
    val cover: ByteArray?,
    val coverContentType: String?,
    val hash: UUID,
    val issued: String?,
    val language: String?,
    val publisher: String?,
    val recommended: Boolean,
    val sequenceId: Int?,
    val sequenceNumber: Int?,
    val summary: String?,
    val summaryContentType: String?,
    val title: String,
    val unread: Boolean,
    val verified: Boolean,
)


class ImportedBook(
    val cover: ByteArray?,
    val coverContentType: String?,
    val hash: UUID,
    val issued: String?,
    val language: String?,
    val publisher: String?,
    val recommended: Boolean,
    val sequence: String?,
    val sequenceNumber: Int?,
    val summary: String?,
    val summaryContentType: String?,
    val title: String,
    val unread: Boolean,
    val verified: Boolean,
    val authors: List<Fb2Author> = emptyList(),
    val genres: List<String> = emptyList(),
    val files: List<ImportedBookFile> = emptyList(),
    val srcFile: File,
    val content: String,
)
