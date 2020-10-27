package name.oshurkov.books.catalog

import org.springframework.http.MediaType.*
import org.springframework.web.bind.annotation.*
import java.util.*

@RestControllerAdvice
@RequestMapping("/catalog")
class CatalogController {

    @GetMapping("root.xml", produces = [APPLICATION_XML_VALUE])
    fun root() = run {

        Feed(
            id = "root.xml",
            title = "Test Catalog Root",
            updated = Date(),
            author = Author(name = "Hadrien Gardeur", uri = "https://github.com/Feedbooks"),
            links = listOf(
                Link(
                    type = "application/atom+xml; profile=opds-catalog; kind=navigation",
                    title = null,
                    rel = "self",
                    href = "root.xml"
                ),
                Link(
                    type = "application/atom+xml;profile=opds-catalog;kind=navigation",
                    title = null,
                    rel = "start",
                    href = "root.xml"
                ),
                Link(
                    type = "application/opensearchdescription+xml",
                    title = "Search on test catalog",
                    rel = "search",
                    href = "opensearch.xml"
                ),
                Link(
                    type = "application/atom+xml;profile=opds-catalog;kind=acquisition",
                    title = "Featured",
                    rel = "http://opds-spec.org/featured",
                    href = "acquisition/main.xml"
                ),
                Link(
                    type = "application/atom+xml;profile=opds-catalog;kind=acquisition",
                    title = "Recommended",
                    rel = "http://opds-spec.org/recommended",
                    href = "acquisition/page2.xml"
                )
            ),
            entries = listOf(
                Entry(
                    title = "First Acquisition feed",
                    id = "main.xml",
                    updated = Date(),
                    content = "Basic acquisition feed",
                    link = Link(
                        type = "application/atom+xml; profile=opds-catalog; kind=acquisition",
                        title = null,
                        rel = "subsection",
                        href = "acquisition/main.xml"
                    )
                ),
                Entry(
                    title = "Link: Recommended",
                    id = "recommended.xml",
                    updated = Date(),
                    content = "Link for additional navigation",
                    link = Link(
                        type = "application/atom+xml; profile=opds-catalog; kind=navigation",
                        title = null,
                        rel = "subsection",
                        href = "links/recommended.xml"
                    )
                )
            )
        )
    }
}
