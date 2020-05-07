package big2.cardpatterns

import pokecards.elements.Deck

class CardsPatternAnalyzer {
    var vald = CardsPatternValidator()

    fun analyzer(cards: Deck, deck: Deck): Boolean {
        var card_valid = vald.validator(cards)
        if (deck.empty()) return if (card_valid != -1) true else false

        var deck_valid = vald.validator(deck)

        if (card_valid == -1 || deck_valid == -1) return false

        var cards_cp = CardPattern(card_valid, cards)
        var deck_cp = CardPattern(deck_valid, deck)
        return if (cards_cp > deck_cp) true else false
    }
}