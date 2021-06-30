package name.oshurkov.books

import java.io.*
import java.nio.*
import java.nio.charset.*
import java.security.*
import java.util.*
import java.util.zip.*

infix fun <A, B, C> Pair<A, B>.and(that: C) = Triple(this.first, this.second, that)

fun uuid(bytes: ByteArray) = run {
    val digest = MessageDigest.getInstance("MD5").digest(bytes)
    val bb = ByteBuffer.wrap(digest)
    UUID(bb.long, bb.long)
}

fun unzipFile(file: File) =
    runCatching { ZipFile(file) }
        .getOrElse { ZipFile(file, Charset.forName("cp1251")) }
        .use {
            val entry = it.entries().toList().first()
            it.getInputStream(entry).use { stream -> stream.readAllBytes() }
        }!!
