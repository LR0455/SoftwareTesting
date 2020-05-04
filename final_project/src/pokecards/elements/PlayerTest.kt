package pokecards.elements

import org.junit.jupiter.api.Assertions.*
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertFailsWith

internal class PlayerTest {

    @org.junit.jupiter.api.Test
    fun testDefaultName() {
        var p = Player()
        assertEquals("name : unknown, id : -1", p.show())
    }

    @org.junit.jupiter.api.Test
    fun testInvalidId() {
        assertFailsWith(IllegalArgumentException::class) {
            var p = Player()
            p.modify(-1, "test")
        }
    }
    @org.junit.jupiter.api.Test
    fun testShow() {
        var p = Player()
        p.modify(3, "test")
        assertEquals("name : test, id : 3", p.show())
    }

    @org.junit.jupiter.api.Test
    fun testCards() {
        var p = Player()
        assertNotNull(p.cards)
    }
}