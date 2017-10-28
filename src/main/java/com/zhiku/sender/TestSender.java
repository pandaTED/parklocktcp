package com.zhiku.sender;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Create by ZhuYunpeng-2017-10-28-12:20
 */
public class TestSender {



    private static final int SERVER_PORT = 20086;
    private static final String IP = "127.0.0.1";

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket(IP, SERVER_PORT);

        System.out.println("Connected to server...sending command string");

        PrintStream writer = new PrintStream(socket.getOutputStream());

        int i = 0;
        while (i < 5) {

            writer.println("HelloWorld");

            Thread.sleep(1000);
            i++;
        }

//        socket.shutdownOutput();

//        writer.close();
//        socket.close();
    }
}