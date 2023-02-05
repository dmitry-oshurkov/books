package name.oshurkov.books.catalog

import name.oshurkov.books.author.*
import name.oshurkov.books.book.*
import name.oshurkov.books.catalog.CatalogPath.Companion.authors
import name.oshurkov.books.catalog.CatalogPath.Companion.catalog
import name.oshurkov.books.catalog.CatalogPath.Companion.genres
import name.oshurkov.books.catalog.CatalogPath.Companion.recent
import name.oshurkov.books.catalog.CatalogPath.Companion.recommended
import name.oshurkov.books.catalog.CatalogPath.Companion.sequences
import name.oshurkov.books.catalog.CatalogPath.Companion.unread
import name.oshurkov.books.catalog.CatalogPath.Companion.unverified
import name.oshurkov.books.file.*
import name.oshurkov.books.genre.*
import name.oshurkov.books.sequence.*
import java.time.*


fun reedRoot() = Feed(
    id = "tag:root",
    title = "Книжный каталог",
    links = listOf(
        Navigation(rel = "self", href = catalog.path),
        Navigation(rel = "start", href = catalog.path),
    ),
    entries = listOf(
        Entry(
            id = "tag:root:unread",
            title = "Непрочитанные",
            updated = OffsetDateTime.now(),
            content = Content("Непрочитанные книги"),
            links = linksAcqImgThumb(subsection = unread.path, img = "/abstract.png")
        ),
        Entry(
            id = "tag:root:recommended",
            title = "Рекомендуемые",
            updated = OffsetDateTime.now(),
            content = Content("Рекомендуемые книги"),
            links = linksAcqImgThumb(subsection = recommended.path, img = "/free.png")
        ),
        Entry(
            id = "tag:root:recent",
            title = "Недавно добавленные",
            updated = OffsetDateTime.now(),
            content = Content("Недавно добавленные книги"),
            links = linksAcqImgThumb(subsection = recent.path, img = "/abstract.png")
        ),
        Entry(
            id = "tag:root:authors",
            title = "По авторам",
            updated = OffsetDateTime.now(),
            content = Content("Поиск книг по авторам"),
            links = linksNavImgThumb(subsection = authors.path, img = "/author.png")
        ),
        Entry(
            id = "tag:root:genre",
            title = "По жанрам",
            updated = OffsetDateTime.now(),
            content = Content("Поиск книг по жанрам"),
            links = linksNavImgThumb(subsection = genres.path, img = "/genre.png")
        ),
        Entry(
            id = "tag:root:unverified",
            title = "Непроверенные",
            updated = OffsetDateTime.now(),
            content = Content("Непроверенные книги"),
            links = linksAcqImgThumb(subsection = unverified.path, img = "/abstract.png")
        ),
    )
)


fun reedRecommended() = Feed(
    id = "tag:recommended",
    title = "Рекомендуемые книги",
    links = linksAcqNavNav(self = recommended.path, up = catalog.path),
    entries = bookEntries(::selectRecommendedBooks)
)


fun reedUnread() = Feed(
    id = "tag:unread",
    title = "Непрочитанные книги",
    links = linksAcqNavNav(self = unread.path, up = catalog.path),
    entries = bookEntries(::selectUnreadBooks)
)


fun reedRecent() = Feed(
    id = "tag:recent",
    title = "Недавно добавленные книги",
    links = linksAcqNavNav(self = recent.path, up = catalog.path),
    entries = bookEntries(::selectRecentBooks, includeSequenceNumber = true, sequenceNumberForRecent = true)
)


fun reedUnverified() = Feed(
    id = "tag:unverified",
    title = "Непроверенные книги",
    links = linksAcqNavNav(self = unverified.path, up = catalog.path),
    entries = bookEntries(::selectUnverifiedBooks)
)


fun reedAuthors() = Feed(
    id = "tag:authors",
    title = "По авторам",
    links = linksAcqNavNav(self = authors.path, up = catalog.path),
    entries = selectAuthors().map {
        Entry(
            id = "tag:authors:${it.id}",
            title = it.toStringForList(),
            updated = it.updated,
            links = linksNavImgThumb(subsection = "${authors.path}/${it.id}/books", img = "/author.png")
        )
    }
)


fun reedAuthorBooks(authorId: Int) = Feed(
    id = "tag:authors:$authorId:books",
    title = "Все книги автора ${selectAuthor(authorId)?.toString()}",
    links = if (selectSequences(authorId).isNotEmpty())
        listOf(Acquisition(rel = "http://opds-spec.org/facet", href = "${authors.path}/$authorId/sequences", title = "По сериям", facetGroup = "Серии", activeFacet = true))
    else
        emptyList(),
    entries = bookEntries({ selectAuthorBooks(authorId) })
)


fun reedAuthorSequences(authorId: Int) = Feed(
    id = "tag:authors",
    title = "По сериям",
    links = linksAcqNavNav(self = "${authors.path}/$authorId/sequences", up = authors.path),
    entries = selectSequences(authorId).map {
        Entry(
            id = "tag:sequences:${it.id}",
            title = it.name,
            updated = it.updated,
            links = listOf(
                Navigation(rel = "subsection", href = "${sequences.path}/${it.id}/books"),
                Navigation(rel = "up", href = "${authors.path}/$authorId/sequences"),
            )
        )
    }
)


fun reedSequenceBooks(id: Int) = Feed(
    id = "tag:sequences:$id",
    title = "Все книги серии",
    links = listOf(),
    entries = bookEntries({ selectSequenceBooks(id) })
)


fun reedGenres() = Feed(
    id = "tag:genres",
    title = "По жанрам",
    links = linksAcqNavNav(self = genres.path, up = catalog.path),
    entries = selectGenres().map {
        Entry(
            id = "tag:genres:${it.id}",
            title = it.name,
            updated = it.updated,
            links = linksNavImgThumb(subsection = "${genres.path}/${it.id}/books", img = "/genre.png")
        )
    }
)


fun reedGenreBooks(genreId: Int) = Feed(
    id = "tag:genre:$genreId",
    title = "Все книги жанра",
    links = listOf(),
    entries = bookEntries({ selectGenreBooks(genreId) })
)


private fun linksAcqNavNav(self: String, up: String) = listOf(
    Acquisition(rel = "self", href = self),
    Navigation(rel = "start", href = catalog.path),
    Navigation(rel = "up", href = up),
)


private fun linksNavImgThumb(subsection: String, img: String) = listOf(
    Navigation(rel = "subsection", href = subsection),
    ImageThumbnail(href = img),
    Thumbnail(href = img),
)


private fun linksAcqImgThumb(subsection: String, img: String) = listOf(
    Acquisition(rel = "subsection", href = subsection),
    ImageThumbnail(href = img),
    Thumbnail(href = img),
)


private fun bookEntries(
    books: () -> List<Book>,
    includeSequenceNumber: Boolean = false,
    sequenceNumberForRecent: Boolean = false
) = run {

    val bookAuthors = selectBookAuthors()
    val bookGenres = selectBookGenres()
    val bookFiles = selectBookFiles()

    books().map {
        val authors = bookAuthors[it.id].orEmpty()
        val genres = bookGenres[it.id].orEmpty()
        val files = bookFiles[it.id].orEmpty()
        it.toEntry(authors, genres, files, includeSequenceNumber, sequenceNumberForRecent)
    }
}


private fun Book.toEntry(
    bookAuthors: List<name.oshurkov.books.author.Author>,
    genres: List<String>,
    files: List<BookFile>,
    includeSequenceNumber: Boolean = false,
    sequenceNumberForRecent: Boolean = false
) = Entry(
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
    content = null,
    summary = Summary(summary, summaryContentType).toPlainText(),
    authors = bookAuthors.map { Author(name = it.toString(), uri = "http://opds-spec.org/authors/${it.id}") },
    categories = genres.map { Category(term = it, scheme = null) } +
        listOf(
            Category(term = "Рекомендовано: ${recommended.asRus()}", scheme = null),
            Category(term = "Не прочитано: ${unread.asRus()}", scheme = null),
            Category(term = "Не проверено: ${verified.not().asRus()}", scheme = null)
        ),
    rights = null,
    language = language,
    issued = issued,
    publisher = publisher,
    sources = emptyList(),
    links = listOf(
        *bookAuthors.map { Navigation(rel = "related", href = "${authors.path}/${it.id}/books", title = "Все книги автора $it") }.toTypedArray(),
        *coverContentType?.let {
            listOf(
                Link(rel = "http://opds-spec.org/image", href = "/books/$id/image", type = coverContentType),
                Link(rel = "http://opds-spec.org/image/thumbnail", href = "/books/$id/image/thumbnail", type = coverContentType),
            )
        }.orEmpty().toTypedArray(),
        *files.map { Link(rel = "http://opds-spec.org/acquisition/open-access", href = "/books/files/${it.id}", type = it.type.contentType, title = it.type.contentType) }.toTypedArray()
    )
)


private fun Summary.toPlainText() = Summary(content?.replace("<[^>]*>".toRegex(), ""), "text") // todo need to disable jackson html escaping

private fun Boolean.asRus() = if (this) "да" else "нет"
