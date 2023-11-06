package com.birdboot.core;

import com.birdboot.http.EmptyRequestException;
import com.birdboot.http.HttpServletRequest;
import com.birdboot.http.HttpServletResponse;

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
            HttpServletResponse response = new HttpServletResponse(socket);

            //2.處理請求
            String path = request.getUri();
            System.out.println("請求路徑: "+path);
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
             /*
                http://localhost:8088/index.html
                path:/index.html
                可以在static資料夾中定位該文件

                下面兩種情况都是404的現象

                http://localhost:8088/abc.html
                path:/abc.html
                在static資料夾中沒有該文件

                http://localhost:8088
                path:/
                定位是static資料夾裡

                404的響應:
                HTTP/1.1 404 NotFound(CRLF)
                Content-Type: text/html(CRLF)
                Content-Length: 2546(404頁面長度)(CRLF)(CRLF)
                1011101010101010101......(404頁面)

             */
            //上述測試沒問題，就可以根據瀏覽器請求路徑去定位static下的文件了
            File file = new File(staticDir,path);

            if (file.isFile()){
                response.setStatusCode(200);
                response.setStatusReason("OK");
                response.setContentFile(file);
            }else {
                response.setStatusCode(404);
                response.setStatusReason("NotFound");
                response.setContentFile(new File(staticDir,"404.html"));
            }

            //3.發送響應
            response.response();

        } catch (IOException | URISyntaxException | EmptyRequestException e) {
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
}