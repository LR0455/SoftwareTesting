package Server

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.lang.Exception
import java.net.ServerSocket
import java.net.Socket
import kotlin.system.exitProcess

class Server(port: Int) : ServerSocket(port) {
    var clientList = mutableListOf<ClientHandler>()
    var turn = -1
    fun waitPlayer() {
        while (true) {
            if (clientList.size == 4) continue
            val client = accept()
            clientList.add(ClientHandler(client, this).build())
        }
    }

    fun sendMessage(msg: String) {
        clientList[turn].write(msg)
    }

    class ClientHandler(client: Socket, var target: Server) : Thread() {

        var reader = BufferedReader(InputStreamReader(client.getInputStream()))
        var writer = PrintWriter(client.getOutputStream(), true)

        fun build(): ClientHandler {
            start()
            return this
        }

        override fun run() {
            while (true) {
                val line = reader.readLine()
                target.sendMessage(line)
            }
        }

        fun write(msg: String) {
            writer.println(msg)
        }
    }
}