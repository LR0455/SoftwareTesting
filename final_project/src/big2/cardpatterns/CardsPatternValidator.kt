package big2.cardpatterns

import pokecards.elements.Card
import pokecards.elements.Deck

class CardsPatternValidator {
    fun validator(cards: Deck):Int {
        if (isStraightFlush(cards)) return 0
        if (isFourOfRank(cards)) return 1
        if (isFullHouse(cards)) return 2
        if (isStraight(cards)) return 3
        if (isPair(cards)) return 4
        if (isSingle(cards)) return 5
        return -1
    }
    fun isStraightFlush(cards: Deck):Boolean {
        if (isStraight(cards) == false)
            return false
        for (i in 1..cards.size-1)
            if (cards[i-1]!!.getSuit() != cards[i]!!.getSuit())
                return false
        return true
    }
    fun isFourOfRank(cards: Deck):Boolean {
        if (cards.size != 5) return false
        var counts = Array<Int>(13) {i -> 0}
        var four = false
        for (i in 0..cards.size-1)
            counts[cards[i]?.getRank()!!] ++
        for (i in 0..12)
            if (counts[i] == 4)
                four = true
        if (four)
            return true
        return false
    }
    fun isFullHouse(cards: Deck):Boolean {
        if (cards.size != 5) return false
        var counts = Array<Int>(13) {i -> 0}
        var three = false
        var two = false
        for (i in 0..cards.size-1)
            counts[cards[i]?.getRank()!!] ++
        for (i in 0..12) {
            if (counts[i] == 3)
                three = true
            if (counts[i] == 2)
                two = true
        }
        if (three && two)
            return true
        return false
    }
    fun isStraight(cards: Deck):Boolean {
        if (cards.size != 5) return false
        var exist = Array<Boolean>(13){i -> false}
        for (i in 0..cards.size-1) {
            if (exist[cards[i]?.getRank()!!] == true) // same
                return false
            exist[cards[i]?.getRank()!!] = true
        }

        // continuous
        var left = 0
        var right = 0
        var index = cards[0]?.getRank()
        for (i in 1..4) {
            var pos = (index!! - i + 13) % 13
            if (exist[pos] == false)
                break
            left ++
        }
        for (i in 1..4) {
            var pos = (index!! + i) % 13
            if (exist[pos] == false)
                break
            right ++
        }
        if (left + right + 1 != 5)
            return false

        // invalid straight -> JQKA2, QKA23, KA234
        if ((exist[10] && exist[1]) || (exist[11] && exist[2]) || (exist[12] && exist[3]))
            return false
        return true
    }
    fun isPair(cards: Deck):Boolean {
        return if (cards.size == 2 && (cards.get(0)!!.getRank() == cards.get(1)!!.getRank())) true else false
    }
    fun isSingle(cards: Deck):Boolean {
        return if (cards.size == 1) true else false
    }
}