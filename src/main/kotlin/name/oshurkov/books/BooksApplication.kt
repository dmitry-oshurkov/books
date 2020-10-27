package name.oshurkov.books

import org.springframework.boot.*
import org.springframework.boot.autoconfigure.*

@SpringBootApplication
class BooksApplication

fun main(args: Array<String>) {
    runApplication<BooksApplication>(*args)
}
