package name.oshurkov.books.catalog

import org.springframework.http.MediaType.*
import org.springframework.web.bind.annotation.*

@RestControllerAdvice
@RequestMapping("/catalog")
class CatalogController {

    @GetMapping("root.xml", produces = [APPLICATION_XML_VALUE])
    fun root() = run {

        Feed(
            id = "",
            title = "",
            updated = null,
            author = null,
            link = listOf(),
            entry = listOf()
        )
    }
}
