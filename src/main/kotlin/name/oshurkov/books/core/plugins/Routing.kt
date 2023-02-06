package name.oshurkov.books.core.plugins

import io.ktor.http.ContentType.*
import io.ktor.http.ContentType.Application.Xml
import io.ktor.http.HttpStatusCode.Companion.InternalServerError
import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.http.HttpStatusCode.Companion.NotFound
import io.ktor.server.application.*
import io.ktor.server.application.Application
import io.ktor.server.http.content.*
import io.ktor.server.plugins.autohead.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.thymeleaf.*
import io.ktor.server.util.*
import io.ktor.util.pipeline.*
import name.oshurkov.books.*
import name.oshurkov.books.author.*
import name.oshurkov.books.book.*
import name.oshurkov.books.catalog.*
import name.oshurkov.books.core.*


fun Application.configureRouting() {

    install(AutoHeadResponse)
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = InternalServerError)
            log1.error(cause)
        }
    }

    routing {

        rapiDoc(pageTitle = "Книжный каталог | Программный интерфейс")

        static("/static") {
            resources("static")
        }

        get({
            info("главная страница веб-сайта")
            response { ok("<!DOCTYPE html><html></html>") { mediaType(Text.Html) } }
        }) {
            call.respond(
                ThymeleafContent(
                    "index",
                    mapOf(
                        "ver" to BUILD_VERSION,
                        "booksCount" to selectBooksCount(),
                        "authorsCount" to selectAuthorsCount(),
                    )
                )
            )
        }

        get("about", {
            info("информация о приложении")
            response { ok("23.1.02051229") { mediaType(Text.Plain) } }
        }) { call.respondText(BUILD_VERSION) }

        books()
        feeds()
    }
}


@RoutingDsl
suspend fun PipelineContext<Unit, ApplicationCall>.noContentOrNotFound(fn: (Int) -> Int) {
    val id: Int by call.parameters
    internalNoContentOrNotFound { fn(id) }
}


@RoutingDsl
suspend inline fun <reified T : Any> PipelineContext<Unit, ApplicationCall>.noContentOrNotFound(crossinline fn: (Int, T) -> Int) {
    val id: Int by call.parameters
    val model = call.receive<T>()
    internalNoContentOrNotFound { fn(id, model) }
}


@PublishedApi
internal suspend inline fun PipelineContext<Unit, ApplicationCall>.internalNoContentOrNotFound(fn: () -> Int) {
    if (fn() == 1)
        call.respond(NoContent)
    else
        call.respond(NotFound)
}


@RoutingDsl
suspend fun <T : Any> ApplicationCall.respondXml(message: T) = respondText(message.toXml(), Xml)


@DslMarker
annotation class RoutingDsl


private val log1 by logger()
