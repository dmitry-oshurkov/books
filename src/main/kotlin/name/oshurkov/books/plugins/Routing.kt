package name.oshurkov.books.plugins

import io.ktor.http.*
import io.ktor.http.HttpStatusCode.Companion.InternalServerError
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.autohead.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import name.oshurkov.books.*
import name.oshurkov.books.book.*
import name.oshurkov.books.catalog.*


fun Application.configureRouting() {

    install(AutoHeadResponse)
    install(CallLogging)
    install(StatusPages) {
        exception<Throwable> { call, cause -> call.respondText(text = "500: $cause", status = InternalServerError) }
    }

    routing {

        static("/static") {
            resources("static")
        }

        /**
         * Возвращает информацию о приложении.
         */
        get("about") { call.respondText(BUILD_VERSION) }

        books()
        catalog()
    }
}


suspend fun <T : Any> ApplicationCall.respondXml(message: T) = respondText(message.toXml(), ContentType.Application.Xml)
