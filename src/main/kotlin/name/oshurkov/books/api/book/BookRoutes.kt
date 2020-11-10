package name.oshurkov.books.api.book

import org.springframework.beans.factory.annotation.*
import org.springframework.context.annotation.*
import org.springframework.data.repository.*
import org.springframework.http.MediaType.*
import org.springframework.web.servlet.function.*
import org.springframework.web.servlet.function.ServerResponse.*

@Configuration
class BookRoutes {

    @Bean
    fun bookRoutes() = router {

        (accept(ALL) and "/api/book").nest {

            GET("{id}/image", ::image)
        }
    }

    fun image(request: ServerRequest): ServerResponse = run {

        val id = request.pathVariable("id").toInt()
        val book = bookRepository.findByIdOrNull(id)

        if (book?.cover != null)
            ok()
                .contentType(parseMediaType(book.coverContentType ?: "image/jpeg"))
                .body(book.cover)
        else
            notFound().build()
    }

    @Autowired
    private lateinit var bookRepository: BookRepository
}
