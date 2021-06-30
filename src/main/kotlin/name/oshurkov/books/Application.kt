package name.oshurkov.books

import org.springframework.boot.*
import org.springframework.boot.autoconfigure.*
import org.springframework.boot.web.servlet.support.*
import org.springframework.scheduling.annotation.*

@SpringBootApplication
@EnableScheduling
class Application : SpringBootServletInitializer()

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
