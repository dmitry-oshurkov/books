package name.oshurkov.books

import org.springframework.http.*


@DslMarker
annotation class RouteContextMarker

@RouteContextMarker
fun RouteInfo.response(fn: ResponseBuilder.() -> Unit) = fn(response)


class ResponseBuilder {

    val headers = HttpHeaders()
}

class HttpHeaderBuilder(private val headers: HttpHeaders) {

    operator fun <T> Pair<String, T>.unaryPlus() = run {
        headers[first] = this.second.toString()
        this@HttpHeaderBuilder
    }
}

@ResponseBuilderMarker
fun ResponseBuilder.headers(fn: HttpHeaderBuilder.() -> Unit) = fn(HttpHeaderBuilder(headers))

@DslMarker
annotation class ResponseBuilderMarker
