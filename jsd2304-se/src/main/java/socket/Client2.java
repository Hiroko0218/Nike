package socket;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 聊天室客戶端
 */
public class Client2 {
    /*
    java.net.Socket 套接字
    該類封裝了TCP協議的通訊细節,我們使用它可以與遠端計算機建立連接並進行
    可靠的傳輸通訊.

    Socket:插座
 */
    private Socket socket;

    public Client2()  {
        try {
            System.out.println("正在連接服務端...");
        /*
                Socket的實例化時需要傳入兩個參數:
                1:服務器的IP地址
                2:服務端應用程序打開的端口
                我們通過IP地址可以找到服務器在網絡中的位置,通過端口找到運行在
                服務器上的服務端應用程序
             */
            socket = new Socket("localhost",8088);
            System.out.println("與服務端建立連接!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void start()  {
        //用来啟動讀取服務端消息的線程
        ServerHandler serverHandler = new ServerHandler();
        Thread t = new Thread(serverHandler);
        t.setDaemon(true);
        t.start();

        try {
        /*
                Socket提供的重要方法:
                OutputStream getOutputStream()
                通過Socket獲取的書出流寫出的字節會發送给連接的遠端計算機
             */
            OutputStream out = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(out, StandardCharsets.UTF_8);
            BufferedWriter bw = new BufferedWriter(osw);
            PrintWriter pw = new PrintWriter(bw,true);

            Scanner scanner = new Scanner(System.in);
            while (true){
                String message = scanner.nextLine();
                if("exit".equalsIgnoreCase(message)){
                    break;
                }
                pw.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args)  {
        Client client = new Client();//實例化客戶端
        client.start();//啟動客戶端

    }
    /**
     * 該線程任務負責讀取來自服務端發送過来的消息
     */
    private class ServerHandler implements Runnable{
        @Override
        public void run() {
            try {
                //通過socket獲取輸入流来讀取服務端發送過来的消息
                InputStream in = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(in,StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);

                String message;
                //讀取来自服務端發送過来的消息
                while ((message=(br.readLine()))!=null){
                    System.out.println(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
