package big2.cardpatterns

import org.junit.jupiter.api.Assertions.*
import pokecards.elements.Card
import pokecards.elements.Deck
import kotlin.random.Random.Default.nextInt

internal class CardsPatternValidatorTest {
    @org.junit.jupiter.api.Test
    fun validator() {
    }

    @org.junit.jupiter.api.Test
    fun isFourOfRank() {
        var cpv = CardsPatternValidator()
        var d = Deck()
        for (i in 1..12) {
            for(j in 0..3)
                d.push(Card(j, i))
            d.push(Card(0, 0))

            if (nextInt(2) == 0) {
                var pos = nextInt(4)
                d[pos]!!.rank = (d[pos]!!.rank + 1) % 13
                assertEquals(false, cpv.isFourOfRank(d))
            }
            else
                assertEquals(true, cpv.isFourOfRank(d))
            d.clear()
        }
    }

    @org.junit.jupiter.api.Test
    fun isFullHouse() {
        var cpv = CardsPatternValidator()
        var d = Deck()
        for (i in 1..12) {
            for(j in 0..2)
                d.push(Card(j, i))
            for (j in 2..3)
                d.push(Card(j, (i+1)%13))
            if (nextInt(2) == 0) {
                var pos = nextInt(4)
                d[pos]?.rank = (d[pos]?.rank?.plus(2))?.rem(13)!!
                assertEquals(false, cpv.isFullHouse(d))
            }
            else
                assertEquals(true, cpv.isFullHouse(d))
            d.clear()
        }
    }

    @org.junit.jupiter.api.Test
    fun isStraightFlush() {
        var cpv = CardsPatternValidator()
        var d = Deck()
        for (i in 0..9) {
            var suit = nextInt(4)
            for (j in 0..4)
                d.push(Card(suit, (i + j) % 13))
            if (nextInt(2) == 0) {
                var pos = nextInt(4)
                d[pos]?.rank = (d[pos]?.rank?.plus(1))?.rem(13)!!
                assertEquals(false, cpv.isStraightFlush(d))
            }
            else
                assertEquals(true, cpv.isStraightFlush(d))
            d.clear()
        }
        for (i in 10..12) {
            for (j in 0..4)
                d.push(Card(nextInt(4), (i + j) % 13))
            assertEquals(false, cpv.isStraightFlush(d))
            d.clear()
        }
    }

    @org.junit.jupiter.api.Test
    fun isStraight() {
        var cpv = CardsPatternValidator()
        var d = Deck()
        for (i in 0..9) {
            for (j in 0..4)
                d.push(Card(nextInt(4), (i + j) % 13))
            if (nextInt(2) == 0) {
                var pos = nextInt(4)
                d[pos]?.rank = (d[pos]?.rank?.plus(1))?.rem(13)!!
                assertEquals(false, cpv.isStraight(d))
            }
            else
                assertEquals(true, cpv.isStraight(d))
            d.clear()
        }
        for (i in 10..12) {
            for (j in 0..4)
                d.push(Card(nextInt(4), (i + j) % 13))
            assertEquals(false, cpv.isStraight(d))
            d.clear()
        }
    }

    @org.junit.jupiter.api.Test
    fun isPair() {
        var cpv = CardsPatternValidator()
        var d = Deck()
        for (i in 0..12) {
            d.push(Card(3,i))
            if (nextInt(2) == 0) {
                d.push(Card(nextInt(3),i))
                assertEquals(true, cpv.isPair(d))
            }
            else
                assertEquals(false, cpv.isPair(d))
            d.clear()
        }
    }

    @org.junit.jupiter.api.Test
    fun isSingle() {
        var cpv = CardsPatternValidator()
        var d = Deck()
        for (i in 0..12) {
            d.push(Card(3,i))
            if (nextInt(2) == 0) {
                d.push(Card(nextInt(3),i))
                assertEquals(false, cpv.isSingle(d))
            }
            else
                assertEquals(true, cpv.isSingle(d))
            d.clear()
        }
    }
}