package Client
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.lang.Exception
import java.net.ServerSocket
import java.net.Socket
import kotlin.system.exitProcess
import java.util.Scanner
class ClientSocket(host: String, port: Int) : Socket(host, port) {
    var reader = BufferedReader(InputStreamReader(inputStream))
    var writer = PrintWriter(outputStream, true)
    val cmd_in = Scanner(System.`in`)
    fun update() {
        println("msg waiting ...")
        while (true) {
            val msg = read()
            if (msg == "exit") exitProcess(0)
            println(msg)

            if (msg.length > 0 && msg[0] == '#') {
                val resp = cmd_in.nextLine()
                write(resp)
            }
        }
    }
    fun read(): String {
        val line = reader.readLine()
        return line
    }
    fun write(msg: String) {
        writer.println(msg)
    }

}

