package name.oshurkov.books.core.data


fun <K, V, T : Pair<K, V>> Iterable<T>.groupedValues() = groupBy { it.first }.mapValues { it.value.map { pair -> pair.second } }
