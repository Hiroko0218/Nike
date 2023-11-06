# 面向對象第四天：

## 回顧：

1. 抽象方法：
   - abstract修飾，只有方法的定義，沒有具體的實現(連{}都沒有)
2. 抽象類：
   - abstract修飾，包含抽象方法的類必須是抽象類，不能被實例化
   - 需要被繼承，派生類：重寫所有抽象方法
   - 意義：代碼覆用，可以包含抽象方法，為派生類提供統一的入口(名字一致)，強制必須重寫
3. 接口：
   - interface定義，抽象方法，不能被實例化，
   - 需要被實現，實現類：必須重寫所有抽象方法
   - 一個類可以實現多個接口，用逗號分隔。若又繼承又實現時，應先繼承後實現
   - 接口可以繼承接口
4. 引用類型數組：
   - 區別1：給引用類型數組的元素賦值時，需要new個對象
   - 區別2：若想訪問引用類型數組元素的屬性/方法，需要通過元素去打點來調用



## 精華筆記：

1. 多態：多種形態

   - 向上造型/自動類型轉換：

     - 超類型的引用指向派生類的對象
     - 能點出來什麽，看引用的類型--------------這是規定，記住它

   - 向下轉型/強制類型轉換，成功的條件只有如下兩種：

     - 引用所指向的對象，就是該類型
     - 引用所指向的對象，實現了該接口或繼承了該類

   - 強轉時若不符合如上條件，則發生ClassCastException類型轉換異常

     > 建議：在強轉之前先通過instanceof來判斷引用的對象是否是該類型
     >
     > 注意：instanceof返回boolean結果，它為true的條件就是強轉成功的條件
     >
     > 何時需要強轉：若想訪問的屬性/行為在超類中沒有，則需要強制類型轉換

2. 成員內部類：應用率低，了解

   - 類中套類，外面的稱為外部類，里面的稱為內部類

   - 內部類通常只服務於外部類，對外不具備可見性

   - 內部類對象通常在外部類中創建

   - 內部類可以直接訪問外部類的成員，在內部類中有個隱式的引用指向創建它的外部類對象

     > 隱式的引用：外部類名.this

   - 何時用：若A類(Baby)只讓B類(Mama)用，並且A類(Baby)還想訪問B類(Mama)的成員時，可以設計成員內部類

3. 匿名內部類：應用率高，掌握

   - 何時用：若想創建一個派生類的對象，並且對象只創建一次，可以設計為匿名內部類，可以大大簡化代碼
   - 注意：匿名內部類中不能修改外面局部變量的值
   - 小面試題：
     - 問：內部類有獨立的.class嗎?
     - 答：有

4. package和import：

   - package：聲明包
     - 作用：避免類的命名沖突
     - 規定：同包中的類不能同名，但不同包中的類可以同名。
     - 類的全稱：包名.類名。包名常常有層次結構
     - 建議：包名所有字母都小寫
   - import：導入類
     - 同包中的類可以直接訪問，但不同包中的類不能直接訪問，若想訪問：
       - 先import導入類，再訪問類--------------建議
       - 類的全稱---------------------------------------太繁瑣、不建議



## 筆記：

1. 多態：多種形態

   - 向上造型/自動類型轉換：

     - 超類型的引用指向派生類的對象
     - 能點出來什麽，看引用的類型--------------這是規定，記住它

   - 向下轉型/強制類型轉換，成功的條件只有如下兩種：

     - 引用所指向的對象，就是該類型
     - 引用所指向的對象，實現了該接口或繼承了該類

   - 強轉時若不符合如上條件，則發生ClassCastException類型轉換異常

     > 建議：在強轉之前先通過instanceof來判斷引用的對象是否是該類型
     >
     > 注意：instanceof返回boolean結果，它為true的條件就是強轉成功的條件
     >
     > 何時需要強轉：若想訪問的屬性/行為在超類中沒有，則需要強制類型轉換

     ```java
     public abstract class Animal {
         String name;
         int age;
         String color;
         Animal(String name,int age,String color){
             this.name = name;
             this.age = age;
             this.color = color;
         }
     
         void drink(){
             System.out.println(color+"色的"+age+"歲的"+name+"正在喝水...");
         }
         abstract void eat();
     }
     public interface Swim {
         /** 遊泳 */
         void swim();
     }
     public class Dog extends Animal implements Swim {
         Dog(String name,int age,String color){
             super(name,age,color);
         }
     
         void lookHome(){
             System.out.println(color+"色的"+age+"歲的狗狗"+name+"正在看家...");
         }
         void eat(){
             System.out.println(color+"色的"+age+"歲的狗狗"+name+"正在吃肯頭...");
         }
         public void swim(){
             System.out.println(color+"色的"+age+"歲的狗狗"+name+"正在遊泳...");
         }
     }
     public class Fish extends Animal implements Swim {
         Fish(String name,int age,String color){
             super(name,age,color);
         }
     
         void eat(){
             System.out.println(color+"色的"+age+"歲的小魚"+name+"正在吃小蝦...");
         }
         public void swim(){
             System.out.println(color+"色的"+age+"歲的小魚"+name+"正在遊泳...");
         }
     }
     public class Chick extends Animal {
         Chick(String name,int age,String color){
             super(name,age,color);
         }
         void layEggs(){
             System.out.println(color+"色的"+age+"歲的小雞"+name+"正在下蛋...");
         }
         void eat(){
             System.out.println(color+"色的"+age+"歲的小雞"+name+"正在吃小米...");
         }
     }
     public class Master {
         void feed(Animal animal){ //喂動物
             animal.eat();
         }
     }
     
     package ooday04;
     /**
      * 演示多態
      */
     public class Test {
         public static void main(String[] args) {
             /*
             //1.引用所指向的對象，就是該類型
             //2.引用所指向的對象，實現了該接口或繼承了該類型
             Animal o = new Dog("小黑",2,"黑"); //向上造型
             Dog g = (Dog)o;   //引用o所指向的對象，就是Dog類型
             Swim s = (Swim)o; //引用o所指向的對象，實現了Swim接口
             //Fish f = (Fish)o; //運行時會發生ClassCastException類型轉換異常
     
             System.out.println(o instanceof Dog);  //true
             System.out.println(o instanceof Swim); //true
             System.out.println(o instanceof Fish); //false
             */
             
             /*
             Animal o1 = new Dog("小黑",2,"黑");
             //o1能強轉為:Dog,Swim,Animal
     
             Animal o2 = new Fish("小黑",2,"黑");
             //o2能強轉為:Fish,Swim,Animal
     
             Animal o3 = new Chick("小黑",2,"黑");
             //o3能強轉為:Chick,Animal
             */
     
             /*
             //演示向上造型(多態)的第2點應用:
             Master master = new Master();
             Dog dog = new Dog("小黑",2,"黑");
             Chick chick = new Chick("小花",3,"花");
             Fish fish = new Fish("小金",1,"金");
             master.feed(dog); //在傳參的同時，系統自動做了向上造型
             master.feed(chick);
             master.feed(fish);
             */
     
             //演示向上造型(多態)的第1點應用:
             //Animal o = new Animal(); //編譯錯誤，抽象類不能被實例化
             Animal[] animals = new Animal[5];
             animals[0] = new Dog("小黑",2,"黑"); //向上造型
             animals[1] = new Dog("小白",1,"白");
             animals[2] = new Fish("小金",1,"金");
             animals[3] = new Fish("小花",2,"花");
             animals[4] = new Chick("小灰",3,"灰");
             for(int i=0;i<animals.length;i++){ //遍歷所有動物
                 System.out.println(animals[i].name); //輸出每個動物的名字
                 animals[i].eat();   //每個動物吃飯
                 animals[i].drink(); //每個動物喝水
     
                 if(animals[i] instanceof Dog){
                     Dog dog = (Dog)animals[i];
                     dog.lookHome();
                 }
                 if(animals[i] instanceof Chick){
                     Chick chick = (Chick)animals[i];
                     chick.layEggs();
                 }
                 if(animals[i] instanceof Swim){ //適用於所有實現Swim接口的(會遊泳的)
                     Swim s = (Swim)animals[i];
                     s.swim();
                 }
             }
         }
     }
     ```

2. 成員內部類：應用率低，了解

   - 類中套類，外面的稱為外部類，里面的稱為內部類

   - 內部類通常只服務於外部類，對外不具備可見性

   - 內部類對象通常在外部類中創建

   - 內部類可以直接訪問外部類的成員，在內部類中有個隱式的引用指向創建它的外部類對象

     > 隱式的引用：外部類名.this

   - 何時用：若A類(Baby)只讓B類(Mama)用，並且A類(Baby)還想訪問B類(Mama)的成員時，可以設計成員內部類

     ```java
     package ooday04;
     //成員內部類
     public class InnerClassDemo {
         public static void main(String[] args) {
             Mama m = new Mama();
             //Baby b = new Baby(); //編譯錯誤，內部類對外不具備可見性
         }
     }
     
     class Mama{ //外部類
         String name;
         void create(){
             Baby b = new Baby(); //正確，內部類對象通常在外部類中創建
         }
         class Baby{ //內部類
             void show(){
                 System.out.println(name); //簡寫
                 System.out.println(Mama.this.name); //完整寫法,Mama.this指外部類對象
                 //System.out.println(this.name); //編譯錯誤，this指當前Baby對象
             }
         }
     }
     ```

3. 匿名內部類：應用率高，掌握

   - 何時用：若想創建一個派生類的對象，並且對象只創建一次，可以設計為匿名內部類，可以大大簡化代碼

   - 注意：匿名內部類中不能修改外面局部變量的值

   - 小面試題：

     - 問：內部類有獨立的.class嗎?
     - 答：有

     ```java
     package ooday04;
     public class AnonInnerClassDemo {
         public static void main(String[] args) {
             //1)創建了Inter的一個派生類，但是沒有名字
             //2)為該派生類創建了一個對象，名為o1，向上造型為Inter類型
             //  ----new Inter(){};是在創建Inter的派生類的對象
             //3)大括號中的為派生類的類體
             Inter o1 = new Inter(){};
     
             //1)創建了Inter的一個派生類，但是沒有名字
             //2)為該派生類創建了一個對象，名為o2，向上造型為Inter類型
             //3)大括號中的為派生類的類體
             Inter o2 = new Inter(){};
     
             int num = 5;
             num = 6;
             //1)創建了InterInter的一個派生類，但是沒有名字
             //2)為該派生類創建了一個對象，名為o3，向上造型為InterInter類型
             //3)大括號中的為派生類的類體
             InterInter o3 = new InterInter(){
                 public void show(){
                     System.out.println("showshow");
                     //num = 55; //編譯錯誤，匿名內部類中不能修改外面局部變量的值
                                 //因為該變量在此處會默認為final的
                 }
             };
             o3.show();
         }
     }
     
     interface InterInter{
         void show();
     }
     
     interface Inter{
     }
     ```

4. package和import：

   - package：聲明包

     - 作用：避免類的命名沖突
     - 規定：同包中的類不能同名，但不同包中的類可以同名。
     - 類的全稱：包名.類名。包名常常有層次結構
     - 建議：包名所有字母都小寫

   - import：導入類

     - 同包中的類可以直接訪問，但不同包中的類不能直接訪問，若想訪問：

       - 先import導入類，再訪問類--------------建議
       - 類的全稱---------------------------------------太繁瑣、不建議

       ```java
       package day04;
       import java.util.Scanner; //1
       //Scanner的演示
       public class ScannerDemo {
           public static void main(String[] args) {
               /*
                 package java.util;
                 class Scanner{
                   Scanner(InputStream s){ ... }
                   int nextInt(){ ... }
                   double nextDouble(){ ... }
                 }
                */
               Scanner scan = new Scanner(System.in); //2
               int age = scan.nextInt(); //3
               double price = scan.nextDouble(); //3
           }
       }
       ```

       

## 補充：

1. 多態的實際應用：

   - 將不同對象(狗、魚、雞)統一封裝到超類數組(動物數組)中來訪問，實現代碼覆用
   - 將超類型(Animal)作為參數或返回值類型，傳遞或返回派生類(Dog/Fish/Chick)對象，以擴大方法的應用範圍(所有Animal)，實現代碼覆用

2. 隱式的引用：

   - this：指代當前對象
   - super：指代當前對象的超類對象
   - 外部類名.this：指代當前對象的外部類對象

3. 關鍵字順序：先package，後import，最後class

4. 明日單詞：

   ```java
   1)public:公開的
   2)private:私有的
   3)protected:受保護的
   4)final:最終的
   5)static:靜態的
   6)enum:枚舉
   7)season:季節
   8)PI:圓周率
   9)spring:春
   10)summer:夏
   11)autumn:秋
   12)winter:冬
   13)value:值
   ```
