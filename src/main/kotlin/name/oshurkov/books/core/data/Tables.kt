package name.oshurkov.books.core.data

import org.ktorm.schema.*


/**
 * Пустая таблица, без полей.
 */
abstract class SimpleTable(tableName: String, alias: String? = null) : Table<Nothing>(tableName, alias, null, null, Nothing::class)


/**
 * Таблица с полем id (Int).
 */
abstract class IdSimpleTable(tableName: String, alias: String? = null) : SimpleTable(tableName, alias) {
    val id = int("id").primaryKey()
}


/**
 * Таблица с полями:
 * - id (Int)
 * - updated (Instant)
 */
abstract class BksTable(tableName: String, alias: String? = null) : IdSimpleTable(tableName, alias) {
    val updated = timestamp("updated")
}


/**
 * Таблица с полями:
 * - id (Int)
 * - updated (Instant)
 * - name (String)
 */
abstract class NamedBksTable(tableName: String, alias: String? = null) : BksTable(tableName, alias) {
    val name = varchar("name")
}
