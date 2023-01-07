package name.oshurkov.books.core

import io.ktor.server.application.*
import io.ktor.server.netty.*
import name.oshurkov.books.core.plugins.*


fun main(args: Array<String>) = EngineMain.main(args)


fun Application.module() {
    configureData()
    configureHTTP()
    configureTemplating()
    configureSerialization()
    configureRouting()
}
