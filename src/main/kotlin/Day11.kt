import java.io.File
import java.util.*

fun main() {
    println(part21("src/main/resources/Day11_01.txt"))
    println(part22("src/main/resources/Day11_01.txt"))
}

fun part21(fileName: String): Int {
    val monkeyTexts = File(fileName).readText().split("\n\n")
    val monkeys = monkeyTexts.map { it.split("\n") }.map { Monkey.parse(it) }
    val activeMonkeys = mutableListOf<Int>()
    repeat(monkeys.size) {
        activeMonkeys.add(0)
    }
    for (round in 1..20) {
        monkeys.forEachIndexed { index, monkey ->
            val startItems = monkey.items.toList()
            startItems.forEach { item ->
                activeMonkeys[index]++
                val worryLevel = (monkey.operator(item) / 3)
                monkey.items.removeFirst()
                if (worryLevel % monkey.divisor == 0L) {
                    monkeys[monkey.trueMonkey].items.add(worryLevel)
                } else {
                    monkeys[monkey.falseMonkey].items.add(worryLevel)
                }
            }
        }
    }
    activeMonkeys.sortDescending()
    return activeMonkeys.take(2).reduce { acc, i -> acc * i }

}

fun part22(fileName: String): Long {
    val monkeyTexts = File(fileName).readText().split("\n\n")
    val monkeys = monkeyTexts.map { it.split("\n") }.map { Monkey.parse(it) }
    val lowestCommonMultiple = monkeys.map { it.divisor }.reduce { acc, i -> acc * i } // ruthlessly stolen
    val activeMonkeys = mutableListOf<Long>()
    repeat(monkeys.size) {
        activeMonkeys.add(0)
    }
    for (round in 1..10000) {
        monkeys.forEachIndexed { index, monkey ->
            val startItems = monkey.items.toList()
            startItems.forEach { item ->
                activeMonkeys[index]++
                val worryLevel = monkey.operator(item) % lowestCommonMultiple
                monkey.items.removeFirst()
                if (worryLevel % monkey.divisor == 0L) {
                    monkeys[monkey.trueMonkey].items.add(worryLevel)
                } else {
                    monkeys[monkey.falseMonkey].items.add(worryLevel)
                }
            }
        }
    }
    activeMonkeys.sortDescending()
    return activeMonkeys.take(2).reduce { acc, i -> acc * i }
}

data class Monkey(
    val items: MutableList<Long>,
    val operator: (Long) -> (Long),
    val divisor: Int,
    val trueMonkey: Int,
    val falseMonkey: Int
) {

    companion object Factory {
        fun parse(file: List<String>): Monkey {
            val items: MutableList<Long> =
                file[1].split(": ").last().split(", ").stream().map { it.toLong() }.toList().toMutableList()
            val op = file[2].split("old ").last().split(" ").first()
            val secondArgument = file[2].split("old ").last().split(" ").last()
            val operator: (a: Long) -> (Long) = if (secondArgument.toIntOrNull() == null) {
                when (op) {
                    "*" -> { x: Long -> x * x }
                    "+" -> { x: Long -> x + x }
                    else -> throw InputMismatchException("check input")
                }
            } else {
                when (op) {
                    "*" -> { x: Long -> x * secondArgument.toInt() }
                    "+" -> { x: Long -> x + secondArgument.toInt() }
                    else -> throw InputMismatchException("check input")
                }
            }
            val divisor = file[3].split(" ").last().toInt()
            val trueMonkey = file[4].split(" ").last().toInt()
            val falseMonkey = file[5].split(" ").last().toInt()
            return Monkey(items, operator, divisor, trueMonkey, falseMonkey)
        }
    }
}
