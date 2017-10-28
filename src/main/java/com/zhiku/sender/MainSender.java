package com.zhiku.sender;

/**
 * Create by ZhuYunpeng-2017-10-28-15:39
 */
public class MainSender {

    public static void main(String[] args) throws InterruptedException {

        for(int i = 1;i<=5000;i++){

                new Thread(new SendThread(String.valueOf(System.currentTimeMillis()))).start();

                Thread.sleep(50);
        }
    }
}
