package name.oshurkov.books.plugins

import io.ktor.http.*
import io.ktor.http.HttpStatusCode.Companion.InternalServerError
import io.ktor.server.application.*
import io.ktor.server.plugins.autohead.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import name.oshurkov.*
import name.oshurkov.books.book.*
import name.oshurkov.books.catalog.*
import name.oshurkov.books.catalog.catalog


fun Application.configureRouting() {

    install(AutoHeadResponse)
    install(CallLogging)
    install(StatusPages) {
        exception<Throwable> { call, cause -> call.respondText(text = "500: $cause", status = InternalServerError) }
    }

    routing {
        books()
        catalog()

        /**
         * Возвращает информацию о приложении.
         */
        get("about") { call.respondText(BUILD_VERSION) }
    }
}


suspend fun <T : Any> ApplicationCall.respondXml(message: T) = respondText(message.toXml(), ContentType.Application.Xml)
