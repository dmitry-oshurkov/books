package name.oshurkov.books.author

import name.oshurkov.books.fb2.*
import java.time.*


class Author(
    val id: Int,
    val updated: OffsetDateTime,
    val lastName: String,
    val firstName: String,
    val middleName: String?,
) {
    override fun toString() = "$firstName$middle $lastName"
    fun toStringForList() = "$lastName, $firstName$middle"

    private val middle get() = if (middleName != null) " $middleName" else ""
}


data class Fb2Author(
    val lastName: String,
    val firstName: String,
    val middleName: String?,
    val nickname: String?,
    val homePages: List<String>?,
    val emails: List<String>?,
    val id: String?,
) {
    constructor(value: TitleInfoType.Author) : this(
        lastName = value.lastName.value,
        firstName = value.firstName.value,
        middleName = value.middleName?.value,
        nickname = value.nickname?.value,
        homePages = value.homePages,
        emails = value.emails,
        id = value.id,
    )

    fun toTitleInfoAuthor() = TitleInfoType.Author(
        firstName.toTextField(),
        middleName?.toTextField(),
        lastName.toTextField(),
        nickname?.toTextField(),
        homePages,
        emails,
        id
    )

    val hash
        get() = "$lastName$firstName$middleName"
            .lowercase()
            .replace(" ", "")
            .replace("ё", "е")
            .replace("й", "и")
}


private fun String.toTextField() = TextFieldType(this, null)
