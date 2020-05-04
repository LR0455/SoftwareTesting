package big2.cardpatterns

import pokecards.elements.Deck

class CardsPatternAnalyzer {
    var vald = CardsPatternValidator()

    fun analyzer(cards: Deck, deck: Deck): Int {
        var v1 = vald.validator(cards)
        if (deck.empty()) return if (v1 != -1) 1 else -1

        var v2 = vald.validator(deck)

        if (v1 == -1 || v2 == -1) return -1

        var cp1 = CardPattern(v1, cards)
        var cp2 = CardPattern(v2, deck)
        return if (cp1 > cp2) 1 else -1
    }
}