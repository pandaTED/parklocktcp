package com.zhiku.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Create by ZhuYunpeng-2017-10-27-9:25
 */
public class ServerThread implements Runnable{

    Logger logger = LogManager.getLogger(ServerThread.class);

    private Socket client = null;

    public ServerThread(Socket client){
        this.client = client;
    }

    public void run() {

        logger.debug("Thread---->"+Thread.currentThread().getName());

        try{
            //获取Socket的输出流，用来向客户端发送数据
            PrintStream out = new PrintStream(client.getOutputStream());
            //获取Socket的输入流，用来接收从客户端发送过来的数据
            BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
            boolean flag =true;

            while(flag){
                //接收从客户端发送过来的数据
                String str = null;

                try{

                    str =  buf.readLine();
                    logger.debug("str----------------->"+str);

                }catch (Exception e){
                    e.printStackTrace();
                }

                if(str == null || "".equals(str)){
                    flag = false;
                }else{
                    if("bye".equals(str)){
                        flag = false;
                    }else{
                        //将接收到的字符串前面加上echo，发送到对应的客户端
                        out.println("echo:" + str);
                    }
                }
            }
            out.close();
            client.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
