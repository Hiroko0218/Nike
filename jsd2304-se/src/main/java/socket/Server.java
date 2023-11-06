package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 聊天室服務端
 */
public class Server {
    //    PrintWriter
    /**
     *  java.net.ServerSocket
     *  ServerSocket 主要有兩個作用
     *  1: 向系统申請服務端口,客戶端就是通過這個端口與服務端建立連接的
     *  2: 監聽端口等待客戶端的連接
     *    一旦一個客戶端與服務端建立連接,此時會創建一個Socket與該客戶端交互
     */
    private ServerSocket serverSocket;
    //創建一個集合用於存放所有客戶端的輸出流
    private List<PrintWriter> allOut = new ArrayList<>();

    public  Server(){
        System.out.println("正在啟動服務端...");
         /*
                實例化ServerSocket時要指定服務端口,如果該端口已經被操作
                系统其他程序佔用時會抛出異常:
                java.net.BingException(): address already in use
             */
        try {
            serverSocket = new ServerSocket(8088);
            System.out.println("服務端啟動完畢!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
        }
    }
    public void  start(){
        try {
            while(true) {
                System.out.println("等待客戶端連接...");
                    /*
                        ServerSocket提供的重要方法:
                        Socket accept()
                        通過"總機"接受客戶端的連接，該方法是一個阻塞方法，調用
                        後會等待客戶端的連接，一旦一個客戶端連接了就會立即返回
                        一個Socket實例，通過該Socket與連接的客戶端交互
                        好比:接電話
                     */
                Socket socket = serverSocket.accept();
                System.out.println("一個客戶端連接了!");
                //啟動一個線程来處理與該客戶端的交互
                ClientHandler handler = new ClientHandler(socket);
                Thread t = new Thread(handler);
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
    /**
     * 该线程任务是与特定的客户端进行交互
     */
    private class ClientHandler implements Runnable{
        private Socket socket;
        private String host;//紀錄該客戶端的IP地址

        public ClientHandler(Socket socket){
            this.socket = socket;
            //通過socket獲取遠端計算機地址信息
            host=socket.getInetAddress().getHostAddress();
        }

        public void run(){
            PrintWriter pw =null;
            try {
                /*
                Socket提供的重要方法:
                InputStream getInputStream()
                通過Socket獲取的輸入流可以讀取来自遠端計算機發送過來的字節數據
             */
                InputStream in = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);

                //通過socket獲取輸出流，用於將消息發送给客戶端
                OutputStream out = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                BufferedWriter bw = new BufferedWriter(osw);
                pw = new PrintWriter(bw, true);

                /*
                    臨界資源適合作為同步監視器對象
                    搶誰就誰
                 */
                synchronized (allOut) {
                    //將該輸出流存入共享集合，便於廣播消息
                    allOut.add(pw);
                }

                //廣播消息，告知所有客戶端該用戶上線了
                sendMessage(host + "上線了,當前在線人數:"+allOut.size());

                String message;
                /*
                服務端在使用BufferedReader的readLine方法讀取来自客戶端
                發送過来的一行字符串時，客戶端的不同操作這裡的效果是不同:
                1: 客戶端此時不發送内容過来: 服務端這里的readLine方法會保持阻塞
                2: 客戶端發送一行字符串過来: 服務端的readLine方法就會立即返回該字符串
                3: 客戶端正常斷開連接(socket.close()): 服務端的readLine方法會返回null
                4: 客戶端異常斷開(没有調用socket.close()就结束了): 服務端的readLine方法會抛出異常:
                  java.net.SocketException: Connection reset
             */

                while ((message = br.readLine()) != null) {
                    sendMessage(host + "說:" + message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                //處理該客戶端段開後的操作
                synchronized (allOut) {
                    //1 將當前客戶端的輸出流從allOut中删除
                    allOut.remove(pw);
                }
                //2 廣播消息给其他客戶端該用戶下線了
                sendMessage(host + "下線了,當前在線人數:" + allOut.size());
                //3 關閉socket釋放資源
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        /**
         * 廣播给定的消息到所有客戶端
         * @param message
         */
        private void sendMessage(String message){
            System.out.println(message);
            synchronized (allOut) {
                //將消息發送给客戶端
                /*
                    要與其他線程可能對集合的增删元素互斥
                    因為迭代器要求遍歷的過程中不可以通過集合方法增删元素
                 */
                for(PrintWriter e : allOut){
                    e.println(message);
                }
            }
        }
    }
}
