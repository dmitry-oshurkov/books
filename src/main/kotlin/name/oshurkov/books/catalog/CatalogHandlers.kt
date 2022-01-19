package name.oshurkov.books.catalog

import name.oshurkov.books.*
import name.oshurkov.books.Repositories.Companion.authorsRep
import name.oshurkov.books.Repositories.Companion.booksRep
import name.oshurkov.books.api.book.*
import org.springframework.data.repository.*
import org.springframework.http.MediaType.*
import org.springframework.web.servlet.function.*
import org.springframework.web.servlet.function.ServerResponse.*
import java.util.*

fun rootCatalog(@Suppress("UNUSED_PARAMETER") request: ServerRequest): ServerResponse = run {

    ok()
        .contentType(APPLICATION_XML)
        .body(
            Feed(
                id = "tag:root",
                title = "Книжный каталог",
                links = listOf(
                    Navigation(rel = "self", href = ROOT_CAT),
                    Navigation(rel = "start", href = ROOT_CAT),
                ),
                entries = listOf(
                    Entry(
                        id = "tag:root:unread",
                        title = "Непрочитанные",
                        updated = Date(),
                        content = Content("Непрочитанные книги"),
                        links = listOf(
                            Acquisition(rel = "subsection", href = UNREAD_CAT),
                            ImageThumbnail(href = "/abstract.png"),
                            Thumbnail(href = "/abstract.png"),
                        )
                    ),
                    Entry(
                        id = "tag:root:recommended",
                        title = "Рекомендуемые",
                        updated = Date(),
                        content = Content("Рекомендуемые книги"),
                        links = listOf(
                            Acquisition(rel = "subsection", href = RECOMMENDED_CAT),
                            ImageThumbnail(href = "/free.png"),
                            Thumbnail(href = "/free.png"),
                        )
                    ),
                    Entry(
                        id = "tag:root:recent",
                        title = "Недавно добавленные",
                        updated = Date(),
                        content = Content("Недавно добавленные книги"),
                        links = listOf(
                            Acquisition(rel = "subsection", href = RECENT_CAT),
                            ImageThumbnail(href = "/abstract.png"),
                            Thumbnail(href = "/abstract.png"),
                        )
                    ),
                    Entry(
                        id = "tag:root:authors",
                        title = "По авторам",
                        updated = Date(),
                        content = Content("Поиск книг по авторам"),
                        links = listOf(
                            Navigation(rel = "subsection", href = AUTHORS_CAT),
                            ImageThumbnail(href = "/author.png"),
                            Thumbnail(href = "/author.png"),
                        )
                    ),
                    Entry(
                        id = "tag:root:genre",
                        title = "По жанрам",
                        updated = Date(),
                        content = Content("Поиск книг по жанрам"),
                        links = listOf(
                            Navigation(rel = "subsection", href = GENRES_CAT),
                            ImageThumbnail(href = "/genre.png"),
                            Thumbnail(href = "/genre.png"),
                        )
                    ),
                    Entry(
                        id = "tag:root:unverified",
                        title = "Непроверенные",
                        updated = Date(),
                        content = Content("Непроверенные книги"),
                        links = listOf(
                            Acquisition(rel = "subsection", href = UNVERIFIED_CAT),
                            ImageThumbnail(href = "/abstract.png"),
                            Thumbnail(href = "/abstract.png"),
                        )
                    ),
                )
            )
        )
}

fun recommendedCatalog(@Suppress("UNUSED_PARAMETER") request: ServerRequest): ServerResponse = run {

    ok()
        .contentType(APPLICATION_XML)
        .body(
            Feed(
                id = "tag:recommended",
                title = "Рекомендуемые книги",
                links = listOf(
                    Acquisition(rel = "self", href = RECOMMENDED_CAT),
                    Navigation(rel = "start", href = ROOT_CAT),
                    Navigation(rel = "up", href = ROOT_CAT),
                ),
                entries = booksRep.findByRecommendedTrue()
                    .map { it.toEntry() }
                    .toList()
            )
        )
}

fun unreadCatalog(@Suppress("UNUSED_PARAMETER") request: ServerRequest): ServerResponse = run {

    ok()
        .contentType(APPLICATION_XML)
        .body(
            Feed(
                id = "tag:unread",
                title = "Непрочитанные книги",
                links = listOf(
                    Acquisition(rel = "self", href = UNREAD_CAT),
                    Navigation(rel = "start", href = ROOT_CAT),
                    Navigation(rel = "up", href = ROOT_CAT),
                ),
                entries = booksRep.findByUnreadTrue()
                    .map { it.toEntry() }
                    .toList()
            )
        )
}

fun recentCatalog(@Suppress("UNUSED_PARAMETER") request: ServerRequest): ServerResponse = run {

    ok()
        .contentType(APPLICATION_XML)
        .body(
            Feed(
                id = "tag:recent",
                title = "Недавно добавленные книги",
                links = listOf(
                    Acquisition(rel = "self", href = RECENT_CAT),
                    Navigation(rel = "start", href = ROOT_CAT),
                    Navigation(rel = "up", href = ROOT_CAT),
                ),
                entries = booksRep.findTop10ByOrderByUpdatedDesc()
                    .map { it.toEntry(includeSequenceNumber = true, sequenceNumberForRecent = true) }
                    .toList()
            )
        )
}

fun unverifiedCatalog(@Suppress("UNUSED_PARAMETER") request: ServerRequest): ServerResponse = run {

    ok()
        .contentType(APPLICATION_XML)
        .body(
            Feed(
                id = "tag:unverified",
                title = "Непроверенные книги",
                links = listOf(
                    Acquisition(rel = "self", href = UNVERIFIED_CAT),
                    Navigation(rel = "start", href = ROOT_CAT),
                    Navigation(rel = "up", href = ROOT_CAT),
                ),
                entries = booksRep.findByVerifiedFalse()
                    .map { it.toEntry() }
                    .toList()
            )
        )
}


fun authorsCatalog(@Suppress("UNUSED_PARAMETER") request: ServerRequest): ServerResponse = run {

    ok()
        .contentType(APPLICATION_XML)
        .body(
            Feed(
                id = "tag:authors",
                title = "По авторам",
                links = listOf(
                    Acquisition(rel = "self", href = AUTHORS_CAT),
                    Navigation(rel = "start", href = ROOT_CAT),
                    Navigation(rel = "up", href = ROOT_CAT),
                ),
                entries = authorsRep.findByOrderByLastName().map {
                    Entry(
                        id = "tag:authors:${it.id}",
                        title = it.toStringForList(),
                        updated = it.updated,
                        links = listOf(
                            Navigation(rel = "subsection", href = "$AUTHORS_CAT/${it.id}/books"),
                            ImageThumbnail(href = "/author.png"),
                            Thumbnail(href = "/author.png"),
                        )
                    )
                }
            )
        )
}

fun authorBooksCatalog(request: ServerRequest): ServerResponse = run {

    val id = request.pathVariable("id").toInt()

    val author = authorsRep.findByIdOrNull(id)
    val bookEntries = booksRep.findByAuthorsIdOrderBySequenceAscSequenceNumber(id)
        .map { it.toEntry() }
        .toList()

    ok()
        .contentType(APPLICATION_XML)
        .body(
            Feed(
                id = "tag:authors:$id",
                title = "Все книги автора ${author?.toString()}",
                links = if (author?.sequences?.isNotEmpty() == true)
                    listOf(Acquisition(rel = "http://opds-spec.org/facet", href = "$AUTHORS_CAT/$id/sequences", title = "По сериям", facetGroup = "Серии", activeFacet = true))
                else
                    emptyList(),
                entries = bookEntries
            )
        )
}

fun authorSequencesCatalog(request: ServerRequest): ServerResponse = run {

    val id = request.pathVariable("id").toInt()

    val author = authorsRep.findByIdOrNull(id)

    val sequencesEntries = author?.sequences.orEmpty().map {
        Entry(
            id = "tag:sequences:${it.id}",
            title = it.name,
            updated = it.updated,
            links = listOf(
                Navigation(rel = "subsection", href = "$AUTHORS_CAT/$id/sequences/${it.id}/books"),
                Navigation(rel = "up", href = "$AUTHORS_CAT/$id/sequences"),
            )
        )
    }

    ok()
        .contentType(APPLICATION_XML)
        .body(
            Feed(
                id = "tag:authors",
                title = "По сериям",
                links = listOf(
                    Acquisition(rel = "self", href = "$AUTHORS_CAT/$id/sequences"),
                    Navigation(rel = "start", href = ROOT_CAT),
                    Navigation(rel = "up", href = AUTHORS_CAT),
                ),
                entries = sequencesEntries
            )
        )
}

fun authorSequenceBooksCatalog(request: ServerRequest): ServerResponse = run {

    val id = request.pathVariable("sequenceId").toInt()

    ok()
        .contentType(APPLICATION_XML)
        .body(
            Feed(
                id = "tag:sequences:$id",
                title = "Все книги серии",
                links = listOf(),
                entries = booksRep.findBySequenceIdOrderBySequenceNumber(id)
                    .map { it.toEntry(includeSequenceNumber = true) }
                    .toList()
            )
        )
}


fun genresCatalog(@Suppress("UNUSED_PARAMETER") request: ServerRequest): ServerResponse = run {

    ok()
        .contentType(APPLICATION_XML)
        .body(
            Feed(
                id = "tag:genres",
                title = "По жанрам",
                links = listOf(
                    Acquisition(rel = "self", href = GENRES_CAT),
                    Navigation(rel = "start", href = ROOT_CAT),
                    Navigation(rel = "up", href = ROOT_CAT),
                ),
                entries = Repositories.genresRep.findByOrderByName().map {
                    Entry(
                        id = "tag:genres:${it.id}",
                        title = it.name,
                        updated = it.updated,
                        links = listOf(
                            Navigation(rel = "subsection", href = "$GENRES_CAT/${it.id}/books"),
                            ImageThumbnail(href = "/genre.png"),
                            Thumbnail(href = "/genre.png"),
                        )
                    )
                }
            )
        )
}

fun genreBooksCatalog(request: ServerRequest): ServerResponse = run {

    val id = request.pathVariable("id").toInt()

    ok()
        .contentType(APPLICATION_XML)
        .body(
            Feed(
                id = "tag:genre:$id",
                title = "genre $id",
                links = listOf(),
                entries = booksRep.findByGenresIdOrderBySequenceAscSequenceNumber(id)
                    .map { it.toEntry() }
                    .toList()
            )
        )
}


private fun Book.toEntry(includeSequenceNumber: Boolean = false, sequenceNumberForRecent: Boolean = false) = Entry(
    id = "tag:books:$id",
    title = when {
        includeSequenceNumber && sequenceNumber != null -> {
            when {
                sequenceNumberForRecent -> "$title [$sequenceNumber]"
                else -> "$sequenceNumber. $title"
            }
        }
        else -> title
    },
    updated = updated,
    content = Content(content).toPlainText(),
    summary = Summary(summary, summaryContentType).toPlainText(),
    authors = authors.map { Author(name = it.toString(), uri = "http://opds-spec.org/authors/$id") },
    categories = genres.map { Category(term = it.name, scheme = null) },
    rights = rights,
    language = language,
    issued = issued,
    publisher = publisher,
    sources = listOf(),
    links = listOf(
        *authors.map { Navigation(rel = "related", href = "$AUTHORS_CAT/${it.id}/books", title = "Все книги автора $it") }.toTypedArray(),
        *coverContentType?.let {
            listOf(
                Link(rel = "http://opds-spec.org/image", href = "/api/books/$id/image", type = coverContentType),
                Link(rel = "http://opds-spec.org/image/thumbnail", href = "/api/books/$id/image/thumbnail", type = coverContentType),
            )
        }.orEmpty().toTypedArray(),
        *files.map { Link(rel = "http://opds-spec.org/acquisition/open-access", href = "/api/books/$id/files/${it.id}", type = it.type.contentType, title = it.type.contentType) }.toTypedArray()
    )
)

private fun Content.toPlainText() = Content(content?.replace("<[^>]*>".toRegex(), ""), "text") // todo need to disable jackson html escaping
private fun Summary.toPlainText() = Summary(content?.replace("<[^>]*>".toRegex(), ""), "text") // todo need to disable jackson html escaping

private const val ROOT_CAT = "/catalog"
private const val RECOMMENDED_CAT = "$ROOT_CAT/recommended"
private const val UNREAD_CAT = "$ROOT_CAT/unread"
private const val RECENT_CAT = "$ROOT_CAT/recent"
private const val AUTHORS_CAT = "$ROOT_CAT/authors"
private const val GENRES_CAT = "$ROOT_CAT/genres"
private const val UNVERIFIED_CAT = "$ROOT_CAT/unverified"
