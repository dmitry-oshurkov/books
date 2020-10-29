package name.oshurkov.books.storage

import name.oshurkov.books.storage.BookExt.*
import org.apache.tomcat.util.codec.binary.*
import org.aspectj.util.*
import org.springframework.beans.factory.annotation.*
import org.springframework.data.domain.*
import org.springframework.web.bind.annotation.*
import java.io.*

@RestControllerAdvice
@RequestMapping("/storage")
class StorageController {

    @PostMapping("rebuild")
    fun rebuild() {

        val booksDir = File("/home/dmitry/yandex.disk/Книги")

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

        val fb2Books = fb2
            .map { (book, file) ->

                val authors = book.description.titleInfo.authors
                    .map {
                        Author(
                            firstName = it.firstName,
                            middleName = it.middleName,
                            lastName = it.lastName,
                            books = emptySet()
                        )
                    }
                    .mapNotNull {

                        try {
                            authorRepository
                                .findOne(Example.of(Author(
                                    firstName = it.firstName,
                                    middleName = null,
                                    lastName = it.lastName,
                                    books = emptySet()
                                )))
                                .orElseGet {
                                    try {
                                        authorRepository.saveAndFlush(it)
                                    } catch (e: Exception) {
                                        null
                                    }
                                }
                        } catch (e: Exception) {
                            null
                        }
                    }
                    .toSet()

                val genres = book.description.titleInfo.genres.map {
                    Genre(
                        term = it,
                        scheme = null
                    )
                }

                val binary = book.binaries[book.description.titleInfo.coverPage.firstOrNull()?.value?.trimStart('#')]

                Book(
                    title = book.title,
                    content = null,
                    summary = null,
                    authors = authors,
                    rights = null,
                    language = when (book.lang.toLowerCase()) {
                        "ru" -> "ru-RU"
                        else -> book.lang
                    },
                    issued = book.description.titleInfo.date,
                    publisher = null,
                    cover = binary?.binary?.let { Base64.decodeBase64(it) },
                    coverContentType = binary?.contentType,
                    file = file.relativeTo(booksDir).path
                )
            }

        bookRepository.saveAll(fb2Books)
    }

    @Autowired
    private lateinit var fictionBookService: FictionBookService

    @Autowired
    private lateinit var bookRepository: BookRepository

    @Autowired
    private lateinit var authorRepository: AuthorRepository
}
