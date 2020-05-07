package big2.games

import big2.cardpatterns.CardsPatternAnalyzer
import pokecards.elements.Deck

class CardsCommitManager {

    fun commit(cards: Deck, deck: Deck, ply_cards: Deck): Boolean {
        var anly = CardsPatternAnalyzer()
        return anly.analyzer(cards, deck)
    }
}