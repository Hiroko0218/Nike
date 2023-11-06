# day08

## 聊天室Socket

### 客戶端與服務端完成第一次通訊(發送一行字符串)

#####  Socket提供了兩個重要的方法:

- **OutputStream getOutputStream()**

  該方法會獲取一個字節輸出流，通過這個輸出流寫出的字節數據會通過網絡發送給對方。

- **InputStream getInputStream()**

  通過該方法獲取的字節輸入流讀取的是遠端計算機發送過來的數據。

#### 原理圖

![image-20230411163427777](images\image-20230411163427777.png)

#### 例

```java
package socket;

import java.io.*;
import java.net.Socket;

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
            OutputStreamWriter osw = new OutputStreamWriter(out,"UTF-8");
            //高級流，負責塊寫文本數據加速
            BufferedWriter bw = new BufferedWriter(osw);
            //高級流，負責按行寫出字符串，自動行刷新
            PrintWriter pw = new PrintWriter(bw,true);

            pw.println("你好服務端!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }
}
```

```java
package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
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

            /*
                Socket提供的方法:
                InputStream getInputStream()
                獲取的字節輸入流讀取的是對方計算機發送過來的字節
             */
            InputStream in = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(in,"UTF-8");
            BufferedReader br = new BufferedReader(isr);

            String message = br.readLine();
            System.out.println("客戶端說:"+message);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}
```



###  實現客戶端循環發消息給服務端

```java
package socket;

import java.io.*;
import java.net.Socket;
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
            OutputStreamWriter osw = new OutputStreamWriter(out,"UTF-8");
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
}
```

```java
package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
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

            /*
                Socket提供的方法:
                InputStream getInputStream()
                獲取的字節輸入流讀取的是對方計算機發送過來的字節
             */
            InputStream in = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(in,"UTF-8");
            BufferedReader br = new BufferedReader(isr);

            String message = null;
            while((message = br.readLine())!=null) {
                System.out.println("客戶端說:" + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}
```

#### 需要注意的幾個點:

- 當客戶端不再與服務端通訊時，需要調用socket.close()斷開鏈接，此時會發送斷開鏈接的信號給服務端。這時服務端的br.readLine()方法會返回null，表示客戶端斷開了鏈接。
- 當客戶端鏈接後不輸入信息發送給服務端時，服務端的br.readLine()方法是出於阻塞狀態的，直到讀取了一行來自客戶端發送的字符串。

### TCP進行連接時的三次握手

![image-20230411161055080](images\image-20230411161055080.png)

### TCP斷開連接時的四次揮手



![image-20230411162032603](images\image-20230411162032603.png)

### 多客戶端鏈接

**只有第一個連接的客戶端可以與服務端說話。**

原因:

服務端只調用過一次accept方法，因此只有第一個客戶端鏈接時服務端接受了鏈接並返回了Socket,此時可以與其交互。

而第二個客戶端建立鏈接時，由於服務端沒有再次調用accept，因此無法與其交互。

![image-20230411172359125](images\image-20230411172359125.png)

```java
package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
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
                /*
                    Socket提供的方法:
                    InputStream getInputStream()
                    獲取的字節輸入流讀取的是對方計算機發送過來的字節
                 */
                InputStream in = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(in, "UTF-8");
                BufferedReader br = new BufferedReader(isr);

                String message = null;
                while ((message = br.readLine()) != null) {
                    System.out.println("客戶端說:" + message);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}
```



**添加循環操作後，發現依然無法實現。**

原因在於:

外層的while循環里面嵌套了一個內層循環(循環讀取客戶端發送消息)，而循環執行機制決定了里層循環不結束，外層循環則無法進入第二次操作。

![image-20230411172451959](images\image-20230411172451959.png)





## 多線程

### 概念

- ##### 線程:一個順序的單一的程序執行流程就是一個線程。代碼一句一句的有先後順序的執行。

- ##### 多線程:多個單一順序執行的流程並發運行。造成"感官上同時運行"的效果。

- ##### 並發:

  多個線程實際運行是走走停停的。線程調度程序會將CPU運行時間劃分為若幹個時間片段並

  盡可能均勻的分配給每個線程，拿到時間片的線程被CPU執行這段時間。當超時後線程調度

  程序會再次分配一個時間片段給一個線程使得CPU執行它。如此反覆。由於CPU執行時間在

  納秒級別，我們感覺不到切換線程運行的過程。所以微觀上走走停停，宏觀上感覺一起運行

  的現象成為並發運行!

- ##### 用途:

  - 當出現多個代碼片段執行順序有沖突時，希望它們各幹各的時就應當放在不同線程上"同時"運行
  - 一個線程可以運行，但是多個線程可以更快時，可以使用多線程運行

###  線程的生命周期圖

![image-20230412120237771](images\image-20230412120237771.png)

### 線程的創建

1. 繼承Thread並重寫run方法
2. 單獨定義線程任務

#### 方式一

**繼承Thread並重寫run方法**

定義一個線程類，重寫run方法，在其中定義線程要執行的任務(希望和其他線程並發執行的任務)。

注:啟動該線程要調用該線程的start方法，而不是run方法！！！

```java
package thread;

/**
 * 多線程
 * 線程:程序中一個單一的順序執行流程，即：線性流程
 * 多線程:多個線性流程"一起"執行。
 *
 * 線程是並發運行的。
 * 並發：線程間的代碼在微觀世界都是走走停停的，宏觀上給我們的感受是在一起執行
 *
 * 線程的創建
 * 第一種方式:繼承Thread並重寫run方法
 *
 */
public class ThreadDemo1 {
    public static void main(String[] args) {
        Thread t1 = new MyThread1();
        Thread t2 = new MyThread2();
        //啟動線程要調用start方法，而不是直接調用run方法!!
        /*
            當我們調用線程的start方法後，線程會納入到線程調度器中被統一管理。
            當線程第一次分配時間片後會自動調用它的run方法開始執行。
         */
        t1.start();
        t2.start();
    }
}

/**
 * 第一種創建線程的方式的優點:結構簡單，利於匿名內部類創建
 * 缺點:
 * 1:存在繼承沖突問題，由於java是單繼承的，這導致如果我們繼承了Thread就無法再
 *   繼承其他類去覆用方法。這在實際開發中是極其不方便的
 * 2:當我們繼承Thread並重寫run方法，在run方法中定義線程要執行的任務。這會導致
 *   線程與任務存在一個必然的耦合關系，不利於線程的重用。
 */
class MyThread1 extends Thread{
    public void run(){
        for (int i = 0; i < 1000; i++) {
            System.out.println("你是誰啊?");
        }
    }
}
class MyThread2 extends Thread{
    public void run(){
        for (int i = 0; i < 1000; i++) {
            System.out.println("開門，查水表的!");
        }
    }
}
```

##### 優缺點

**優點:**

在於結構簡單，便於匿名內部類形式創建。

**缺點:**

- 1:直接繼承線程，會導致不能在繼承其他類去覆用方法，這在實際開發中是非常不便的。
- 2:定義線程的同時重寫了run方法，會導致線程與線程任務綁定在了一起，不利於線程的重用。

#### 方式二

**實現Runnable接口單獨定義線程任務**

```java
package thread;

/**
 * 第二種創建線程的方式:單獨定義線程任務
 * 定義任務:
 * 1:實現Runnable接口
 * 2:實現Callable接口(如果線程執行完畢後需要返回值時使用，多用於線程池)
 *
 */
public class ThreadDemo2 {
    public static void main(String[] args) {
        //1創建線程要執行的任務
        Runnable r1 = new MyRunnable1();
        Runnable r2 = new MyRunnable2();
        //2創建線程
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        //3啟動
        t1.start();
        t2.start();
    }
}
class MyRunnable1 implements Runnable{
    public void run(){
        for (int i = 0; i < 1000; i++) {
            System.out.println("你是誰啊?");
        }
    }
}
class MyRunnable2 implements Runnable{
    public void run(){
        for (int i = 0; i < 1000; i++) {
            System.out.println("查水表的!");
        }
    }
}


```

####  匿名內部類形式的線程創建

```java
package thread;

/**
 * 使用匿名內部類形式完成線程的兩種創建
 */
public class ThreadDemo3 {
    public static void main(String[] args) {
        //繼承Thread重寫run方法
        Thread t1 = new Thread(){
            public void run(){
                for (int i = 0; i < 1000; i++) {
                    System.out.println("你是誰啊?");
                }
            }
        };
        //實現Runnable接口重寫run方法
//        Runnable r2 = new Runnable() {
//            public void run() {
//                for (int i = 0; i < 1000; i++) {
//                    System.out.println("我是查水表的!");
//                }
//            }
//        };
//        Thread t2 = new Thread(r2);

        //lambda表達式
//        Runnable r2 = ()->{
//            for (int i = 0; i < 1000; i++) {
//                System.out.println("我是查水表的!");
//            }
//        };
//        Thread t2 = new Thread(r2);

        Thread t2 = new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                System.out.println("我是查水表的!");
            }
        });


        t1.start();
        t2.start();
    }
}
```

### 主線程

#### java中的代碼都是靠線程運行的，執行main方法的線程稱為"主線程"。

線程提供了一個方法:

- static Thread currentThread()

  該方法可以獲取運行這個方法的線程

```java
package thread;

/**
 * 主線程
 * java程序啟動後，JVM會創建一條線程來執行main方法。並且JVM為該線程取名為"main"
 * 因此我們稱執行main方法的線程為主線程
 *
 * 線程提供了一個靜態方法:
 * static Thread currentThread()
 * 該方法可以獲取運行這個方法的線程
 */
public class CurrentThreadDemo {
    public static void main(String[] args) {
        //讓主線程執行該方法，此時該方法返回的就是主線程
        Thread main = Thread.currentThread();
        System.out.println(main);
        dosome();//主線程執行dosome方法

        Thread myThread = new Thread(){
            public void run(){
                dosome();//自定義線程執行dosome
            }
        };
        myThread.start();
    }

    public static void dosome(){
        //獲取執行dosome方法的線程
        Thread t = Thread.currentThread();
        System.out.println("執行dosome方法的線程是:"+t);
    }
}


```

 

### 使用多線程實現多客戶端連接服務端

**若想使一個服務端可以支持多客戶端連接，我們需要解決以下問題**

- 循環調用accept方法偵聽客戶端的連接
- 使用線程來處理單一客戶端的數據交互

**因為需要處理多客戶端，所以服務端要循環調用accept方法，但該方法會產生阻塞，且讀取客戶端的消息也是依靠一個循環完成的，這會導致它接近是一個死循環，不結束的話也會影響服務端再次調用accept方法。所以與某個客戶端的交互就需要使用線程來並發處理。**

![image-20230412120732357](images\image-20230412120732357.png)

#### 服務端代碼改造

```java
package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * 聊天室服務端
 */
public class Server_V2 {
    /**
     * java.net.ServerSocket
     * 運行在服務端的ServerSocket主要有兩個工作:
     * 1:打開服務端口，客戶端就是通過這個端口與服務端建立連接的
     * 2:監聽服務端口，一旦一個客戶端連接，則立即返回一個Socket實例
     */
    private ServerSocket serverSocket;

    public Server_V2(){
        try {
            /*
                ServerSocket實例化的同時指定服務端口
                如果該端口被其他程序占用則會拋出異常:
                java.net.BindException:address already in use
                此時我們需要更換端口，或者殺死占用該端口的進程。

                端口號範圍:0-65535
             */
            System.out.println("正在啟動服務端...");
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
            /*
                ServerSocket的一個重要方法:
                Socket accept()
                該方法用於接受客戶端的連接。這是一個阻塞方法，調用後會"卡住"，直到
                一個客戶端與ServerSocket連接，此時該方法會立即返回一個Socket實例
                通過這個Socket實例與該客戶端對等連接並進行通訊。
                相當於"接電話"的動作
             */
            while(true) {
                System.out.println("等待客戶端連接...");
                Socket socket = serverSocket.accept();
                System.out.println("一個客戶端連接了!");
                //啟動一個線程負責與該客戶端交互
                ClientHandler handler = new ClientHandler(socket);
                //創建線程
                Thread t = new Thread(handler);
                //啟動線程
                t.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Server_V2 server = new Server_V2();
        server.start();
    }

    /**
     * 第二種創建線程的方式:實現Runnable接口單獨定義線程任務
     * 這個線程任務就是讓一個線程與指定的客戶端進行交互
     */
    private class ClientHandler implements Runnable{
        private Socket socket;

        public ClientHandler(Socket socket){
            this.socket = socket;
        }

        public void run(){
            try {
                InputStream in = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println("客戶端說:" + line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
```