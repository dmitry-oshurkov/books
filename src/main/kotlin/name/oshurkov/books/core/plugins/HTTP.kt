package name.oshurkov.books.core.plugins

import io.github.smiley4.ktorswaggerui.*
import io.ktor.http.HttpMethod.Companion.Delete
import io.ktor.http.HttpMethod.Companion.Options
import io.ktor.http.HttpMethod.Companion.Patch
import io.ktor.http.HttpMethod.Companion.Put
import io.ktor.server.application.*
import io.ktor.server.plugins.compression.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.defaultheaders.*
import name.oshurkov.books.*


fun Application.configureHTTP() {

    install(Compression) {
        gzip { priority = 1.0 }
        deflate {
            priority = 10.0
            minimumSize(1024) // condition
        }
    }

    install(CORS) {
        allowMethod(Put)
        allowMethod(Patch)
        allowMethod(Delete)
        allowMethod(Options)

        allowOrigins { true }
    }

    install(DefaultHeaders) {
        header("X-Engine", "Books") // will send this header with each response
    }

    install(SwaggerUI) {

        swagger { swaggerUrl = "openapi" }

        info {
            title = "Books REST API"
            version = BUILD_VERSION
            description = "Программный интерфейс ядра приложения «Книжный каталог»"
            license {
                name = "MIT"
                url = "https://raw.githubusercontent.com/dmitry-oshurkov/books/main/LICENSE"
            }
        }

        server { url = "/" }

        tag(BOOKS) { description = "Управление книгами" }
        tag(CATALOG) { description = "Управление каталогом" }

        automaticTagGenerator = {
            when (it.firstOrNull()) {
                "books" -> BOOKS
                "catalog" -> CATALOG
                else -> ""
            }
        }

        pathFilter = { _, path -> path.isNotEmpty() && (path.first().startsWith("books") || path.first().startsWith("catalog")) }
    }
}


private const val BOOKS = "Книги"
private const val CATALOG = "Каталог"
