package name.oshurkov.books.core

import name.oshurkov.books.*
import name.oshurkov.books.core.BuildMode.*


enum class BuildMode { DEV, PROD }


/**
 * Выполняет блок кода для окружения Development.
 */
fun dev(block: () -> Unit) = if (BUILD_MODE == DEV) block() else Unit


/**
 * Выполняет блок кода для окружения Production.
 */
fun prod(block: () -> Unit) = if (BUILD_MODE == PROD) block() else Unit
