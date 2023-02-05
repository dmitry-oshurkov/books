package name.oshurkov.books.core.plugins

import io.ktor.http.ContentType.*
import io.ktor.server.application.*
import io.ktor.server.application.Application
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.thymeleaf.*
import name.oshurkov.books.*
import name.oshurkov.books.author.*
import name.oshurkov.books.book.*
import name.oshurkov.books.core.*
import org.thymeleaf.templateresolver.*
import kotlin.text.Charsets.UTF_8


fun Application.configureTemplating() {

    install(Thymeleaf) {
        setTemplateResolver(
            ClassLoaderTemplateResolver().apply {
                prefix = "templates/thymeleaf/"
                suffix = ".html"
                characterEncoding = UTF_8.name()
            }
        )
    }

    routing {

        get("/", {
            info("главная страница веб-сайта")
            response { ok("<!DOCTYPE html ><html></html>") { mediaType(Text.Html) } }
        }) {
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


        get("/admin", {
            info("администрирование")
            response { ok("<!DOCTYPE html ><html></html>") { mediaType(Text.Html) } }
        }) {
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
