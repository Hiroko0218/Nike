# 語言基礎第二天：

## 回顧：

1. java開發環境：

   - 編譯運行過程：

     - 編譯期：.java源文件，經過編譯，生成.class字節碼文件

     - 運行期：JVM加載.class並運行.class

       > 特點：跨平台，一次編譯到處使用

   - 名詞：

     - JVM：java虛擬機   JVM加載.class並運行.class
     - JRE：java運行環境  JRE = JVM+java系統類庫(小零件)
     - JDK：java開發工具包   JDK = JRE+編譯、運行等命令工具

2. idea：

   - JetBrains，分為社區版和終級版
   - 開發步驟：
     - 新建Java項目/工程
     - 新建Java包
     - 新建Java類
   - 注釋：解釋性文本
     - 單行注釋：//
     - 多行注釋：/*   */
     - 文檔注釋：/**     */



## 精華筆記：

1. 變量：存數的

   - 聲明：----相當於在銀行開了個帳戶
   - 初始化：----相當於給帳戶存錢
   - 使用：-----使用的是帳戶里面的錢
     - 對變量的使用就是對它所存的那個數的使用
     - 變量的用之前必須聲明並初始化
   - 命名：-----相當於給帳戶起名
     - 只能包含字母、數字、_和$符，不能以數字開頭
     - 嚴格區分大小寫
     - 不能使用關鍵字
     - 允許中文命名，但不建議，建議"英文的見名知意"、"小駝峰命名法"

2. 八種基本數據類型：byte、short、int、long、float、double、boolean、char

   - int：整型，4個字節，-21個多億到21個多億

     - 整數直接量默認為int類型，但不能超出範圍，若超範圍則發生編譯錯誤
     - 兩個整數相除，結果還是整數，小數位無條件舍棄(不會四舍五入)
     - 運算時若超出範圍，則發生溢出，溢出不是錯誤，但是需要避免

   - long：長整型，8個字節，-900萬萬億多到900萬萬億多

     - 若想表示長整型直接量，需在數字後加L或l 
     - 運算時若有可能溢出，建議在第1個數字後加L

   - double：浮點型，8個字節，很大很大很大

     - 小數直接量默認為double型，若想表示float，需在數字後加F或f
     - 不能表示精確數據，運算時有可能會發生舍入誤差，精確場合不能使用

   - boolean：布爾型，1個字節

     - 只能存儲true或false

   - char：字符型，2個字節

     - 采用的是Unicode編碼格式，一個字符對應一個碼

       表現的形式是字符char，但本質上是碼int(0到65535之間)

       (ASCII：'a'----97     'A'----65    '0'----48)

     - 字符型直接量必須放在單引號中，有且僅有1個

     - 特殊符號需要通過\來轉義

3. 類型間的轉換：

   - 基本數據類型從小到大依次為：

     - byte----short----int----long----float----double

                      char----

   - 兩種方式：

     - 自動/隱式類型轉換：小類型到大類型
     - 強制類型轉換：大類型到小類型
       - 語法：(要轉換成為的數據類型)變量
       - 注意：強轉有可能會溢出或丟失精度

   - 兩點規則：

     - 整數直接量可以直接賦值給byte,short,char，但不能超出範圍

     - byte,short,char型數據參與運算時，系統會將其自動轉換為int類型再運算




## 筆記：

1. 變量：存數的

   - 聲明：----相當於在銀行開了個帳戶

     ```java
     int a; //聲明一個整型的變量，名為a
     int b,c,d; //聲明三個整型的變量，名為b,c,d
     //int a; //編譯錯誤，變量不能同名
     ```

   - 初始化：----相當於給帳戶存錢

     ```java
     int e = 250; //聲明整型變量e並賦值為250----開戶的同時存錢
     int f;   //聲明整型變量f----------先開戶
     f = 250; //給變量f賦值為250-------後存錢
     f = 360; //修改變量f的值為360
     int g=5,h=8,i=10; //聲明三個整型變量g,h,i，並分別賦值為5,8,10
     ```

   - 使用：-----使用的是帳戶里面的錢

     - 對變量的使用就是對它所存的那個數的使用

       ```java
       int j = 5; //聲明整型變量j並賦值為5
       int k = j+10; //取出j的值5，加10後，再賦值給變量k
       //輸出語句中，若不加雙引號，則java認為它就是一個變量
       System.out.println(k);   //輸出變量k的值15
       System.out.println("k"); //輸出k，雙引號中的原樣輸出
       j = j+10; //在j本身基礎之上增10
       System.out.println(j); //15
       int balance = 5000; //帳戶余額
       balance = balance+1000; //存款1000
       System.out.println(balance); //6000
       ```

     - 變量的用之前必須聲明並初始化

       ```java
       //System.out.println(m); //編譯錯誤，變量m未聲明
       int m;
       //System.out.println(m); //編譯錯誤，變量m未初始化
       ```

   - 命名：-----相當於給帳戶起名

     - 只能包含字母、數字、_和$符，不能以數字開頭

     - 嚴格區分大小寫

     - 不能使用關鍵字

     - 允許中文命名，但不建議，建議"英文的見名知意"、"小駝峰命名法"

       ```java
       int a1,a_5$,_3c,$5;
       //int a*b; //編譯錯誤，不能包含*號等特殊符號
       //int 1a; //編譯錯誤，不能以數字開頭
       int aa = 5;
       //System.out.println(aA); //編譯錯誤，嚴格區分大小寫
       //int class; //編譯錯誤，不能使用關鍵字
       int 年齡; //正確，但不建議
       int nianLing; //必須杜絕
       int age; //建議"英文的見名知意"
       int score,myScore,myJavaScore; //建議"小駝峰命名法"
       ```

2. 八種基本數據類型：byte、short、int、long、float、double、boolean、char

   - int：整型，4個字節，-21個多億到21個多億

     - 整數直接量默認為int類型，但不能超出範圍，若超範圍則發生編譯錯誤

       ```java
       int a = 25; //25為整數直接量，默認為int類型
       //int b = 10000000000; //編譯錯誤，100億默認為int類型，但超出範圍了
       //int c = 3.14; //編譯錯誤，整型變量中只能裝整數
       ```

     - 兩個整數相除，結果還是整數，小數位無條件舍棄(不會四舍五入)

       ```java
       System.out.println(5/2);   //2
       System.out.println(2/5);   //0
       System.out.println(5/2.0); //2.5
       ```

     - 運算時若超出範圍，則發生溢出，溢出不是錯誤，但是需要避免

       ```java
       int d = 2147483647; //int的最大值
       d = d+1;
       System.out.println(d); //-2147483648(int的最小值)，發生溢出了，需要避免
       ```

   - long：長整型，8個字節，-900萬萬億多到900萬萬億多

     - 若想表示長整型直接量，需在數字後加L或l 

       ```java
       long a = 25L; //25L為長整型直接量
       //long b = 10000000000; //編譯錯誤，100億默認int類型，但超出int範圍了
       long c = 10000000000L; //100億L為長整型直接量
       //long d = 3.14; //編譯錯誤，長整型變量中只能裝整數
       ```

     - 運算時若有可能溢出，建議在第1個數字後加L

       ```java
       //運算時若有可能溢出，建議在第1個數字後加L
       long e = 1000000000*2*10L;
       System.out.println(e); //200億
       long f = 1000000000*3*10L;
       System.out.println(f); //不是300億
       long g = 1000000000L*3*10;
       System.out.println(g); //300億
       ```

   - double：浮點型，8個字節，很大很大很大

     - 小數直接量默認為double型，若想表示float，需在數字後加F或f

       ```java
       double a = 3.14; //3.14為小數直接量，默認為double型
       float b = 3.14F; //3.14F為float型直接量
       ```

     - 不能表示精確數據，運算時有可能會發生舍入誤差，精確場合不能使用

       ```java
       double c=3.0,d=2.9;
       System.out.println(c-d); //0.10000000000000009，有可能發生舍入誤差
       ```

   - boolean：布爾型，1個字節

     - 只能存儲true或false

       ```java
       boolean a = true;  //true為布爾型直接量
       boolean b = false; //false為布爾型直接量
       //boolean c = 250; //編譯錯誤，布爾型只能存儲true或false
       ```

   - char：字符型，2個字節

     - 采用的是Unicode編碼格式，一個字符對應一個碼

       表現的形式是字符char，但本質上是碼int(0到65535之間)

       (ASCII：'a'----97     'A'----65    '0'----48)

     - 字符型直接量必須放在單引號中，有且僅有1個

       ```java
       char c1 = '女'; //字符女
       char c2 = 'f'; //字符f
       char c3 = '6'; //字符6
       char c4 = ' '; //空格符
       //char c5 = 女; //編譯錯誤，字符型直接量必須放在單引號中
       //char c6 = ''; //編譯錯誤，必須有字符
       //char c7 = '10'; //編譯錯誤，只能存儲1個字符
       
       char c8 = 65; //0到65535之間
       System.out.println(c8); //println()會依據變量的類型做輸出展示
       //c8為char型，所以會以字符的形式輸出
       
       char c9 = '\\';
       System.out.println(c9); //\
       ```

     - 特殊符號需要通過\來轉義

       ```java
       char c9 = '\\';
       System.out.println(c9); //\
       ```

3. 類型間的轉換：

   - 基本數據類型從小到大依次為：

     - byte----short----int----long----float----double

                      char----

   - 兩種方式：

     - 自動/隱式類型轉換：小類型到大類型

     - 強制類型轉換：大類型到小類型

       - 語法：(要轉換成為的數據類型)變量

       - 注意：強轉有可能會溢出或丟失精度

         ```java
         int a = 5;
         long b = a; //自動類型轉換
         int c = (int)b; //強制類型轉換
         
         long d = 5; //自動類型轉換
         double e = 5; //自動類型轉換
         
         long f = 10000000000L;
         int g = (int)f;
         System.out.println(g); //1410065408，強轉有可能發生溢出
         double h = 25.987;
         int i = (int)h;
         System.out.println(i); //25，強轉有可能丟失精度
         ```

   - 兩點規則：

     - 整數直接量可以直接賦值給byte,short,char，但不能超出範圍

     - byte,short,char型數據參與運算時，系統會將其自動轉換為int類型再運算

       ```java
       byte b1 = 5;
       byte b2 = 6;
       byte b3 = (byte)(b1+b2);
       
       System.out.println(2+2);     //4
       System.out.println(2+'2');   //52，2加上'2'的碼50
       System.out.println('2'+'2'); //100，'2'的碼50，加上'2'的碼50
       System.out.println('2'); //2，因為沒有運算，所以輸出的是字符2
       
       int a = 'a';
       System.out.println(a); //查看字符對應的碼
       char c = 97;
       System.out.println(c); //查看碼對應的字符
       ```



## 補充：

1. 命名法：

   - 小駝峰命名法：第1個單詞首字母小寫，其余單詞首字母大寫------------變量/方法

     ```java
     score,myScore,myJavaScore
     ```

   - 帕斯卡命名法/大駝峰命名法：所有單詞首字母大寫--------------------------類

     ```java
     Score,MyScore,MyJavaScore
     ```

2. Unicode：萬國碼、統一碼、通用碼，是世界級通用的定長(16位)字符集

3. 明日單詞：

   ```java
   1)name:姓名
   2)number/num:數字
   3)flag:標記
   4)max:最大值
   5)if:如果
   6)price:價格
   7)else:否則、其它的
   8)operator/oper:運算符
   ```