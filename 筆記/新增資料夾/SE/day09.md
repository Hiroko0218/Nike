- # day09

  ## 多線程

  ### 線程API

  #### 獲取線程相關信息的方法

  ```java
  package thread;
  
  /**
   * 獲取線程相關信息的一組方法
   */
  public class ThreadInfoDemo {
      public static void main(String[] args) {
          Thread main = Thread.currentThread();//獲取主線程
  
          String name = main.getName();//獲取線程的名字
          System.out.println("名字:"+name);
  
          long id = main.getId();//獲取該線程的唯一標識
          System.out.println("id:"+id);
  
          int priority = main.getPriority();//獲取該線程的優先級
          System.out.println("優先級:"+priority);
  
          boolean isAlive = main.isAlive();//該線程是否活著
          System.out.println("是否活著:"+isAlive);
  
          boolean isDaemon = main.isDaemon();//是否為守護線程
          System.out.println("是否為守護線程:"+isDaemon);
  
          boolean isInterrupted = main.isInterrupted();//是否被中斷了
          System.out.println("是否被中斷了:"+isInterrupted);
  
      }
  }
  ```

  

  #### 線程優先級

  線程start後會納入到線程調度器中統一管理,線程只能被動的被分配時間片並發運行,而無法主動索取時間片.線程調度器盡可能均勻的將時間片分配給每個線程.

  線程有10個優先級,使用整數1-10表示

  - 1為最小優先級,10為最高優先級.5為默認值
  - 調整線程的優先級可以最大程度的幹涉獲取時間片的幾率.優先級越高的線程獲取時間片的次數越多,反之則越少.
  - Thread提供了對應的常量:MAX_PRIORITY表示最高優先級10，MIN_PRIORITY表示最低優先級，NORM_PRIORITY表示默認優先級5

  

  ```java
  package thread;
  
  public class PriorityDemo {
      public static void main(String[] args) {
          Thread max = new Thread(){
              public void run(){
                  for(int i=0;i<10000;i++){
                      System.out.println("max");
                  }
              }
          };
          Thread min = new Thread(){
              public void run(){
                  for(int i=0;i<10000;i++){
                      System.out.println("min");
                  }
              }
          };
          Thread norm = new Thread(){
              public void run(){
                  for(int i=0;i<10000;i++){
                      System.out.println("nor");
                  }
              }
          };
          min.setPriority(Thread.MIN_PRIORITY);
          max.setPriority(Thread.MAX_PRIORITY);
          min.start();
          norm.start();
          max.start();
      }
  }
  ```

  

  #### sleep阻塞

  線程提供了一個靜態方法:

  - static void sleep(long ms)
  - 使運行該方法的線程進入阻塞狀態指定的毫秒,超時後線程會自動回到RUNNABLE狀態等待再次獲取時間片並發運行.

  ```java
  package thread;
  
  public class SleepDemo {
      public static void main(String[] args) {
          System.out.println("程序開始了!");
          try {
              Thread.sleep(5000);//主線程阻塞5秒鐘
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          System.out.println("程序結束了!");
      }
  }
  ```

  ##### 完成一個倒計時程序

  ```java
  package thread;
  
  import java.io.BufferedReader;
  import java.io.IOException;
  import java.io.InputStreamReader;
  import java.util.Scanner;
  
  /**
   * sleep阻塞
   * 線程提供了一個靜態方法:
   * static void sleep(long ms)
   * 該方法可以讓執行該方法的線程處於阻塞狀態指定毫秒，超時後線程會再次回到RUNNABLE狀態
   * 再次並發
   */
  public class SleepDemo {
      public static void main(String[] args) throws IOException {
          System.out.println("程序開始了");
          /*
              簡易的倒計時程序
              程序啟動後在控制台上輸入一個整數，從該數字開始每秒遞減，到0時輸出時間到
           */
  //        BufferedReader br = new BufferedReader(
  //                new InputStreamReader(
  //                        System.in
  //                )
  //        );
  //        System.out.println("請輸入一個數字");
  //        int num = Integer.parseInt(br.readLine());
  
          Scanner scanner = new Scanner(System.in);
          System.out.println("請輸入一個數字");
          for(int num = scanner.nextInt();num>0;num--) {
              System.out.println(num);
              try {
                  Thread.sleep(1000);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
          System.out.println("程序結束了");
      }
  }
  ```

  

  #####  sleep阻塞(續)

  sleep方法處理異常:InterruptedException.

  當一個線程調用sleep方法處於睡眠阻塞的過程中,該線程的interrupt()方法被調用時,sleep方法會拋出該異常從而打斷睡眠阻塞.

  ```java
  package thread;
  
  /**
   * sleep方法要求必須處理中斷異常
   * 當一個線程調用sleep方法處於睡眠阻塞的過程中，如果此時該線程的interrupt()方法被
   * 調用，此時會中斷該線程的睡眠阻塞，那麽sleep方法就會拋出中斷異常。
   */
  public class SleepDemo2 {
      public static void main(String[] args) {
          Thread lin = new Thread("林永健"){
              public void run(){
                  System.out.println(getName()+":剛美完容，睡一會吧...");
                  try {
                      Thread.sleep(500000000);
                  } catch (InterruptedException e) {
                      System.out.println(getName()+":幹嘛呢!幹嘛呢!幹嘛呢!都破了相了!");
                  }
                  System.out.println(getName()+":醒了");
              }
          };
  
          Thread huang = new Thread("黃宏"){
              public void run(){
                  System.out.println(getName()+":大錘80，小錘40，開始砸墻！");
                  for(int i=0;i<5;i++){
                      System.out.println(getName()+":80!");
                      try {
                          Thread.sleep(1000);
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }
                  }
                  System.out.println("咣當！！！");
                  System.out.println(getName()+":大哥！搞定！");
                  lin.interrupt();//中斷lin線程的睡眠阻塞
              }
          };
          lin.start();
          huang.start();
      }
  }
  ```

  

  ### 守護線程

  ##### 概念

  守護線程也稱為:後台線程

  - 守護線程是通過普通線程調用setDaemon(boolean on)方法設置而來的,因此創建上與普通線程無異.
  - 守護線程的結束時機上有一點與普通線程不同,即:進程的結束.
  - 進程結束:當一個進程中的所有普通線程都結束時,進程就會結束,此時會殺掉所有正在運行的守護線程.

  ##### 用途

  - GC就是運行在守護線程上的
  - 當我們有一個任務不需要關注何時結束，當程序需要結束時可以一起結束的任務就可以放在守護線程上執行

  ##### 例

  ```java
  package thread;
  
  /**
   * 守護線程
   * 線程提供了一個方法:
   * void setDaemon(boolean on)
   * 如果參數為true，則會將當前線程設置為守護線程。
   *
   * 守護線程與普通的用戶線程(線程創建出來時默認就是用戶線程)的區別在於進程結束
   *
   * 進程結束:
   * 當一個JAVA進程中所有的用戶線程都結束時，進程就會結束，此時會強制殺死所有還在運行
   * 的守護線程。
   *
   * GC就是運行在一條守護線程上的。
   */
  public class DaemonThreadDemo {
      public static void main(String[] args) {
          Thread rose = new Thread("rose"){
              public void run(){
                  for(int i=0;i<5;i++){
                      System.out.println(getName()+":let me go !!!");
                      try {
                          Thread.sleep(1000);
                      } catch (InterruptedException e) {
                      }
                  }
                  System.out.println(getName()+":啊啊啊啊啊AAAAAAaaaaa....");
                  System.out.println("噗通！");
              }
          };
  
          Thread jack = new Thread("jack"){
              public void run(){
                  while(true){
                      System.out.println(getName()+":you jump!i jump!");
                      try {
                          Thread.sleep(1000);
                      } catch (InterruptedException e) {
                      }
                  }
              }
          };
  
          rose.start();
          jack.setDaemon(true);//設置守護線程必須在線程啟動前進行
          jack.start();
  
  
      }
  }
  ```

  

  ### 多線程並發安全問題

  #### 概念

  當多個線程並發操作同一臨界資源,由於線程切換時機不確定,導致操作臨界資源的順序出現混亂嚴重時可能導致系統癱瘓.
  **臨界資源**:操作該資源的全過程同時只能被單個線程完成.

  #### 例

  當beans為1時，若兩個線程同時調用getBean方法，t1線程先進行if判斷，此時beans不為0，於是執行if後面的操作準備獲取beans的值並對其進行--操作，但是還沒有執行這句話發生了線程切換，那麽t2線程也進行if判斷，由於beans不為0，t2線程也執行if後面的操作獲取beans的值並對其進行--操作，這會導致兩個線程最終將beans的值從-減為了-1.導致後續操作出現死循環。

  這就是由於線程切換不確定導致執行順序出現了混亂，也就是所謂的並發安全問題

  ```java
  package thread;
  
  /**
   * 多線程並發安全問題
   * 當多個線程並發操作同一臨界資源，由於線程切換的時機不確定，導致操作順序出現
   * 混亂，嚴重時可能導致系統癱瘓。
   * 臨界資源:同時只能被單一線程訪問操作過程的資源。
   */
  public class SyncDemo {
      public static void main(String[] args) {
          Table table = new Table();
          Thread t1 = new Thread(){
              public void run(){
                  while(true){
                      int bean = table.getBean();
                      Thread.yield();
                      System.out.println(getName()+":"+bean);
                  }
              }
          };
          Thread t2 = new Thread(){
              public void run(){
                  while(true){
                      int bean = table.getBean();
                      /*
                          static void yield()
                          線程提供的這個靜態方法作用是讓執行該方法的線程
                          主動放棄本次時間片。
                          這里使用它的目的是模擬執行到這里CPU沒有時間了，發生
                          線程切換，來看並發安全問題的產生。
                       */
                      Thread.yield();
                      System.out.println(getName()+":"+bean);
                  }
              }
          };
          t1.start();
          t2.start();
      }
  }
  
  class Table{
      private int beans = 20;//桌子上有20個豆子
  
      public int getBean(){
          if(beans==0){
              throw new RuntimeException("沒有豆子了!");
          }
          Thread.yield();
          return beans--;
      }
  }
  ```

  

  #### synchronized關鍵字

  **解決並發安全問題的本質就是將多個線程並發(同時)操作改為同步(排隊)操作來解決。**

  synchronized有兩種使用方式

  - 在方法上修飾,此時該方法變為一個同步方法
  - 同步塊,可以更準確的鎖定需要排隊的代碼片段

  ##### 同步方法

  當一個方法使用synchronized修飾後,這個方法稱為"同步方法",即:多個線程不能同時 在方法內部執行.只能有先後順序的一個一個進行. 將並發操作同一臨界資源的過程改為同步執行就可以有效的解決並發安全問題.

  ```java
  package thread;
  
  /**
   * 多線程並發安全問題
   * 當多個線程並發操作同一臨界資源，由於線程切換的時機不確定，導致操作順序出現
   * 混亂，嚴重時可能導致系統癱瘓。
   * 臨界資源:同時只能被單一線程訪問操作過程的資源。
   */
  public class SyncDemo {
      public static void main(String[] args) {
          Table table = new Table();
          Thread t1 = new Thread(){
              public void run(){
                  while(true){
                      int bean = table.getBean();
                      Thread.yield();
                      System.out.println(getName()+":"+bean);
                  }
              }
          };
          Thread t2 = new Thread(){
              public void run(){
                  while(true){
                      int bean = table.getBean();
                      /*
                          static void yield()
                          線程提供的這個靜態方法作用是讓執行該方法的線程
                          主動放棄本次時間片。
                          這里使用它的目的是模擬執行到這里CPU沒有時間了，發生
                          線程切換，來看並發安全問題的產生。
                       */
                      Thread.yield();
                      System.out.println(getName()+":"+bean);
                  }
              }
          };
          t1.start();
          t2.start();
      }
  }
  
  class Table{
      private int beans = 20;//桌子上有20個豆子
  
      /**
       * 當一個方法使用synchronized修飾後，這個方法稱為同步方法，多個線程不能
       * 同時執行該方法。
       * 將多個線程並發操作臨界資源的過程改為同步操作就可以有效的解決多線程並發
       * 安全問題。
       * 相當於讓多個線程從原來的搶著操作改為排隊操作。
       */
      public synchronized int getBean(){
          if(beans==0){
              throw new RuntimeException("沒有豆子了!");
          }
          Thread.yield();
          return beans--;
      }
  }
  ```

  

  ##### 同步塊

  有效的縮小同步範圍可以在保證並發安全的前提下盡可能的提高並發效率.同步塊可以更準確的控制需要多個線程排隊執行的代碼片段.

  語法:

  ```java
  synchronized(同步監視器對象){
     需要多線程同步執行的代碼片段
  }
  ```

  **同步監視器對象即上鎖的對象,要想保證同步塊中的代碼被多個線程同步運行,則要求多個線程看到的同步監視器對象是同一個.**

  ```java
  package thread;
  
  /**
   * 有效的縮小同步範圍可以在保證並發安全的前提下盡可能提高並發效率。
   *
   * 同步塊
   * 語法:
   * synchronized(同步監視器對象){
   *     需要多個線程同步執行的代碼片段
   * }
   * 同步塊可以更準確的鎖定需要多個線程同步執行的代碼片段來有效縮小排隊範圍。
   */
  public class SyncDemo2 {
      public static void main(String[] args) {
          Shop shop = new Shop();
          Thread t1 = new Thread(){
              public void run(){
                  shop.buy();
              }
          };
          Thread t2 = new Thread(){
              public void run(){
                  shop.buy();
              }
          };
          t1.start();
          t2.start();
      }
  }
  
  class Shop{
      public void buy(){
          /*
              在方法上使用synchronized，那麽同步監視器對象就是this。
           */
  //    public synchronized void buy(){
          Thread t = Thread.currentThread();//獲取運行該方法的線程
          try {
              System.out.println(t.getName()+":正在挑衣服...");
              Thread.sleep(5000);
              /*
                  使用同步塊需要指定同步監視器對象，即:上鎖的對象
                  這個對象可以是java中任何引用類型的實例，只要保證多個需要排隊
                  執行該同步塊中代碼的線程看到的該對象是"同一個"即可
               */
              synchronized (this) {
  //            synchronized (new Object()) {//沒有效果!
                  System.out.println(t.getName() + ":正在試衣服...");
                  Thread.sleep(5000);
              }
  
              System.out.println(t.getName()+":結賬離開");
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
  
      }
  }
  ```

  

  ##### 在靜態方法上使用synchronized

  當在靜態方法上使用synchronized後,該方法是一個同步方法.由於靜態方法所屬類,所以一定具有同步效果.

  靜態方法使用的同步監視器對象為當前類的類對象(Class的實例).

  注:類對象會在後期反射知識點介紹.

  ```java
  package thread;
  
  /**
   * 靜態方法上如果使用synchronized，則該方法一定具有同步效果。
   */
  public class SyncDemo3 {
      public static void main(String[] args) {
          Thread t1 = new Thread(){
              public void run(){
                  Boo.dosome();
              }
          };
          Thread t2 = new Thread(){
              public void run(){
                  Boo.dosome();
              }
          };
          t1.start();
          t2.start();
      }
  }
  class Boo{
      /**
       * synchronized在靜態方法上使用是，指定的同步監視器對象為當前類的類對象。
       * 即:Class實例。
       * 在JVM中，每個被加載的類都有且只有一個Class的實例與之對應，後面講反射
       * 知識點的時候會介紹類對象。
       */
      public synchronized static void dosome(){       
              try {
                  Thread t = Thread.currentThread();
                  System.out.println(t.getName() + ":正在執行dosome方法...");
                  Thread.sleep(5000);
                  System.out.println(t.getName() + ":執行dosome方法完畢!");
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
      }
  }
  ```

  **靜態方法中使用同步塊時,指定的鎖對象通常也是當前類的類對象**

  ```java
  package thread;
  
  public class SyncDemo3 {
      public static void main(String[] args) {
  //        new Thread(()->Foo.dosome()).start();
  //        new Thread(Foo::dosome).start();
  
          Foo f1 = new Foo();
          Foo f2 = new Foo();
          new Thread(()->f1.dosome()).start();
          new Thread(()->f2.dosome()).start();
      }
  }
  
  class Foo{
  //    public synchronized static void dosome(){
      public static void dosome(){
          /*
              在靜態方法中使用同步塊時，同步監視器對象還是使用當前類的類對象
              獲取類對象的方式:類名.class
              例如獲取Foo的類對象就是:Foo.class
           */
          synchronized (Foo.class) {
              try {
                  Thread t = Thread.currentThread();
                  System.out.println(t.getName() + ":正在執行dosome方法");
                  Thread.sleep(5000);
                  System.out.println(t.getName() + ":執行dosome方法完畢");
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
      }
  }
  ```

  #### 互斥鎖

  當多個線程執行不同的代碼片段,但是這些代碼片段之間不能同時運行時就要設置為互斥的.

  使用synchronized鎖定多個代碼片段,並且指定的同步監視器是同一個時,這些代碼片段之間就是互斥的.

  ```java
  package thread;
  
  /**
   * 互斥鎖
   * 當使用synchronized鎖定多個不同的代碼片段，並且指定的同步監視器對象相同時，
   * 這些代碼片段之間就是互斥的，即:多個線程不能同時訪問這些方法。
   */
  public class SyncDemo4 {
      public static void main(String[] args) {
          Foo foo = new Foo();
          Thread t1 = new Thread(){
              public void run(){
                  foo.methodA();
              }
          };
          Thread t2 = new Thread(){
              public void run(){
                  foo.methodB();
              }
          };
          t1.start();
          t2.start();
      }
  }
  class Foo{
      public synchronized void methodA(){
          Thread t = Thread.currentThread();
          try {
              System.out.println(t.getName()+":正在執行A方法...");
              Thread.sleep(5000);
              System.out.println(t.getName()+":執行A方法完畢!");
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      }
      public synchronized void methodB(){
          Thread t = Thread.currentThread();
          try {
              System.out.println(t.getName()+":正在執行B方法...");
              Thread.sleep(5000);
              System.out.println(t.getName()+":執行B方法完畢!");
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      }
  }
  ```

  

  ## 總結

  ### 多線程

  線程:單一的順序執行流程就是一個線程，順序執行:代碼一句一句的先後執行。

  多線程:多個線程並發執行。線程之間的代碼是快速被CPU切換執行的，造成一種感官上"同時"執行的效果。

  #### 線程的創建方式

  1. 繼承Thread，重寫run方法，在run方法中定義線程要執行的任務

     優點:

     - 結構簡單，便於匿名內部類創建

     缺點:

     - 繼承沖突:由於java單繼承，導致如果繼承了線程就無法再繼承其他類去覆用方法
     - 耦合問題:線程與任務耦合在一起，不利於線程的重用。

  2. 實現Runnable接口單獨定義線程任務

     優點:

     - 猶豫是實現接口，沒有繼承沖突問題
     - 線程與任務沒有耦合關系，便於線程的重用

     缺點:

     - 創建覆雜一些(其實也不能算缺點)

  #### 線程Thread類的常用方法

  void run():線程本身有run方法，可以在第一種創建線程時重寫該方法來定義線程任務。

  void start():**啟動線程**的方法。調用後線程被納入到線程調度器中統一管理，並處於RUNNABLE狀態，等待分配時間片開始並發運行。

  ```
        注:線程第一次獲取時間片開始執行時會自動執行run方法。
  
                **啟動線程一定是調用start方法，而不能調用run方法!**
  ```

  String getName():獲取線程名字

  long getId():獲取線程唯一標識

  int getPriority():獲取線程優先級，對應的是整數1-10

  boolean isAlive():線程是否還活著

  boolean isDaemon():是否為守護線程

  boolean isInterrupted():是否被中斷了

  void setPriority(int priority):設置線程優先級，參數可以傳入整數1-10。1為最低優先級，5為默認優先級，10為最高優先級

  ```java
  優先級越高的線程獲取時間片的次數越多。可以使用Thread的常量MIN_PRIORITY,NORM_PRIORITY,MAX_PRIORITY。
  他們分別表示最低，默認，最高優先級
  ```

  static void sleep(long ms):靜態方法sleep可以讓運行該方法的線程阻塞參數ms指定的毫秒。

  static Thread currentThread():獲取運行該方法的線程。

  void setDaemon(boolean on):設置線程是否為守護線程，當參數為true時當前線程被設置為守護線程。**該操作必須在線程啟動前進行**

  **守護線程與普通線程的區別**主要體現在當java進程中所有的普通線程都結束時進程會結束，在結束前會殺死所有還在運行的守護線程。

  

  #### 重點:多線程並發安全問題

  - 什麽是多線程並發安全問題:

    當多個線程並發操作同一臨界資源，由於線程切換時機不確定，導致執行順序出現混亂。

    解決辦法:

    將並發操作改為同步操作就可有效的解決多線程並發安全問題

  - 同步與異步的概念:同步和異步都是說的多線程的執行方式。

    多線程各自執行各自的就是異步執行，而多線程執行出現了先後順序進行就是同步執行

  - synchronized的兩種用法

    1.直接在方法上聲明，此時該方法稱為同步方法，同步方法同時只能被一個線程執行

    2.同步塊，推薦使用。同步塊可以更準確的控制需要同步執行的代碼片段。

    **有效的縮小同步範圍可以在保證並發安全的前提下提高並發效率**

  - 同步監視器對象的選取:

    對於同步的成員方法而言，同步監視器對象不可指定，只能是this

    對於同步的靜態方法而言，同步監視器對象也不可指定，只能是類對象

    對於同步塊而言，需要自行指定同步監視器對象，選取原則:

    1.必須是引用類型

    2.多個需要同步執行該同步塊的線程看到的該對象必須是**同一個**

  - 互斥性

    當使用多個synchronized修飾了多個代碼片段，並且指定的同步監視器都是同一個對象時，這些代碼片段就是互斥的，多個線程不能同時在這些代碼片段上執行。