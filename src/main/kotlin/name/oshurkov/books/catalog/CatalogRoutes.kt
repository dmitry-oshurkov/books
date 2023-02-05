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
import name.oshurkov.books.core.plugins.*


fun Routing.catalog() {

    route(catalog.value) {

        /**
         * Книжный каталог.
         */
        get { call.respondXml(reedRoot()) }

        /**
         * Рекомендуемые книги.
         */
        get(recommended.value) { call.respondXml(reedRecommended()) }

        /**
         * Непрочитанные книги.
         */
        get(unread.value) { call.respondXml(reedUnread()) }

        /**
         * Недавно добавленные книги.
         */
        get(recent.value) { call.respondXml(reedRecent()) }

        /**
         * Непроверенные книги.
         */
        get(unverified.value) { call.respondXml(reedUnverified()) }


        route(authors.value) {

            /**
             * По авторам.
             */
            get { call.respondXml(reedAuthors()) }

            route("{id}") {

                /**
                 * Все книги автора.
                 */
                get("books") {
                    val id: Int by call.parameters
                    call.respondXml(reedAuthorBooks(id))
                }

                route("sequences") {

                    /**
                     * По сериям.
                     */
                    get {
                        val id: Int by call.parameters
                        call.respondXml(reedAuthorSequences(id))
                    }
                }
            }
        }


        route(sequences.value) {

            /**
             * Все книги серии.
             */
            get("{id}/books") {
                val id: Int by call.parameters
                call.respondXml(reedSequenceBooks(id))
            }
        }


        route(genres.value) {

            /**
             * По жанрам.
             */
            get { call.respondXml(reedGenres()) }

            /**
             * Все книги жанра.
             */
            get("{id}/books") {
                val id: Int by call.parameters
                call.respondXml(reedGenreBooks(id))
            }
        }
    }
}
