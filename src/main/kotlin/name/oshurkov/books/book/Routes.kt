package name.oshurkov.books.book

import io.ktor.http.*
import io.ktor.http.ContentDisposition.Companion.Attachment
import io.ktor.http.ContentDisposition.Parameters.FileNameAsterisk
import io.ktor.http.ContentType.Image.JPEG
import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.http.HttpStatusCode.Companion.NotFound
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import name.oshurkov.books.core.*


fun Routing.books() {

    route("books") {

        route("{id}") {

            delete({
                info("удаление книги")
                request { id }
                response {
                    noContent
                    notFound
                }
            }) {
                val id: Int by call.parameters
                if (deleteBook(id) == 1)
                    call.respond(NoContent)
                else
                    call.respond(NotFound)
            }


            route("image") {

                get({
                    info("обложка книги")
                    request { id }
                    response {
                        ok(byteArrayOf(1, 2, 3, 4, 5)) { mediaType(JPEG) }
                        notFound
                    }
                }) {

                    val id: Int by call.parameters
                    val (contentType, cover) = bookImage(id)

                    if (cover != null) {
                        call.response.header(HttpHeaders.ContentType, contentType)
                        call.respondBytes(cover)
                    } else
                        call.respond(NotFound)
                }


                get("thumbnail", {
                    info("миниатюра обложки книги")
                    request { id }
                    response {
                        ok(byteArrayOf(1, 2, 3, 4, 5)) { mediaType(JPEG) }
                        notFound
                    }
                }) {

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

        post("move", {
            info("изменение автора книги")
            request {
                body<String> {
                    description = "идентификаторы старого и нового автора"
                    example("1", "123,456")
                }
            }
            response { noContent }
        }) {
            val ids = call.receiveText().split(",")
            moveBooksToOtherAuthor(ids[0].toInt(), ids[1].toInt())
            call.respond(NoContent)
        }


        get("files/{id}", {
            info("выгрузка файла книги")
            request { id }
            response {
                ok(byteArrayOf(1, 2, 3, 4, 5)) {
                    mediaType(ContentType("application", "fb2+zip"))
                    mediaType(ContentType("application", "fb2"))
                }
                notFound
            }
        }) {

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


        post("import", {
            info("импорт книг | Выполняет импорт книг из указанного каталога файловой системы. Файлы книг могут быть находиться во вложенных каталогах.")
            request {
                body<String> {
                    description = "каталог файловой системы с импортируемыми книгами"
                    example("1", "/dir/books/import")
                }
            }
            response { noContent }
        }) {
            val rootDir = call.receiveText()
            importBooks(rootDir)
            call.respond(NoContent)
        }


        post("export", {
            info("экспорт книг | Выполняет экспорт книг в указанный каталог файловой системы.")
            request {
                body<String> {
                    description = "каталог файловой системы для экспорта"
                    example("1", "/var/lib/books/export/")
                }
            }
            response { noContent }
        }) {
            val targetDir = call.receiveText()
            exportBooks(targetDir)
            call.respond(NoContent)
        }


        post("backup", {
            info("архивирование книг | Выполняет архивирование книг в указанный каталог файловой системы. Архив 7Z.")
            request {
                body<String> {
                    description = "каталог файловой системы для архива"
                    example("1", "/dir/books/backup")
                }
            }
            response { noContent }
        }) {
            val targetDir = call.receiveText()
            backupBooks(targetDir)
            call.respond(NoContent)
        }
    }
}
