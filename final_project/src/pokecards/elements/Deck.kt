package pokecards.elements

import java.util.*
import pokecards.elements.Card;

class Deck : Stack<Card?>() {
    fun shuffle() {
        Collections.shuffle(this)
    }

    fun sort() {
        for (i in 0..this.size-1)
            for (j in i+1..this.size-1)
                if (this.get(i)!! > this.get(j))
                    swap(i, j)
    }
    fun swap(i: Int, j: Int) {
        var temp = this.get(i)
        this.set(i, this.get(j))
        this.set(j, temp)
    }
}