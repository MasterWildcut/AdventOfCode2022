import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day12KtTest {

    @Test
    fun part23() {
        assertEquals(31, part23("src/test/resources/Day12_01.txt"))
    }

    @Test
    fun part24() {
        assertEquals(29, part24("src/test/resources/Day12_01.txt"))
    }


    @Test
    fun isConnected() {
        assertTrue(isConnected('a', 'b'))
        assertTrue(isConnected('S', 'a'))
        assertTrue(isConnected('z', 'E'))
        assertFalse(isConnected('S', 'E'))
        assertFalse(isConnected('a', 'E'))
        assertFalse(isConnected('y', 'E'))
        assertFalse(isConnected('a', 'c'))
        assertTrue(isConnected('c', 'a'))
    }


}
