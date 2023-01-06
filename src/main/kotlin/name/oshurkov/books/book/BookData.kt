package name.oshurkov.books.book

import name.oshurkov.books.*
import name.oshurkov.books.author.*
import name.oshurkov.books.core.*
import name.oshurkov.books.core.data.*
import name.oshurkov.books.file.*
import name.oshurkov.books.genre.*
import name.oshurkov.books.sequence.*
import org.ktorm.dsl.*
import org.ktorm.schema.*
import org.ktorm.support.postgresql.*
import org.slf4j.*


object Books : BksTable("book") {
    val title = varchar("title")
    val summary = varchar("summary")
    val recommended = boolean("recommended")
    val verified = boolean("verified")
    val unread = boolean("unread")
    val cover = bytes("cover")
    val cover_content_type = varchar("cover_content_type")
    val hash = uuid("hash")
    val issued = varchar("issued")
    val language = varchar("language")
    val publisher = varchar("publisher")
    val sequence_id = int("sequence_id")
    val sequence_number = int("sequence_number")
    val summary_content_type = varchar("summary_content_type")
}


fun insertBooksMetadata(books: List<ImportedBook>) {

    val importedAuthors = books.flatMap { it.authors }.distinct()
    val importedGenres = books.flatMap { it.genres }.distinct()
    val importedSequences = books.mapNotNull { it.sequence }.distinct()


    db.bulkInsertOrUpdate(Authors) {
        importedAuthors.map { author ->
            item {
                set(it.last_name, author.lastName)
                set(it.first_name, author.firstName)
                set(it.middle_name, author.middleName)
            }
        }

        onConflict(it.last_name, it.first_name) { doNothing() }
    }

    db.bulkInsertOrUpdate(Genres) {
        importedGenres.map { genre ->
            item { set(it.name, genre) }
        }
        onConflict(it.name) { doNothing() }
    }

    db.bulkInsertOrUpdate(Sequences) {
        importedSequences.map { sequence ->
            item { set(it.name, sequence) }
        }
        onConflict(it.name) { doNothing() }
    }
}


fun insertBook(book: ImportedBook, authors: List<Author>, genres: List<Genre>, sequences: List<Sequence>) {

    try {
        db.useTransaction {

            val sequence = sequences.find { g -> g.name == book.sequence }

            val id = db.insertOrUpdateReturning(Books, Books.id) {
                set(it.title, book.title)
                set(it.summary, book.summary)
                set(it.recommended, book.recommended)
                set(it.verified, book.verified)
                set(it.unread, book.unread)
                set(it.cover, book.cover)
                set(it.cover_content_type, book.coverContentType)
                set(it.hash, book.hash)
                set(it.issued, book.issued)
                set(it.language, book.language)
                set(it.publisher, book.publisher)
                set(it.sequence_id, sequence?.id)
                set(it.sequence_number, book.sequenceNumber)
                set(it.summary_content_type, book.summaryContentType)
                onConflict(it.hash) { doNothing() }
            }


            if (id != null) {

                db.bulkInsert(BookAuthors) {
                    authors
                        .filter { author -> book.authors.singleOrNull { author.lastName == it.lastName && author.firstName == it.firstName } != null }
                        .onEach { author ->
                            if (sequence != null)
                                db.insertOrUpdate(AuthorSequences) {
                                    set(it.author_id, author.id)
                                    set(it.sequences_id, sequence.id)
                                    onConflict { doNothing() }
                                }
                        }
                        .map { author ->
                            item {
                                set(it.book_id, id)
                                set(it.authors_id, author.id)
                            }
                        }
                }


                db.bulkInsert(BookGenres) {
                    genres
                        .filter { genre -> book.genres.singleOrNull { genre.name == it } != null }
                        .map { genre ->
                            item {
                                set(it.book_id, id)
                                set(it.genres_id, genre.id)
                            }
                        }
                }


                db.bulkInsert(BookFiles) {
                    book.files.map { file ->
                        item {
                            set(it.book_id, id)
                            set(it.content, file.content)
                            set(it.hash, file.hash)
                            set(it.type, file.type)
                        }
                    }
                }

                log.info("Imported: ${book.title}")
            }
        }
    } catch (e: Exception) {
        log.error("Error import: ${book.title}; ${e.message}")
    }
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


fun selectBooksCount() = Books.count(db)


private fun book(row: QueryRowSet) = Book(
    id = row[Books.id]!!,
    updated = row[Books.updated]!!.atMoscowOffset(),
    cover = row[Books.cover],
    coverContentType = row[Books.cover_content_type],
    hash = row[Books.hash]!!,
    issued = row[Books.issued],
    language = row[Books.language],
    publisher = row[Books.publisher],
    recommended = row[Books.recommended]!!,
    sequenceId = row[Books.sequence_id],
    sequenceNumber = row[Books.sequence_number],
    summary = row[Books.summary],
    summaryContentType = row[Books.summary_content_type],
    title = row[Books.title]!!,
    unread = row[Books.unread]!!,
    verified = row[Books.verified]!!,
)


private val log by logger()
