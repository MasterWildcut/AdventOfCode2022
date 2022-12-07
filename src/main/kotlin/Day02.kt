import java.io.File
import java.util.*

fun main() {
    println(part03("src/main/resources/Day02_01"))
    println(part04("src/main/resources/Day02_01"))
}

fun part03(fileName: String): Int {
    return File(fileName).useLines { lines ->
        lines.map { play(it.split(' ')) }.sum()
    }
}

fun part04(fileName: String): Int {
    return File(fileName).useLines { lines ->
        lines.map { play2(it.split(' ')) }.sum()
    }
}

fun play2(plays: List<String>): Int {
    val opponentPlay = Hand.fromString(plays[0])
    val myPlay = Hand.fromMission(opponentPlay, plays[1])
    return getScore(opponentPlay, myPlay)
}

// A x Rock
// B Y Paper
// C Z Scissors
fun play(plays: List<String>): Int {
    val opponentPlay = Hand.fromString(plays[0])
    val myPlay = Hand.fromString(plays[1])
    return getScore(opponentPlay, myPlay)
}

private fun getScore(opponentPlay: Hand, myPlay: Hand): Int {
    var score1 = 0
    if (opponentPlay == myPlay) {
        score1 += 3
    } else {
        when (opponentPlay) {
            Hand.ROCK -> if (myPlay == Hand.PAPER) score1 += 6
            Hand.SCISSOR -> if (myPlay == Hand.ROCK) score1 += 6
            Hand.PAPER -> if (myPlay == Hand.SCISSOR) score1 += 6
        }
    }

    score1 += myPlay.score
    return score1
}

enum class Hand(val score: Int) {
    ROCK(1),
    PAPER(2),
    SCISSOR(3);

    companion object {
        fun fromString(value: String): Hand {
            return when (value) {
                "A", "X" ->  ROCK
                "B", "Y" ->  PAPER
                "C", "Z" ->  SCISSOR
                else -> {throw InputMismatchException(value)}
            }
        }
        // X lose
        // Y draw
        // Z win
        fun fromMission(opponent: Hand, mission: String): Hand {
            return when (mission) {
                "X" -> when(opponent){
                    ROCK -> SCISSOR
                    PAPER -> ROCK
                    SCISSOR -> PAPER
                }
                "Y" -> opponent
                "Z" -> when(opponent){
                    ROCK -> PAPER
                    PAPER -> SCISSOR
                    SCISSOR -> ROCK
                }

                else -> {throw InputMismatchException(mission)}
            }
        }
    }
}

