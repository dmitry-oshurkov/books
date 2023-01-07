package name.oshurkov.books.file

import name.oshurkov.books.author.*
import name.oshurkov.books.book.*
import name.oshurkov.books.core.*
import name.oshurkov.books.file.FileType.*
import name.oshurkov.books.sequence.*
import java.io.*
import java.nio.charset.*
import java.util.zip.*
import java.util.zip.Deflater.*


fun summaryType(value: String?) = if (value?.contains("</p>") == true)
    "text/html"
else
    "text"


fun lang(value: String) = when (value.lowercase()) {
    "ru" -> "ru-RU"
    "en" -> "en-US"
    else -> value
}


fun bookHash(bookAuthors: List<ImportedAuthor>, title: String) = uuid("${bookAuthors.sortedBy { it.lastName }.joinToString()}$title".toByteArray())


fun bookFile(file: File, type: FileType, title: String, seqNo: Int?) = run {

    val (content, hash) = when (type) {

        FBZ -> {
            val bytes = unzip(file)
            zip(seqNo, title, bytes) to uuid(bytes)
        }

        FB2 -> if (forceFb2CompressForStore)
            file.readBytes().let { zip(seqNo, title, it) to uuid(it) }
        else
            file.readBytes().let { it to uuid(it) }

        else -> file.readBytes().let { it to uuid(it) }
    }

    val newType = if (type == FB2 && forceFb2CompressForStore)
        FBZ
    else
        type

    ImportedBookFile(content, hash, newType)
}


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


private fun zip(seqNo: Int?, title: String, bytes: ByteArray) =
    ByteArrayOutputStream().use {
        ZipOutputStream(it).use { stream ->
            stream.setLevel(BEST_COMPRESSION)
            stream.putNextEntry(ZipEntry(if (seqNo != null) "[$seqNo] $title.fb2" else "$title.fb2"))
            stream.write(bytes, 0, bytes.size)
            stream.closeEntry()
        }
        it.toByteArray()
    }


private const val RECOMMENDED_TAG = "+"
private const val UNREAD_TAG = "!"
private const val NOT_VERIFIED_TAG = "^"

private val attrReg = "^(?>\\[(?<ATTR>.+)\\])?.*".toRegex()


private val forceFb2CompressForStore = true // todo create app param
