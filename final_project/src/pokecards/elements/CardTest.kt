package pokecards.elements

import org.junit.jupiter.api.Assertions.*
import kotlin.test.assertFailsWith

internal class CardTest {

    @org.junit.jupiter.api.Test
    fun testInvalidCard() {
        assertFailsWith(IllegalArgumentException::class) {
            Card(-1, -2) // i, i
        }
        assertFailsWith(IllegalArgumentException::class) {
            Card(10, 20) // i, i
        }
        assertFailsWith(IllegalArgumentException::class) {
            Card(-1, 2) // i, v
        }
        assertFailsWith(IllegalArgumentException::class) {
            Card(10, 2) // i, v
        }
        assertFailsWith(IllegalArgumentException::class) {
            Card(1, -2) // v, i
        }
        assertFailsWith(IllegalArgumentException::class) {
            Card(1, 20) // v, i
        }
    }
    @org.junit.jupiter.api.Test
    fun testCompare() {
        var d = Deck()
        for (j in 2..12)
            for (i in 0..3)
                d.push(Card(i, j))
        for (j in 0..1)
            for (i in 0..3)
                d.push(Card(i, j))

        for (i in 0..51)
            for (j in i+1..51)
                assertEquals(true, d[i]!! < d[j])
    }
    @org.junit.jupiter.api.Test
    fun testShow() {
        assertEquals("CK", Card(0, 12).show());
        assertEquals("DA", Card(1, 0).show());
        assertEquals("HJ", Card(2, 10).show());
        assertEquals("ST", Card(3, 9).show());
    }

}