package name.oshurkov.books.core

import io.ktor.http.*
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.routing.*
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import kotlinx.html.*


/**
 * Provides an out-of-the-box route to view docs using RapiDoc on the specified [path].
 * @param pageTitle Webpage title you wish to be displayed on your docs
 * @param route path to docs resource
 * @param specUrl url to point RapiDoc to the OpenAPI json document
 */
fun Route.rapiDoc(pageTitle: String = "Docs", path: String = "/docs", specUrl: String = "/openapi/api.json") {

    route(path) {
        get {
            call.respondHtml(OK) {
                head {
                    title { +pageTitle }
                    link {
                        rel = "icon"
                        href = "static/favicon.svg"
                        type = "image/svg+xml"
                    }
                    meta { charset = "utf-8" }
                    script {
                        type = "module"
                        src = "static/lib/rapidoc-min.js"
                    }
                }
                body {
                    unsafe {
                        +"""
                    <rapi-doc 
                        spec-url="$specUrl"
                        show-method-in-nav-bar="as-colored-text"
                        persist-auth="true"
                        show-header="false"
                        allow-server-selection="false"
                        allow-authentication="false"
                        >
                        <div slot="nav-logo" style="display: flex; align-items: center; justify-content: left;"> 
                            <img src="static/favicon.svg" style="width:40px; margin-right: 20px; margin-bottom: 5px"><span style="color:#bebebe"><b>Книжный каталог</b></span>
                        </div>
                    </rapi-doc>
                    """
                    }
                }
            }
        }
    }
}
