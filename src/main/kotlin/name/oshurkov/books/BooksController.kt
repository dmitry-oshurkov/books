package name.oshurkov.books

import name.oshurkov.books.api.author.*
import name.oshurkov.books.api.book.*
import org.springframework.core.env.*
import org.springframework.stereotype.*
import org.springframework.ui.*
import org.springframework.web.bind.annotation.*
import javax.servlet.http.*

@Controller
class BooksController(
    val env: Environment,
    val bookRepository: BookRepository,
    val authorRepository: AuthorRepository
) {

    @GetMapping("/")
    fun index(model: Model, request: HttpServletRequest) = run {
        model.addAttribute("booksCount", bookRepository.count())
        model.addAttribute("authorsCount", authorRepository.count())
        model.addAttribute("opds", "${request.requestURL}catalog")
        "index"
    }

    @GetMapping("/admin")
    fun admin(model: Model) = run {

        val baseBooksDir = if (env.activeProfiles.contains("docker"))
            "/var/lib/books"
        else
            "/home/dmitry/Загрузки/books"

        model.addAttribute("baseBooksDir", baseBooksDir)
        "admin"
    }
}
