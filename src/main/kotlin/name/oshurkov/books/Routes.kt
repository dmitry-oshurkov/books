package name.oshurkov.books

import name.oshurkov.books.book.*
import name.oshurkov.books.catalog.*
import org.springframework.web.servlet.function.*


val routes = router {

    "books".nest {
        POST("export", ::exportBooks)
        POST("backup", ::backupBooks)

        "import".nest {
            POST("batch", ::importBatch)
            POST(::import)
        }

        "{id}".nest {
            POST("recommended", ::setBookAsRecommended)
            GET("files/{fileId}", ::downloadBook)

            "image".nest {
                GET("thumbnail", ::bookThumbnail)
                GET(::bookImage)
            }

            DELETE(::deleteBook)
        }
    }

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
}
