package com.birdboot.core;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 該線程任務負責與指定的客戶端(瀏覽器)完成一次HTTP交互
 * HTTP協議要求瀏覽器與服务端採取一問一答的模式，因此这里的交互流程分為三步：
 * 1：解析請求
 * 2：處理請求
 * 3：發送響應
 */
public class ClientHandler implements Runnable{
    private Socket socket;

    public ClientHandler(Socket socket){
        this.socket = socket;
    }

    public void run() {
        try {
            //測試:讀取来自瀏覽器發送過来的内容
            //HTTP協議要求，在没有附件的情况下，瀏覽器發送過来的全是文字，單字節文字(英文，數字，符號)
            InputStream in = socket.getInputStream();
            int d;
            while((d = in.read()) != -1){
                System.out.print((char)d);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
