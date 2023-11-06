package V20.src.main.java.com.birdboot.core;

import V20.src.main.java.com.birdboot.core.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 項目主啟動類
 */
public class BirdBootApplication {
    private ServerSocket serverSocket;
    private ExecutorService threadPool;//線程池

    public BirdBootApplication(){
        try {
            System.out.println("正在啟動服務端...");
            serverSocket = new ServerSocket(8088);
            threadPool = Executors.newFixedThreadPool(50);
            System.out.println("服務端啟動完畢!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(){
        try {
        while (true) {
            System.out.println("等待客戶端連接...");
            Socket socket = serverSocket.accept();
            System.out.println("一個客戶端連接了!");
            //啟動線程来處理該客戶端交互
            ClientHandler handler = new ClientHandler(socket);
            //1:這裡要傳遞 2:如果編譯報錯說明ClientHandler沒有實現Runnable接口
            threadPool.execute(handler);
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BirdBootApplication application = new BirdBootApplication();
        application.start();
    }
}
