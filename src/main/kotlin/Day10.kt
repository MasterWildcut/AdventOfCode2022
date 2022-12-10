import java.io.File

fun main() {
    println(part19("src/main/resources/Day10_01.txt"))
    println(part20("src/main/resources/Day10_01.txt"))
}

fun part19(fileName: String): Int {
    var cycle = 0
    var register = 1
    val interestingSignals = mutableListOf<Int>()
    File(fileName).readLines().forEach {
        cycle++
        checkInterestingCycle(cycle, interestingSignals, register)

        if (it.startsWith("addx")) {
            cycle++
            checkInterestingCycle(cycle, interestingSignals, register)
            register += it.split(" ")[1].toInt()
        }
    }
    return interestingSignals.sum()
}

fun part20(fileName: String): String {
    var cycle = 0
    var register = 1
    val crt = mutableListOf<Char>()
    File(fileName).readLines().forEach {
        cycle++
        draw(cycle, crt, register)

        if (it.startsWith("addx")) {
            cycle++
            draw(cycle, crt, register)
            register += it.split(" ")[1].toInt()
        }
    }
    var result = ""
    crt.stream().forEach { result = result.plus(it) }
    result = result.plus("\n")
    return result
}

fun draw(cycle: Int, crt: MutableList<Char>, register: Int) {
    if ((cycle - 1) % 40 == 0 && cycle > 1) {
        crt.add("\n".toCharArray().first())
    }
    if (((cycle - 1) % 40) in IntRange(register - 1, register + 1)) {
        crt.add('#')
    } else {
        crt.add('.')
    }
}

private fun checkInterestingCycle(
    cycle: Int,
    interestingSignals: MutableList<Int>,
    register: Int
) {
    if (cycle == 20 || ((cycle > 20) && ((cycle - 20) % 40 == 0))) {
        interestingSignals.add(cycle * register)
    }
}
