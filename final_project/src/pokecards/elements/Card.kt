package pokecards.elements

class Card(var suit: Int, var rank: Int) : Comparable<Card?> {
    var suit_symbol = arrayOf("C", "D", "H", "S")
    var rank_symbol = arrayOf("A", "2", "3", "4", "5",
                              "6", "7", "8", "9", "T",
                              "J", "Q", "K")

    override fun compareTo(c: Card?): Int {
        var r1 = if (rank < 2) rank + 13 else rank
        var r2 = if (c!!.rank < 2) c.rank + 13 else c.rank
        if (r1 == r2)
            return Integer.compare(suit, c.suit)
        return Integer.compare(r1, r2)
    }
    fun show() {
        print(suit_symbol[suit])
        print(rank_symbol[rank])
    }
}
