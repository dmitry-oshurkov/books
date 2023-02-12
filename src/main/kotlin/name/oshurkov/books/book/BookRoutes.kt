package name.oshurkov.books.book

import io.ktor.http.*
import io.ktor.http.ContentDisposition.Companion.Attachment
import io.ktor.http.ContentDisposition.Parameters.FileNameAsterisk
import io.ktor.http.ContentType.Application.OctetStream
import io.ktor.http.ContentType.Image.JPEG
import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.http.HttpStatusCode.Companion.NotFound
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import io.ktor.util.pipeline.*
import name.oshurkov.books.core.*
import name.oshurkov.books.core.plugins.*


fun Routing.books() {

    route("books") {

        post({
            info("импорт | Выполняет импорт книги.")
            request {
                body<ByteArray> {
                    description = "файл книги"
                    mediaType(OctetStream)
                    example("1: пример", byteArrayOf(1, 2, 3))
                }
            }
            response { noContent }
        }) {
            val bytes = call.receive<ByteArray>()
            importBooks(bytes)
            call.respond(NoContent)
        }


        route("{id}") {

            patch({
                info("изменение")
                request {
                    id
                    body<PatchBook> {
                        description = "вв"
                        example("1: установить состояние: прочитанная", PatchBook.setUnreadExample)
                        example("2: пример", PatchBook.example)
                    }
                }
                response {
                    noContent
                    notFound
                }
            }) { noContentOrNotFound(::updateBook) }


            delete({
                info("удаление")
                request { id }
                response {
                    noContent
                    notFound
                }
            }) { noContentOrNotFound(::deleteBook) }


            route("image") {

                get({
                    info("обложка")
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
                    info("миниатюра обложки")
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
            info("изменение автора")
            request {
                body<String> {
                    description = "идентификаторы старого и нового автора"
                    example("1: пример", "123,456")
                }
            }
            response { noContent }
        }) {
            val ids = call.receiveText().split(",")
            moveBooksToOtherAuthor(ids[0].toInt(), ids[1].toInt())
            call.respond(NoContent)
        }


        get("files/{id}", {
            info("загрузка файла")
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
            info("импорт списка | Выполняет импорт книг из указанного каталога файловой системы. Файлы книг могут находиться во вложенных каталогах.")
            request {
                body<String> {
                    description = "каталог файловой системы с импортируемыми книгами"
                    example("1: пример", "/var/lib/books/import/")
                }
            }
            response { noContent }
        }) {
            val rootDir = call.receiveText()
            importBooks(rootDir)
            call.respond(NoContent)
        }


        post("export", {
            info("экспорт | Выполняет экспорт книг в указанный каталог файловой системы.")
            request {
                body<String> {
                    description = "каталог файловой системы для экспорта"
                    example("1: пример", "/var/lib/books/export/")
                }
            }
            response { noContent }
        }) {
            val targetDir = call.receiveText()
            exportBooks(targetDir)
            call.respond(NoContent)
        }


        post("backup", {
            info("архивирование | Выполняет архивирование книг в указанный каталог файловой системы. Архив 7Z.")
            request {
                body<String> {
                    description = "каталог файловой системы для архива"
                    example("1: пример", "/var/lib/books/backup/")
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
