import java.io.File
import java.util.*
import kotlin.collections.ArrayDeque

fun main() {
    println(part09("src/main/resources/Day05_01.txt"))
    println(part10("src/main/resources/Day05_01.txt"))
}

fun part09(fileName: String): String {
    val (crates, instructions) = parseCargo(fileName)
    instructions.forEach {
        val instruction = parseInstruction(it)
        for (i in IntRange(1, instruction[0])) {
            crates[instruction[2] - 1].push(crates[instruction[1] - 1].pop())
        }
    }
    return getSolution(crates)
}

fun part10(fileName: String): String {
    val (crates, instructions) = parseCargo(fileName)
    instructions.forEach {
        val instruction = parseInstruction(it)
        val buffer = ArrayDeque<Char>()
        for (i in IntRange(1, instruction[0])) {
            buffer.add(crates[instruction[1] - 1].pop())
        }
        while(buffer.isNotEmpty()) {
            crates[instruction[2] - 1].push(buffer.removeLast())
        }
    }
    return getSolution(crates)
}

private fun parseInstruction(it: String): List<Int> {
    return Regex("[0-9]+").findAll(it)
        .map(MatchResult::value)
        .map { it.toInt() }
        .toList()
}

private fun getSolution(crates: List<LinkedList<Char>>): String {
    var solution = ""
    crates.stream().forEach { solution += it.pop() }
    return solution
}

private fun parseCargo(fileName: String): Pair<List<LinkedList<Char>>, List<String>> {
    val lines = File(fileName).readLines()
    val countCrates = (lines.find { it.startsWith(" 1") }!!.length + 2) / 4
    val crates = List(countCrates) {
        LinkedList<Char>()
    }
    // parse image into stacks
    var lineIndex = 0
    while (lines[lineIndex][1] != '1') {
        for (c in lines[lineIndex].indices) {
            if (lines[lineIndex][c].isLetter())
                crates[c / 4].add(lines[lineIndex][c])
        }
        lineIndex++
    }
    val instructions = lines.subList(lineIndex + 2, lines.size)
    return Pair(crates, instructions)
}
