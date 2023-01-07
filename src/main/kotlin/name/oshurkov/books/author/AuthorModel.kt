package name.oshurkov.books.author

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


data class ImportedAuthor(
    val lastName: String,
    val firstName: String?,
    val middleName: String?,
)
