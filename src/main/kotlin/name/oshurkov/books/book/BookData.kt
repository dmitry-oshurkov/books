package name.oshurkov.books.book

import name.oshurkov.books.*
import name.oshurkov.books.author.*
import name.oshurkov.books.core.*
import name.oshurkov.books.core.data.*
import name.oshurkov.books.genre.*
import org.ktorm.dsl.*
import org.ktorm.schema.*


object Books : BksTable("book") {
    val title = varchar("title")
    val summary = varchar("summary")
    val recommended = boolean("recommended")
    val verified = boolean("verified")
    val unread = boolean("unread")
    val content = varchar("content") // todo unused
    val cover = bytes("cover")
    val cover_content_type = varchar("cover_content_type")
    val hash = uuid("hash")
    val issued = varchar("issued")
    val language = varchar("language")
    val publisher = varchar("publisher")
    val rights = varchar("rights")
    val sequence_id = int("sequence_id")
    val sequence_number = int("sequence_number")
    val summary_content_type = varchar("summary_content_type")
}


fun selectBooks() = Books.select(db, transform = ::book)


fun selectBook(id: Int) = Books.find(id, db, transform = ::book)


fun deleteBook(id: Int) = db.delete(Books) { it.id eq id }


fun selectRecommendedBooks() = db
    .from(Books)
    .select(Books.columns)
    .where { Books.recommended eq true }
    .map(::book)


fun selectUnreadBooks() = db
    .from(Books)
    .select(Books.columns)
    .where { Books.unread eq true }
    .orderBy(Books.sequence_id.asc(), Books.sequence_number.asc()) // todo order by author
    .map(::book)


fun selectRecentBooks() = db
    .from(Books)
    .select(Books.columns)
    .limit(10)
    .orderBy(Books.updated.desc())
    .map(::book)


fun selectUnverifiedBooks() = db
    .from(Books)
    .select(Books.columns)
    .where { Books.verified eq false } // todo order by author
    .map(::book)


fun selectAuthorBooks(authorId: Int) = db
    .from(Books)
    .innerJoin(BookAuthors, on = BookAuthors.book_id eq Books.id)
    .select(Books.columns)
    .where { BookAuthors.authors_id eq authorId }
    .orderBy(Books.sequence_id.asc(), Books.sequence_number.asc())
    .map(::book)


fun selectSequenceBooks(sequenceId: Int) = db
    .from(Books)
    .innerJoin(BookAuthors, on = BookAuthors.book_id eq Books.id)
    .innerJoin(AuthorSequences, on = AuthorSequences.author_id eq BookAuthors.authors_id)
    .select(Books.columns)
    .where { AuthorSequences.sequences_id eq sequenceId }
    .orderBy(Books.sequence_number.asc())
    .map(::book)


fun selectGenreBooks(genreId: Int) = db
    .from(Books)
    .innerJoin(BookGenres, on = BookGenres.book_id eq Books.id)
    .select(Books.columns)
    .where { BookGenres.genres_id eq genreId }
    .orderBy(Books.sequence_id.asc(), Books.sequence_number.asc())
    .map(::book)


private fun book(row: QueryRowSet) = Book(
    id = row[Books.id]!!,
    updated = row[Books.updated]!!.atMoscowOffset(),
    content = row[Books.content],
    cover = row[Books.cover],
    coverContentType = row[Books.cover_content_type],
    hash = row[Books.hash]!!,
    issued = row[Books.issued],
    language = row[Books.language],
    publisher = row[Books.publisher],
    recommended = row[Books.recommended]!!,
    rights = row[Books.rights],
    sequenceId = row[Books.sequence_id],
    sequenceNumber = row[Books.sequence_number],
    summary = row[Books.summary],
    summaryContentType = row[Books.summary_content_type],
    title = row[Books.title]!!,
    unread = row[Books.unread]!!,
    verified = row[Books.verified]!!,
)
