package pokecards.elements

import org.junit.jupiter.api.Assertions.*

internal class CardTest {

    @org.junit.jupiter.api.Test
    fun compareTo() {
        var sorted_d = Deck()
        for (j in 2..12)
            for (i in 0..3)
                sorted_d.push(Card(i, j))
        for (j in 0..1)
            for (i in 0..3)
                sorted_d.push(Card(i, j))
        println()
        for (i in 0..51)
            for (j in i+1..51)
                assertEquals(true, sorted_d[i]!! < sorted_d[j])
    }
}