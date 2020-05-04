package pokecards.elements

class Player {
    var id: Int
    var name: String
    var cards: Deck

    init {
        id = -1
        name = "unknown"
        cards = Deck()
    }
}