package name.oshurkov.books.api.file

import name.oshurkov.books.*
import name.oshurkov.books.api.author.*

fun summaryType(value: String?) = if (value?.contains("</p>") == true)
    "text/html"
else
    "text"

fun lang(value: String) = when (value.lowercase()) {
    "ru" -> "ru-RU"
    "en" -> "en-US"
    else -> value
}

fun bookHash(bookAuthors: Set<Author>, title: String) = uuid("${bookAuthors.sortedBy { it.lastName }.joinToString()}$title".toByteArray())
