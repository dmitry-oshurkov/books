package name.oshurkov.books.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.thymeleaf.*
import name.oshurkov.books.*
import name.oshurkov.books.author.*
import name.oshurkov.books.book.*
import nz.net.ultraq.thymeleaf.layoutdialect.*
import org.thymeleaf.templateresolver.*
import kotlin.text.Charsets.UTF_8


fun Application.configureTemplating() {

    install(Thymeleaf) {
        addDialect(LayoutDialect())
        setTemplateResolver(
            ClassLoaderTemplateResolver().apply {
                prefix = "templates/thymeleaf/"
                suffix = ".html"
                characterEncoding = UTF_8.name()
            }
        )
    }

    routing {

        /**
         * Главная страница веб-сайта.
         */
        get("/") {
            call.respond(
                ThymeleafContent(
                    "index",
                    mapOf(
                        "ver" to BUILD_VERSION,
                        "booksCount" to selectBooksCount(),
                        "authorsCount" to selectAuthorsCount(),
                    )
                )
            )
        }

        /**
         * Страница администрирования.
         */
        get("/admin") {
            call.respond(
                ThymeleafContent(
                    "admin",
                    mapOf(
                        "ver" to BUILD_VERSION,
                        "baseBooksDir" to "/var/lib/books",
                    )
                )
            )
        }
    }
}
