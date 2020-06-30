package big2.cardpatterns

import pokecards.elements.Card
import pokecards.elements.Deck

class CardPattern(var pattern: Int, var cards: Deck): Comparable<CardPattern?> {
    override fun compareTo(p: CardPattern?): Int {
        when (pattern) {
            0, 3 -> { // StraightFlush and Straight
                if (p != null) {
                    if (pattern == 0 && p.pattern != 0) return 1 // StraightFlush, any
                    if (pattern == 3 && (p.pattern == 0 || p.pattern == 1)) return -1 // Straight, (StraightFlush, FourOfRank)
                    if (pattern != p.pattern) return -1 // invalid

                    cards.sort()
                    p.cards.sort()
                    // 23456 -sort-> 34562 -get-> 2
                    // 12345 -sort-> 34512 -get-> 5
                    // 34567 -sort-> 34567 -get-> 7

                    var cmp_c1 = if (cards[0]?.getRank() == 2 && cards[5-2]?.getRank() == 0) cards[2] else cards[5-1]
                    var cmp_c2 = if (p.cards[0]?.getRank() == 2 && p.cards[5-2]?.getRank() == 0) p.cards[2] else p.cards[5-1]

                    if (cmp_c1 != null) {
                        return if (cmp_c1 > cmp_c2) 1 else -1
                    }
                }
            }
            1 -> { // FourOfRank
                if (p != null) {
                    if (p.pattern == 0) return -1 // StraightFlush
                    if (p.pattern != 1) return 1
                    var max_c1 = if (cards[0] == cards[1]) cards[0] else cards[1]
                    var max_c2 = if (p.cards[0] == p.cards[1]) p.cards[0] else p.cards[1]
                    if (max_c1 != null) {
                        return if (max_c1 > max_c2) 1 else -1
                    }
                }
            }
            2 -> { // FullHouse
                if (p != null) { // invalid
                    if (pattern != p.pattern) return -1
                }

                var c1 = cards[0]
                if (cards[1]?.getRank() != cards[2]?.getRank())
                    c1 = cards[3]
                var c2 = p!!.cards[0]
                if (p != null) {
                    if (p.cards[1]!!.getRank() != p.cards[2]!!.getRank())
                        c2 = p.cards[3]
                }
                if (c1 != null) {
                    return if (c1 > c2) 1 else -1
                }
            }
            4, 5 -> { // Pair and Single
                if (p != null) { // invalid
                    if (pattern != p.pattern) return -1
                }
                if (p != null) {
                    if (pattern == 4)
                        return if (cards[1]!! > p.cards[1]) 1 else -1
                    else
                        return if (cards[0]!! > p.cards[0]) 1 else -1
                }
            }
            else -> return -1
        }
        return -1
    }
}