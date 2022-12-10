import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day10KtTest {

    @Test
    fun part19() {
        assertEquals(13140, part19("src/test/resources/Day10_01.txt"))
    }

    @Test
    fun part20() {
        assertEquals(File("src/test/resources/Day10_02.txt").readText(), part20("src/test/resources/Day10_01.txt"))
    }
}
