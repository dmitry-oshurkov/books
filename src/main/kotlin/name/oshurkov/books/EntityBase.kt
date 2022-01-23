package name.oshurkov.books

import org.hibernate.annotations.*
import org.springframework.data.util.*
import java.util.*
import javax.persistence.*
import javax.persistence.GenerationType.*

@MappedSuperclass
abstract class EntityBase(
    @Id
    @GeneratedValue(strategy = AUTO)
    val id: Int = 0,
    @UpdateTimestamp
    @Column(nullable = false)
    val updated: Date = Date(0),
) {
    override fun equals(other: Any?) = when {
        other == null -> false
        this === other -> true
        javaClass != ProxyUtils.getUserClass(other) -> false
        else -> {
            other as EntityBase
            id == other.id
        }
    }

    override fun hashCode() = 42
}
