package name.oshurkov.books

import java.io.*

val booksDir = File("/home/dmitry/yandex.disk/Книги")

infix fun <A, B, C> Pair<A, B>.and(that: C) = Triple(this.first, this.second, that)
