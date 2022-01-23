package name.oshurkov.books

import name.oshurkov.books.author.*
import name.oshurkov.books.book.*
import name.oshurkov.books.genre.*
import name.oshurkov.books.sequence.*
import org.springframework.beans.factory.annotation.*
import org.springframework.stereotype.*

class Repositories {

    @Component
    companion object {

        lateinit var booksRep: BookRepository
            @Autowired set

        lateinit var authorsRep: AuthorRepository
            @Autowired set

        lateinit var genresRep: GenreRepository
            @Autowired set

        lateinit var sequencesRep: SequenceRepository
            @Autowired set

        lateinit var bookFilesRep: name.oshurkov.books.file.BookFileRepository
            @Autowired set
    }
}
