package name.oshurkov.books

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import name.oshurkov.books.plugins.*


fun main(args: Array<String>) = EngineMain.main(args)


fun Application.module() {
    configureData()
    configureHTTP()
    configureTemplating()
    configureSerialization()
    configureRouting()
}
