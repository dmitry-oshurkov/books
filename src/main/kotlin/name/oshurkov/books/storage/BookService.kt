package name.oshurkov.books.storage

import name.oshurkov.books.*
import name.oshurkov.books.fb2.parser.*
import name.oshurkov.books.storage.BookExt.*
import org.apache.tomcat.util.codec.binary.*
import org.slf4j.*
import org.springframework.beans.factory.annotation.*
import org.springframework.stereotype.*
import java.io.*
import javax.xml.namespace.*

@Component
class BookService {

    fun import(arrayOfFiles: Array<out File>, bookRootDir: File, preprocessFile: (String, Set<Author>, Sequence?, Int?, BookExt, File) -> File) = run {

        if (arrayOfFiles.isEmpty())
            return@run

        val files = arrayOfFiles
            .groupBy {
                when {
                    it.extension == "fb2" -> FB2
                    it.name.endsWith(".fb2.zip") -> FBZ
                    it.extension == "epub" -> EPUB
                    else -> UNSUPPORTED
                }
            }

        val fb2 = fictionBookService.parse(files)
        val epub = epubService.parse(files)

        val authors = authors(fb2, epub)
        val genres = genres(fb2, epub)
        val sequences = sequences(fb2)

        val fb2Books = fb2
            .map { (fb, f, ext) ->

                val bookAuthors = fb.description.titleInfo.authors
                    .mapNotNull { authors.find { a -> a.firstName == it.firstName && a.middleName == it.middleName && a.lastName == it.lastName } }
                    .toSet()

                val bookGenres = fb.description.titleInfo.genres
                    .map { fb2GenreToString(it) }
                    .mapNotNull { genres.find { g -> g.name == it } }
                    .toSet()

                val summary = fb.description.titleInfo.annotation?.annotations?.firstOrNull()?.text
                val binary = fb.binaries[fb.description.titleInfo.coverPage.firstOrNull()?.value?.trimStart('#')]
                val bookSequence = sequences.find { g -> g.name == fb.description.titleInfo.sequence?.name }
                val sequenceNumber = fb.description.titleInfo.sequence?.number?.toInt()

                val file = preprocessFile(fb.title, bookAuthors, bookSequence, sequenceNumber, ext, f)

                Book(
                    title = fb.title,
                    content = null,
                    summary = summary,
                    summaryContentType = summaryType(summary),
                    rights = null,
                    language = lang(fb.lang),
                    issued = fb.description.titleInfo.date,
                    publisher = null,
                    cover = binary?.binary?.let { Base64.decodeBase64(it) },
                    coverContentType = binary?.contentType,
                    file = file.relativeTo(bookRootDir).path,
                    fileContentType = fileType(ext),
                    sequence = bookSequence,
                    sequenceNumber = sequenceNumber,
                    authors = bookAuthors,
                    genres = bookGenres
                )
            }

        val epubBooks = epub
            .map { (ep, f, ext) ->

                val bookAuthors = ep.metadata.authors
                    .mapNotNull { authors.find { a -> a.firstName == it.firstname && a.lastName == it.lastname } }
                    .toSet()

                val bookGenres = ep.metadata.subjects
                    .mapNotNull { genres.find { g -> g.name == it } }
                    .toSet()

                val summary = ep.metadata.descriptions.firstOrNull()
                val file = preprocessFile(ep.metadata.titles[0], bookAuthors, null, null, ext, f)

                Book(
                    title = ep.metadata.titles[0],
                    content = ep.metadata.otherProperties[QName("se:long-description")],
                    summary = summary,
                    summaryContentType = summaryType(summary),
                    rights = ep.metadata.rights.firstOrNull(),
                    language = lang(ep.metadata.language),
                    issued = ep.metadata.dates.firstOrNull()?.value,
                    publisher = ep.metadata.publishers.firstOrNull(),
                    cover = ep.coverImage?.data,
                    coverContentType = ep.coverImage?.mediaType?.name,
                    file = file.relativeTo(bookRootDir).path,
                    fileContentType = fileType(ext),
                    sequence = null,
                    sequenceNumber = null,
                    authors = bookAuthors,
                    genres = bookGenres
                )
            }

        bookRepository.saveAll(fb2Books + epubBooks).onEach {
            log.info("Imported: ${it.title} [${it.file}]")
        }
    }


    private fun sequences(fb2: List<Triple<FictionBook, File, BookExt>>) = run {

        val fb2Sequences = fb2
            .mapNotNull { (fb, _, _) -> fb.description.titleInfo.sequence?.name }

        val epubSequences = emptyList<String>()

        val entities = (
            sequenceRepository.findAll() + (fb2Sequences + epubSequences)
                .map { Sequence(name = it) }
            )
            .distinctBy { it.name }

        sequenceRepository.saveAll(entities)
    }

    private fun genres(fb2: List<Triple<FictionBook, File, BookExt>>, epub: List<Triple<nl.siegmann.epublib.domain.Book, File, BookExt>>) = run {

        val fb2Genres = fb2
            .flatMap { (fb, _, _) -> fb.description.titleInfo.genres }
            .map { fb2GenreToString(it) }

        val epubGenres = epub.flatMap { (ep, _, _) -> ep.metadata.subjects }

        val entities = (
            genreRepository.findAll() + (fb2Genres + epubGenres)
                .map { Genre(name = it) }
            )
            .distinctBy { it.name }

        genreRepository.saveAll(entities)
    }

    private fun fb2GenreToString(code: String) = when (code) {
        "sf_history" -> "Альтернативная история"
        "sf_action" -> "Боевая фантастика"
        "sf_epic" -> "Эпическая фантастика"
        "sf_heroic" -> "Героическая фантастика"
        "sf_detective" -> "Детективная фантастика"
        "sf_cyberpunk" -> "Киберпанк"
        "sf_space" -> "Космическая фантастика"
        "sf_social" -> "Социально-психологическая фантастика"
        "sf_horror" -> "Ужасы и мистика"
        "sf_humor" -> "Юмористическая фантастика"
        "sf_fantasy" -> "Фэнтези"
        "sf" -> "Научная фантастика"
        "det_classic" -> "Классический детектив"
        "det_police" -> "Полицейский детектив"
        "det_action" -> "Боевик"
        "det_irony" -> "Иронический детектив"
        "det_history" -> "Исторический детектив"
        "det_espionage" -> "Шпионский детектив"
        "det_crime" -> "Криминальный детектив"
        "det_political" -> "Политический детектив"
        "det_maniac" -> "Маньяки"
        "det_hard" -> "Крутой детектив"
        "thriller" -> "Триллер"
        "detective" -> "Детектив (не относящийся в прочие категории)"
        "prose_classic" -> "Классическая проза"
        "prose_history" -> "Историческая проза"
        "prose_contemporary" -> "Современная проза"
        "prose_counter" -> "Контркультура"
        "prose_rus_classic" -> "Русская классическая проза"
        "prose_su_classics" -> "Советская классическая проза"
        "love_contemporary" -> "Современные любовные романы"
        "love_history" -> "Исторические любовные романы"
        "love_detective" -> "Остросюжетные любовные романы"
        "love_short" -> "Короткие любовные романы"
        "love_erotica" -> "Эротика"
        "adv_western" -> "Вестерн"
        "adv_history" -> "Исторические приключения"
        "adv_indian" -> "Приключения про индейцев"
        "adv_maritime" -> "Морские приключения"
        "adv_geo" -> "Путешествия и география"
        "adv_animal" -> "Природа и животные"
        "adventure" -> "Прочие приключения (то, что не вошло в другие категории)"
        "child_tale" -> "Сказка"
        "child_verse" -> "Детские стихи"
        "child_prose" -> "Детскиая проза"
        "child_sf" -> "Детская фантастика"
        "child_det" -> "Детские остросюжетные"
        "child_adv" -> "Детские приключения"
        "child_education" -> "Детская образовательная литература"
        "children" -> "Прочая детская литература (то, что не вошло в другие категории)"
        "poetry" -> "Поэзия"
        "dramaturgy" -> "Драматургия"
        "antique_ant" -> "Античная литература"
        "antique_european" -> "Европейская старинная литература"
        "antique_russian" -> "Древнерусская литература"
        "antique_east" -> "Древневосточная литература"
        "antique_myths" -> "Мифы. Легенды. Эпос"
        "antique" -> "Прочая старинная литература (то, что не вошло в другие категории)"
        "sci_history" -> "История"
        "sci_psychology" -> "Психология"
        "sci_culture" -> "Культурология"
        "sci_religion" -> "Религиоведение"
        "sci_philosophy" -> "Философия"
        "sci_politics" -> "Политика"
        "sci_business" -> "Деловая литература"
        "sci_juris" -> "Юриспруденция"
        "sci_linguistic" -> "Языкознание"
        "sci_medicine" -> "Медицина"
        "sci_phys" -> "Физика"
        "sci_math" -> "Математика"
        "sci_chem" -> "Химия"
        "sci_biology" -> "Биология"
        "sci_tech" -> "Технические науки"
        "science" -> "Прочая научная литература (то, что не вошло в другие категории)"
        "comp_www" -> "Интернет"
        "comp_programming" -> "Программирование"
        "comp_hard" -> "Компьютерное \"железо\" (аппаратное обеспечение)"
        "comp_soft" -> "Программы"
        "comp_db" -> "Базы данных"
        "comp_osnet" -> "ОС и сети"
        "computers" -> "Прочая околокомпьтерная литература (то, что не вошло в другие категории)"
        "ref_encyc" -> "Энциклопедии"
        "ref_dict" -> "Словари"
        "ref_ref" -> "Справочники"
        "ref_guide" -> "Руководства"
        "reference" -> "Прочая справочная литература (то, что не вошло в другие категории)"
        "nonf_biography" -> "Биографии и мемуары"
        "nonf_publicism" -> "Публицистика"
        "nonf_criticism" -> "Критика"
        "design" -> "Искусство и дизайн"
        "nonfiction" -> "Прочая документальная литература (то, что не вошло в другие категории)"
        "religion_rel" -> "Религия"
        "religion_esoterics" -> "Эзотерика"
        "religion_self" -> "Самосовершенствование"
        "religion" -> "Прочая религионая литература (то, что не вошло в другие категории)"
        "humor_anecdote" -> "Анекдоты"
        "humor_prose" -> "Юмористическая проза"
        "humor_verse" -> "Юмористические стихи"
        "humor" -> "Прочий юмор (то, что не вошло в другие категории)"
        "home_cooking" -> "Кулинария"
        "home_pets" -> "Домашние животные"
        "home_crafts" -> "Хобби и ремесла"
        "home_entertain" -> "Развлечения"
        "home_health" -> "Здоровье"
        "home_garden" -> "Сад и огород"
        "home_diy" -> "Сделай сам"
        "home_sport" -> "Спорт"
        "home_sex" -> "Эротика, секс"
        "home" -> "Прочее домоводство (то, что не вошло в другие категории)"
        else -> "Прочее"
    }

    private fun authors(fb2: List<Triple<FictionBook, File, BookExt>>, epub: List<Triple<nl.siegmann.epublib.domain.Book, File, BookExt>>) = run {

        val fb2Authors = fb2
            .flatMap { (fb, _, _) -> fb.description.titleInfo.authors }
            .map { it.firstName to it.middleName and (it.lastName ?: "?") }

        val epubAuthors = epub
            .flatMap { (ep, _, _) -> ep.metadata.authors }
            .map { it.firstname to null and it.lastname }

        val entities = (
            authorRepository.findAll() + (fb2Authors + epubAuthors)
                .map { (firstName, middleName, lastName) ->
                    Author(
                        firstName = firstName?.trim()?.ifEmpty { null },
                        middleName = middleName?.trim(),
                        lastName = lastName.trim(),
                    )
                }
            )
            .distinctBy { listOf(it.lastName, it.middleName, it.firstName) }

        authorRepository.saveAll(entities)
    }

    private fun summaryType(value: String?) = if (value?.contains("</p>") == true)
        "text/html"
    else
        "text"

    private fun fileType(ext: BookExt) = when (ext) {
        FB2 -> "application/fb2"
        FBZ -> "application/fb2+zip"
        EPUB -> "application/epub+zip"
        UNSUPPORTED -> ""
    }

    private fun lang(value: String) = when (value.toLowerCase()) {
        "ru" -> "ru-RU"
        "en" -> "en-US"
        else -> value
    }

    @Autowired
    private lateinit var fictionBookService: FictionBookService

    @Autowired
    private lateinit var epubService: EpubService

    @Autowired
    private lateinit var bookRepository: BookRepository

    @Autowired
    private lateinit var authorRepository: AuthorRepository

    @Autowired
    private lateinit var genreRepository: GenreRepository

    @Autowired
    private lateinit var sequenceRepository: SequenceRepository

    private val log = LoggerFactory.getLogger(BookService::class.java)
}
