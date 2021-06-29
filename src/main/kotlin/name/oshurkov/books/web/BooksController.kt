package name.oshurkov.books.web

import name.oshurkov.books.Repositories.Companion.authorsRep
import name.oshurkov.books.Repositories.Companion.booksRep
import org.springframework.core.env.*
import org.springframework.stereotype.*
import org.springframework.ui.*
import org.springframework.web.bind.annotation.*
import javax.servlet.http.*

@Controller
class BooksController(val env: Environment) {

    @GetMapping("/")
    fun index(model: Model, request: HttpServletRequest) = run {
        model.addAttribute("booksCount", booksRep.count())
        model.addAttribute("authorsCount", authorsRep.count())
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
