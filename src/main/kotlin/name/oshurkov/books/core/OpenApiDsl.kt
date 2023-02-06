package name.oshurkov.books.core

import io.github.smiley4.ktorswaggerui.dsl.*
import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.http.HttpStatusCode.Companion.NotFound
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.util.*
import io.ktor.util.pipeline.*
import io.github.smiley4.ktorswaggerui.dsl.delete as delete1
import io.github.smiley4.ktorswaggerui.dsl.get as get1
import io.github.smiley4.ktorswaggerui.dsl.patch as patch1
import io.github.smiley4.ktorswaggerui.dsl.post as post1


@OpenApiDslMarker
infix fun OpenApiRoute.info(info: String) {
    val strings = info.split("|")
    summary = strings[0].trim()
    description = strings.getOrNull(1)?.trim()
}


@OpenApiDslMarker
val OpenApiRequest.id get() = pathParameter<Int>("id") { description = "идентификатор" }


@OpenApiDslMarker
inline fun <reified TBody : Any> OpenApiResponses.ok(example: TBody, crossinline init: OpenApiSimpleBody.() -> Unit = {}) = OK to {
    description = "выполнено успешно (с возможным телом сообщения)"
    body<TBody> {
        description = "результат операции"
        example("1: пример", example)
        init()
    }
}

@OpenApiDslMarker
val OpenApiResponses.noContent get() = NoContent to { description = "выполнено успешно (без тела сообщения)" }

@OpenApiDslMarker
val OpenApiResponses.notFound get() = NotFound to { description = "ресурс не найден" }


@KtorDsl
fun Route.get(builder: OpenApiRoute.() -> Unit = { }, body: PipelineInterceptor<Unit, ApplicationCall>) = get1(builder, body)

@KtorDsl
fun Route.get(path: String, builder: OpenApiRoute.() -> Unit = { }, body: PipelineInterceptor<Unit, ApplicationCall>) = get1(path, builder, body)

@KtorDsl
fun Route.post(builder: OpenApiRoute.() -> Unit = { }, body: PipelineInterceptor<Unit, ApplicationCall>) = post1(builder, body)

@KtorDsl
fun Route.post(path: String, builder: OpenApiRoute.() -> Unit = { }, body: PipelineInterceptor<Unit, ApplicationCall>) = post1(path, builder, body)

@KtorDsl
fun Route.patch(builder: OpenApiRoute.() -> Unit = { }, body: PipelineInterceptor<Unit, ApplicationCall>) = patch1(builder, body)

@KtorDsl
fun Route.patch(path: String, builder: OpenApiRoute.() -> Unit = { }, body: PipelineInterceptor<Unit, ApplicationCall>) = patch1(path, builder, body)

@KtorDsl
fun Route.delete(builder: OpenApiRoute.() -> Unit = { }, body: PipelineInterceptor<Unit, ApplicationCall>) = delete1(builder, body)
