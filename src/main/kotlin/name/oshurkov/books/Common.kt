package name.oshurkov.books

import java.nio.*
import java.security.*
import java.util.*

infix fun <A, B, C> Pair<A, B>.and(that: C) = Triple(this.first, this.second, that)

fun uuid(bytes: ByteArray) = run {
    val digest = MessageDigest.getInstance("MD5").digest(bytes)
    val bb = ByteBuffer.wrap(digest)
    UUID(bb.long, bb.long)
}
