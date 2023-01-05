package name.oshurkov.books.book

import io.ktor.http.*
import io.ktor.http.ContentDisposition.Companion.Attachment
import io.ktor.http.ContentDisposition.Parameters.FileNameAsterisk
import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.http.HttpStatusCode.Companion.NotFound
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*


fun Routing.books() {

    route("books") {

        route("{id}") {

            /**
             * Удаление книги.
             */
            delete {
                val id: Int by call.parameters
                if (deleteBook(id) == 1)
                    call.respond(NoContent)
                else
                    call.respond(NotFound)
            }

            route("image") {

                /**
                 * Обложка книги.
                 */
                get {

                    val id: Int by call.parameters
                    val (contentType, cover) = bookImage(id)

                    if (cover != null) {
                        call.response.header(HttpHeaders.ContentType, contentType)
                        call.respondBytes(cover)
                    } else
                        call.respond(NotFound)
                }

                /**
                 * Миниатюра обложки книги.
                 */
                get("thumbnail") {

                    val id: Int by call.parameters
                    val (contentType, cover) = bookThumbnail(id)

                    if (cover != null) {
                        call.response.header(HttpHeaders.ContentType, contentType)
                        call.respondBytes(cover)
                    } else
                        call.respond(NotFound)
                }
            }
        }


        /**
         * Загрузка файла книги.
         */
        get("files/{id}") {

            val id: Int by call.parameters
            val userAgent = call.request.userAgent() ?: ""

            val (filename, contentType, content) = downloadBook(
                fileId = id,
                fullFilename = userAgent.contains("Chrome") || userAgent.contains("Mozilla") || userAgent.contains("Safari")
            )

            if (content != null) {
                call.response.header(HttpHeaders.ContentType, contentType)
                call.response.header(HttpHeaders.ContentDisposition, Attachment.withParameter(FileNameAsterisk, filename, encodeValue = true).toString())
                call.respondBytes(content)
            } else
                call.respond(NotFound)
        }


        /**
         * Выполняет импорт книг из указанного каталога файловой системы. Файлы книг могут быть находиться во вложенных каталогах.
         */
        post("import") {
            val rootDir = call.receiveText()
            importBooks(rootDir)
            call.respond(NoContent)
        }

        /**
         * Выполняет экспорт книг в указанный каталог файловой системы.
         */
        post("export") {
            val targetDir = call.receiveText()
            exportBooks(targetDir)
            call.respond(NoContent)
        }

        /**
         * Выполняет архивирование книг в указанный каталог файловой системы. Архив 7Z.
         */
        post("backup") {
            val targetDir = call.receiveText()
            backupBooks(targetDir)
            call.respond(NoContent)
        }
    }
}
