import java.io.File

fun main() {
    println(part07("src/main/resources/Day04_01.txt"))
    println(part08("src/main/resources/Day04_01.txt"))
}

fun part07(fileName: String): Int {
    return File(fileName).useLines { lines ->
        lines.filter {
            val (firstRangeSet, secondRangeSet) = pair(it)
            return@filter (firstRangeSet.containsAll(secondRangeSet) ||
                    secondRangeSet.containsAll(firstRangeSet))
        }.count()
    }

}

fun part08(fileName: String): Int {
    return File(fileName).useLines { lines ->
        lines.filter {
            val (firstRangeSet, secondRangeSet) = pair(it)
            return@filter (firstRangeSet.intersect(secondRangeSet).isNotEmpty())
        }.count()
    }
}

private fun pair(l: String): Pair<Set<Int>, Set<Int>> {
    val (firstStart, firstEnd, secondStart, secondEnd) = l.split(',')
        .flatMap { it.split('-') }
        .map(String::toInt)
    return Pair(
        IntRange(firstStart, firstEnd).toSet(),
        IntRange(secondStart, secondEnd).toSet()
    )
}
