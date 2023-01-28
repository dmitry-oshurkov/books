package name.oshurkov.books.file

import name.oshurkov.books.author.*
import name.oshurkov.books.book.*
import name.oshurkov.books.core.*
import name.oshurkov.books.sequence.*
import org.apache.commons.compress.archivers.zip.*
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream.UnicodeExtraFieldPolicy.*
import java.io.*
import java.nio.charset.*
import java.util.zip.Deflater.*
import java.util.zip.ZipFile


fun summaryType(value: String?) = if (value?.contains("</p>") == true)
    "text/html"
else
    "text"


fun lang(value: String) = when (value.lowercase()) {
    "ru" -> "ru-RU"
    "en" -> "en-US"
    else -> value
}


fun bookHash(authors: List<Fb2Author>, title: String) = uuid("${authors.sortedBy { it.lastName }.joinToString { it.hash }}$title".toByteArray())


fun String.isRecommended() = contains(RECOMMENDED_TAG)
fun String.isUnread() = contains(UNREAD_TAG)
fun String.isNotVerified() = contains(NOT_VERIFIED_TAG)

fun String.parseAttrs() = attrReg.find(this)?.groupValues?.get(1) ?: ""


fun prefix(book: Book, sequence: Sequence?) = run {

    val sequenceNumber = if (sequence != null && book.sequenceNumber != null) book.sequenceNumber.toString() else ""
    val recommended = if (book.recommended) RECOMMENDED_TAG else ""
    val unread = if (book.unread) UNREAD_TAG else ""
    val notVerified = if (!book.verified) NOT_VERIFIED_TAG else ""

    val attrs = listOf(sequenceNumber, recommended, unread, notVerified).joinToString("")

    if (attrs.isNotEmpty())
        "[$attrs] "
    else
        ""
}


fun unzip(file: File) =
    runCatching { ZipFile(file) }
        .getOrElse { ZipFile(file, Charset.forName("cp1251")) }
        .use {
            val entry = it.entries().toList().first()
            it.getInputStream(entry).use { stream -> stream.readAllBytes() }
        }!!


fun zip(title: String, seqNo: Int?, bytes: ByteArray): ByteArray = ByteArrayOutputStream().use { stream ->
    ZipArchiveOutputStream(stream).use { write(it, seqNo, title, bytes) }
    stream.toByteArray()
}

fun createZipFile(file: File, title: String, seqNo: Int?, bytes: ByteArray) = ZipArchiveOutputStream(file).use { write(it, seqNo, title, bytes) }


private fun write(archive: ZipArchiveOutputStream, seqNo: Int?, title: String, bytes: ByteArray) {
    archive.setLevel(BEST_COMPRESSION)
    archive.setCreateUnicodeExtraFields(ALWAYS)
    archive.putArchiveEntry(ZipArchiveEntry("${if (seqNo != null) "[$seqNo] " else ""}$title.fb2"))
    archive.write(bytes)
    archive.closeArchiveEntry()
}


private const val RECOMMENDED_TAG = "+"
private const val UNREAD_TAG = "!"
private const val NOT_VERIFIED_TAG = "^"

private val attrReg = "^(?>\\[(?<ATTR>.+)])?.*".toRegex()
