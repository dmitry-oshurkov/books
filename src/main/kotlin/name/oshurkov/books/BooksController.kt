package name.oshurkov.books

import org.springframework.stereotype.*
import org.springframework.ui.*
import org.springframework.web.bind.annotation.*

@Controller
class BooksController {

    @GetMapping("/")
    fun main(model: Model) = "welcome"
}
