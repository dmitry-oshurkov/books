package name.oshurkov.books.api.book

import name.oshurkov.books.*
import name.oshurkov.books.api.author.*
import name.oshurkov.books.api.file.*
import name.oshurkov.books.api.file.FileType.*
import name.oshurkov.books.api.file.fb2.parser.*
import name.oshurkov.books.api.genre.*
import name.oshurkov.books.api.sequence.*
import name.oshurkov.books.api.sequence.Sequence
import org.apache.commons.compress.archivers.sevenz.*
import org.apache.tomcat.util.codec.binary.Base64
import org.slf4j.*
import org.springframework.beans.factory.annotation.*
import org.springframework.stereotype.*
import java.io.*
import java.nio.*
import java.nio.file.*
import java.security.*
import java.text.*
import java.util.*
import java.util.Date
import java.util.zip.*
import java.util.zip.Deflater.*
import javax.xml.namespace.*

@Component
class BookService(
    val fictionBookService: Fb2Service,
    val epubService: EpubService,
    val bookRepository: BookRepository,
    val authorRepository: AuthorRepository,
    val genreRepository: GenreRepository,
    val sequenceRepository: SequenceRepository,
    val bookFileRepository: BookFileRepository
) {

    fun import(arrayOfFiles: Array<out File>, afterSaveFile: (File) -> Unit = {}) = run {

        if (arrayOfFiles.isEmpty())
            return@run

        val files = arrayOfFiles
            .groupBy {
                when {
                    it.extension == "fb2" -> FB2
                    it.name.endsWith(".fb2.zip") -> FBZ
                    it.extension == "epub" -> EPUB
                    else -> null
                }
            }

        val fb2 = fictionBookService.parse(files)
        val epub = epubService.parse(files)

        val authors = authors(fb2, epub)
        val genres = genres(fb2, epub)
        val sequences = sequences(fb2)

        val fb2Books = fb2
            .mapNotNull { (fb, file, type) ->
                runCatching {

                    val bookSequence = sequences.find { g -> g.name == fb.description.titleInfo.sequence?.name }

                    val bookAuthors = fb.description.titleInfo.authors
                        .mapNotNull { authors.find { a -> a.firstName == it.firstName && a.middleName == it.middleName && a.lastName == it.lastName } }
                        .onEach {
                            if (bookSequence != null && !it.sequences.contains(bookSequence))
                                it.sequences.add(bookSequence)
                        }
                        .toSet()

                    val bookGenres = fb.description.titleInfo.genres
                        .map { fb2GenreToString(it) }
                        .mapNotNull { genres.find { g -> g.name == it } }
                        .toSet()

                    val summary = fb.annotation?.annotations?.map { it.text }?.joinToString("\n")
                    val binary = fb.binaries[fb.description.titleInfo.coverPage.firstOrNull()?.value?.trimStart('#')]

                    val sequenceNumber = fb.description.titleInfo.sequence?.number?.toInt()

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
                        sequence = bookSequence,
                        sequenceNumber = sequenceNumber,
                        authors = bookAuthors,
                        genres = bookGenres
                    )
                        .also {
                            val bookFile = bookFile(it, file, type, fb.title, sequenceNumber)
                            it.files += bookFile
                        }
                }
                    .onSuccess {
                        afterSaveFile(file)
                        log.info("Parsed: [${file.absolutePath}]")
                    }
                    .onFailure {
                        afterSaveFile(file)
                        log.error("Error import: [${file.absolutePath}] - message: ${it.message}")
                    }
                    .getOrNull()
            }

        val epubBooks = epub
            .mapNotNull { (ep, file, type) ->
                runCatching {
                    val bookAuthors = ep.metadata.authors
                        .mapNotNull { authors.find { a -> a.firstName == it.firstname && a.lastName == it.lastname } }
                        .toSet()

                    val bookGenres = ep.metadata.subjects
                        .mapNotNull { genres.find { g -> g.name == it } }
                        .toSet()

                    val summary = ep.metadata.descriptions.firstOrNull()

                    Book(
                        title = ep.title,
                        content = ep.metadata.otherProperties[QName("se:long-description")],
                        summary = summary,
                        summaryContentType = summaryType(summary),
                        rights = ep.metadata.rights.firstOrNull(),
                        language = lang(ep.metadata.language),
                        issued = ep.metadata.dates.firstOrNull()?.value,
                        publisher = ep.metadata.publishers.firstOrNull(),
                        cover = ep.coverImage?.data,
                        coverContentType = ep.coverImage?.mediaType?.name,
                        sequence = null,
                        sequenceNumber = null,
                        authors = bookAuthors,
                        genres = bookGenres
                    )
                        .also {
                            val bookFile = bookFile(it, file, type, ep.title, null)
                            it.files += bookFile
                        }
                }
                    .onSuccess {
                        afterSaveFile(file)
                        log.info("Parsed: [${file.absolutePath}]")
                    }
                    .onFailure {
                        afterSaveFile(file)
                        log.error("Error import: [${file.absolutePath}] - message: ${it.message}")
                    }
                    .getOrNull()
            }

        genreRepository.saveAll(genres)
        sequenceRepository.saveAll(sequences)
        authorRepository.saveAll(authors)

        bookRepository.saveAll(fb2Books + epubBooks).onEach {
            bookFileRepository.saveAll(it.files)
            log.info("Imported: ${it.title}")
        }
    }

    fun export(targetDir: String) {

        val root = File(targetDir)

        if (root.exists())
            root.deleteRecursively()

        bookRepository.findAll().forEach {

            val authorsDir = it.authors.joinToString { a -> a.toStringForList() }

            val newFileDir = if (it.sequence != null && it.sequenceNumber != null)
                Path.of(root.absolutePath, authorsDir, it.sequence.name).toFile()
            else
                Path.of(root.absolutePath, authorsDir).toFile()

            newFileDir.mkdirs()

            it.files.forEach { f ->

                val baseName = "${it.title}.${f.type.extension}"

                val newFileName = if (it.sequence != null && it.sequenceNumber != null)
                    "[${it.sequenceNumber}] $baseName"
                else
                    baseName

                File(newFileDir, newFileName).writeBytes(f.content)
            }
        }
    }

    fun backup(targetDir: String) {

        val root = File(targetDir)
        val backupName = "books-${SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(Date())}"
        val exported = File(root, backupName)
        export(exported.absolutePath)

        createSevenZipFile(File(root, "$backupName.7z"), exported)
        exported.deleteRecursively()
    }


    private fun createSevenZipFile(sevenZ: File, folder: File) {

        runCatching {

            SevenZOutputFile(sevenZ).use { out ->

                Files.walk(folder.toPath())
                    .map { it.toFile() }
                    .filter { !it.isDirectory }
                    .forEach { file ->
                        FileInputStream(file).use {
                            val entry = out.createArchiveEntry(file, file.toRelativeString(folder))
                            out.putArchiveEntry(entry)
                            out.write(file.readBytes())
                            out.closeArchiveEntry()
                        }
                    }

                out.finish()
            }
        }
    }

    private fun bookFile(book: Book, file: File, type: FileType, title: String, seqNo: Int?) = run {

        val (content, hash) = when (type) {

            FBZ -> {

                val bytes = ZipFile(file).use {
                    val entry = it.entries().toList().first()
                    it.getInputStream(entry).use { stream -> stream.readAllBytes() }
                }

                zip(seqNo, title, bytes) to uuid(bytes)
            }

            FB2 -> if (forceCompress)
                file.readBytes().let { zip(seqNo, title, it) to uuid(it) }
            else
                file.readBytes().let { it to uuid(it) }

            else -> file.readBytes().let { it to uuid(it) }
        }

        val newType = if (type == FB2 && forceCompress)
            FBZ
        else
            type

        BookFile(book, content, hash, newType)
    }

    private fun uuid(bytes: ByteArray) = run {
        val digest = MessageDigest.getInstance("MD5").digest(bytes)
        val bb = ByteBuffer.wrap(digest)
        UUID(bb.long, bb.long)
    }

    private fun zip(seqNo: Int?, title: String, bytes: ByteArray) =
        ByteArrayOutputStream().use {
            ZipOutputStream(it).use { stream ->
                stream.setLevel(BEST_COMPRESSION)
                stream.putNextEntry(ZipEntry(if (seqNo != null) "[$seqNo] $title.fb2" else "$title.fb2"))
                stream.write(bytes, 0, bytes.size)
                stream.closeEntry()
            }
            it.toByteArray()
        }

    private fun sequences(fb2: List<Triple<FictionBook, File, FileType>>) = run {

        val fb2Sequences = fb2
            .mapNotNull { (fb, _, _) -> fb.description.titleInfo.sequence?.name }

        val epubSequences = emptyList<String>()

        (
            sequenceRepository.findAll() + (fb2Sequences + epubSequences)
                .map { Sequence(name = it) }
            )
            .distinctBy { it.name }
    }

    private fun genres(fb2: List<Triple<FictionBook, File, FileType>>, epub: List<Triple<nl.siegmann.epublib.domain.Book, File, FileType>>) = run {

        val fb2Genres = fb2
            .flatMap { (fb, _, _) -> fb.description.titleInfo.genres }
            .map { fb2GenreToString(it) }

        val epubGenres = epub.flatMap { (ep, _, _) -> ep.metadata.subjects }

        (
            genreRepository.findAll() + (fb2Genres + epubGenres)
                .map { Genre(name = it) }
            )
            .distinctBy { it.name }
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

    private fun authors(fb2: List<Triple<FictionBook, File, FileType>>, epub: List<Triple<nl.siegmann.epublib.domain.Book, File, FileType>>) = run {

        val fb2Authors = fb2
            .flatMap { (fb, _, _) -> fb.description.titleInfo.authors }
            .map { it.firstName to it.middleName and (it.lastName ?: "?") }

        val epubAuthors = epub
            .flatMap { (ep, _, _) -> ep.metadata.authors }
            .map { it.firstname to null and it.lastname }

        (
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
    }

    private fun summaryType(value: String?) = if (value?.contains("</p>") == true)
        "text/html"
    else
        "text"

    private fun lang(value: String) = when (value.toLowerCase()) {
        "ru" -> "ru-RU"
        "en" -> "en-US"
        else -> value
    }

    @Value("\${books.fileMonitor.forceCompress}")
    private var forceCompress: Boolean = true

    private val log = LoggerFactory.getLogger(BookService::class.java)
}
