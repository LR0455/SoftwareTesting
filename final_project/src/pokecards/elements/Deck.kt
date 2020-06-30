package pokecards.elements

import java.util.*

class Deck : Stack<Card?>() {
    fun shuffle() {
        Collections.shuffle(this)
    }

    fun sort() {
        Collections.sort(this)
    }

    fun show():String {
        if (this.empty() == true) return ""

        var str_cards = ""
        for (i in 0..this.size-1)
            str_cards += this[i]!!.show() + " "

        return str_cards
    }
}