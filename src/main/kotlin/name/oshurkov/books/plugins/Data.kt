package name.oshurkov.books.plugins

import com.zaxxer.hikari.*
import io.ktor.server.application.*
import name.oshurkov.books.core.*
import org.flywaydb.core.*
import org.ktorm.database.*
import org.ktorm.logging.*


fun Application.configureData() {

    val url = environment.config.property("books.datasource.url").getString()
    val password = environment.config.property("books.datasource.password").getString()
    val username = environment.config.property("books.datasource.username").getString()

    db = Database.connect(
        dataSource = HikariDataSource().also {
            it.jdbcUrl = url
            it.username = password
            it.password = username

            Flyway.configure().dataSource(it).load().migrate()
        },
        logger = Slf4jLoggerAdapter(dataLog),
    )
}


lateinit var db: Database


private val dataLog by logger()
