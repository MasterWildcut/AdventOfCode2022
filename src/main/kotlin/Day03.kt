import java.io.File

fun main() {
    println(part05("src/main/resources/Day03_01"))
    println(part06("src/main/resources/Day03_01"))
}

fun part06(fileName: String): Int {
    var score = 0
    return File(fileName).useLines { lines ->
        lines.chunked(3).map {
            getBadgeScore(it[0], it[1], it[2])
        }.sum()
    }
}

fun getBadgeScore(firstElf: String, secondElf: String, thirdElf: String): Int {
    val firstSet = firstElf.toHashSet()
    val secondSet = secondElf.toHashSet()
    val thirdSet = thirdElf.toHashSet()
    firstSet.retainAll(secondSet)
    firstSet.retainAll(thirdSet)
    val badge = firstSet.first()
    return badge.score()

}

fun part05(fileName: String): Int {
    var score = 0
    File(fileName).forEachLine { l ->
        val mid = (l.length / 2)
        val firstComponent = l.substring(0, mid)
        val secondComponent = l.substring(mid)
        score += getPriorityOfWronglyPackedItem(firstComponent, secondComponent)
    }
    return score
}

fun getPriorityOfWronglyPackedItem(firstComponent: String, secondComponent: String): Int {
    val firstSet = firstComponent.toHashSet()
    val secondSet = secondComponent.toHashSet()
    firstSet.retainAll(secondSet)
    val missPackedItem = firstSet.first()
    return missPackedItem.score()
}

private const val alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

private fun Char.score(): Int = alphabet.indexOf(this) + 1
