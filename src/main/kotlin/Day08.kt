import java.io.File

fun main() {
    println(part15("src/main/resources/Day08_01.txt"))
    println(part16("src/main/resources/Day08_01.txt"))
}

fun part15(fileName: String): Int {
    val forest = File(fileName).readLines().map { it.toCharArray()}
    val outerTrees = forest.size * 4 - 4
    var visibleTrees = outerTrees
    for (i in 1 until forest.size - 1) {
        for (j in 1 until forest.size - 1) {
            if (visibleFromNorth(forest, i, j) ||
                visibleFromWest(forest, i, j) ||
                visibleFromEast(forest, i, j) ||
                visibleFromSouth(forest, i, j)
            ) {
                visibleTrees++
            }
        }
    }
    return visibleTrees
}

fun part16(fileName: String): Int {
    val forest = File(fileName).readLines().map { it.toCharArray()}
    var maxScore = 0
    for (i in 1 until forest.size - 1) {
        for (j in 1 until forest.size - 1) {
            val scenicScore = getScenicScore(forest, i, j)
            maxScore = maxOf(maxScore, scenicScore)
        }
    }
    return maxScore
}

fun visibleFromNorth(forest: List<CharArray>, i: Int, j: Int): Boolean {
    val value = forest[i][j]
    for (k in 0 until i) {
        if (forest[k][j] >= value) return false
    }
    return true

}

fun visibleFromSouth(forest: List<CharArray>, i: Int, j: Int): Boolean {
    val value = forest[i][j]
    for (k in i + 1 until forest.size) {
        if (forest[k][j] >= value) return false
    }
    return true
}

fun visibleFromWest(forest: List<CharArray>, i: Int, j: Int): Boolean {
    val value = forest[i][j]
    for (k in 0 until j) {
        if (forest[i][k] >= value) return false
    }
    return true
}

fun visibleFromEast(forest: List<CharArray>, i: Int, j: Int): Boolean {
    val value = forest[i][j]
    for (k in j + 1 until forest.size) {
        if (forest[i][k] >= value) return false
    }
    return true
}

private fun getScenicScore(forest: List<CharArray>, i: Int, j: Int) =
    scenicScoreNorth(forest, i, j) *
            scenicScoreSouth(forest, i, j) *
            scenicScoreWest(forest, i, j) *
            scenicScoreEast(forest, i, j)

fun scenicScoreNorth(forest: List<CharArray>, i: Int, j: Int): Int {
    val value = forest[i][j]
    var score = 0
    for (k in (i - 1) downTo 0) {
        score++
        if (forest[k][j] >= value) return score
    }
    return score
}

fun scenicScoreSouth(forest: List<CharArray>, i: Int, j: Int): Int {
    val value = forest[i][j]
    var score = 0
    for (k in (i + 1) until forest.size) {
        score++
        if (forest[k][j] >= value) return score
    }
    return score
}

fun scenicScoreWest(forest: List<CharArray>, i: Int, j: Int): Int {
    val value = forest[i][j]
    var score = 0
    for (k in (j - 1) downTo 0) {
        score++
        if (forest[i][k] >= value) return score
    }
    return score
}

fun scenicScoreEast(forest: List<CharArray>, i: Int, j: Int): Int {
    val value = forest[i][j]
    var score = 0
    for (k in (j + 1) until forest.size) {
        score++
        if (forest[i][k] >= value) return score
    }
    return score
}


