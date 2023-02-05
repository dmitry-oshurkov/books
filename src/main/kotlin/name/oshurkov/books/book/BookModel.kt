package name.oshurkov.books.book

import io.ktor.http.ContentType.Image.JPEG
import name.oshurkov.books.author.*
import name.oshurkov.books.core.*
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
) {
    companion object {

        val example = Book(
            id = 345,
            updated = OffsetDateTime.now(),
            cover = byteArrayOf(),
            coverContentType = JPEG.contentType,
            hash = uuid(),
            issued = "1986",
            language = "ru-RU",
            publisher = "АСТ, АСТ Москва, Хранитель",
            recommended = true,
            sequenceId = 1521,
            sequenceNumber = 11,
            summary = "«Фиаско» – последний роман Станислава Лема",
            summaryContentType = "text",
            title = "Фиаско",
            unread = false,
            verified = false,
        )
    }
}


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
)
