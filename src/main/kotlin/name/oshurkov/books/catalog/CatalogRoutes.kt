package name.oshurkov.books.catalog

import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import name.oshurkov.books.plugins.*


fun Routing.catalog() {

    route("catalog") {

        /**
         * Книжный каталог.
         */
        get { call.respondXml(root()) }

        /**
         * Рекомендуемые книги.
         */
        get("recommended") { call.respondXml(recommended()) }

        /**
         * Непрочитанные книги.
         */
        get("unread") { call.respondXml(unread()) }

        /**
         * Недавно добавленные книги.
         */
        get("recent") { call.respondXml(recent()) }

        /**
         * Непроверенные книги.
         */
        get("unverified") { call.respondXml(unverified()) }


        route("authors") {

            /**
             * По авторам.
             */
            get { call.respondXml(authors()) }

            route("{id}") {

                /**
                 * Все книги автора.
                 */
                get("books") {
                    val id: Int by call.parameters
                    call.respondXml(authorBooks(id))
                }

                route("sequences") {

                    /**
                     * По сериям.
                     */
                    get {
                        val id: Int by call.parameters
                        call.respondXml(authorSequences(id))
                    }
                }
            }
        }


        route("sequences") {

            /**
             * Все книги серии.
             */
            get("{id}/books") {
                val id: Int by call.parameters
                call.respondXml(sequenceBooks(id))
            }
        }


        route("genres") {

            /**
             * По жанрам.
             */
            get { call.respondXml(genres()) }

            /**
             * Все книги жанра.
             */
            get("{id}/books") {
                val id: Int by call.parameters
                call.respondXml(genreBooks(id))
            }
        }
    }
}
