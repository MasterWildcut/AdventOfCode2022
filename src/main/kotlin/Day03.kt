import java.io.File

fun main() {
    println(part05("src/main/resources/Day03_01"))
    println(part06("src/main/resources/Day03_01"))
}

fun part06(fileName: String): Int {
    var score = 0
    val lines = File(fileName).useLines { it.toList() }
    for (i in 0..(lines.size - 1) / 3) {
        score += getBadgeScore(lines[3 * i], lines[3 * i + 1], lines[3 * i + 2])
    }
    return score
}

fun getBadgeScore(firstElf: String, secondElf: String, thirdElf: String): Int {
    val firstSet = firstElf.toHashSet()
    val secondSet = secondElf.toHashSet()
    val thirdSet = thirdElf.toHashSet()
    firstSet.retainAll(secondSet)
    firstSet.retainAll(thirdSet)
    val badge = firstSet.first()
    val alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
    return alphabet.indexOf(badge) + 1

}

fun part05(fileName: String): Int {
    var score = 0
    File(fileName).forEachLine { l ->
        run {
            val mid = (l.length / 2)
            val firstComponent = l.substring(0, mid)
            val secondComponent = l.substring(mid)
            score += getPriorityOfWronglyPackedItem(firstComponent, secondComponent)
        }
    }
    return score
}

fun getPriorityOfWronglyPackedItem(firstComponent: String, secondComponent: String): Int {
    val firstSet = firstComponent.toHashSet()
    val secondSet = secondComponent.toHashSet()
    firstSet.retainAll(secondSet)
    val missPackedItem = firstSet.first()
    val alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
    return alphabet.indexOf(missPackedItem) + 1
}

