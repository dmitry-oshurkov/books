package name.oshurkov.books

import com.zaxxer.hikari.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import name.oshurkov.books.plugins.*
import org.flywaydb.core.*
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
    dataSource = HikariDataSource().also {
        it.jdbcUrl = "jdbc:postgresql://localhost:5712/books"
        it.username = "postgres"
        it.password = "postgres"

        Flyway.configure().dataSource(it).load().migrate()
    },
    logger = ConsoleLogger(threshold = INFO),
)
