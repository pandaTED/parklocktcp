package com.zhiku;

import com.zhiku.server.ServerThread;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.StringFormattedMessage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Hello world!
 */
public class ServerRun {


    static Logger logger = LogManager.getLogger(ServerRun.class);

    public static void main(String[] args) throws IOException {

        String clientId = null;

        //服务端在20006端口监听客户端请求的TCP连接
        ServerSocket server = new ServerSocket(20006);
        Socket client = null;

        boolean f = true;


        Map<String,Socket> clientMap = new ConcurrentHashMap<>();

        while(f){

            //等待客户端的连接，如果没有获取连接
            client = server.accept();

            clientId = String.valueOf(System.currentTimeMillis());
            clientMap.put(clientId,client);

            logger.debug("客户端已连接");
            logger.debug("client---->"+client);
            logger.debug("port------>"+client.getLocalPort());
            logger.debug("Ip------>"+client.getInetAddress().getHostAddress());


            logger.debug(clientMap.toString());

            //为每个客户端连接开启一个线程
            new Thread(new ServerThread(client)).start();

        }
        server.close();
    }
}
