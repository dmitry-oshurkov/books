package name.oshurkov.books.core.data

import org.ktorm.schema.*
import org.postgresql.*
import java.sql.*


inline fun <reified C : Enum<C>> BaseTable<*>.enumOrd(name: String) = registerColumn(name, EnumOrdSqlType(C::class.java))


/**
 * [SqlType] implementation that saves enums as ordinal values.
 *
 * @property enumClass the enum class.
 */
class EnumOrdSqlType<C : Enum<C>>(private val enumClass: Class<C>) : SqlType<C>(Types.OTHER, "enum") {

    private val hasPostgresqlDriver by lazy { runCatching { Class.forName("org.postgresql.Driver") }.isSuccess }

    override fun setParameter(ps: PreparedStatement, index: Int, parameter: C?) {
        when {
            parameter != null -> doSetParameter(ps, index, parameter)
            hasPostgresqlDriver && ps.isWrapperFor(PGStatement::class.java) -> ps.setNull(index, Types.OTHER)
            else -> ps.setNull(index, Types.VARCHAR)
        }
    }

    override fun doSetParameter(ps: PreparedStatement, index: Int, parameter: C) {
        if (hasPostgresqlDriver && ps.isWrapperFor(PGStatement::class.java))
            ps.setObject(index, parameter.ordinal, Types.OTHER)
        else
            ps.setInt(index, parameter.ordinal)
    }

    override fun doGetResult(rs: ResultSet, index: Int) = byOrdinal(enumClass, rs.getInt(index))
}


private fun <E : Enum<E>> byOrdinal(clazz: Class<E>, ordinal: Int) = clazz.enumConstants.find { it.ordinal == ordinal } ?: throw IllegalArgumentException()
