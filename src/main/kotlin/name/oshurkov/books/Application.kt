package name.oshurkov.books

import org.springframework.boot.*
import org.springframework.boot.autoconfigure.*
import org.springframework.boot.web.servlet.support.*
import org.springframework.context.annotation.*
import org.springframework.scheduling.annotation.*

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

@SpringBootApplication
@EnableScheduling
class Application : SpringBootServletInitializer()

@Configuration
class Routes {
    @Bean
    fun routerFunction() = routes
}
