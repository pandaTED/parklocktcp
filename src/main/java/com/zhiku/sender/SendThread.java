package com.zhiku.sender;

import java.io.PrintStream;
import java.net.Socket;

/**
 * Create by ZhuYunpeng-2017-10-28-15:30
 */
public class SendThread implements Runnable {


    private static final int SERVER_PORT = 20086;
    private static final String IP = "127.0.0.1";

    String sendStr = null;

    public SendThread(String sendStr) {
        this.sendStr = sendStr;
    }

    @Override
    public void run() {

        try {
            System.out.println("---->"+Thread.currentThread().getName());
            Socket socket = new Socket(IP, SERVER_PORT);
            System.out.println("Connected to server...sending command string");
            PrintStream writer = new PrintStream(socket.getOutputStream());

//            int i = 0;
            while (true) {

                writer.print(sendStr+"\r\n");

                Thread.sleep(25*1000);
//                i++;
            }

//        socket.shutdownOutput();
//        writer.close();
//        socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
