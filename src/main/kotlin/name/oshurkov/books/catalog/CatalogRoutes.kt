package name.oshurkov.books.catalog

import io.github.smiley4.ktorswaggerui.dsl.*
import io.ktor.http.ContentType.Application.Xml
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import name.oshurkov.books.catalog.CatalogPath.Companion.authors
import name.oshurkov.books.catalog.CatalogPath.Companion.catalog
import name.oshurkov.books.catalog.CatalogPath.Companion.genres
import name.oshurkov.books.catalog.CatalogPath.Companion.recent
import name.oshurkov.books.catalog.CatalogPath.Companion.recommended
import name.oshurkov.books.catalog.CatalogPath.Companion.sequences
import name.oshurkov.books.catalog.CatalogPath.Companion.unread
import name.oshurkov.books.catalog.CatalogPath.Companion.unverified
import name.oshurkov.books.core.*
import name.oshurkov.books.core.get
import name.oshurkov.books.core.plugins.*


fun Routing.feeds() {

    route(catalog.value) {

        get({
            info("книжный каталог")
            response { xml(Feed.rootExample) }
        }) { call.respondXml(reedRoot()) }


        get(recommended.value, {
            info("рекомендуемые книги")
            response { xml(Feed.recommendedExample) }
        }) { call.respondXml(reedRecommended()) }


        get(unread.value, {
            info("непрочитанные книги")
            response { xml(Feed.unreadExample) }
        }) { call.respondXml(reedUnread()) }


        get(recent.value, {
            info("недавно добавленные книги")
            response { xml(Feed.recentExample) }
        }) { call.respondXml(reedRecent()) }


        get(unverified.value, {
            info("непроверенные книги")
            response { xml(Feed.unverifiedExample) }
        }) { call.respondXml(reedUnverified()) }


        route(authors.value) {

            get({
                info("по авторам")
                response { xml(Feed.authorsExample) }
            }) { call.respondXml(reedAuthors()) }

            route("{id}") {

                get("books", {
                    info("все книги автора")
                    request { id }
                    response { xml(Feed.authorBooksExample) }
                }) {
                    val id: Int by call.parameters
                    call.respondXml(reedAuthorBooks(id))
                }

                route("sequences") {

                    get({
                        info("все книги автора по сериям")
                        request { id }
                        response { xml(Feed.authorBooksBySequenceExample) }
                    }) {
                        val id: Int by call.parameters
                        call.respondXml(reedAuthorSequences(id))
                    }
                }
            }
        }


        route(sequences.value) {

            get("{id}/books", {
                info("все книги серии")
                request { id }
                response { xml(Feed.sequencesExample) }
            }) {
                val id: Int by call.parameters
                call.respondXml(reedSequenceBooks(id))
            }
        }


        route(genres.value) {

            get({
                info("по жанрам")
                response { xml(Feed.genresExample) }
            }) { call.respondXml(reedGenres()) }


            get("{id}/books", {
                info("все книги жанра")
                request { id }
                response { xml(Feed.booksByGenreExample) }
            }) {
                val id: Int by call.parameters
                call.respondXml(reedGenreBooks(id))
            }
        }
    }
}


private fun OpenApiResponses.xml(example: Feed) = ok(example.toXml()) { mediaType(Xml) }
