package name.oshurkov.books

import org.springframework.boot.*
import org.springframework.boot.WebApplicationType.*
import org.springframework.boot.autoconfigure.*
import org.springframework.boot.web.servlet.support.*
import org.springframework.scheduling.annotation.*

@SpringBootApplication
@EnableScheduling
class BooksApplication : SpringBootServletInitializer()

fun main(args: Array<String>) {
    runApplication<BooksApplication>(*args) {
        webApplicationType = REACTIVE
    }
}
