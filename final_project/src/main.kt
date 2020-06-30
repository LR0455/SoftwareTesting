
import big2.games.*;
import java.lang.Exception;
import Server.Server
import Client.ClientSocket

fun main() {
    val host = "127.0.0.1"
    val port = 2333
    try {
        Thread {
            var server = Server(port)
            server.waitPlayer()
        }.start()
    }
    catch (e:Exception) {
        e.printStackTrace()
    }

}