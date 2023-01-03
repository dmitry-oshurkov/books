package name.oshurkov.books

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import name.oshurkov.books.plugins.*
import org.ktorm.database.*
import org.ktorm.logging.*
import org.ktorm.logging.LogLevel.*


fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}


fun Application.module() {
    configureHTTP()
    configureTemplating()
    configureSerialization()
    configureRouting()
}


val db = Database.connect(
    url = "jdbc:postgresql://localhost:5712/books",
    user = "postgres",
    password = "postgres",
    logger = ConsoleLogger(threshold = INFO)
)
