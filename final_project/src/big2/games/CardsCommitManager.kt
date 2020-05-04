package big2.games

import big2.cardpatterns.CardsPatternAnalyzer
import pokecards.elements.Deck

class CardsCommitManager {

    fun pass() {

    }
    fun commit(cards: Deck, deck: Deck, ply_cards: Deck): Int {
        var anly = CardsPatternAnalyzer()
        if (anly.analyzer(cards, deck) == 1) {
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
            return 1
        }
        return -1
    }
}