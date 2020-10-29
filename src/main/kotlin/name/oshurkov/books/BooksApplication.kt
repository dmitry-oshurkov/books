package name.oshurkov.books

import org.springframework.boot.*
import org.springframework.boot.autoconfigure.*
import java.io.*

@SpringBootApplication
class BooksApplication

fun main(args: Array<String>) {
    runApplication<BooksApplication>(*args)
}

val booksDir = File("/home/dmitry/yandex.disk/Книги")

infix fun <A, B, C> Pair<A, B>.and(that: C) = Triple(this.first, this.second, that)
