import java.io.File

fun main() {
    println(part11("src/main/resources/Day06_01.txt"))
    println(part12("src/main/resources/Day06_01.txt"))
}

fun part11(fileName: String): Int {
    val bufferedReader = File(fileName).bufferedReader()
    val marker = mutableListOf<Int>()
    var i = 4
    marker.add(bufferedReader.read())
    marker.add(bufferedReader.read())
    marker.add(bufferedReader.read())
    while (true) {
        marker.add(bufferedReader.read())
        if (marker.toSet().size == 4) {
            return i
        }
        marker.removeFirst()
        i++
    }
}

fun part12(fileName: String): Int {
    val bufferedReader = File(fileName).bufferedReader()
    val marker = mutableListOf<Int>()
    var i = 14
    for(x in 1..13){
        marker.add(bufferedReader.read())
    }
    while (true) {
        marker.add(bufferedReader.read())
        if (marker.toSet().size == 14) {
            return i
        }
        marker.removeFirst()
        i++
    }
}
