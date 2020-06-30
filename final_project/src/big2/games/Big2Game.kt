package big2.games

import Server.Server
import big2.cardpatterns.CardsPatternAnalyzer
import big2.cardpatterns.CardsPatternValidator
import pokecards.elements.Card
import pokecards.elements.Deck
import pokecards.tables.PokerGameTable
import java.util.*
import kotlin.collections.ArrayList

class Big2Game(var player_num: Int) {
    var PlaysTable: PokerGameTable
    init {
        PlaysTable = PokerGameTable()
    }

    fun startGame(server: Server) {
        val table = PlaysTable
        table.initTable(player_num)

        // player
        for (i in 0..table.player_num-1) {
            server.client_list[i].write("#Please enter your name:")
            var name = server.client_list[i].read()
            println(name)
            table.players!![i]?.modify(i, name)
        }

        table.allotCardsToPlayers()

        var pass_counts = 0
        var first = true
        var manager = CardsCommitManager()

        while(true) {
            // local variable
            var turn = table.turn
            var player = table.players?.get(turn)
            var player_num = table.player_num

            for (i in 0..player_num-1)
                server.client_list[i].write("-------------------------------")
            if (player != null)
                for (i in 0..player_num-1) {
                    server.client_list[i].write("This is ${player.name}'s turn.")
                }
            // table's cards
            for (i in 0..player_num-1)
                server.client_list[i].write("table : ${table.deck?.show()}")

            // player's cards
            if (player != null)
                server.client_list[turn].write("\npass " + player.cards.show())

            // cards' option
            var hand_card = ""
            if (player != null)
                for (i in 0..player.cards.size) {
                    // i + 'a' - 1 (0 is pass)
                    if (i != 0)
                        hand_card += "  ${(i+97-1).toChar()}"
                    else
                        hand_card += "  0 "
                }
            hand_card += "\n"
            server.client_list[turn].write(hand_card)

            // player choose cards' option
            server.client_list[turn].write("#Please choose cards that you want :")
            var line = server.client_list[turn].read().replace("\\s".toRegex(), "")
            var max_num = player?.cards?.size

            // pass
            if (line.length == 1 && line[0] == '0') {
                if (first) {
                    server.client_list[turn].write("You are first player, NOT allow to pass.")
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
                for (i in 0..player_num-1) {
                    if (i == turn)
                        server.client_list[i].write("You choose wrong cards, please choose again.")
                    else
                        server.client_list[i].write("${player!!.name} choose wrong cards, wait ${player.name} choose again.")
                }

                continue
            }

            // enter cards' number
            for (i in 0..line.length-1) {
                var pos = -1
                if (line[i] in 'a'..'z')
                    pos = line[i].toInt() - 'a'.toInt()
                table.discards?.push(player?.cards?.get(pos))
            }
            var result = manager.commit(table.discards!!, table.deck!!, player!!.cards)
            if (result == false) {
                for (i in 0..player_num-1) {
                    if (i == turn)
                        server.client_list[i].write("You choose wrong cards, please choose again.")
                    else
                        server.client_list[i].write("${player.name} choose wrong cards, wait ${player.name} choose again.")
                }
                table.discards!!.clear()
                continue
            }
            else {
                for (i in 0..player_num-1)
                    table.discards?.show()?.let { server.client_list[i].write("${player!!.name} play ${it}") }

                table.discardCards()
                if (player != null)
                    if (player.cards!!.empty()) {
                        for (i in 0..player_num-1) {
                            server.client_list[i].write("${player?.name} win!!")
                            server.client_list[i].write("exit")
                        }
                        break
                    }
                table.turn = (turn + 1) % player_num
                pass_counts = 0
            }

            // avoid first turn pass
            first = false
            server.client_list[turn].write("-------------------------------")
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