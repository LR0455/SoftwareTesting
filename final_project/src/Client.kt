package Client
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.lang.Exception
import java.net.ServerSocket
import java.net.Socket
import kotlin.system.exitProcess

class ClientSocket(host: String, port: Int) : Socket(host, port) {
    var reader = BufferedReader(InputStreamReader(inputStream))
    var writer = PrintWriter(outputStream, true)

    fun update() {
        ReadHandler(reader).build()
        while (true) {
            // 从控制台读入
            val msg = readLine()
            if (msg == "exit") exitProcess(0)
            // 向服务器写入
            writer.println(msg)
        }
    }

    // 开启一个线程从服务器读取数据
    class ReadHandler(var reader: BufferedReader) : Thread() {
        fun build(): ReadHandler {
            start()
            return this
        }

        override fun run() {
            while (true) {
                // 从服务器读取一行
                val line = reader.readLine()
                // 向控制台写入
                println(line)
            }
        }
    }
}