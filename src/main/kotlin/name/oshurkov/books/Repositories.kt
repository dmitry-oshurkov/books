package name.oshurkov.books

import name.oshurkov.books.api.author.*
import name.oshurkov.books.api.book.*
import name.oshurkov.books.api.file.*
import name.oshurkov.books.api.genre.*
import name.oshurkov.books.api.sequence.*
import org.springframework.beans.factory.annotation.*
import org.springframework.stereotype.*

@Component
class Repositories {

    @Component
    companion object {

        @JvmStatic
        lateinit var booksRep: BookRepository
            @Autowired set

        @JvmStatic
        lateinit var authorsRep: AuthorRepository
            @Autowired set

        @JvmStatic
        lateinit var genresRep: GenreRepository
            @Autowired set

        @JvmStatic
        lateinit var sequencesRep: SequenceRepository
            @Autowired set

        @JvmStatic
        lateinit var bookFilesRep: BookFileRepository
            @Autowired set
    }
}
