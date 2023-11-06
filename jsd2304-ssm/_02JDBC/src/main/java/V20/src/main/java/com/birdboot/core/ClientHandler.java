package V20.src.main.java.com.birdboot.core;

import V20.src.main.java.com.birdboot.http.EmptyRequestException;
import V20.src.main.java.com.birdboot.http.HttpServletRequest;
import V20.src.main.java.com.birdboot.http.HttpServletResponse;

import java.io.IOException;
import java.net.Socket;

/**
 * 該線程任務負責與指定的客戶端(瀏覽器)完成一次 HTTP交互
 * HTTP協議要求瀏覽器與服務端採取一問一答的模式，因此這里的交互流程分為三步：
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
            // 1.解析請求
            HttpServletRequest request = new HttpServletRequest(socket);
            HttpServletResponse response = new HttpServletResponse(socket);

            // 2.處理請求
            DispatcherServlet.getInstance().service(response, request);

            // 3.發送響應
            response.response();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (EmptyRequestException e) {
            //無需將空請求異常輸出到控制台
        } finally {
            try {
                //按照HTTP1.0協議規則，一問一答後段開連接
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
