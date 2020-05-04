package pokecards.elements

class Card(private var suit: Int, private var rank: Int) : Comparable<Card?> {
    var suit_symbol = arrayOf("C", "D", "H", "S")
    var rank_symbol = arrayOf("A", "2", "3", "4", "5",
                              "6", "7", "8", "9", "T",
                              "J", "Q", "K")

    init {
        if (suit !in 0..3) throw IllegalArgumentException("Card.modify-suit")
        if (rank !in 0..12) throw IllegalArgumentException("Card.modify-rank")
    }

    override fun compareTo(c: Card?): Int {
        var r1 = if (rank < 2) rank + 13 else rank
        var r2 = if (c!!.getRank() < 2) c.getRank() + 13 else c.getRank()
        if (r1 == r2)
            return Integer.compare(suit, c.getSuit())
        return Integer.compare(r1, r2)
    }
    fun getSuit(): Int {
        return suit
    }
    fun getRank(): Int {
        return rank
    }
    fun show():String {
        var res = "${suit_symbol[suit]}${rank_symbol[rank]}"
        return res
    }
}
