package pokecards.elements

import java.util.*

class Deck : Stack<Card?>() {
    fun shuffle() {
        Collections.shuffle(this)
    }

    fun sort() {
        Collections.sort(this)
    }
}