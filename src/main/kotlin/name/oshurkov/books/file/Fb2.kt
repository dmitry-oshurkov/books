package name.oshurkov.books.file

import jakarta.xml.bind.*
import jakarta.xml.bind.Marshaller.*
import name.oshurkov.books.author.*
import name.oshurkov.books.book.*
import name.oshurkov.books.core.*
import name.oshurkov.books.fb2.*
import name.oshurkov.books.file.FileType.*
import org.glassfish.jaxb.runtime.marshaller.*
import java.io.*
import kotlin.text.Charsets.UTF_8


fun parseFb2(files: List<File>) = files
    .map {
        when {
            it.extension == "fb2" -> it.readBytes()
            it.name.endsWith(".fb2.zip") -> unzip(it)
            else -> null
        } to it
    }
    .filter { (bytes) -> bytes != null }
    .map { (bytes, file) -> importedBook(bytes!!, file) }


fun parseFb2(bytes: ByteArray) = listOf(importedBook(bytes = bytes, file = null, unread = true))


fun repackFb2(id: Int) {

    val files = selectBookFiles()[id]

    if (files != null) {

        val authors = selectBookAuthors(id).map { it.toTitleInfoAuthor() }
        val book = selectBook(id)!!

        val fb2 = unmarshalFb2(unzip(files.first().content))
        fb2.description.titleInfo.authors.clear()
        (fb2.description.titleInfo.authors as ArrayList<TitleInfoType.Author>).addAll(authors)

        val newFb2 = marshal(fb2Marshaller, fb2)
        zip(book.title, book.sequenceNumber, newFb2.toByteArray())
    }
}


private fun importedBook(bytes: ByteArray, file: File?, unread: Boolean? = null) = run {

    val fb2 = unmarshalFb2(bytes)
    val titleInfo = fb2.description.titleInfo
    val sequenceType = titleInfo.sequences.firstOrNull()

    val title = titleInfo.bookTitle.value
    val bookAuthors = titleInfo.authors.map { Fb2Author(it) }.distinct()
    val bookGenres = titleInfo.genres.map { fb2GenreToString(it.value?.value()) }.distinct()
    val summary = titleInfo.annotation?.content?.flatMap { it.value.asString() }?.joinToString("\n")
    val binary = fb2.binaries.firstOrNull { it.id == titleInfo.coverpage?.images?.firstOrNull()?.href?.trimStart('#') }
    val attrs = file?.name?.parseAttrs() ?: ""
    val fbz = zip(title, sequenceType?.number, bytes)
    val bookFile = ImportedBookFile(
        content = fbz,
        hash = uuid(fbz),
        type = FBZ
    )

    ImportedBook(
        title = title,
        summary = summary,
        summaryContentType = summaryType(summary),
        language = lang(titleInfo.lang),
        issued = titleInfo.date?.value,
        publisher = fb2.description.publishInfo?.publisher?.value,
        cover = binary?.value,
        coverContentType = binary?.contentType,
        recommended = attrs.isRecommended(),
        verified = !attrs.isNotVerified(),
        unread = unread ?: attrs.isUnread(),
        sequence = sequenceType?.name,
        sequenceNumber = sequenceType?.number,
        hash = bookHash(bookAuthors, title),
        authors = bookAuthors,
        genres = bookGenres,
        files = listOf(bookFile),
        srcFile = file,
    )
        .also {
            log.info("Parsed: ${it.title} [${file?.absolutePath}]")
        }
}


private fun Any.asString(): List<String> = when (this) {
    is String -> listOf(this)
    is JAXBElement<*> -> value.asString()
    is StyleType -> content.flatMap { it.asString() }
    is PoemType -> listOf("<**>")
    is CiteType -> listOf("<**>")
    is TableType -> listOf("<**>")
    else -> listOf("<**>")
}


private fun fb2GenreToString(code: String?) = when (code) {
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


private fun unmarshalFb2(bytes: ByteArray) = fb2Unmarshaller.unmarshal(bytes.inputStream()) as FictionBook


private fun marshal(marshaller: Marshaller, obj: Any) = ByteArrayOutputStream().use {

    marshaller.marshal(obj, it)

    it.toString(UTF_8.name())
        .let { s -> toSelfClosed.replace(s, """<${'$'}1/>""") }
        .let { s -> newLine.replace(s, """<p>""") }
        .replace(" xmlns:ns3=\"http://www.gribuser.ru/xml/fictionbook/2.0/markup\"", "")
        .trim()
}


private fun marshaller(context: JAXBContext, addXmlDeclaration: Boolean = true) = context.createMarshaller()
    .apply {
        setProperty(JAXB_FORMATTED_OUTPUT, true)
        setProperty("org.glassfish.jaxb.indentString", "  ")
        setProperty("org.glassfish.jaxb.xmlDeclaration", false)
        if (addXmlDeclaration) setProperty("org.glassfish.jaxb.xmlHeaders", """<?xml version="1.0" encoding="UTF-8"?>""")
        setProperty(
            "org.glassfish.jaxb.namespacePrefixMapper",
            object : NamespacePrefixMapper() {
                override fun getPreferredPrefix(namespaceUri: String?, suggestion: String?, requirePrefix: Boolean) = when (namespaceUri) {
                    "http://www.w3.org/1999/xlink" -> "l"
                    else -> suggestion ?: ""
                }
            }
        )
    }


private val fb2Context = JAXBContext.newInstance(FictionBook::class.java)!!
private val fb2Unmarshaller = fb2Context.createUnmarshaller()!!
private val fb2Marshaller = marshaller(fb2Context)!!


private val toSelfClosed = """<[^>]*></([^>]*)>""".toRegex()
private val newLine = """<p>\n\s*""".toRegex()

private val log by logger()
