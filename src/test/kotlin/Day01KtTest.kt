import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day01KtTest {

    @Test
    fun part01() {
        assertEquals(24000, part01("src/test/resources/Day01_01"))
    }

    @Test
    fun part02() {
        assertEquals(45000, part02("src/test/resources/Day01_01"))
    }
}
