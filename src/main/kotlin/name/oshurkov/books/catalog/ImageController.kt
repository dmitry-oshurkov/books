package name.oshurkov.books.catalog

import name.oshurkov.books.storage.*
import org.springframework.beans.factory.annotation.*
import org.springframework.http.MediaType.*
import org.springframework.web.bind.annotation.*

@RestControllerAdvice
@RequestMapping("/catalog/image")
class ImageController {

    @GetMapping("{bookId}", produces = [IMAGE_JPEG_VALUE])
    @ResponseBody
    fun image(@PathVariable bookId: Int) = bookRepository.getOne(bookId).cover

    @GetMapping("thumbnail/{bookId}", produces = [IMAGE_JPEG_VALUE])
    @ResponseBody
    fun thumbnail(@PathVariable bookId: Int) = bookRepository.getOne(bookId).cover

    @Autowired
    private lateinit var bookRepository: BookRepository
}
