package name.oshurkov.books

import name.oshurkov.books.api.author.*
import name.oshurkov.books.api.book.*
import name.oshurkov.books.api.file.*
import name.oshurkov.books.api.genre.*
import name.oshurkov.books.api.sequence.*
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

        lateinit var bookFilesRep: BookFileRepository
            @Autowired set
    }
}
