package big2.games

import big2.cardpatterns.CardsPatternAnalyzer
import pokecards.elements.Deck

class CardsCommitManager {

    fun commit(cards: Deck, deck: Deck, ply_cards: Deck): Boolean {
        var anly = CardsPatternAnalyzer()
        if (anly.analyzer(cards, deck) == true) {
            deck.clear()
            for (i in 0..cards.size-1)
                deck.push(cards[i])

            for (i in 0..ply_cards.size-1)
                for (j in 0..cards.size-1) {
                    if (i >= ply_cards.size)
                        continue
                    if (ply_cards[i] == cards[j])
                        ply_cards.removeAt(i)
                }
            return true
        }
        return false
    }
}