# 面向對象第一天：



## 精華筆記：

1. 什麽是類？什麽是對象？

   - 現實生活是由很多很多對象組成的，基於對象抽出了類

   - 對象：軟件中真實存在的單個的個體/東西

     類：類型/類別，代表一類個體

   - 類是對象的模子/模板，對象是類的具體的實例，可以將類理解為類別/模子/圖紙

   - 類中可以包含：

     - 對象的屬性/特征/數據----------------------成員變量
     - 對象的行為/動作/功能----------------------方法

   - 一個類可以創建多個對象

2. 如何創建類？如何創建對象？如何訪問成員？

3. this：指代當前對象，哪個對象調用方法它指的就是哪個對象

   - 只能用在方法中，方法中訪問成員變量之前默認有個this.

   - this的用法：

     - this.成員變量名--------------------訪問成員變量(必須掌握)

       > 當成員變量與局部變量同名時，若想訪問成員變量，則this不能省略

     - this.方法名()-------------------------調用方法(一般不用)

     - this()-----------------------------------調用構造方法(一般不用)

4. 構造方法：構造函數、構造器、構建器----------好處：覆用給成員變量賦初始值的代碼

   - 作用：給成員變量賦初始值
   - 語法：與類同名，沒有返回值類型(連void都沒有)
   - 調用：在創建(new)對象時被自動調用
   - 若自己不寫構造方法，則編譯器默認提供一個無參構造方法，若自己寫了構造方法，則不再默認提供
   - 構造方法可以重載



## 筆記：

1. 什麽是類？什麽是對象？

   - 現實生活是由很多很多對象組成的，基於對象抽出了類

   - 對象：軟件中真實存在的單個的個體/東西

     類：類型/類別，代表一類個體

   - 類是對象的模子/模板，對象是類的具體的實例，可以將類理解為類別/模子/圖紙

   - 類中可以包含：

     - 對象的屬性/特征/數據----------------------成員變量
     - 對象的行為/動作/功能----------------------方法

   - 一個類可以創建多個對象

2. 如何創建類？如何創建對象？如何訪問成員？

   ```java
   public class Student {
       //成員變量---描述對象的屬性
       String name;
       int age;
       String className;
       String stuId;
   
       //方法------描述對象的行為
       void study(){
           System.out.println(name+"在學習...");
       }
       void sayHi(){
           System.out.println("大家好，我叫"+this.name+"，今年"+this.age+"歲了，所在班級為"+this.className+"，學號為:"+this.stuId);
       }
       void playWith(String anotherName){
           System.out.println(this.name+"正在和"+anotherName+"一起玩...");
       }
   }
   
   public class StudentTest {
       public static void main(String[] args) {
           //創建一個學生對象
           Student zs = new Student();
           //訪問成員變量
           zs.name = "張三";
           zs.age = 24;
           zs.className = "jsd2302";
           zs.stuId = "001";
           //調用方法
           zs.study();
           zs.sayHi();
           zs.playWith("李四");
   
           Student ls = new Student();
           ls.name = "李四";
           ls.age = 25;
           ls.className = "jsd2302";
           ls.stuId = "002";
           ls.study();
           ls.sayHi();
           ls.playWith("張三");
   
           //1)創建了一個學生對象
           //2)給所有成員變量賦默認值
           Student ww = new Student();
           ww.study();
           ww.sayHi();
           ww.playWith("張三");
       }
   }
   ```

3. this：指代當前對象，哪個對象調用方法它指的就是哪個對象

   - 只能用在方法中，方法中訪問成員變量之前默認有個this.

   - this的用法：  

     - this.成員變量名--------------------訪問成員變量(必須掌握)

       > 當成員變量與局部變量同名時，若想訪問成員變量，則this不能省略

     - this.方法名()-------------------------調用方法(一般不用)

     - this()-----------------------------------調用構造方法(一般不用)

4. 構造方法：構造函數、構造器、構建器----------好處：覆用給成員變量賦初始值的代碼

   - 作用：給成員變量賦初始值

   - 語法：與類同名，沒有返回值類型(連void都沒有)

   - 調用：在創建(new)對象時被自動調用

   - 若自己不寫構造方法，則編譯器默認提供一個無參構造方法，若自己寫了構造方法，則不再默認提供

   - 構造方法可以重載

     ```java
     public class Student {
         //成員變量---描述對象的屬性
         String name;
         int age;
         String className;
         String stuId;
         //構造方法---給成員變量賦初始值
         Student(){
         }
         Student(String name,int age,String className,String stuId){
             this.name = name;
             this.age = age;
             this.className = className;
             this.stuId = stuId;
         }
     
         //方法------描述對象的行為
         void study(){
             System.out.println(this.name+"在學習...");
         }
         
         void sayHi(){
             System.out.println("大家好，我叫"+this.name+"，今年"+this.age+"歲了，所在班級為:"+this.className+"，學號為:"+this.stuId);
         }
         
         void playWith(String anotherName){
             System.out.println(this.name+"正在和"+anotherName+"一起玩...");
         }
     }
     public class ConsDemo {
         public static void main(String[] args) {
             Student zs = new Student("張三",24,"jsd2304","001");
             zs.sayHi();
     
             Student ls = new Student("李四",25,"jsd2304","002");
             ls.sayHi();
         }
     }
     ```

5. 綜合練習：

   ```java
   public class Car {
       String brand; //品牌
       String color; //顏色
       double price; //價格
   
       Car(){
       }
       Car(String brand,String color,double price){
           this.brand = brand;
           this.color = color;
           this.price = price;
       }
   
       void start(){
           System.out.println(brand+"牌子的"+color+"顏色的"+price+"塊錢的車啟動了...");
       }
       void run(){
           System.out.println(brand+"牌子的"+color+"顏色的"+price+"塊錢的車開始跑了...");
       }
       void stop(){
           System.out.println(brand+"牌子的"+color+"顏色的"+price+"塊錢的車停止了...");
       }
   }
   
   public class CarTest {
       public static void main(String[] args) {
           Car car1 = new Car();
           car1.brand = "奔弛";
           car1.color = "黑";
           car1.price = 80;
           car1.start();
           car1.run();
           car1.stop();
   
           Car car2 = new Car("奧迪","銀",40);
           car2.start();
           car2.run();
           car2.stop();
   
           Car car3 = new Car("特斯拉","白",70);
           car3.start();
           car3.run();
           car3.stop();
       }
   }
   ```

   



## 補充：

1. 面向過程和面向對象：

   - 面向過程：以方法為單位來解決問題，比較適合簡單的業務(大象裝冰箱、去銀行取錢)
   - 面向對象：以對象為單位來解決問題，比較適合覆雜的業務(造個汽車、造個航母)

2. 課程：面向對象5天是面向對象的入門，基本概念、語法、設計都講了----講造車零部件

3. OO：面向對象

   OOA：面向對象分析

   OOD：面向對象設計

   OOAD：面向對象分析與設計

   OOP：面向對象編程---------------------------你們所參與的部分

4. 高質量的代碼：-----------------------你們以後的目標

   - 覆用性好、擴展性好、維護性好、移植性好、
   - 可讀性好、健壯性好、效率好......

5. 類是一種引用數據類型

6. 創建對象：

   ```java
              引用
   數據類型 引用類型變量 指向    對象
   Student    zs       = new Student(); //聲明Student類型引用zs指向一個學生對象
   ```

7. 默認值規則：

   ```java
   byte,short,int,long,char---------------0
   float,double---------------------------0.0
   boolean--------------------------------false
   引用類型---------------------------------null
   ```

8. 內存管理：由JVM來管理的-------------了解即可

   - 堆：new出來的對象(包括成員變量、數組的元素、方法的地址)
   - 棧：局部變量(包括方法的參數)
   - 方法區：.class字節碼文件(包括所有方法、靜態變量)

9. 明日單詞：

   ```java
   1)Person:人
   2)eat:吃
   3)sleep:睡
   4)salary:工資
   5)title:職稱
   6)teach:講、教
   7)cut:剪、切
   8)Animal:動物
   9)drink:喝
   10)chick:雞
   11)dog:狗
   12)fish:魚
   13)layEgg:下蛋
   14)look:看
   15)home:家
   ```
