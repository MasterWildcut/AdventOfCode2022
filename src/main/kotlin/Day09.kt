import java.io.File
import kotlin.math.abs
import kotlin.math.sign

fun main() {
    println(part17("src/main/resources/Day09_01.txt"))
    println(part18("src/main/resources/Day09_01.txt"))
}

fun part17(fileName: String): Int {
    var head = Pair(0, 0)
    var tail = Pair(0, 0)
    val visitedPositions = mutableSetOf(tail)
    File(fileName).readLines().forEach {
        val split = it.split(" ")
        val (newHeadPosition, newTailPosition) = move(head, tail, split[0], split[1].toInt(), visitedPositions)
        head = newHeadPosition
        tail = newTailPosition
    }
    return visitedPositions.size
}

fun part18(fileName: String): Int {
    var head = Pair(0, 0)
    var tail = mutableListOf<Pair<Int, Int>>().apply {
        repeat(9) { this.add(head) }
    }
    val visitedPositions = mutableSetOf(head)
    File(fileName).readLines().forEach {
        val split = it.split(" ")
        val (newHeadPosition, newTailPosition) = moveLongRope(head, tail, split[0], split[1].toInt(), visitedPositions)
        head = newHeadPosition
        tail = newTailPosition
    }
    return visitedPositions.size
}

// Pair<x, y>
// this and longer Rope method can be united for one generic method
fun move(
    head: Pair<Int, Int>,
    tail: Pair<Int, Int>,
    direction: String,
    distance: Int,
    visitedPositions: MutableSet<Pair<Int, Int>>
): Pair<Pair<Int, Int>, Pair<Int, Int>> {
    var newHeadPosition = head
    var newTailPosition = tail
    for (i in 0 until distance) {
        when (direction) {
            "L" -> {
                newHeadPosition = Pair(newHeadPosition.first - 1, newHeadPosition.second)
            }

            "U" -> {
                newHeadPosition = Pair(newHeadPosition.first, newHeadPosition.second + 1)
            }

            "R" -> {
                newHeadPosition = Pair(newHeadPosition.first + 1, newHeadPosition.second)
            }

            "D" -> {
                newHeadPosition = Pair(newHeadPosition.first, newHeadPosition.second - 1)
            }
        }
        newTailPosition = moveTail(newHeadPosition, newTailPosition)
        visitedPositions.add(newTailPosition)
    }
    return Pair(newHeadPosition, newTailPosition)
}

// Pair<x, y>
fun moveLongRope(
    head: Pair<Int, Int>,
    tail: MutableList<Pair<Int, Int>>,
    direction: String,
    distance: Int,
    visitedPositions: MutableSet<Pair<Int, Int>>
): Pair<Pair<Int, Int>, MutableList<Pair<Int, Int>>> {
    var newHeadPosition = head
    for (i in 0 until distance) {
        when (direction) {
            "L" -> {
                newHeadPosition = Pair(newHeadPosition.first - 1, newHeadPosition.second)
            }

            "U" -> {
                newHeadPosition = Pair(newHeadPosition.first, newHeadPosition.second + 1)
            }

            "R" -> {
                newHeadPosition = Pair(newHeadPosition.first + 1, newHeadPosition.second)
            }

            "D" -> {
                newHeadPosition = Pair(newHeadPosition.first, newHeadPosition.second - 1)
            }
        }
        tail[0] = moveTail(newHeadPosition, tail.first())
        for (j in 1 until tail.size) {
            tail[j] = moveTail(tail[j - 1], tail[j])
        }
        visitedPositions.add(tail.last())
    }
    return Pair(newHeadPosition, tail)
}

fun moveTail(headPosition: Pair<Int, Int>, tail: Pair<Int, Int>): Pair<Int, Int> {
    val xDifference = headPosition.first - tail.first
    val yDifference = headPosition.second - tail.second
    // adjacent
    if (abs(xDifference) <= 1 &&
        abs(yDifference) <= 1
    ) {
        return tail
    }
    if (yDifference == 0) {
        return Pair(tail.first + xDifference.sign, tail.second)
    }

    if (xDifference == 0) {
        return Pair(tail.first, tail.second + yDifference.sign)
    }
    return Pair(tail.first + xDifference.sign, tail.second + yDifference.sign)
}
