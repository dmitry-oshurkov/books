package name.oshurkov.books.api.book

import org.springframework.context.annotation.*
import org.springframework.data.repository.*
import org.springframework.http.MediaType.*
import org.springframework.transaction.annotation.*
import org.springframework.web.servlet.function.*
import org.springframework.web.servlet.function.ServerResponse.*

@Configuration
class BookRoutes(
    val bookRepository: BookRepository
) {

    @Bean
    fun routes() = router {

        "/api/book".nest {

            DELETE("{id}", ::delete)
            GET("{id}/image", ::image)
        }
    }

    @Transactional
    fun delete(request: ServerRequest): ServerResponse = run {

        val id = request.pathVariable("id").toInt()
        val book = bookRepository.findByIdOrNull(id)

        if (book != null) {
            bookRepository.delete(book)
            ok().build()
        } else
            notFound().build()
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
}
