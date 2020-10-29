package name.oshurkov.books.catalog

import com.kursx.parser.fb2.*
import nl.siegmann.epublib.epub.*
import org.apache.tomcat.util.codec.binary.Base64
import org.springframework.http.MediaType.*
import org.springframework.web.bind.annotation.*
import java.io.*
import java.nio.file.*
import java.util.Date

@RestControllerAdvice
@RequestMapping("/catalog")
class CatalogController {

    @GetMapping(produces = [APPLICATION_XML_VALUE])
    fun root() = run {

        Feed(
            id = "tag:root",
            title = "Каталог книг",
            updated = Date(),
            links = listOf(
                Link(rel = "self", href = "catalog", type = "application/atom+xml;profile=opds-catalog;kind=navigation"),
                Link(rel = "start", href = "catalog", type = "application/atom+xml;profile=opds-catalog;kind=navigation"),
                Link(rel = "http://opds-spec.org/crawlable", href = "catalog/featured", type = "application/atom+xml;profile=opds-catalog;kind=acquisition")
            ),
            entries = listOf(
                Entry(
                    id = "tag:root:featured",
                    title = "Рекомендуемые",
                    updated = Date(),
                    content = Content("Рекомендуемые книги"),
                    links = listOf(Link(href = "catalog/featured", type = "application/atom+xml;profile=opds-catalog;kind=acquisition"))
                ),
                Entry(
                    id = "tag:root:authors",
                    title = "По авторам",
                    updated = Date(),
                    content = Content("Поиск книг по авторам"),
                    links = listOf(Link(href = "catalog/authors", type = "application/atom+xml;profile=opds-catalog;kind=navigation"))
                ),
                Entry(
                    id = "tag:root:genre",
                    title = "По жанрам",
                    updated = Date(),
                    content = Content("Поиск книг по жанрам"),
                    links = listOf(Link(href = "catalog/genres", type = "application/atom+xml;profile=opds-catalog;kind=navigation"))
                )
            )
        )
    }

    @GetMapping("featured", produces = [APPLICATION_XML_VALUE])
    fun featured() = run {

        val fb2 = FictionBook(File("/home/dmitry/Загрузки/Пикник на обочине.fb2"))
        val s = fb2.description.titleInfo.coverPage.firstOrNull()?.value?.trimStart('#')
        val binary = fb2.binaries[s]
        if (binary != null) {

            when (binary.contentType) {
                "image/jpeg" -> {
                    val bytes = Base64.decodeBase64(binary.binary)
                    Files.write(Path.of("/home/dmitry/Загрузки/Пикник на обочине.fb2.jpeg"), bytes)
                }
            }
        }

        val epub = EpubReader().readEpub(FileInputStream("/home/dmitry/Загрузки/qq.epub"))
//        val epub = EpubReader().readEpub(FileInputStream("/home/dmitry/yandex.disk/Книги/Брэдбери, Рэй/451 градус по Фаренгейту.epub"))
        Files.write(Path.of("/home/dmitry/Загрузки/qq.epub${epub.coverImage.mediaType.defaultExtension}"), epub.coverImage.data)

        Feed(
            id = "tag:featured",
            title = "Рекомендуемые книги",
            updated = Date(),
            links = listOf(
                Link(rel = "self", href = "catalog/featured", type = "application/atom+xml;profile=opds-catalog;kind=acquisition"),
                Link(rel = "start", href = "catalog", type = "application/atom+xml;profile=opds-catalog;kind=navigation"),
                Link(rel = "up", href = "catalog", type = "application/atom+xml;profile=opds-catalog;kind=navigation"),
                Link(rel = "http://opds-spec.org/crawlable", href = "catalog/featured", type = "application/atom+xml;profile=opds-catalog;kind=acquisition"),
            ),
            entries = listOf(
                Entry(
                    id = "1",
                    title = fb2.title,
                    updated = Date(),
                    content = null,
                    summary = null,
                    authors = fb2.description.titleInfo.authors.map {
                        Author(
                            name = "${it.firstName} ${it.middleName} ${it.lastName}",
                            uri = null
                        )
                    },
                    categories = fb2.description.titleInfo.genres.map {
                        Category(
                            scheme = null,
                            term = it
                        )
                    },
                    rights = null,
                    language = fb2.lang,
//                    issued = fb2.description.titleInfo.date,
                    publisher = null,
                    sources = listOf(),
                    links = listOf(
                        Link(rel = "http://opds-spec.org/image", href = "file:///home/dmitry/Загрузки/Пикник на обочине.fb2.jpeg", type = "image/jpeg"),
                        Link(rel = "http://opds-spec.org/image/thumbnail", href = "file:///home/dmitry/Загрузки/Пикник на обочине.fb2.jpeg", type = "image/jpeg"),
                        Link(rel = "http://opds-spec.org/acquisition/open-access", href = "rowland-smith.epub", type = "application/epub+zip", title = "Recommended compatible epub")
                    )
                ),
                Entry(
                    id = "2",
                    title = epub.metadata.titles[0],
                    updated = Date(),
                    summary = Summary(epub.metadata.descriptions.firstOrNull()),
                    authors = epub.metadata.authors.map {
                        Author(
                            name = "${it.firstname} ${it.lastname}",
                            uri = null
                        )
                    },
                    categories = epub.metadata.subjects.map {
                        Category(
                            scheme = "http://purl.org/dc/terms/LCSH",
                            term = it
                        )
                    },
                    rights = epub.metadata.rights.firstOrNull(),
                    language = epub.metadata.language,
//                    issued = book.metadata.dates.firstOrNull()?.let { ZonedDateTime.parse(it.value, DateTimeFormatter.ISO_INSTANT)  },
                    publisher = epub.metadata.publishers.firstOrNull(),
                    sources = null,
                    content = Content(
                        content = "<p>\n" +
                            "            <a href=\"https://standardebooks.org/ebooks/h-g-wells\">\n" +
                            "               <abbr>H. G.</abbr>\n" +
                            "               Wells’\n" +
                            "            </a>\n" +
                            "            classic tale of alien invasion has to this day never been out of print. Like many works of the era, it was originally published as a serial—though the publisher,\n" +
                            "            <i>Pearson’s Magazine</i>\n" +
                            "            , demanded to know the ending before committing to publication.\n" +
                            "         </p>\n" +
                            "         <p>\n" +
                            "            <i>The War of the Worlds</i>\n" +
                            "            , with its matter-of-fact narrative style and deft mixture of contemporary science and fictionalized interstellar war machines, became an instant hit. Its themes of colonialism, social Darwinism, good and evil, and total war still resonate with modern-day readers, so much so that it’s been continuously adapted for screen, radio, television, comics, and print.\n" +
                            "         </p>",
                        type = "text/html"
                    ),
                    links = listOf(
                        Link(rel = "http://opds-spec.org/image", href = "file:///home/dmitry/Загрузки/qq.epub.jpg", type = "image/jpeg"),
                        Link(rel = "http://opds-spec.org/image/thumbnail", href = "file:///home/dmitry/Загрузки/qq.epub.jpg", type = "image/jpeg"),
                        Link(rel = "http://opds-spec.org/acquisition/open-access", href = "rowland-smith.epub", type = "application/epub+zip", title = "Recommended compatible epub")
                    )
                )
            )
        )
    }

    @GetMapping("authors", produces = [APPLICATION_XML_VALUE])
    fun authors() = run {
    }

    @GetMapping("genres", produces = [APPLICATION_XML_VALUE])
    fun genres() = run {
    }
}
