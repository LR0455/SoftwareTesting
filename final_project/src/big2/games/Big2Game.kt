package big2.games

import big2.cardpatterns.CardsPatternAnalyzer
import big2.cardpatterns.CardsPatternValidator
import pokecards.elements.Card
import pokecards.elements.Deck
import pokecards.tables.PokerGameTable
import java.util.*
import kotlin.collections.ArrayList

class Big2Game {
    var fourPlaysTalbe: PokerGameTable
    fun startGame() {
        val cmd_in = Scanner(System.`in`)

        val table = PokerGameTable()
        table.initTable()
        table.allotCardsToPlayers();

        var pass_counts = 0
        while(true) {
            println("-------------------------------")
            print("This is ")
            print(table.players?.get(table.turn)?.name)
            println("'s turn.")
            println("table :")
            if (table.deck!!.empty() == false) {
                for (i in 0..table.deck!!.size-1) {
                    table.deck!![i]?.show()
                    print(" ")
                }
            }

            println()
            print("pass ")
            for (i in 0..table.players?.get(table.turn)?.cards!!.size - 1) {
                table.players?.get(table.turn)?.cards?.get(i)?.show()
                print(" ")
            }
            println()
            for (i in 0..table.players?.get(table.turn)?.cards!!.size) {
                if (i != 0) {
                    print("  ")
                    print(Integer.toHexString(i))
                }
                else
                    print("  0 ")
            }
            println()

            println("Please choose cards that you want :")
            var line = cmd_in.nextLine()
            var cards = Deck()
            var max_num = table.players!![table.turn]?.cards?.size

            // enter wrong cards' number
            if (max_num?.let { isChooseValid(line, it) } == -1) {
                println("You choose wrong cards, please choose again.")
                continue
            }
            // pass
            if (line.length == 1 && line[0] == '0') {
                table.turn = (table.turn + 1) % 4
                pass_counts ++
                if (pass_counts == 3) {
                    table.deck!!.clear()
                    pass_counts = 0
                }
                continue
            }

            // enter cards' number
            for (i in 0..line.length-1) {
                var pos = -1
                if (line[i] in '1'..'9')
                    pos = line[i].toInt() - '0'.toInt() - 1
                if (line[i] in 'a'..'d')
                    pos = line[i].toInt() - 'a'.toInt() + 10 - 1
                if (pos != -1)
                    cards.push(table.players?.get(table.turn)?.cards?.get(pos))
            }
            for (i in 0..cards.size-1) {
                cards[i]?.show()
                print(" ")
            }
            println()

            var manager = CardsCommitManager()
            var result = table.deck?.let { table.players!!.get(table.turn)?.cards?.let { it1 -> manager.commit(cards, it, it1) } }
            if (result == -1)
                println("You choose wrong cards, please choose again.")
            else {
                if (table.players!![table.turn]?.cards!!.size == 0) {
                    print(table.players!![table.turn]?.name)
                    println(" win !!")
                    break
                }
                table.turn = (table.turn + 1) % 4
                pass_counts = 0
            }
            println("-------------------------------")
        }

    }
    fun isChooseValid(line: String, max_num :Int): Int {
        var pass = false
        for (i in 0..line.length-1) {
            if (line[i] == ' ') continue // whitespace is valid
            if (line[i] == '0') pass = true
            var num = -1
            if (line[i] in '0'..'9')
                num = line[i] - '0'
            else if (line[i] in 'a'..'d')
                num = line[i] - 'a' + 10
            else
                return -1
            if (num > max_num)
                return -1
        }
        // choose pass and other cards' number (ex: 01)
        return if (pass && line.length != 1) -1 else 1
    }
    init {
        fourPlaysTalbe = PokerGameTable()
    }
}