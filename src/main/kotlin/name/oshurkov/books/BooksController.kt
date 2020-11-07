package name.oshurkov.books

import name.oshurkov.books.api.author.*
import name.oshurkov.books.api.book.*
import org.springframework.beans.factory.annotation.*
import org.springframework.stereotype.*
import org.springframework.ui.*
import org.springframework.web.bind.annotation.*

@Controller
class BooksController {

    @GetMapping("/")
    fun index(model: Model) = run {
        model.addAttribute("booksCount", bookRepository.count())
        model.addAttribute("authorsCount", authorRepository.count())
        "index"
    }

    @Autowired
    private lateinit var bookRepository: BookRepository

    @Autowired
    private lateinit var authorRepository: AuthorRepository
}
