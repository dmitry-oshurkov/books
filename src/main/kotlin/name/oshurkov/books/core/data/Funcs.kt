package name.oshurkov.books.core.data

import org.ktorm.database.*
import org.ktorm.dsl.*
import org.ktorm.schema.*


fun <R> IdSimpleTable.select(db: Database, vararg columns: ColumnDeclaring<*>, transform: (row: QueryRowSet) -> R) = db
    .from(this)
    .select(*columns)
    .map(transform)


fun <R> IdSimpleTable.find(id: Int, db: Database, vararg columns: ColumnDeclaring<*>, transform: (row: QueryRowSet) -> R) = db
    .from(this)
    .select(*columns)
    .where { IdSimpleTable::id.get(this) eq id }
    .map(transform)
    .singleOrNull()


fun <K, V, T : Pair<K, V>> Iterable<T>.groupedValues() = groupBy { it.first }.mapValues { it.value.map { pair -> pair.second } }
