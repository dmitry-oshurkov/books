package name.oshurkov.books.catalog

import name.oshurkov.books.author.*
import name.oshurkov.books.book.*
import name.oshurkov.books.file.*
import name.oshurkov.books.genre.*
import name.oshurkov.books.sequence.*
import java.time.*


fun root() = Feed(
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
            updated = OffsetDateTime.now(),
            content = Content("Непрочитанные книги"),
            links = linksAcqImgThumb(subsection = UNREAD_CAT, img = "/abstract.png")
        ),
        Entry(
            id = "tag:root:recommended",
            title = "Рекомендуемые",
            updated = OffsetDateTime.now(),
            content = Content("Рекомендуемые книги"),
            links = linksAcqImgThumb(subsection = RECOMMENDED_CAT, img = "/free.png")
        ),
        Entry(
            id = "tag:root:recent",
            title = "Недавно добавленные",
            updated = OffsetDateTime.now(),
            content = Content("Недавно добавленные книги"),
            links = linksAcqImgThumb(subsection = RECENT_CAT, img = "/abstract.png")
        ),
        Entry(
            id = "tag:root:authors",
            title = "По авторам",
            updated = OffsetDateTime.now(),
            content = Content("Поиск книг по авторам"),
            links = linksNavImgThumb(subsection = AUTHORS_CAT, img = "/author.png")
        ),
        Entry(
            id = "tag:root:genre",
            title = "По жанрам",
            updated = OffsetDateTime.now(),
            content = Content("Поиск книг по жанрам"),
            links = linksNavImgThumb(subsection = GENRES_CAT, img = "/genre.png")
        ),
        Entry(
            id = "tag:root:unverified",
            title = "Непроверенные",
            updated = OffsetDateTime.now(),
            content = Content("Непроверенные книги"),
            links = linksAcqImgThumb(subsection = UNVERIFIED_CAT, img = "/abstract.png")
        ),
    )
)


fun recommended() = Feed(
    id = "tag:recommended",
    title = "Рекомендуемые книги",
    links = linksAcqNavNav(self = RECOMMENDED_CAT, up = ROOT_CAT),
    entries = bookEntries(::selectRecommendedBooks)
)


fun unread() = Feed(
    id = "tag:unread",
    title = "Непрочитанные книги",
    links = linksAcqNavNav(self = UNREAD_CAT, up = ROOT_CAT),
    entries = bookEntries(::selectUnreadBooks)
)


fun recent() = Feed(
    id = "tag:recent",
    title = "Недавно добавленные книги",
    links = linksAcqNavNav(self = RECENT_CAT, up = ROOT_CAT),
    entries = bookEntries(::selectRecentBooks, includeSequenceNumber = true, sequenceNumberForRecent = true)
)


fun unverified() = Feed(
    id = "tag:unverified",
    title = "Непроверенные книги",
    links = linksAcqNavNav(self = UNVERIFIED_CAT, up = ROOT_CAT),
    entries = bookEntries(::selectUnverifiedBooks)
)


fun authors() = Feed(
    id = "tag:authors",
    title = "По авторам",
    links = linksAcqNavNav(self = AUTHORS_CAT, up = ROOT_CAT),
    entries = selectAuthors().map {
        Entry(
            id = "tag:authors:${it.id}",
            title = it.toStringForList(),
            updated = it.updated,
            links = linksNavImgThumb(subsection = "$AUTHORS_CAT/${it.id}/books", img = "/author.png")
        )
    }
)


fun authorBooks(authorId: Int) = Feed(
    id = "tag:authors:$authorId:books",
    title = "Все книги автора ${selectAuthor(authorId)?.toString()}",
    links = if (selectSequences(authorId).isNotEmpty())
        listOf(Acquisition(rel = "http://opds-spec.org/facet", href = "$AUTHORS_CAT/$authorId/sequences", title = "По сериям", facetGroup = "Серии", activeFacet = true))
    else
        emptyList(),
    entries = bookEntries({ selectAuthorBooks(authorId) })
)


fun authorSequences(authorId: Int) = Feed(
    id = "tag:authors",
    title = "По сериям",
    links = linksAcqNavNav(self = "$AUTHORS_CAT/$authorId/sequences", up = AUTHORS_CAT),
    entries = selectSequences(authorId).map {
        Entry(
            id = "tag:sequences:${it.id}",
            title = it.name,
            updated = it.updated,
            links = listOf(
                Navigation(rel = "subsection", href = "$SEQUENCES_CAT/${it.id}/books"),
                Navigation(rel = "up", href = "$AUTHORS_CAT/$authorId/sequences"),
            )
        )
    }
)


fun sequenceBooks(id: Int) = Feed(
    id = "tag:sequences:$id",
    title = "Все книги серии",
    links = listOf(),
    entries = bookEntries({ selectSequenceBooks(id) })
)


fun genres() = Feed(
    id = "tag:genres",
    title = "По жанрам",
    links = linksAcqNavNav(self = GENRES_CAT, up = ROOT_CAT),
    entries = selectGenres().map {
        Entry(
            id = "tag:genres:${it.id}",
            title = it.name,
            updated = it.updated,
            links = linksNavImgThumb(subsection = "$GENRES_CAT/${it.id}/books", img = "/genre.png")
        )
    }
)


fun genreBooks(genreId: Int) = Feed(
    id = "tag:genre:$genreId",
    title = "Все книги жанра",
    links = listOf(),
    entries = bookEntries({ selectGenreBooks(genreId) })
)


private fun linksAcqNavNav(self: String, up: String) = listOf(
    Acquisition(rel = "self", href = self),
    Navigation(rel = "start", href = ROOT_CAT),
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
    authors: List<name.oshurkov.books.author.Author>,
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
    content = Content(content).toPlainText(),
    summary = Summary(summary, summaryContentType).toPlainText(),
    authors = authors.map { Author(name = it.toString(), uri = "http://opds-spec.org/authors/${it.id}") },
    categories = genres.map { Category(term = it, scheme = null) } +
        listOf(
            Category(term = "Рекомендовано: ${recommended.asRus()}", scheme = null),
            Category(term = "Не прочитано: ${unread.asRus()}", scheme = null),
            Category(term = "Не проверено: ${verified.not().asRus()}", scheme = null)
        ),
    rights = rights,
    language = language,
    issued = issued,
    publisher = publisher,
    sources = emptyList(),
    links = listOf(
        *authors.map { Navigation(rel = "related", href = "$AUTHORS_CAT/${it.id}/books", title = "Все книги автора $it") }.toTypedArray(),
        *coverContentType?.let {
            listOf(
                Link(rel = "http://opds-spec.org/image", href = "/books/$id/image", type = coverContentType),
                Link(rel = "http://opds-spec.org/image/thumbnail", href = "/books/$id/image/thumbnail", type = coverContentType),
            )
        }.orEmpty().toTypedArray(),
        *files.map { Link(rel = "http://opds-spec.org/acquisition/open-access", href = "/books/files/${it.id}", type = it.type.contentType, title = it.type.contentType) }.toTypedArray()
    )
)


private fun Content.toPlainText() = Content(content?.replace("<[^>]*>".toRegex(), ""), "text") // todo need to disable jackson html escaping
private fun Summary.toPlainText() = Summary(content?.replace("<[^>]*>".toRegex(), ""), "text") // todo need to disable jackson html escaping

private fun Boolean.asRus() = if (this) "да" else "нет"

private const val ROOT_CAT = "/catalog"
private const val RECOMMENDED_CAT = "$ROOT_CAT/recommended"
private const val UNREAD_CAT = "$ROOT_CAT/unread"
private const val RECENT_CAT = "$ROOT_CAT/recent"
private const val AUTHORS_CAT = "$ROOT_CAT/authors"
private const val SEQUENCES_CAT = "$ROOT_CAT/sequences"
private const val GENRES_CAT = "$ROOT_CAT/genres"
private const val UNVERIFIED_CAT = "$ROOT_CAT/unverified"
