package com.birdboot.core;

import com.birdboot.http.HttpServletRequest;

import java.io.*;
import java.net.Socket;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
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
            //1.解析請求
            HttpServletRequest request = new HttpServletRequest(socket);

            String path = request.getUri();
            System.out.println("請求路徑: "+path);

            //2.處理請求
            /*
                後期開發中一个非常常用的相對路徑:類加載路徑
                如何定位類加載路徑的位置:
                File baseDir = new File(
                    當前類.class.getClassLoader().getResource(".").toURI()
                )

                類加載路徑對應的資料夾時包含我們當前項目所有包的那個資料夾。
                可以理解為是你項目中任意一個類上定義的包package中頂級包的上一级資料夾
                舉例:以當前類ClientHandler為例
                ClientHandler上包定義: package com.birdboot.core;
                說明當前類在core包中，core在birdboot中，birdboot在com包中。因此com包
                就是當前類的頂級包，而類加載路徑對應的資料夾就是包含com的那個資料夾


                由於JVM執行的是class文件，因此實際類加載路徑應當是ClientHandler.class
                所在頂級包的上一级，也就是target/classes這個資料夾
             */

            //測試回覆固定的index.html頁面
            File baseDir = new File(
                    ClientHandler.class.getClassLoader().getResource(".").toURI()
            );
            //定位類加載路徑中的static資料夾
            File staticDir = new File(baseDir,"static");
            //定位static資料夾中的index.html頁面
//            File file = new File(staticDir,"index.html");
            //上述測試沒問題，就可以根據瀏覽器請求路徑去定位static下的文件了
            File file = new File(staticDir,path);


            //3.發送響應
            /*
                HTTP/1.1 200 OK(CRLF)
                Content-Type: text/html(CRLF)
                Content-Length: 2546(CRLF)(CRLF)
                1011101010101010101......
             */
            OutputStream out = socket.getOutputStream();
            //1.發送狀態行
            String line = "HTTP/1.1 200 OK";
            byte[] data = line.getBytes(StandardCharsets.ISO_8859_1);
            out.write(data);
            out.write(13);//單獨發送了Enter
            out.write(10);//單獨發送換行符

            //2.發送響應頭
            line = "Content-Type: text/html";
            data = line.getBytes(StandardCharsets.ISO_8859_1);
            out.write(data);
            out.write(13);//單獨發送了Enter
            out.write(10);//單獨發送換行符

            line = "Content-Length: " + file.length();
            data = line.getBytes(StandardCharsets.ISO_8859_1);
            out.write(data);
            out.write(13);//單獨發送了Enter
            out.write(10);//單獨發送換行符

            //單獨發送了Enter+換行表達影響頭部分發送完畢了
            out.write(13);
            out.write(10);

            //3.發送響應正文
            FileInputStream fis = new FileInputStream(file);
            int len = 0;
            byte[] buf = new byte[1024*10];
            while((len = fis.read(buf))!=-1){
                out.write(buf,0,len);
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }finally {
            try {
                //按照HTTP1.0協議規則，一問一答後段開連接
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws URISyntaxException {
        File baseDir = new File(
                ClientHandler.class.getClassLoader().getResource(".").toURI()
        );
        System.out.println(baseDir);
    }
}