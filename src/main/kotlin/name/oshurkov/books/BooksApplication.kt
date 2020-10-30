package name.oshurkov.books

import org.springframework.boot.*
import org.springframework.boot.autoconfigure.*
import org.springframework.scheduling.annotation.*

@SpringBootApplication
@EnableScheduling
class BooksApplication

fun main(args: Array<String>) {
    runApplication<BooksApplication>(*args)
}
