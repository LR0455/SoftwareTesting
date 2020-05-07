package big2.games

import big2.cardpatterns.CardsPatternAnalyzer
import big2.cardpatterns.CardsPatternValidator
import pokecards.elements.Card
import pokecards.elements.Deck
import pokecards.tables.PokerGameTable
import java.util.*
import kotlin.collections.ArrayList

class Big2Game {
    var fourPlaysTable: PokerGameTable
    init {
        fourPlaysTable = PokerGameTable()
    }

    fun startGame() {
        val cmd_in = Scanner(System.`in`)

        val table = fourPlaysTable
        table.initTable(2)
        table.allotCardsToPlayers();

        var pass_counts = 0
        var first = true
        while(true) {
            // local variable
            var turn = table.turn
            var player = table.players?.get(turn)
            var player_num = table.player_num

            println("-------------------------------")
            if (player != null)
                println("This is ${player.name}'s turn.")

            // table's cards
            print("table :")
            if (table.deck!!.empty() == false)
                for (i in 0..table.deck!!.size-1)
                    print(" ${table.deck!![i]?.show()}")

            // player's cards
            print("\npass ")
            if (player != null)
                for (i in 0..player.cards.size-1)
                    print("${player.cards[i]?.show()} ")
            println()
            // cards' option
            if (player != null)
                for (i in 0..player.cards.size) {
                    // i + 'a' - 1 (0 is pass)
                    if (i != 0)
                        print("  ${(i+97-1).toChar()}")
                    else
                        print("  0 ")
                }
            println()

            // player choose cards' option
            println("Please choose cards that you want :")
            var line = cmd_in.nextLine().replace("\\s".toRegex(), "")
            var max_num = player?.cards?.size

            // pass
            if (line.length == 1 && line[0] == '0') {
                if (first) {
                    println("You are first player, NOT allow to pass.")
                    continue
                }

                table.turn = (turn + 1) % player_num
                pass_counts ++
                if (pass_counts == player_num - 1) {
                    table.deck!!.clear()
                    pass_counts = 0
                }
                continue
            }

            // enter wrong cards' number
            if (max_num?.let { isChooseValid(line, it) } == false) {
                println("You choose wrong cards, please choose again.")
                continue
            }

            // enter cards' number
            for (i in 0..line.length-1) {
                var pos = -1
                if (line[i] in 'a'..'z')
                    pos = line[i].toInt() - 'a'.toInt()
                table.discards?.push(player?.cards?.get(pos))
            }

            for (i in 0..table.discards!!.size-1)
                print("${table.discards?.get(i)?.show()} ")
            println()

            var manager = CardsCommitManager()
            var result = manager.commit(table.discards!!, table.deck!!, player!!.cards)
            if (result == false)
                println("You choose wrong cards, please choose again.")
            else {
                table.discardCards()
                if (player != null)
                    if (player.cards!!.empty()) {
                        print("${player?.name} win!!")
                        break
                    }
                table.turn = (turn + 1) % player_num
                pass_counts = 0
            }

            // avoid first turn pass
            first = false
            println("-------------------------------")
        }

    }

    fun isChooseValid(line: String, max_num :Int): Boolean {
        var pass = false
        for (i in 0..line.length-1) {
            if (line[i] == ' ') continue // whitespace is valid
            if (line[i] == '0') pass = true
            var num = -1
            if (line[i] in 'a'..'z') num = line[i] - 'a'
            else return false

            if (num > max_num) return false
        }
        // choose pass and other cards' number (ex: 01)
        return if (pass && line.length != 1) false else true
    }
}