package name.oshurkov.books.api.book

import name.oshurkov.books.Repositories.Companion.booksRep
import org.apache.tomcat.util.http.fileupload.FileUploadBase.*
import org.aspectj.util.*
import org.springframework.core.io.*
import org.springframework.data.repository.*
import org.springframework.http.*
import org.springframework.http.MediaType.*
import org.springframework.web.servlet.function.*
import org.springframework.web.servlet.function.ServerResponse.*
import java.io.*
import kotlin.text.Charsets.UTF_8

fun deleteBook(request: ServerRequest): ServerResponse = run {

    val id = request.pathVariable("id").toInt()
    val book = booksRep.findByIdOrNull(id)

    if (book != null) {
        booksRep.delete(book)
        noContent().build()
    } else
        notFound().build()
}

fun bookImage(request: ServerRequest): ServerResponse = run {

    val id = request.pathVariable("id").toInt()
    val book = booksRep.findByIdOrNull(id)

    if (book?.cover != null)
        ok()
            .contentType(parseMediaType(book.coverContentType ?: IMAGE_JPEG_VALUE))
            .body(book.cover)
    else
        notFound().build()
}

fun bookThumbnail(request: ServerRequest) = bookImage(request)

fun downloadBook(request: ServerRequest): ServerResponse = run {

    val id = request.pathVariable("id").toInt()
    val fileId = request.pathVariable("fileId").toInt()
    val userAgent = request.headers().firstHeader("user-agent") ?: ""

    val book = booksRep.findByIdOrNull(id)

    if (book != null) {

        val filename = if (userAgent.contains("Chrome") || userAgent.contains("Mozilla") || userAgent.contains("Safari"))
            "[${book.authors.joinToString()}] - ${book.title}"
        else
            book.id.toString()

        val bookFile = book.files.find { it.id == fileId }

        if (bookFile != null)
            ok()
                .contentType(parseMediaType(bookFile.type.contentType))
                .headers {
                    it.contentDisposition = ContentDisposition.builder(ATTACHMENT)
                        .filename(filename, UTF_8)
                        .build()
                }
                .body(ByteArrayResource(bookFile.content))
        else
            notFound().build()
    } else
        notFound().build()
}

fun setBookAsRecommended(request: ServerRequest): ServerResponse = run {

    val id = request.pathVariable("id").toInt()
    val book = booksRep.findByIdOrNull(id)

    if (book != null) {
        book.recommended = true
        booksRep.save(book)
        noContent().build()
    } else
        notFound().build()
}

fun import(request: ServerRequest): ServerResponse = run {

    val rootDir = request.body<String>()

    val files = FileUtil
        .listFiles(File(rootDir)) { it.extension in listOf("fb2", "epub") || it.name.endsWith(".fb2.zip") }
        .toList()

    importBooks(files)

    noContent().build()
}

fun importBatch(request: ServerRequest): ServerResponse = run {

    val urls = request.body(String::class.java).split("\n")
    importBooksBatch(urls)

    noContent().build()
}

fun exportBooks(request: ServerRequest): ServerResponse = run {

    val targetDir = request.body<String>()
    exportBooks(targetDir)

    noContent().build()
}

fun backupBooks(request: ServerRequest): ServerResponse = run {

    val targetDir = request.body<String>()
    backupBooks(targetDir)

    noContent().build()
}
