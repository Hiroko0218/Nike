# 語言基礎第三天：

## 回顧：

1. 變量：存數的

   ```java
   int a;  int b,c;
   int a = 5;  int a;  a = 5;
   int b = a+10; System.out.println(b);
   a = a+10;
   ```

2. 八種基本數據類型：byte、short、int、long、float、double、boolean、char

   - int：整型，4個字節，5，25，250......
   - long：長整型，8個字節，5L，100000000000L......
   - double：浮點型，8個字節，5.0，3.14，25.678......
   - boolean：布爾型，1個字節，true，false
   - char：字符型，2個字節，'你'，'y'，'6'，'*'......

3. 類型間的轉換：

   - 兩種方式：

     - 自動/隱式類型轉換：小到大
     - 強制類型轉換：大到小
       - (要轉換成為的數據類型)變量
       - 有可能溢出或丟失精度

   - 兩點規則：

     ```java
     short s1 = 5;
     short s2 = 6;
     short s3 = (short)(s1+s2);
     ```

     

## 精華筆記：

1. 運算符：
   - 算術：+，-，*，/，%，++，--
   - 關系：>、<、>=、<=、==、!=
   - 邏輯：&&、||、!
   - 賦值：=、+=、-=、*=、/=、%=
   - 字符串連接：+
   - 條件：boolean？數1：數2
2. 分支結構(上)：基於條件執行
   - if結構：1條路
   - if...else結構：2條路
   - if...else if結構：多條路



## 筆記：

1. 運算符：

   - 算術：+，-，*，/，%，++，--

     - %:取模/取余，余數為0即為整除

       ```java
       System.out.println(5%2); //1，商2余1
       System.out.println(8%2); //0，商4余0----整除
       System.out.println(2%8); //2，商0余2
       ```

     - ++/--:自增1/自減1，可在變量前也可在變量後

       - 單獨使用時，在前在後都一樣
       - 被使用時，在前在後不一樣
         - a++的值為a------------(a--的值為a)
         - ++a的值為a+1--------(--a的值為a-1)

       ```java
       //演示++單獨使用
       int a=5,b=5;
       a++; //相當於a=a+1
       ++b; //相當於b=b+1
       System.out.println(a); //6
       System.out.println(b); //6
       
       //演示++被使用
       int a=5,b=5;
       int c = a++; //將a++的值5賦值給c，同時a自增1
       int d = ++b; //將++b的值6賦值給d，同時b自增1
       System.out.println(a); //6
       System.out.println(b); //6
       System.out.println(c); //5
       System.out.println(d); //6
       
       //演示--單獨使用:
       int a=5,b=5;
       a--; //相當於a=a-1
       --b; //相當於b=b-1
       System.out.println(a); //4
       System.out.println(b); //4
       
       //演示--被使用:
       int a=5,b=5;
       int c = a--; //將a--的值5賦值給c，同時a自減1變為4
       int d = --b; //將--b的值4賦值給d，同時b自減1變為4
       System.out.println(a); //4
       System.out.println(b); //4
       System.out.println(c); //5
       System.out.println(d); //4
       ```

   - 關系：>、<、>=、<=、==、!=

     ```java
     1)>(大於)、<(小於)
       >=(大於或等於)、<=(小於或等於)
       ==(等於)、!=(不等於)
     2)關系運算的結果為boolean型，
       關系成立則為true，關系不成立則為false
     ```

     ```java
     int a=5,b=10,c=5;
     boolean b1 = a>b;
     System.out.println(b1);   //false
     System.out.println(c<b);  //true
     System.out.println(a>=c); //true
     System.out.println(a<=b); //true
     System.out.println(a==c); //true
     System.out.println(a!=c); //false
     
     System.out.println(a%2==0); //false
     System.out.println(a+c>b);  //false
     System.out.println(a++>5); //false------a自增1變為6
     System.out.println(a++>5); //true-------a自增1變為7
     ```

   - 邏輯：&&、||、!

     - 邏輯運算是建立在關系運算的基礎之上的
       邏輯運算的結果也是boolean型  

     - &&：短路與(並且)，兩邊都為真則為真，見false則false

       - 第1個條件為false時，則發生短路(後面的不執行了)

         ```java
         int a=5,b=10,c=5;
         boolean b1 = b>=a && b<c;
         System.out.println(b1);          //true&&false=false
         System.out.println(b<=c && b>a); //false&&true=false
         System.out.println(a==b && b<a); //false&&false=false
         System.out.println(b!=c && b>a); //true&&true=true
         int age = 99;
         System.out.println(age>=18 && age<=50); //年齡在18到50之間
         int score = 86;
         System.out.println(score>=0 && score<=100); //成績在0到100之間
         
         //演示短路:
         boolean b3 = a>b && c++>2;
         System.out.println(b3); //false
         System.out.println(c);  //5，發生短路了
         ```

     - ||：短路或(或者)，有真則為真，見true則true

       - 第1個條件為true時，則發生短路(後面的不執行了)

         ```java
         int a=5,b=10,c=5;
         System.out.println(b>=a || b<c); //true||false=true
         System.out.println(b<=c || b>a); //false||true=true
         System.out.println(b!=c || b>a); //true||true=true
         System.out.println(a==b || b<a); //false||false=false
         int score = 90;
         System.out.println(score<0 || score>100); //成績不合法驗證
         
         //演示短路:
         boolean b3 = a<b || c++>2;
         System.out.println(b3); //true
         System.out.println(c);  //5，發生短路了
         ```

     - !：邏輯非(取反)，非真則假，非假則真

       ```java
       int a=5,b=10,c=5;
       boolean b2 = !(a<b);
       System.out.println(b2);     //!true=false
       System.out.println(!(a>b)); //!false=true
       ```

   - 賦值：=、+=、-=、*=、/=、%=

     - 簡單賦值運算符：=

     - 擴展賦值運算符：+=,-=,*=,/=,%=    

       - 注意:擴展賦值運算符自帶強轉功能

       ```java
       int a = 5;
       a += 10; //相當於a=(int)(a+10)
       System.out.println(a); //15
       a *= 2; //相當於a=(int)(a*2)
       System.out.println(a); //30
       a /= 6; //相當於a=(int)(a/6)
       System.out.println(a); //5
       
       //小面試題:
       short s = 5;
       //s = s+10; //編譯錯誤，需強轉，改為:s=(short)(s+10);
       s += 10; //相當於s=(short)(s+10);
       ```

   - 字符串連接：+

     - 若兩邊為數字，則做加法運算

     - 若兩邊出現了字符串，則做字符串連接

       ```java
       int age = 39;
       System.out.println("age="); //age=
       System.out.println(age);    //39
       System.out.println("age="+age); //age=39
       System.out.println("我今年"+age+"歲了"); //我今年39歲了
       
       String name = "WKJ";
       System.out.println("大家好，我叫"+name); //大家好，我叫WKJ
       System.out.println("大家好，我叫"+name+"，今年"+age+"歲了"); //大家好，我叫WKJ，今年39歲了
       ```

     - 任何類型的數據與字符串連接，結果都會變為字符串型

       ```java
       System.out.println(10+20+""+30); //3030---------String
       System.out.println(""+10+20+30); //102030-------String
       System.out.println(10+20+30+""); //60-----------String
       ```

   - 條件：boolean？數1：數2

     - 語法：

       - boolean?數1:數2

     - 執行過程：

       - 整個條件運算是有值的，它的值要麽是?號後的數1，要麽是:號後的數2
       - 計算boolean的值:
         - 若為true，則整個表達式的值為?號後的數1
         - 若為false，則整個表達式的值為:號後的數2

       ```java
       int num = 5;
       int flag = num>0?1:-1;
       System.out.println(flag); //1
       
       int a=8,b=5;
       int max = a>b?a:b;
       System.out.println("max="+max); //max=8
       ```

2. 分支結構(上)：基於條件執行

   - if結構：1條路

     ```java
     1)語法:
        if(boolean){
           語句塊--------------基於條件執行的語句
        }
     2)執行過程:
         判斷boolean的值:
            若為true，則執行語句塊(整個結束)
            若為false，則直接結束
     ```

     ```java
     //1)滿500打8折:
     double price = 300.0; //消費金額  帶數(600.0,300.0)
     if(price>=500){   //滿500
         price *= 0.8; //打8折
     }
     System.out.println("最終消費金額為:"+price);
     
     //2)判斷成績是否合法
     int score = 555; //帶數(95,-5,555)
     if(score>=0 && score<=100){
         System.out.println("成績合法");
     }
     System.out.println("繼續執行...");
     ```

   - if...else結構：2條路

     ```java
     1)語法:
        if(boolean){
           語句塊1
        }else{
           語句塊2
        }
     2)執行過程:
         判斷boolean的值:
           若為true，則執行語句塊1(整個結束)
           若為false，則執行語句塊2(整個結束)
     3)說明:
         語句塊1和語句塊2，必走其中之一-------------2選1
     ```

     ```java
     //1)滿500打8折，不滿500打9折:
     double price = 300.0;   //帶數(600.0,300.0)
     if(price>=500){ //滿
         price*=0.8;
     }else{ //不滿
         price*=0.9;
     }
     System.out.println("最終消費金額為:"+price);
     
     //2)判斷成績合法還是不合法
     int score = 95; //帶數(95,-5,555)
     if(score>=0 && score<=100){
         System.out.println(score+"是合法成績");
     }else{
         System.out.println(score+"是不合法成績");
     }
     ```

   - if...else if結構：多條路

     ```java
     1)語法:
       if(boolean-1){
         語句塊1
       }else if(boolean-2){
         語句塊2
       }else if(boolean-3){
         語句塊3
       }else{
         語句塊4
       }
     2)執行過程:
         判斷boolean-1，若為true則執行語句塊1(結束)，若為false則
         再判斷boolean-2，若為true則執行語句塊2(結束)，若為false則
         再判斷boolean-3，若為true則執行語句塊3(結束)，若為false則執行語句塊4(結束)
     3)說明:
         語句塊1/2/3/4，必走其中之一--------多選1
     ```

     ```java
     //1)滿2000打5折，滿1000不滿2000打7折，滿500不滿1000打8折，不滿500打9折:
     double price = 6000.0; //帶數(2000.0,1000.0,600.0,300.0)
     if(price>=2000){
         price*=0.5;
     }else if(price>=1000){
         price*=0.7;
     }else if(price>=500){
         price*=0.8;
     }else{
         price*=0.9;
     }
     System.out.println("最終消費金額為:"+price);
     ```

     

## 補充：

1. 任何覆雜的程序邏輯都可以通過三種結構來實現：

   - 順序結構：從上往下逐行執行，每句必走
   - 分支結構：有條件的執行某語句，並非每句必走
   - 循環結構：明天講

2. 明日單詞：

   ```java
   1)Scanner/scan：掃描儀
   2)import:引入、導入
   3)System:系統
   4)in:進入
   5)new:新的
   6)nextInt:下一個整數
   7)nextDouble:下一個浮點數
   8)switch:開關
   9)case:案例
   10)break:中斷、退出
   11)command:命令
   12)by:通過
   13)times:次數
   14)while:當...的時候，循環的一種
   15)do:做、幹
   16)math:數字
   17)random:隨機
   18)guess/guessing:猜
   19)game:遊戲
   20)count:數量
   ```