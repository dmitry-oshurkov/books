package name.oshurkov.books.core.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.request.*
import name.oshurkov.books.core.*
import org.slf4j.event.Level.*


fun Application.configureMonitoring() {

    dev {
        install(CallLogging) {
            level = INFO
            filter { call -> call.request.path().startsWith("/") }
        }
    }
}
