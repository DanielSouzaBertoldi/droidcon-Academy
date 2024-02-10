package daniel.bertoldi.bookstorenotes

fun validateQuery(query: String) = query.length >= 2

fun List<String>.authorsToString() = joinToString(separator = ", ")