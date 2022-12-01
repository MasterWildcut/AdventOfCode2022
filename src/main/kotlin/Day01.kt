import java.io.File

fun main() {
    println(part01("src/main/resources/Day01_01"))
    println(part02("src/main/resources/Day01_01"))
}

fun part01(file: String): Int {
    val map = getCaloriesMap(file)
    return map.maxBy { it.value }.value
}

fun part02(file: String): Int {
    val map = getCaloriesMap(file)
    var entryList = map.entries.stream().map { e -> e.value }.toList()
    entryList = entryList.toMutableList()
    entryList.sortDescending()
    return entryList.take(3).sum()
}

private fun getCaloriesMap(file: String): MutableMap<Int, Int> {
    var k = 0
    val map = mutableMapOf<Int, Int>()
    map[k] = 0
    File(file).forEachLine { l ->
        run {
            if (l.isBlank()) {
                k++
                map[k] = 0
            } else {
                map[k] = map[k]!! + l.toInt()
            }
        }
    }
    return map
}
