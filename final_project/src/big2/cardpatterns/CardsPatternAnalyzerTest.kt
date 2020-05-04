package big2.cardpatterns

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import pokecards.elements.Card
import pokecards.elements.Deck
import kotlin.random.Random.Default.nextInt

internal class CardsPatternAnalyzerTest {

    @Test
    fun analyzer() {
        var anly = CardsPatternAnalyzer()
    }
    @Test
    fun testSingle() {
        var anly = CardsPatternAnalyzer()
        for (i in 0..3)
            for (j in 0..12)
                for (x in 0..3)
                    for (y in 0..12) {
                        if (i == x && j == y) continue
                        var c1 = Card(x, y)
                        var c2 = Card(i, j)
                        var cards = Deck()
                        cards.push(c1)
                        var deck = Deck()
                        deck.push(c2)
                        if (c1 < c2)
                            assertEquals(-1, anly.analyzer(cards, deck))
                        else
                            assertEquals(1, anly.analyzer(cards, deck))
                    }

    }
    @Test
    fun testPair() {
        var anly = CardsPatternAnalyzer()
        for (i in 0..2)
            for (j in 0..12)
                for (x in 0..2)
                    for (y in 0..12) {
                        if (i == x && j == y) continue
                        var c1 = Card(x, y)
                        var c2 = Card(x+1, y)
                        var c3 = Card(i, j)
                        var c4 = Card(i+1, j)
                        var cards = Deck()
                        cards.push(c1)
                        cards.push(c2)
                        var deck = Deck()
                        deck.push(c3)
                        deck.push(c4)
                        if (c2 < c4)
                            assertEquals(-1, anly.analyzer(cards, deck))
                        else
                            assertEquals(1, anly.analyzer(cards, deck))
                    }

    }
    @Test
    fun testStraight() {
        var anly = CardsPatternAnalyzer()
        for (i in 0..9)
            for (j in 0..9) {
                if (i == j) continue
                var cards = Deck()
                var deck = Deck()
                for (k in 0..4) {
                    cards.push(Card(nextInt(4), (i + k) % 13))
                    deck.push(Card(nextInt(4), (j + k) % 13))
                }
                var cmp_c1 = if (i == 1) cards[0] else cards[4]
                var cmp_c2 = if (j == 1) deck[0] else deck[4]
                println()
                showCards(cards)
                showCards(deck)
                print("test compare ")
                cmp_c1?.show()
                print(" ")
                cmp_c2?.show()
                println()

                if (cmp_c1 != null) {
                    if (cmp_c1 < cmp_c2)
                        assertEquals(-1, anly.analyzer(cards, deck))
                    else
                        assertEquals(1, anly.analyzer(cards, deck))
                }
            }

    }
    @Test
    fun testStraightFlush() {
        var anly = CardsPatternAnalyzer()
        for (i in 0..9)
            for (j in 0..9) {
                if (i == j) continue
                var cards = Deck()
                var deck = Deck()
                for (k in 0..4) {
                    cards.push(Card(0, (i + k) % 13))
                    deck.push(Card(0, (j + k) % 13))
                }
                var cmp_c1 = if (i == 1) cards[0] else cards[4]
                var cmp_c2 = if (j == 1) deck[0] else deck[4]

                if (cmp_c1 != null) {
                    if (cmp_c1 < cmp_c2)
                        assertEquals(-1, anly.analyzer(cards, deck))
                    else
                        assertEquals(1, anly.analyzer(cards, deck))
                }
            }
        // test StraightFlush and non-StraightFlush
        var cards = Deck()
        for (i in 0..4)
            cards.push(Card(0, i))
        var deck = Deck()
        // test StraightFlush and (Single or Pair)
        for (i in 0..12) {
            deck.push(Card(0, i))
            assertEquals(1, anly.analyzer(cards, deck))
            deck.push(Card(1, i))
            assertEquals(1, anly.analyzer(cards, deck))
            deck.clear()
        }

        // test StraightFlush and (FullHouse or FourOfRank)
        for (i in 0..11) {
            // FullHouse
            for (j in 0..2)
                deck.push(Card(j, i))
            for (j in 0..1)
                deck.push(Card(j, 12))
            assertEquals(1, anly.analyzer(cards, deck))
            deck.clear()

            // FourOfRank
            for (j in 0..3)
                deck.push(Card(j, i))
            for (j in 0..0)
                deck.push(Card(j, 12))
            assertEquals(1, anly.analyzer(cards, deck))
            deck.clear()
        }

        // test StraightFlush and Straight
        for (i in 0..9) {
            for (j in 0..4)
                deck.push(Card(nextInt(4), (i+j)%13))
            assertEquals(1, anly.analyzer(cards, deck))
            deck.clear()
        }
    }
    @Test
    fun testFourOfRank() {
        var anly = CardsPatternAnalyzer()
        for (i in 0..12)
            for (j in 0..12) {
                if (i == j) continue
                var cards = Deck()
                var deck = Deck()
                for (k in 0..3) {
                    cards.push(Card(k, i))
                    deck.push(Card(k, j))
                }
                cards.push(Card(0, (i+1)%13))
                deck.push(Card(0, (j+1)%13))

                if (cards[0]!! < deck[0])
                    assertEquals(-1, anly.analyzer(cards, deck))
                else
                    assertEquals(1, anly.analyzer(cards, deck))
            }

        // test FourOfRank and non-FourOfRank
        var cards = Deck()
        for (i in 0..3)
            cards.push(Card(i, 0))
        cards.push(Card(0, 1))
        var deck = Deck()
        // test FourOfRank and (Single or Pair)
        for (i in 0..12) {
            deck.push(Card(0, i))
            assertEquals(1, anly.analyzer(cards, deck))
            deck.push(Card(1, i))
            assertEquals(1, anly.analyzer(cards, deck))
            deck.clear()
        }

        // test FourOfRank and FullHouse
        for (i in 0..11) {
            for (j in 0..2)
                deck.push(Card(j, i))
            for (j in 0..1)
                deck.push(Card(j, 12))
            assertEquals(1, anly.analyzer(cards, deck))
            deck.clear()
        }

        // test FourOfRank and (StraightFlush, Straight)
        for (i in 0..9) {
            for (j in 0..4)
                deck.push(Card(nextInt(4), (i+j)%13))
            assertEquals(1, anly.analyzer(cards, deck))
            deck.clear()

            for (j in 0..4)
                deck.push(Card(0, (i+j)%13))
            assertEquals(-1, anly.analyzer(cards, deck))
            deck.clear()
        }
    }
    @Test
    fun testFullHouse() {
        var anly = CardsPatternAnalyzer()
        for (i in 0..12)
            for (j in 0..12) {
                if (i == j) continue
                var cards = Deck()
                var deck = Deck()
                for (k in 0..2) {
                    cards.push(Card(k, i))
                    deck.push(Card(k, j))
                }
                for (k in 0..1) {
                    cards.push(Card(k, (i + 1) % 13))
                    deck.push(Card(k, (j + 1) % 13))
                }

                if (i < j)
                    assertEquals(-1, anly.analyzer(cards, deck))
                else
                    assertEquals(1, anly.analyzer(cards, deck))
            }


    }

    fun showCards(cards: Deck) {
        for (i in 0..cards.size-1) {
            cards[i]?.show()
            print(" ")
        }
        println()
    }
}