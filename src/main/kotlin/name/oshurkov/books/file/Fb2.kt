package name.oshurkov.books.file

import io.ktor.util.*
import name.oshurkov.books.author.*
import name.oshurkov.books.book.*
import name.oshurkov.books.core.*
import name.oshurkov.books.file.FileType.*
import name.oshurkov.books.file.fb2.parser.*
import java.io.*


fun parseFb2(files: Map<FileType, List<File>>) = run {

    val fbz = files[FBZ].orEmpty()
        .mapNotNull { file ->

            runCatching {
                val bytes = unzip(file)
                FictionBook(null, bytes) to file and FBZ
            }
                .getOrNull()
        }

    val fb2plain = files[FB2].orEmpty()
        .map { FictionBook(it, null) to it and FB2 }

    fb2plain + fbz
}


fun fb2ToBooks(fb2: List<Triple<FictionBook, File, FileType>>, afterSaveFile: (File) -> Unit) = fb2.mapNotNull { (fb, file, type) ->

    runCatching {

        val bookAuthors = fb.description.titleInfo.authors.map { ImportedAuthor(lastName = it.lastName!!, firstName = it.firstName, middleName = it.middleName) }.distinct()
        val bookGenres = fb.description.titleInfo.genres.map { fb2GenreToString(it) }.distinct()
        val summary = fb.annotation?.annotations?.map { it.text }?.joinToString("\n")
        val binary = fb.binaries[fb.description.titleInfo.coverPage.firstOrNull()?.value?.trimStart('#')]
        val attrs = file.name.parseAttrs()

        ImportedBook(
            title = fb.title,
            summary = summary,
            summaryContentType = summaryType(summary),
            language = lang(fb.lang),
            issued = fb.description.titleInfo.date,
            publisher = fb.description.publishInfo.publisher,
            cover = binary?.binary?.decodeBase64Bytes(),
            coverContentType = binary?.contentType,
            recommended = attrs.isRecommended(),
            verified = !attrs.isNotVerified(),
            unread = attrs.isUnread(),
            sequence = fb.description.titleInfo.sequence?.name,
            sequenceNumber = fb.description.titleInfo.sequence?.number?.toInt(),
            hash = bookHash(bookAuthors, fb.title),
            authors = bookAuthors,
            genres = bookGenres,
            files = listOf(bookFile(file, type, fb.title, null)),
        )
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
    "child_prose" -> "Детская проза"
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
    "comp_hard" -> "Компьютерное «железо» (аппаратное обеспечение)"
    "comp_soft" -> "Программы"
    "comp_db" -> "Базы данных"
    "comp_osnet" -> "ОС и сети"
    "computers" -> "Прочая около-компьютерная литература (то, что не вошло в другие категории)"
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
    "religion" -> "Прочая религиозная литература (то, что не вошло в другие категории)"
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
    "sf_etc" -> "Фантастика: прочее"
    "romance" -> "Неоромантический приключенческий роман"
    "romance_multicultural" -> "Неоромантический приключенческий роман"
    "foreign_prose" -> "Зарубежная классическая проза"
    "russian_contemporary" -> "Русская современная проза"
    "hronoopera" -> "Хроноопера"
    "sci_cosmos" -> "Астрономия и Космос"
    "foreign_contemporary" -> "Зарубежная современная проза"
    "literature_20" -> "Литература ХX века (эпоха Социальных революций)"
    "foreign_detective" -> "Зарубежный детектив"
    "sf_fantasy_city" -> "Городское фэнтези"
    else -> {
        log.warn("Genre [$code] is unknown; use Прочее")
        "Прочее"
    }
}


private val log by logger()
