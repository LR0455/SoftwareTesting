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
    fun initTable() {
        // initial
        deck = Deck()
        discards = Deck()
        players = ArrayList<Player?>()
        turn = 0
        for (i in 0..3)
            players!!.add(Player())

        // generate card on the table
        for (i in 0..3)
            for (j in 0..12)
                deck!!.push(Card(i, j))
        deck!!.shuffle()

        // player
        val cmd_in = Scanner(System.`in`)
        for (i in 0..3) {
            println("Please enter your name:")
//            players!![i]?.name = cmd_in.nextLine()
//            players!![i]?.id = i
        }

    }

    fun sortDiscardsIntoDeck() {}
    fun allotCardsToPlayers() {
        for (i in 0..51) {
            //players!![i % 4]!!.cards.push(deck!![i])
            deck!![i]?.let { players!![i % 4]!!.cards.push(it) }
            if (deck!![i]!!.getSuit() == 0 && deck!![i]!!.getRank() == 2)
                turn = i % 4
        }

        deck?.clear()
        for (i in 0..3)
            players!![i]?.cards!!.sort()

    }

    fun discardCard(c: Card?) {}
    fun discardCards(c: Array<Card?>?) {}
}