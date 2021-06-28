package name.oshurkov.books.api.file

import name.oshurkov.books.*
import name.oshurkov.books.api.file.FileType.*
import name.oshurkov.books.api.file.fb2.parser.*
import org.springframework.stereotype.*
import java.io.*
import java.util.zip.*

fun parseFb2(files: Map<FileType, List<File>>) = run {

    val fbz = files[FBZ].orEmpty()
        .mapNotNull { file ->
            runCatching {

                val bytes = ZipFile(file).use {
                    val entry = it.entries().toList().first()
                    it.getInputStream(entry).use { stream -> stream.readAllBytes() }
                }

                FictionBook(null, bytes) to file and FBZ
            }
                .getOrNull()
        }

    val fb2plain = files[FB2].orEmpty()
        .map { FictionBook(it, null) to it and FB2 }

    fb2plain + fbz
}
