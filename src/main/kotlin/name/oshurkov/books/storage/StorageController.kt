package name.oshurkov.books.storage

import name.oshurkov.books.*
import name.oshurkov.books.storage.BookExt.*
import org.apache.tomcat.util.codec.binary.*
import org.aspectj.util.*
import org.springframework.beans.factory.annotation.*
import org.springframework.web.bind.annotation.*
import javax.xml.namespace.*

@RestControllerAdvice
@RequestMapping("/storage")
class StorageController {

    @PostMapping("rebuild")
    fun rebuild() {

        val files = FileUtil.listFiles(booksDir) { it.extension in listOf("fb2", "epub") || it.name.endsWith(".fb2.zip") }
            .groupBy {
                when {
                    it.extension == "fb2" -> FB2
                    it.name.endsWith(".fb2.zip") -> FBZ
                    it.extension == "epub" -> EPUB
                    else -> error("Unsupported book type")
                }
            }

        val fb2 = fictionBookService.parse(files)
        val epub = epubService.parse(files)

        val fb2Authors = fb2
            .flatMap { (fb, _, _) -> fb.description.titleInfo.authors }
            .map {
                Author(
                    firstName = it.firstName,
                    middleName = it.middleName,
                    lastName = it.lastName,
                )
            }
            .distinctBy { listOf(it.lastName, it.middleName, it.firstName) }

        val epubAuthors = epub
            .flatMap { (ep, _, _) -> ep.metadata.authors }
            .map {
                Author(
                    firstName = it.firstname,
                    middleName = null,
                    lastName = it.lastname,
                )
            }
            .distinctBy { listOf(it.lastName, it.middleName, it.firstName) }

        val fb2Books = fb2
            .map { (fb, file, ext) ->

                val bookAuthors = fb.description.titleInfo.authors
                    .mapNotNull {
                        fb2Authors.find { a ->
                            a.firstName == it.firstName && a.middleName == it.middleName && a.lastName == it.lastName
                        }
                    }
                    .toSet()

                val genres = fb.description.titleInfo.genres.map { Genre(value = it) }

                val binary = fb.binaries[fb.description.titleInfo.coverPage.firstOrNull()?.value?.trimStart('#')]

                Book(
                    title = fb.title,
                    content = null,
                    summary = null,
                    summaryContentType = null,
                    rights = null,
                    language = lang(fb.lang),
                    issued = fb.description.titleInfo.date,
                    publisher = null,
                    cover = binary?.binary?.let { Base64.decodeBase64(it) },
                    coverContentType = binary?.contentType,
                    file = file.relativeTo(booksDir).path,
                    fileContentType = fileType(ext),
                    authors = bookAuthors,
                    genres = emptySet()
                )
            }

        val epubBooks = epub
            .map { (ep, file, ext) ->

                val bookAuthors = ep.metadata.authors
                    .mapNotNull {
                        epubAuthors.find { a ->
                            a.firstName == it.firstname && a.lastName == it.lastname
                        }
                    }
                    .toSet()

                val genres = ep.metadata.subjects.map { Genre(value = it) }

                Book(
                    title = ep.metadata.titles[0],
                    content = ep.metadata.otherProperties[QName("se:long-description")],
                    summary = ep.metadata.descriptions.firstOrNull(),
                    summaryContentType = summaryType(ep.metadata.descriptions.firstOrNull()),
                    rights = ep.metadata.rights.firstOrNull(),
                    language = lang(ep.metadata.language),
                    issued = ep.metadata.dates.firstOrNull()?.value,
                    publisher = ep.metadata.publishers.firstOrNull(),
                    cover = ep.coverImage?.data,
                    coverContentType = ep.coverImage?.mediaType?.name,
                    file = file.relativeTo(booksDir).path,
                    fileContentType = fileType(ext),
                    authors = bookAuthors,
                    genres = emptySet()
                )
            }

        bookRepository.saveAll(fb2Books + epubBooks)
    }

    private fun summaryType(value: String?) = if (value?.contains("</p>") == true)
        "text/html"
    else
        "text"

    private fun fileType(ext: BookExt) = when (ext) {
        FB2 -> "application/xml"
        FBZ -> "application/zip"
        EPUB -> "application/epub+zip"
    }

    private fun lang(value: String) = when (value.toLowerCase()) {
        "ru" -> "ru-RU"
        "en" -> "en-US"
        else -> value
    }

    @Autowired
    private lateinit var fictionBookService: FictionBookService

    @Autowired
    private lateinit var epubService: EpubService

    @Autowired
    private lateinit var bookRepository: BookRepository
}
