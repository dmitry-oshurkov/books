package name.oshurkov.books.core.plugins

import io.ktor.http.ContentType.Application.Xml
import io.ktor.http.HttpStatusCode.Companion.InternalServerError
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.autohead.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import name.oshurkov.books.*
import name.oshurkov.books.book.*
import name.oshurkov.books.catalog.*
import name.oshurkov.books.core.*


fun Application.configureRouting() {

    install(AutoHeadResponse)
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = InternalServerError)
            log1.error(cause)
        }
    }

    routing {

        rapiDoc(pageTitle = "Книжный каталог | Программный интерфейс")

        static("/static") {
            resources("static")
        }

        /**
         * Возвращает информацию о приложении.
         */
        get("about") { call.respondText(BUILD_VERSION) }

        books()
        feeds()
    }
}


suspend fun <T : Any> ApplicationCall.respondXml(message: T) = respondText(message.toXml(), Xml)


private val log1 by logger()
