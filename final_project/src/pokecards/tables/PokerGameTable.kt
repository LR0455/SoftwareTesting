package pokecards.tables

import pokecards.elements.Card
import pokecards.elements.Deck
import pokecards.elements.Player
import java.util.*

class PokerGameTable {
    var deck: Deck? = null
    var discards: Deck? = null
    var players: ArrayList<Player?>? = null
    var turn = 0
    var player_num = 0
    fun initTable(player_num: Int) {
        if (player_num != 2 && player_num != 4) throw IllegalArgumentException("PokerGameTable.initTable")
        // initial
        deck = Deck()
        discards = Deck()
        players = ArrayList<Player?>()
        turn = 0
        for (i in 0..player_num-1)
            players!!.add(Player())
        this.player_num = player_num

        // generate card on the table
        for (i in 0..3)
            for (j in 0..12)
                deck!!.push(Card(i, j))
        deck!!.shuffle()

        // player
        val cmd_in = Scanner(System.`in`)
        for (i in 0..player_num-1) {
            println("Please enter your name:")
            var name = cmd_in.nextLine()
            players!![i]?.modify(i, name)
        }

    }

    fun sortDiscardsIntoDeck() {
        deck?.clear()
        discards?.sort()
        deck!!.addAll(discards!!)
        discards!!.clear()
    }
    fun allotCardsToPlayers() {
        for (i in 0..51) {
            players!![i % player_num]!!.cards.push(deck!![i])
            // C3 -> first turn
            if (deck!![i]!!.getSuit() == 0 && deck!![i]!!.getRank() == 2)
                turn = i % player_num
        }

        deck?.clear()
        for (i in 0..player_num-1)
            players!![i]?.cards!!.sort()
    }

    fun discardCards() {
        var ply_cards = players!![turn]?.cards
        if (ply_cards != null) {
            ply_cards.removeAll(discards!!)
        }
        sortDiscardsIntoDeck()
    }
}