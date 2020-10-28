package name.oshurkov.books.catalog

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
                    title = "книга 1",
                    updated = Date(),
                    content = "<p>\n" +
                        "            <a href=\"https://standardebooks.org/ebooks/vsevolod-garshin\">Vsevolod Garshin’s</a>\n" +
                        "            literary career followed a stint as a infantry soldier and later an officer, and he received both public and critical acclaim in the 1880s. Before his sadly early death at the age of thirty-three after a lifelong battle with mental illness he wrote and published nineteen short stories. He drew on his military career and life in\n" +
                        "            <abbr>St.</abbr>\n" +
                        "            Petersburg as initial source material, and his varied cast of characters includes soldiers, painters, architects, madmen, bears, frogs and even flowers and trees. All are written with a depth of feeling and sympathy that marks Garshin out from his contemporaries.\n" +
                        "         </p>\n" +
                        "         <p>Collected here are the seventeen translations into English by Rowland Smith of Garshin’s short stories and novellas, in chronological order of the original Russian publication.</p>",
                    link = Link(rel = "http://opds-spec.org/acquisition/open-access", href = "rowland-smith.epub", type = "application/epub+zip", title = "Recommended compatible epub")
                ),
                Entry(
                    id = "2",
                    title = "книга 2",
                    updated = Date(),
                    content = "<p>\n" +
                        "            <a href=\"https://standardebooks.org/ebooks/vsevolod-garshin\">Vsevolod Garshin’s</a>\n" +
                        "            literary career followed a stint as a infantry soldier and later an officer, and he received both public and critical acclaim in the 1880s. Before his sadly early death at the age of thirty-three after a lifelong battle with mental illness he wrote and published nineteen short stories. He drew on his military career and life in\n" +
                        "            <abbr>St.</abbr>\n" +
                        "            Petersburg as initial source material, and his varied cast of characters includes soldiers, painters, architects, madmen, bears, frogs and even flowers and trees. All are written with a depth of feeling and sympathy that marks Garshin out from his contemporaries.\n" +
                        "         </p>\n" +
                        "         <p>Collected here are the seventeen translations into English by Rowland Smith of Garshin’s short stories and novellas, in chronological order of the original Russian publication.</p>",
                    link = Link(rel = "http://opds-spec.org/acquisition/open-access", href = "rowland-smith.epub", type = "application/epub+zip", title = "Recommended compatible epub")
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
