package name.oshurkov.books

import kotlinx.coroutines.*
import org.slf4j.*
import java.io.*
import java.nio.*
import java.nio.charset.*
import java.security.*
import java.util.*
import java.util.zip.*


infix fun <A, B, C> Pair<A, B>.and(that: C) = Triple(this.first, this.second, that)


fun <A, B> List<A>.pmap(f: suspend (A) -> B) = runBlocking {
    map { async { f(it) } }.awaitAll()
}


fun uuid(bytes: ByteArray) = run {
    val digest = MessageDigest.getInstance("MD5").digest(bytes)
    val bb = ByteBuffer.wrap(digest)
    UUID(bb.long, bb.long)
}

fun uuid(value: String) = UUID.fromString(value)!!


fun unzipFile(file: File) =
    runCatching { ZipFile(file) }
        .getOrElse { ZipFile(file, Charset.forName("cp1251")) }
        .use {
            val entry = it.entries().toList().first()
            it.getInputStream(entry).use { stream -> stream.readAllBytes() }
        }!!


fun Logger.debug(e: Throwable) = debug(e.message, e)
fun Logger.info(e: Throwable) = info(e.message, e)
fun Logger.warn(e: Throwable) = warn(e.message, e)
fun Logger.error(e: Throwable) = error(e.message, e)

fun logger() = lazy { LoggerFactory.getLogger(Thread.currentThread().stackTrace[4].className) }
