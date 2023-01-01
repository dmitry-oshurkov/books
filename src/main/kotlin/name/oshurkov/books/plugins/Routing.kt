package name.oshurkov.books.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.autohead.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import name.oshurkov.*
import name.oshurkov.books.catalog.*


fun Application.configureRouting() {

    install(AutoHeadResponse)

    routing {
        catalog()

        /**
         * Возвращает информацию о приложении.
         */
        get("about") { call.respondText(BUILD_VERSION) }
    }
}
