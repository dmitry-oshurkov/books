package name.oshurkov.books.plugins

import io.ktor.http.HttpHeaders.Authorization
import io.ktor.http.HttpMethod.Companion.Delete
import io.ktor.http.HttpMethod.Companion.Options
import io.ktor.http.HttpMethod.Companion.Patch
import io.ktor.http.HttpMethod.Companion.Put
import io.ktor.server.application.*
import io.ktor.server.plugins.compression.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.defaultheaders.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.routing.*


fun Application.configureHTTP() {

    install(CORS) {
        allowMethod(Options)
        allowMethod(Put)
        allowMethod(Delete)
        allowMethod(Patch)
        allowHeader(Authorization)
    }

    install(DefaultHeaders) {
        header("X-Engine", "Books") // will send this header with each response
    }

    install(Compression) {
        gzip { priority = 1.0 }
        deflate {
            priority = 10.0
            minimumSize(1024) // condition
        }
    }

    routing {
        swaggerUI(path = "openapi") {
            version = "4.15.5"
        }
    }
}
