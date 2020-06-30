package Server

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.lang.Exception
import java.net.ServerSocket
import java.net.Socket
import java.util.*
import kotlin.system.exitProcess
import big2.games.*;

class Server(port: Int) : ServerSocket(port) {
    var client_list = mutableListOf<ClientHandler>()
    var turn = 0

    fun waitPlayer() {
        var cmd_in = Scanner(System.`in`)
        println("Please input number of players:")
        var player_num = cmd_in.nextInt()
        if (player_num != 2 && player_num != 4) throw IllegalArgumentException("Server.waitPlayer")

        println("Player waiting ...")
        while (client_list.size < player_num) {
            val client = accept()
            client_list.add(ClientHandler(client, this))
            println("new Player ...")
        }
        println("Big2 starting ...")
        var game = Big2Game(player_num)
        game.startGame(this)
    }
    class ClientHandler(client: Socket, var server: Server) {
        var reader = BufferedReader(InputStreamReader(client.getInputStream()))
        var writer = PrintWriter(client.getOutputStream(), true)

        fun read(): String {
            var line = reader.readLine()
            return line
        }

        fun write(msg: String) {
            writer.println(msg)
        }
    }
}