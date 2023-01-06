package name.oshurkov.books.core

import com.zaxxer.hikari.*
import org.flywaydb.core.*
import org.ktorm.database.*
import org.ktorm.logging.*


private val log by logger()


val db = Database.connect(
    dataSource = HikariDataSource().also {
        it.jdbcUrl = "jdbc:postgresql://localhost:5712/books"
        it.username = "postgres"
        it.password = "postgres"

        Flyway.configure().dataSource(it).load().migrate()
    },
    logger = Slf4jLoggerAdapter(log),
)
