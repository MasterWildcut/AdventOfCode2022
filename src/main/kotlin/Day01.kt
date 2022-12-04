import java.io.File

fun main() {
    println(part01("src/main/resources/Day01_01"))
    println(part02("src/main/resources/Day01_01"))
}

fun part01(file: String): Int {
    return getCaloriesList(file).max()
}

fun part02(file: String): Int {
    return getCaloriesList(file).sortedDescending().take(3).sum()
}

private fun getCaloriesList(file: String): Collection<Int> {
    var k = 0
    val map = mutableMapOf(k to 0)
    File(file).forEachLine { l ->
        if (l.isBlank()) {
            k++
            map[k] = 0
        } else {
            map[k] = map[k]!! + l.toInt()
        }
    }
    return map.values
}


private fun getCaloriesListAlt(file: String): List<Int> {
    return File(file).useLines {
        it.fold(Pair(0, emptyList<Int>())) {
                (currentElf, allElves), line ->
            if (line.isBlank()) {
                Pair(0, allElves + currentElf)
            } else {
                Pair(currentElf + line.toInt(), allElves)
            }
        }
    }.second
}