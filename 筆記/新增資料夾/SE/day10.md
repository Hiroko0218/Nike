# day10

## 聊天室(續)

#### 實現服務端發送消息給客戶端

在服務端通過Socket獲取輸出流,客戶端獲取輸入流,實現服務端將消息發送給客戶端.

這里讓服務端直接將客戶端發送過來的消息再回覆給客戶端來進行測試.

服務端代碼:

```java
package socket;

import java.io.*;
import java.net.ServerSocket;
import java.nio.charset.StandardCharsets;
import java.net.Socket;

/**
 * 聊天室服務端
 */
public class Server {
    /**
     * 運行在服務端的ServerSocket主要完成兩個工作:
     * 1:向服務端操作系統申請服務端口，客戶端就是通過這個端口與ServerSocket建立鏈接
     * 2:監聽端口，一旦一個客戶端建立鏈接，會立即返回一個Socket。通過這個Socket
     *   就可以和該客戶端交互了
     *
     * 我們可以把ServerSocket想象成某客服的"總機"。用戶打電話到總機，總機分配一個
     * 電話使得服務端與你溝通。
     */
    private ServerSocket serverSocket;

    /**
     * 服務端構造方法，用來初始化
     */
    public Server(){
        try {
            System.out.println("正在啟動服務端...");
            /*
                實例化ServerSocket時要指定服務端口，該端口不能與操作系統其他
                應用程序占用的端口相同，否則會拋出異常:
                java.net.BindException:address already in use

                端口是一個數字，取值範圍:0-65535之間。
                6000之前的的端口不要使用，密集綁定系統應用和流行應用程序。
             */
            serverSocket = new ServerSocket(8088);
            System.out.println("服務端啟動完畢!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 服務端開始工作的方法
     */
    public void start(){
        try {
            while(true) {
                System.out.println("等待客戶端鏈接...");
                /*
                    ServerSocket提供了接受客戶端鏈接的方法:
                    Socket accept()
                    這個方法是一個阻塞方法，調用後方法"卡住"，此時開始等待客戶端
                    的鏈接，直到一個客戶端鏈接，此時該方法會立即返回一個Socket實例
                    通過這個Socket就可以與客戶端進行交互了。

                    可以理解為此操作是接電話，電話沒響時就一直等。
                 */
                Socket socket = serverSocket.accept();
                System.out.println("一個客戶端鏈接了！");
                //啟動一個線程與該客戶端交互
                ClientHandler clientHandler = new ClientHandler(socket);
                Thread t = new Thread(clientHandler);
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
     * 定義線程任務
     * 目的是讓一個線程完成與特定客戶端的交互工作
     */
    private class ClientHandler implements Runnable{
        private Socket socket;
        private String host;//記錄客戶端的IP地址信息

        public ClientHandler(Socket socket){
            this.socket = socket;
            //通過socket獲取遠端計算機地址信息
            host = socket.getInetAddress().getHostAddress();
        }
        public void run(){
            try{
                 /*
                    Socket提供的方法:
                    InputStream getInputStream()
                    獲取的字節輸入流讀取的是對方計算機發送過來的字節
                 */
                InputStream in = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);

                OutputStream out = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(out,StandardCharsets.UTF_8);
                BufferedWriter bw = new BufferedWriter(osw);
                PrintWriter pw = new PrintWriter(bw,true);


                String message = null;
                while ((message = br.readLine()) != null) {
                    System.out.println(host + "說:" + message);
                    //將消息回覆給客戶端
                    pw.println(host + "說:" + message);
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }


}
```

客戶端代碼:

```java
package socket;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 聊天室客戶端
 */
public class Client {
    /*
        java.net.Socket 套接字
        Socket封裝了TCP協議的通訊細節，我們通過它可以與遠端計算機建立鏈接，
        並通過它獲取兩個流(一個輸入，一個輸出)，然後對兩個流的數據讀寫完成
        與遠端計算機的數據交互工作。
        我們可以把Socket想象成是一個電話，電話有一個聽筒(輸入流)，一個麥克
        風(輸出流)，通過它們就可以與對方交流了。
     */
    private Socket socket;

    /**
     * 構造方法，用來初始化客戶端
     */
    public Client(){
        try {
            System.out.println("正在鏈接服務端...");
            /*
                實例化Socket時要傳入兩個參數
                參數1:服務端的地址信息
                     可以是IP地址，如果鏈接本機可以寫"localhost"
                參數2:服務端開啟的服務端口
                我們通過IP找到網絡上的服務端計算機，通過端口鏈接運行在該機器上
                的服務端應用程序。
                實例化的過程就是鏈接的過程，如果鏈接失敗會拋出異常:
                java.net.ConnectException: Connection refused: connect
             */
            socket = new Socket("localhost",8088);
            System.out.println("與服務端建立鏈接!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 客戶端開始工作的方法
     */
    public void start(){
        try {
            /*
                Socket提供了一個方法:
                OutputStream getOutputStream()
                該方法獲取的字節輸出流寫出的字節會通過網絡發送給對方計算機。
             */
            //低級流，將字節通過網絡發送給對方
            OutputStream out = socket.getOutputStream();
            //高級流，負責銜接字節流與字符流，並將寫出的字符按指定字符集轉字節
            OutputStreamWriter osw = new OutputStreamWriter(out,StandardCharsets.UTF_8);
            //高級流，負責塊寫文本數據加速
            BufferedWriter bw = new BufferedWriter(osw);
            //高級流，負責按行寫出字符串，自動行刷新
            PrintWriter pw = new PrintWriter(bw,true);

            //通過socket獲取輸入流讀取服務端發送過來的消息
            InputStream in = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(in,StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);


            Scanner scanner = new Scanner(System.in);
            while(true) {
                String line = scanner.nextLine();
                if("exit".equalsIgnoreCase(line)){
                    break;
                }
                pw.println(line);

                line = br.readLine();
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                /*
                    通訊完畢後調用socket的close方法。
                    該方法會給對方發送斷開信號。
                 */
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }
}
```

### 服務端轉發消息給所有客戶端

當一個客戶端發送一個消息後,服務端收到後如何轉發給所有客戶端.

問題:例如紅色的線程一收到客戶端消息後如何獲取到橙色的線程二中的輸出流?得不到就無法將消息轉發給橙色的客戶端(進一步延伸就是無法轉發給所有其他客戶端)

解決:內部類可以訪問外部類的成員,因此在Server類上定義一個集合allOut可以被所有內部類ClientHandler實例訪問.從而將這些ClientHandler實例之間想互訪的數據存放在這個集合中達到共享數據的目的.對此只需要將所有ClientHandler中的輸出流都存入到集合allOut中就可以達到互訪輸出流轉發消息的目的了.


服務端代碼:

```java
package socket;

import java.io.*;
import java.net.ServerSocket;
import java.nio.charset.StandardCharsets;
import java.net.Socket;
import java.util.List;
import java.util.ArrayList;

/**
 * 聊天室服務端
 */
public class Server {
    /**
     * 運行在服務端的ServerSocket主要完成兩個工作:
     * 1:向服務端操作系統申請服務端口，客戶端就是通過這個端口與ServerSocket建立鏈接
     * 2:監聽端口，一旦一個客戶端建立鏈接，會立即返回一個Socket。通過這個Socket
     *   就可以和該客戶端交互了
     *
     * 我們可以把ServerSocket想象成某客服的"總機"。用戶打電話到總機，總機分配一個
     * 電話使得服務端與你溝通。
     */
    private ServerSocket serverSocket;
    /*
        存放所有客戶端輸出流，用於廣播消息
     */
    private List<PrintWriter> allOut = new ArrayList();

    /**
     * 服務端構造方法，用來初始化
     */
    public Server(){
        try {
            System.out.println("正在啟動服務端...");
            /*
                實例化ServerSocket時要指定服務端口，該端口不能與操作系統其他
                應用程序占用的端口相同，否則會拋出異常:
                java.net.BindException:address already in use

                端口是一個數字，取值範圍:0-65535之間。
                6000之前的的端口不要使用，密集綁定系統應用和流行應用程序。
             */
            serverSocket = new ServerSocket(8088);
            System.out.println("服務端啟動完畢!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 服務端開始工作的方法
     */
    public void start(){
        try {
            while(true) {
                System.out.println("等待客戶端鏈接...");
                /*
                    ServerSocket提供了接受客戶端鏈接的方法:
                    Socket accept()
                    這個方法是一個阻塞方法，調用後方法"卡住"，此時開始等待客戶端
                    的鏈接，直到一個客戶端鏈接，此時該方法會立即返回一個Socket實例
                    通過這個Socket就可以與客戶端進行交互了。

                    可以理解為此操作是接電話，電話沒響時就一直等。
                 */
                Socket socket = serverSocket.accept();
                System.out.println("一個客戶端鏈接了！");
                //啟動一個線程與該客戶端交互
                ClientHandler clientHandler = new ClientHandler(socket);
                Thread t = new Thread(clientHandler);
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
     * 定義線程任務
     * 目的是讓一個線程完成與特定客戶端的交互工作
     */
    private class ClientHandler implements Runnable{
        private Socket socket;
        private String host;//記錄客戶端的IP地址信息

        public ClientHandler(Socket socket){
            this.socket = socket;
            //通過socket獲取遠端計算機地址信息
            host = socket.getInetAddress().getHostAddress();
        }
        public void run(){
            try{
                 /*
                    Socket提供的方法:
                    InputStream getInputStream()
                    獲取的字節輸入流讀取的是對方計算機發送過來的字節
                 */
                InputStream in = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);

                OutputStream out = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(out,StandardCharsets.UTF_8);
                BufferedWriter bw = new BufferedWriter(osw);
                PrintWriter pw = new PrintWriter(bw,true);

                //將該輸出流存入allOut中
                //1對allOut數組擴容
                allOut.add(pw);


                String message = null;
                while ((message = br.readLine()) != null) {
                    System.out.println(host + "說:" + message);
                    //將消息回覆給所有客戶端
                    for(PrintWriter o : allOut) {
                        o.println(host + "說:" + message);
                    }
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }


}
```

#### 客戶端解決收發消息的沖突問題

由於客戶端start方法中循環進行的操作順序是先通過控制台輸入一句話後將其發送給服務端,然後再讀取服務端發送回來的一句話.這導致如果客戶端不輸入內容就無法收到服務端發送過來的其他信息(其他客戶端的聊天內容).因此要將客戶端中接收消息的工作移動到一個單獨的線程上執行,才能保證收發消息互不打擾.

客戶端代碼:

```java
package socket;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 聊天室客戶端
 */
public class Client {
    /*
        java.net.Socket 套接字
        Socket封裝了TCP協議的通訊細節，我們通過它可以與遠端計算機建立鏈接，
        並通過它獲取兩個流(一個輸入，一個輸出)，然後對兩個流的數據讀寫完成
        與遠端計算機的數據交互工作。
        我們可以把Socket想象成是一個電話，電話有一個聽筒(輸入流)，一個麥克
        風(輸出流)，通過它們就可以與對方交流了。
     */
    private Socket socket;

    /**
     * 構造方法，用來初始化客戶端
     */
    public Client(){
        try {
            System.out.println("正在鏈接服務端...");
            /*
                實例化Socket時要傳入兩個參數
                參數1:服務端的地址信息
                     可以是IP地址，如果鏈接本機可以寫"localhost"
                參數2:服務端開啟的服務端口
                我們通過IP找到網絡上的服務端計算機，通過端口鏈接運行在該機器上
                的服務端應用程序。
                實例化的過程就是鏈接的過程，如果鏈接失敗會拋出異常:
                java.net.ConnectException: Connection refused: connect
             */
            socket = new Socket("localhost",8088);
            System.out.println("與服務端建立鏈接!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 客戶端開始工作的方法
     */
    public void start(){
        try {
            //啟動讀取服務端發送過來消息的線程
            ServerHandler handler = new ServerHandler();
            Thread t = new Thread(handler);
            t.setDaemon(true);
            t.start();


            /*
                Socket提供了一個方法:
                OutputStream getOutputStream()
                該方法獲取的字節輸出流寫出的字節會通過網絡發送給對方計算機。
             */
            //低級流，將字節通過網絡發送給對方
            OutputStream out = socket.getOutputStream();
            //高級流，負責銜接字節流與字符流，並將寫出的字符按指定字符集轉字節
            OutputStreamWriter osw = new OutputStreamWriter(out,StandardCharsets.UTF_8);
            //高級流，負責塊寫文本數據加速
            BufferedWriter bw = new BufferedWriter(osw);
            //高級流，負責按行寫出字符串，自動行刷新
            PrintWriter pw = new PrintWriter(bw,true);


            Scanner scanner = new Scanner(System.in);
            while(true) {
                String line = scanner.nextLine();
                if("exit".equalsIgnoreCase(line)){
                    break;
                }
                pw.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                /*
                    通訊完畢後調用socket的close方法。
                    該方法會給對方發送斷開信號。
                 */
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }

    /**
     * 該線程負責接收服務端發送過來的消息
     */
    private class ServerHandler implements Runnable{
        public void run(){
            //通過socket獲取輸入流讀取服務端發送過來的消息
            try {
                InputStream in = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(in,StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);

                String line;
                //循環讀取服務端發送過來的每一行字符串
                while((line = br.readLine())!=null){
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

## 服務端解決多線程並發安全問題

為了讓能叫消息轉發給所有客戶端，我們 在Server上添加了一個集合類型的屬性allOut,並且共所有線程ClientHandler使用，這時對集合的操作要考慮並發安全問題，還要考慮對集合的不同操作之間的互斥問題。因此，對allOut集合的添加元素，刪除元素和遍歷操作要進行互斥。

最終代碼:

```java
package socket;

import java.io.*;
import java.net.ServerSocket;
import java.nio.charset.StandardCharsets;
import java.net.Socket;

/**
 * 聊天室服務端
 */
public class Server {
    /**
     * 運行在服務端的ServerSocket主要完成兩個工作:
     * 1:向服務端操作系統申請服務端口，客戶端就是通過這個端口與ServerSocket建立鏈接
     * 2:監聽端口，一旦一個客戶端建立鏈接，會立即返回一個Socket。通過這個Socket
     *   就可以和該客戶端交互了
     *
     * 我們可以把ServerSocket想象成某客服的"總機"。用戶打電話到總機，總機分配一個
     * 電話使得服務端與你溝通。
     */
    private ServerSocket serverSocket;
    /*
        存放所有客戶端輸出流，用於廣播消息
     */
    private PrintWriter[] allOut = {};

    /**
     * 服務端構造方法，用來初始化
     */
    public Server(){
        try {
            System.out.println("正在啟動服務端...");
            /*
                實例化ServerSocket時要指定服務端口，該端口不能與操作系統其他
                應用程序占用的端口相同，否則會拋出異常:
                java.net.BindException:address already in use

                端口是一個數字，取值範圍:0-65535之間。
                6000之前的的端口不要使用，密集綁定系統應用和流行應用程序。
             */
            serverSocket = new ServerSocket(8088);
            System.out.println("服務端啟動完畢!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 服務端開始工作的方法
     */
    public void start(){
        try {
            while(true) {
                System.out.println("等待客戶端鏈接...");
                /*
                    ServerSocket提供了接受客戶端鏈接的方法:
                    Socket accept()
                    這個方法是一個阻塞方法，調用後方法"卡住"，此時開始等待客戶端
                    的鏈接，直到一個客戶端鏈接，此時該方法會立即返回一個Socket實例
                    通過這個Socket就可以與客戶端進行交互了。

                    可以理解為此操作是接電話，電話沒響時就一直等。
                 */
                Socket socket = serverSocket.accept();
                System.out.println("一個客戶端鏈接了！");
                //啟動一個線程與該客戶端交互
                ClientHandler clientHandler = new ClientHandler(socket);
                Thread t = new Thread(clientHandler);
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
     * 定義線程任務
     * 目的是讓一個線程完成與特定客戶端的交互工作
     */
    private class ClientHandler implements Runnable{
        private Socket socket;
        private String host;//記錄客戶端的IP地址信息

        public ClientHandler(Socket socket){
            this.socket = socket;
            //通過socket獲取遠端計算機地址信息
            host = socket.getInetAddress().getHostAddress();
        }
        public void run(){
            PrintWriter pw = null;
            try{
                 /*
                    Socket提供的方法:
                    InputStream getInputStream()
                    獲取的字節輸入流讀取的是對方計算機發送過來的字節
                 */
                InputStream in = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);

                OutputStream out = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(out,StandardCharsets.UTF_8);
                BufferedWriter bw = new BufferedWriter(osw);
                pw = new PrintWriter(bw,true);

                //將該輸出流存入allOut中
                synchronized (allOut) {
                   allOut.add(pw);
                }
                //通知所有客戶端該用戶上線了
                System.out.println(host + "上線了,當前在線人數:"+allOut.length);


                String message = null;
                while ((message = br.readLine()) != null) {
                    System.out.println(host + "說:" + message);
                    //將消息回覆給所有客戶端
                    synchronized (allOut) {
                   for (PrintWriter o : allOut) {
                       allOut[i].println(host + "說:" + message);
                   }
            }
                }
            }catch(IOException e){
                e.printStackTrace();
            }finally{
                //處理客戶端斷開鏈接的操作
                //將當前客戶端的輸出流從allOut中刪除
                synchronized (allOut) {
                    allOut.remove(pw);
                }
                System.out.println(host+"下線了，當前在線人數:"+allOut.length);
                try {
                    socket.close();//與客戶端斷開鏈接
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
```