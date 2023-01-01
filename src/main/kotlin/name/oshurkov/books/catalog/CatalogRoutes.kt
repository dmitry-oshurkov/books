package name.oshurkov.books.catalog

import io.ktor.server.routing.*


fun Routing.catalog() {

    route("catalog") {

        /**
         * Рекомендуемые книги
         */
        get("recommended") {
        }

        get("unread") {}
        get("recent") {}
        get("unverified") {}
    }

    /*
        "catalog".nest {
        GET("recommended", ::recommendedCatalog)
        GET("unread", ::unreadCatalog)
        GET("recent", ::recentCatalog)
        GET("unverified", ::unverifiedCatalog)

        "authors".nest {
            "{id}".nest {
                GET("books", ::authorBooksCatalog)

                "sequences".nest {
                    GET("{sequenceId}/books", ::authorSequenceBooksCatalog)
                    GET(::authorSequencesCatalog)
                }
            }

            GET(::authorsCatalog)
        }

        "genres".nest {
            GET("{id}/books", ::genreBooksCatalog)
            GET(::genresCatalog)
        }

        GET(::rootCatalog)
    }
         */
}
