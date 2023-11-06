package com.birdboot.core;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

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
            //trim的目的是將最後讀取到的Enter去除
            String line = readLin();
            System.out.println("請求行:"+line);

            //請求相關訊息
            String method;//請求方式
            String uri;//抽象路徑
            String protocol;//協議版本
            //測試路徑:http://localhost:8088/index.html

            String[] data = line.split("\\s");
            method = data[0];
            uri = data[1];
            protocol = data[2];

            System.out.println("method: "+method);
            System.out.println("uri: "+uri);
            System.out.println("protocol: "+protocol);

            //讀取消息頭
            Map<String,String> headers = new HashMap<>();
            //測試路徑:http://localhost:8088/index.html

            while (true){
                line = readLin();
                if (line.isEmpty()) {
                    break;
                }
                System.out.println("消息頭: "+line);
                data = line.split(":\\s");
                headers.put(data[0],data[1]);
            }
            System.out.println("headers: "+headers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 讀取客戶端發送過来的一行字符串
     * @return
     */
    private String readLin() throws IOException {
        //實現讀取一行字符串的操作(瀏覽器發送過来的内容為請求，其中第一行應當是請求行)
        InputStream in = socket.getInputStream();

        char pre = 'a', cur = 'a';//pre表示上次讀取的字符，cur表示本次讀取的字符
        StringBuilder builder = new StringBuilder();//紀錄以讀取一行字符串的内容
        int d;//每次讀取的字節
        while ((d = in.read()) != -1) {
            cur = (char) d;//本次讀取的字符
            if (pre == 13 && cur == 10) {//是否已經连連續取到了Enter+換行
                break;
            }
            builder.append(cur);//將本次讀取的字符拼接
            pre = cur;//再下次讀取前，將本次讀取的字符記為上次讀取的字符
        }
        //trim的目的是將最後讀取到的Enter去除
        return builder.toString().trim();
    }
}
