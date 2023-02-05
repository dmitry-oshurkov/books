package name.oshurkov.books.core

import kotlinx.coroutines.*
import org.slf4j.*
import java.nio.*
import java.security.*
import java.util.*


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

fun uuid() = UUID.randomUUID()!!


fun Logger.debug(e: Throwable) = debug(e.message, e)
fun Logger.info(e: Throwable) = info(e.message, e)
fun Logger.warn(e: Throwable) = warn(e.message, e)
fun Logger.error(e: Throwable) = error(e.message, e)


fun logger() = lazy {
    LoggerFactory.getLogger(
        try {
            Thread.currentThread().stackTrace[4].className
        } catch (ex: Throwable) {
            println(ex)
            "name.oshurkov.books"
        }
    )
}
