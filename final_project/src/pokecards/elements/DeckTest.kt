package pokecards.elements

import org.junit.jupiter.api.Assertions.*

internal class DeckTest {

    @org.junit.jupiter.api.Test
    fun testSort() {
        var d = Deck()
        for (i in 0..12)
            d.push(Card(i%4, i))
        d.sort()

        for (i in 0..12)
            for (j in i+1..12) {
                assertEquals(true, d[i]!! < d[j])
                assertEquals(false, d[i]!! > d[j])
            }
    }

    @org.junit.jupiter.api.Test
    fun testShow() {
        var d = Deck()
        d.push(Card(0, 0))
        d.push(Card(3, 1))
        d.push(Card(2, 10))
        assertEquals("CA S2 HJ ", d.show())
    }
}