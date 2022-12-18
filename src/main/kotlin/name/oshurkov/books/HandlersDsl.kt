@file:Suppress("unused", "UNUSED_PARAMETER")

package name.oshurkov.books

import org.springframework.http.*
import org.springframework.http.HttpStatus.*
import org.springframework.web.reactive.function.server.*
import java.util.*


/**
 * Возвращает результат обработчика запроса со статусом 201 CREATED.
 *
 * @param B тип модели запроса
 * @param fn обработчик запроса
 */
@RouterDslMarker
suspend inline fun <reified B : Any> ServerRequest.created(crossinline fn: suspend (B) -> UUID) = handle<B> { CREATED to fn(it) }

/**
 * Возвращает результат обработчика запроса со статусом 200 OK.
 *
 * @param R тип результата
 * @param fn обработчик запроса
 */
@RouterDslMarker
suspend fun <R : Any> ServerRequest.ok(fn: suspend RouteInfo.() -> R) = okOrNotFound { fn() }

/**
 * Возвращает результат обработчика запроса со статусом 200 OK.
 *
 * @param B тип модели запроса
 * @param R тип результата
 * @param fn обработчик запроса
 */
@RouterDslMarker
suspend inline fun <reified B : Any, R : Any> ServerRequest.okPost(crossinline fn: suspend (B) -> R) = handle<B> { OK to fn(it) }

/**
 * Возвращает результат обработчика запроса со статусом 200 OK.
 * Если результат равен null, то возвращается 404 NOT_FOUND.
 *
 * @param R тип результата
 * @param fn обработчик запроса
 */
@RouterDslMarker
suspend fun <R : Any> ServerRequest.okOrNotFound(fn: suspend RouteInfo.() -> R?) = handle { okOrNotFound(fn) }

/**
 * Возвращает статус как результат обработчика запроса.
 *
 * @param B тип модели запроса
 * @param fn обработчик запроса
 * @return HTTP-статус
 */
@RouterDslMarker
suspend inline fun <reified B : Any> ServerRequest.status(crossinline fn: suspend RouteInfo.(B) -> HttpStatus) =
    handle<B> {
        fn(this, it) to null
    }

/**
 * Возвращает результат обработчика модели запроса.
 *
 * @param B тип модели запроса
 * @param fn обработчик запроса
 */
@RouterDslMarker
suspend inline fun <reified B : Any> ServerRequest.handle(crossinline fn: suspend RouteInfo.(B) -> HandleResult) =
    handle {
        val requestBody = awaitBody<B>()
        val response = fn(requestBody)
        response
    }

/**
 * Возвращает результат обработчика запроса.
 *
 * @param fn обработчик запроса
 */
@RouterDslMarker
suspend fun ServerRequest.handle(fn: suspend RouteInfo.() -> HandleResult) = internalHandleUnsecured {
    TODO()
}

/**
 * Возвращает результат обработчика запроса со статусом 200 OK (без аутентификации).
 * Если результат равен null, то возвращается 404 NOT_FOUND.
 *
 * @param R тип результата
 * @param fn обработчик запроса
 */
@RouterDslMarker
suspend fun <R : Any> ServerRequest.okOrNotFoundUnsecured(fn: suspend RouteInfo.() -> R?) = internalHandleUnsecured { okOrNotFound(fn) }

/**
 * Возвращает результат обработчика модели запроса без проверки пользователя (без аутентификации).
 *
 * @param B тип модели запроса
 * @param fn обработчик запроса
 */
@RouterDslMarker
suspend inline fun <reified B : Any> ServerRequest.handleUnsecured(crossinline fn: suspend RouteInfo.(B) -> HandleResult) =
    internalHandleUnsecured {
        val requestBody = awaitBody<B>()
        fn(requestBody)
    }

@PublishedApi
@RouterDslMarker
internal suspend fun ServerRequest.internalHandleUnsecured(fn: suspend RouteInfo.() -> HandleResult) = run {

    val routeInfo = RouteInfo(this)

    val (status, body) = try {
        fn(routeInfo)
    } catch (e: Throwable) {
        log.error(e)
        INTERNAL_SERVER_ERROR to e.message
    }

    ServerResponse
        .status(status)
        .headers { it.addAll(routeInfo.response.headers) }
        .let {
            if (body != null)
                it.bodyValueAndAwait(body)
            else
                it.buildAndAwait()
        }
}


private suspend fun <R : Any> RouteInfo.okOrNotFound(fn: suspend RouteInfo.() -> R?) = run {

    val response = fn()

    val status = if (response != null)
        OK
    else
        NOT_FOUND

    status to response
}

@Suppress("FunctionName")
fun CoRouterFunctionDsl.GET(f: suspend ServerRequest.() -> ServerResponse) = GET("", f)

@Suppress("FunctionName")
fun CoRouterFunctionDsl.zGET(pattern: String, f: suspend ServerRequest.() -> ServerResponse) = GET(pattern, f)

@Suppress("FunctionName")
fun CoRouterFunctionDsl.PUT(f: suspend ServerRequest.() -> ServerResponse) = PUT("", f)

@Suppress("FunctionName")
fun CoRouterFunctionDsl.POST(f: suspend ServerRequest.() -> ServerResponse) = POST("", f)

@Suppress("FunctionName")
fun CoRouterFunctionDsl.PATCH(f: suspend ServerRequest.() -> ServerResponse) = PATCH("", f)

@Suppress("FunctionName")
fun CoRouterFunctionDsl.DELETE(f: suspend ServerRequest.() -> ServerResponse) = DELETE("", f)

@DslMarker
annotation class RouterDslMarker


class RouteInfo(
    val request: ServerRequest,
    val response: ResponseBuilder = ResponseBuilder()
) {
    lateinit var currentUserId: UUID
    fun id(name: String = "id") = uuid(pathVariable(name))
    fun pathVariable(name: String) = request.pathVariable(name)
    fun header(name: String) = request.headers().firstHeader(name)
    fun param(name: String) = request.queryParamOrNull(name)
}

typealias HandleResult = Pair<HttpStatus, *>


private val log by logger()
