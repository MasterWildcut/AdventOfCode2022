import java.io.File

fun main() {
    println(part07("src/main/resources/Day04_01.txt"))
    println(part08("src/main/resources/Day04_01.txt"))
}

fun part07(fileName: String): Int {
    return File(fileName).readLines()
        .filter {
            val pair = pair(it)
            return@filter (pair.first.containsAll(pair.second) ||
                    pair.second.containsAll(pair.first))
        }.size

}

fun part08(fileName: String): Int {
    return File(fileName).readLines()
        .filter {
            val pair = pair(it)
            return@filter (pair.first.intersect(pair.second).isNotEmpty())
        }.size
}

private fun pair(l: String): Pair<Set<Int>, Set<Int>> {
    val split = l.split(",")
    val firstRange = split[0].split("-")
    val secondRange = split[1].split("-")
    return Pair(
        IntRange(firstRange[0].toInt(), firstRange[1].toInt()).toSet(),
        IntRange(secondRange[0].toInt(), secondRange[1].toInt()).toSet()
    )
}
