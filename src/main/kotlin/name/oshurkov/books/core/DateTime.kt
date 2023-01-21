package name.oshurkov.books.core


import java.time.*


fun Instant.atMoscowOffset() = atOffset(moscowOffset)!!


private val moscowOffset = ZoneOffset.ofHours(3)!!
