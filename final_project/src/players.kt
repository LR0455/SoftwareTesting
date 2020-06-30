import Client.ClientSocket
import java.lang.Exception

fun main () {
    val host = "127.0.0.1"
    val port = 2333
    try {
        Thread {
            var client = ClientSocket(host, port)
            client.update()
        }.start()
    }
    catch (e: Exception) {
        e.printStackTrace()
    }
}

