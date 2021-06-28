package name.oshurkov.books.api.file

import name.oshurkov.books.*
import name.oshurkov.books.api.author.*
import name.oshurkov.books.api.book.*
import name.oshurkov.books.api.file.FileType.*
import name.oshurkov.books.api.file.fb2.parser.*
import name.oshurkov.books.api.genre.*
import name.oshurkov.books.api.sequence.Sequence
import org.apache.tomcat.util.codec.binary.*
import org.slf4j.*
import org.springframework.stereotype.*
import java.io.*
import java.util.zip.*

fun parseFb2(files: Map<FileType, List<File>>) = run {

    val fbz = files[FBZ].orEmpty()
        .mapNotNull { file ->
            runCatching {

                val bytes = ZipFile(file).use {
                    val entry = it.entries().toList().first()
                    it.getInputStream(entry).use { stream -> stream.readAllBytes() }
                }

                FictionBook(null, bytes) to file and FBZ
            }
                .getOrNull()
        }

    val fb2plain = files[FB2].orEmpty()
        .map { FictionBook(it, null) to it and FB2 }

    fb2plain + fbz
}

fun fb2ToBooks(
    fb2: List<Triple<FictionBook, File, FileType>>,
    authors: List<Author>,
    genres: List<Genre>,
    bookFileFn: (Book, File, FileType, String, Int?) -> BookFile,
    afterSaveFile: (File) -> Unit,
    sequences: List<Sequence>
) =
    fb2
        .mapNotNull { (fb, file, type) ->

            try {
            } catch (e: Exception) {
            } finally {
            }

            runCatching {

                val bookSequence = sequences.find { g -> g.name == fb.description.titleInfo.sequence?.name }

                val bookAuthors = fb.description.titleInfo.authors
                    .mapNotNull { authors.find { a -> a.firstName == it.firstName && a.middleName == it.middleName && a.lastName == it.lastName } }
                    .onEach {
                        if (bookSequence != null && !it.sequences.contains(bookSequence))
                            it.sequences.add(bookSequence)
                    }
                    .toSet()

                val bookGenres = fb.description.titleInfo.genres
                    .map { fb2GenreToString(it) }
                    .mapNotNull { genres.find { g -> g.name == it } }
                    .toSet()

                val summary = fb.annotation?.annotations?.map { it.text }?.joinToString("\n")
                val binary = fb.binaries[fb.description.titleInfo.coverPage.firstOrNull()?.value?.trimStart('#')]
                val sequenceNumber = fb.description.titleInfo.sequence?.number?.toInt()

                Book(
                    title = fb.title,
                    content = null,
                    summary = summary,
                    summaryContentType = summaryType(summary),
                    rights = null,
                    language = lang(fb.lang),
                    issued = fb.description.titleInfo.date,
                    publisher = null,
                    cover = binary?.binary?.let { Base64.decodeBase64(it) },
                    coverContentType = binary?.contentType,
                    recommended = file.name.contains("*]") || file.name.startsWith("*"),
                    sequence = bookSequence,
                    sequenceNumber = sequenceNumber,
                    hash = bookHash(bookAuthors, fb.title),
                    authors = bookAuthors,
                    genres = bookGenres
                )
                    .also {
                        val bookFile = bookFileFn(it, file, type, it.title, sequenceNumber)
                        it.files += bookFile
                    }
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

private val log = LoggerFactory.getLogger(::parseFb2::class.java)
