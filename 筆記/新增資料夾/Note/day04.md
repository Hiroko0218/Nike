# 語言基礎第四天：

## 回顧：

1. 運算符：
   - 算術：+、-、*、/、%、++、--
   - 關系：>、<、>=、<=、==、!=
   - 邏輯：&&、||、!
   - 賦值：=、+=、-=、*=、/=、%=
   - 字符串連接：+
   - 條件：boolean?數1:數2
2. 分支結構(上)：基於條件執行的
   - if結構：1條路
   - if...else結構：2條路----------2選1
   - if...else if結構：多條路------多選1



## 精華筆記：

1. Scanner接收用戶輸入的數據：------共3步，先背下來，面向對象第5天才能理解

2. 分支結構(下)：

   - switch...case結構：多條路

     - 優點：效率高、結構清晰

     - 缺點：只能對整數判斷相等

     - break：跳出switch

       > switch中數據的類型：byte,short,int,char,String,枚舉

3. 循環：反覆多次執行一段相同或相似的代碼

4. 循環三要素：------------------------非常重要

   - 循環變量的初始化

   - 循環的條件(以循環變量為基礎) 

   - 循環變量的改變

     > 循環變量：在整個循環過程中所反覆改變的那個數

5. 循環結構：

   - while結構：先判斷後執行，有可能一次都不執行

   - do...while結構：先執行後判斷，至少執行一次

     > 當第1要素的代碼與第3要素的代碼相同時，首選do...while

   - for結構：應用率最高，適合與次數相關的

6. 三種循環結構如何選擇：

   - 先看循環是否與次數有關：
     - 若有關--------------------------------------直接上for
     - 若無關，再看第1要素與第3要素的代碼是否相同：
       - 若相同--------------------------------直接上do...while
       - 若不同--------------------------------直接上while







## 筆記：

1. Scanner接收用戶輸入的數據：------共3步，先背下來，面向對象第5天才能理解

   ```java
   package day04;
   import java.util.Scanner; //1.導入一個掃描儀
   //Scanner的演示
   public class ScannerDemo {
       public static void main(String[] args) {
           //創建類CommandBySwitch，接收用戶輸入的命令command(int)，並輸出
           Scanner scan = new Scanner(System.in); //2.新建一個掃描儀scan
           System.out.println("請輸入年齡:");
           int age = scan.nextInt(); //掃描一個整數並賦值給age
           System.out.println("請輸入商品價格:");
           double price = scan.nextDouble(); //掃描一個小數並賦值給price
           System.out.println("年齡為:"+age+"，價格為:"+price);
       }
   }
   ```

2. 分支結構(下)：

   - switch...case結構：多條路

     - 優點：效率高、結構清晰

     - 缺點：只能對整數判斷相等

     - break：跳出switch

       > switch中數據的類型：byte,short,int,char,String,枚舉

       ```java
       package day04;
       import java.util.Scanner;
       //命令解析程序
       public class CommandBySwitch {
           public static void main(String[] args) {
               Scanner scan = new Scanner(System.in);
               System.out.println("請選擇功能: 1.存款 2.取款 3.查詢余額 4.退卡");
               int command = scan.nextInt();
       
               switch(command){
                   case 1:
                       System.out.println("存款業務...");
                       break;
                   case 2:
                       System.out.println("取款業務...");
                       break;
                   case 3:
                       System.out.println("查詢余額業務...");
                       break;
                   case 4:
                       System.out.println("退卡成功");
                       break;
                   default:
                       System.out.println("輸入錯誤");
               }
           }
       }
       ```

3. 循環：反覆多次執行一段相同或相似的代碼

4. 循環三要素：------------------------非常重要

   - 循環變量的初始化

   - 循環的條件(以循環變量為基礎) 

   - 循環變量的改變

     > 循環變量：在整個循環過程中所反覆改變的那個數

     ```java
     1)輸出5次"行動是成功的階梯":
         行動是成功的階梯
         行動是成功的階梯
         行動是成功的階梯
         行動是成功的階梯
         行動是成功的階梯
       循環變量:次數times
       1)int times=0;
       2)times<5
       3)times++;
         times=0/1/2/3/4/ 5時結束
             
     2)輸出9的乘法表:
         1*9=9
         2*9=18
         3*9=27
         4*9=36
         5*9=45
         6*9=54
         7*9=63
         8*9=72
         9*9=81
       循環變量:因數num
       1)int num=1;
       2)num<=9
       3)num++;
         num=1/2/3/4/5/6/7/8/9/ 10時結束
             
         1*9=9
         3*9=27
         5*9=45
         7*9=63
         9*9=81
       循環變量:因數num
       1)int num=1;
       2)num<=9
       3)num+=2;
         num=1/3/5/7/9/ 11時結束
     ```

5. 循環結構：

   - while結構：先判斷後執行，有可能一次都不執行

     ```java
     1)語法:
       while(boolean){
         語句塊/循環體------------反覆執行的代碼
       }
     2)執行過程:
         先判斷boolean的值，若為true則執行語句塊，
         再判斷boolean的值，若為true則再執行語句塊，
         再判斷boolean的值，若為true則再執行語句塊，
         如此反覆，直到boolean的值為false時，while循環結束
     ```

     ```java
     //1)輸出5次"行動是成功的階梯":
     int times = 0;  //1)循環變量的初始化
     while(times<5){ //2)循環的條件
         System.out.println("行動是成功的階梯");
         times++;    //3)循環變量的改變
     }
     System.out.println("繼續執行...");
     /*
      執行過程:------帶數
                   times=0
        true  輸出  times=1
        true  輸出  times=2
        true  輸出  times=3
        true  輸出  times=4
        true  輸出  times=5
        false while循環結束
        輸出繼續執行...
     */
     
     //2)輸出9的乘法表:    3*9=27
     int num = 1;
     while(num<=9){
         System.out.println(num+"*9="+num*9);
         num++;  //num+=2;
     }
     System.out.println("繼續執行...");
     ```

     ```java
     package day04;
     import java.util.Scanner;
     //猜數字小遊戲
     public class Guessing {
         public static void main(String[] args) {
             Scanner scan = new Scanner(System.in);
             int num = (int)(Math.random()*1000+1); //1到1000之內的
             System.out.println(num); //作弊
     		
             //300(大),200(小),250(對)
             System.out.println("猜吧!");
             int guess = scan.nextInt(); //1.
             while(guess!=num){ //2.
                 if(guess>num){
                     System.out.println("猜大了");
                 }else{
                     System.out.println("猜小了");
                 }
                 System.out.println("猜吧!");
                 guess = scan.nextInt(); //3.
             }
             System.out.println("恭喜你猜對了!");
         }
     }
     ```

   - do...while結構：先執行後判斷，至少執行一次

     > 當第1要素的代碼與第3要素的代碼相同時，首選do...while

     ```java
     1)語法:
       do{
         語句塊
       }while(boolean);
     2)執行過程:
         先執行語句塊，再判斷boolean的值，若為true則
         再執行語句塊，再判斷boolean的值，若為true則
         再執行語句塊，再判斷boolean的值，若為true則
         再執行語句塊，如此反覆，直到boolean的值為false時，do...while結束
     ```

     ```java
     package day04;
     import java.util.Scanner;
     //猜數字小遊戲
     public class Guessing {
         public static void main(String[] args) {
             Scanner scan = new Scanner(System.in);
             int num = (int)(Math.random()*1000+1); //1到1000之內的
             System.out.println(num); //作弊
     
             //假設num=250
             //300(大),200(小),250(對)
             int guess;
             do{
                 System.out.println("猜吧!");
                 guess = scan.nextInt(); //1+3
                 if(guess>num){
                     System.out.println("猜大了");
                 }else if(guess<num){
                     System.out.println("猜小了");
                 }else{
                     System.out.println("恭喜你猜對了");
                 }
             }while(guess!=num); //2
         }
     }
     ```

   - for結構：應用率最高，適合與次數相關的

     ```java
     1)語法:
         //    1    2    3
         for(要素1;要素2;要素3){
           語句塊/循環體--------------反覆執行的代碼  4
         }
     2)執行過程:
         1243243243243243...2為false時，循環結束
     ```

     ```java
     //for中的循環變量num的作用域，僅在當前for中-----特殊記憶
     for(int num=1;num<=9;num++){
         System.out.println(num+"*9="+num*9);
     }
     //System.out.println(num); //編譯錯誤，超出num作用域了
     for(int num=1;num<=9;num+=2){
         System.out.println(num+"*9="+num*9);
     }
     
     for(int times=0;times<5;times++){
         System.out.println("行動是成功的階梯");
     }
     System.out.println("繼續執行...");
     /*
        執行過程:
          times=0  true  輸出
          times=1  true  輸出
          times=2  true  輸出
          times=3  true  輸出
          times=4  true  輸出
          times=5  false for循環結束
          輸出繼續執行...
     */
     ```

6. 三種循環結構如何選擇：

   - 先看循環是否與次數有關：
     - 若有關--------------------------------------直接上for
     - 若無關，再看第1要素與第3要素的代碼是否相同：
       - 若相同--------------------------------直接上do...while
       - 若不同--------------------------------直接上while



## 補充：

1. 任何覆雜的程序邏輯都可以通過三種結構來實現：

   - 順序結構：從上往下逐行執行，每句必走
   - 分支結構：有條件的執行某語句一次，並非每句必走
   - 循環結構：有條件的執行某語句多次，並非每句必走

2. 生成隨機數：1到1000之內的

   ```java
   Math.random()-------------0.0到0.999999999999...
   *1000---------------------0.0到999.9999999999...
   +1------------------------1.0到1000.999999999...
   (int)---------------------1到1000
   ```

3. 變量的作用域/範圍：

   - 從變量的聲明開始，到包含它最近的大括號結束

4. 變量的重名問題：

   - 作用域重疊時，變量不同重名

5. 明日單詞：

   ```java
   1)for:為了、循環的一種
   2)continue:繼續
   3)result:結果
   4)answer:回答
   5)array/arr:數組
   6)length:長度
   7)multi:多
   8)table:表格
   9)addition:加法
   10)index:下標、索引
   11)out of:超出
   12)bounds:界限
   13)exception:異常
   ```