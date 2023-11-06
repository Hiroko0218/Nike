# 面向對象第三天：

## 回顧：

1. 繼承：

   - 代碼覆用、extends、超類:共有的  派生類:特有的

   - 派生類可以訪問派生類的和超類的，但超類不能訪問派生類的

   - 單一繼承，具有傳遞性

   - java規定：構造派生類之前必須先構造超類

     - 若派生類的構造方法中沒有調用超類構造方法，則默認super()調超類無參構造方法

     - 若派生類的構造方法中調用了超類構造方法，則不再默認提供

       > super()調用超類構造方法，必須位於派生類構造方法的第1行

2. super：指代當前對象的超類對象

   - super.成員變量名-----------------------訪問超類的成員變量
   - super.方法名()----------------------------調用超類的方法
   - super()--------------------------------------調用超類的構造方法

3. 方法的重寫(override/overriding)：

   - 發生在父子類中，方法名相同，參數列表相同
   - 重寫方法被調用時，看對象的類型





## 精華筆記：

1. 抽象方法：
   - 由abstract修飾
   - 只有方法的定義，沒有具體的實現(連{}都沒有)
2. 抽象類：
   - 由abstract修飾
   - 包含抽象方法的類必須是抽象類，但不包含抽象方法的類也可以聲明為抽象類
   - 抽象類不能被實例化(new對象)
   - 抽象類是需要被繼承的，派生類：
     - 必須重寫所有抽象方法------------------變不完整為完整
     - 也聲明為抽象類----------------------------一般不這麽用
   - 抽象類的意義：
     - 封裝共有的屬性和行為------------------代碼覆用
     - 可以包含抽象方法，為所有派生類統一入口(名字統一)，強制必須重寫
3. 接口：
   - 是一種引用數據類型
   - 由interface定義
   - 只能包含抽象方法(常量、默認方法、靜態方法、私有方法------暫時擱置)
   - 不能被實例化
   - 接口是需要被實現/繼承的，實現類/派生類：必須重寫接口中的所有抽象方法
     - 注意：重寫接口中的方法時，必須加public(先記住)
   - 一個類可以實現多個接口，用逗號分隔。若又繼承又實現時，應先繼承後實現
   - 接口可以繼承接口
4. 引用類型數組：------記住它和基本類型數組的兩種區別即可
   - 區別1：給引用類型數組的元素賦值時，需要new個對象
   - 區別2：訪問引用類型數組的元素的屬性/行為時，需要打點訪問

5. 綜合練習：達內員工管理系統



## 筆記：

1. 抽象方法：

   - 由abstract修飾
   - 只有方法的定義，沒有具體的實現(連{}都沒有)

2. 抽象類：

   - 由abstract修飾

   - 包含抽象方法的類必須是抽象類，但不包含抽象方法的類也可以聲明為抽象類

   - 抽象類不能被實例化(new對象)

   - 抽象類是需要被繼承的，派生類：

     - 必須重寫所有抽象方法------------------變不完整為完整
     - 也聲明為抽象類----------------------------一般不這麽用

   - 抽象類的意義：

     - 封裝共有的屬性和行為------------------代碼覆用

     - 可以包含抽象方法，為所有派生類統一入口(名字統一)，強制必須重寫

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
       
       public class Dog extends Animal{
           Dog(String name,int age,String color){
               super(name,age,color);
           }
       
           void lookHome(){
               System.out.println(color+"色的"+age+"歲的狗狗"+name+"正在看家...");
           }
           void eat(){
               System.out.println(color+"色的"+age+"歲的狗狗"+name+"正在吃肯頭...");
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
       
       public class Fish extends Animal {
           Fish(String name,int age,String color){
               super(name,age,color);
           }
       
           void eat(){
               System.out.println(color+"色的"+age+"歲的小魚"+name+"正在吃小蝦...");
           }
       }
       ```

3. 接口：

   - 是一種引用數據類型

   - 由interface定義

   - 只能包含抽象方法(常量、默認方法、靜態方法、私有方法------暫時擱置)

     ```java
     interface Inter {
         abstract void show();
         void test(); //接口中的方法默認都是抽象的，前面默認有abstract
         //void say(){} //編譯錯誤，抽象方法不能有方法體
     }
     ```

   - 不能被實例化

     ```java
     public class InterfaceDemo {
         public static void main(String[] args) {
             //Inter o = new Inter(); //編譯錯誤，接口不能被實例化
         }
     }
     ```

   - 接口是需要被實現/繼承的，實現類/派生類：必須重寫接口中的所有抽象方法

     - 注意：重寫接口中的方法時，必須加public(先記住)

       ```java
       interface Inter {
           abstract void show();
           void test(); //接口中的方法默認都是抽象的，前面默認有abstract
           //void say(){} //編譯錯誤，抽象方法不能有方法體
       }
       class InterImpl implements Inter {
           public void show(){ //重寫接口中的抽象方法時，必須加public
           }
           public void test(){
           }
       }
       ```

   - 一個類可以實現多個接口，用逗號分隔。若又繼承又實現時，應先繼承後實現

     ```java
     //演示接口的多實現
     interface Inter1{
         void show();
     }
     interface Inter2{
         void test();
     }
     abstract class Aoo{
         abstract void say();
     }
     class Boo extends Aoo implements Inter1,Inter2{
         public void show(){}
         public void test(){}
         void say(){}
     }
     ```

   - 接口可以繼承接口

     ```java
     //演示接口繼承接口
     interface Inter3{
         void show();
     }
     interface Inter4 extends Inter3{
         void test();
     }
     class Coo implements Inter4{
         public void test(){}
         public void show(){}
     }
     ```

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
         void swim(); //遊泳
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
     public class SwimTest {
         public static void main(String[] args) {
             Dog dog = new Dog("小黑", 2, "黑");
             dog.eat();      //Dog類重寫之後的
             dog.drink();    //覆用Animal類的
             dog.swim();     //Dog類重寫之後的
             dog.lookHome(); //Dog類所特有的
     
             Chick chick = new Chick("小白", 1, "白");
             chick.eat();     //Chick類重寫之後的
             chick.drink();   //覆用Animal類的
             chick.layEggs(); //Chick類所特有的
     
             Fish fish = new Fish("小金", 1, "金");
             fish.eat();   //Fish類重寫之後的
             fish.drink(); //覆用Animal類的
             fish.swim();  //Fish類重寫之後的
         }
     }
     ```

4. 引用類型數組：------記住它和基本類型數組的兩種區別即可

   - 區別1：給引用類型數組的元素賦值時，需要new個對象

   - 區別2：訪問引用類型數組的元素的屬性/行為時，需要打點訪問

     ```java
     public class RefArrayDemo {
         public static void main(String[] args) {
             Dog[] dogs = new Dog[3];
             dogs[0] = new Dog("小黑",2,"黑");
             dogs[1] = new Dog("小白",1,"白");
             dogs[2] = new Dog("小灰",3,"灰");
             System.out.println(dogs[0].name); //輸出第1只狗狗的名字
             dogs[1].age = 4; //修改第2只狗狗的年齡為4歲
             dogs[2].swim(); //第3只狗狗在遊泳
             for(int i=0;i<dogs.length;i++){ //遍歷dogs數組
                 System.out.println(dogs[i].name); //輸出每只狗狗的名字
                 dogs[i].eat(); //每只狗狗吃飯
             }
     
             Chick[] chicks = new Chick[2];
             chicks[0] = new Chick("小花",1,"花");
             chicks[1] = new Chick("大花",2,"白");
             for(int i=0;i<chicks.length;i++){ //遍歷chicks數組
                 System.out.println(chicks[i].name);
                 chicks[i].layEggs();
             }
     
             Fish[] fish = new Fish[4];
             fish[0] = new Fish("小金",2,"金");
             fish[1] = new Fish("大金",4,"白");
             fish[2] = new Fish("小綠",1,"綠");
             fish[3] = new Fish("小紅",3,"紅");
             for(int i=0;i<fish.length;i++){ //遍歷fish數組
                 System.out.println(fish[i].color);
                 fish[i].drink();
             }
     
             /*
             //聲明Dog型數組dogs，包含3個元素，每個元素都是Dog類型，默認值為null
             Dog[] dogs = new Dog[3];
             //聲明Chick型數組chicks，包含3個元素，每個元素都是Chick類型，默認值為null
             Chick[] chicks = new Chick[3];
             //聲明Fish型數組fish，包含2個元素，每個元素都是Fish類型，默認值為null
             Fish[] fish = new Fish[2];
              */
         }
     }
     ```

5. 綜合練習：達內員工管理系統

   ```java
   需求:
   1)教研總監:名字、年齡、工資、上班打卡()、下班打卡()、完成工作()、
             解決企業問題()、培訓員工()、編輯書籍()
   2)講師:名字、年齡、工資、上班打卡()、下班打卡()、完成工作()、
          解決企業問題()、培訓員工()、編輯書籍()
   3)項目經理:名字、年齡、工資、上班打卡()、下班打卡()、完成工作()、
             編輯書籍()
   4)班主任:名字、年齡、工資、上班打卡()、下班打卡()、完成工作()
       
   設計:
   1)雇員超類:名字、年齡、工資、上班打卡(){}、下班打卡(){}、abstract 完成工作()
   2)企業顧問接口: 解決企業問題()、培訓員工()
   3)技術作者接口: 編輯書籍()
   4)教研總監類，繼承雇員超類，實現企業顧問接口和技術作者接口: 重寫4個抽象方法
   5)講師類，繼承雇員超類，實現企業顧問接口和技術作者接口: 重寫4個抽象方法
   6)項目經理類，繼承雇員超類，實現技術作者接口: 重寫2個抽象方法
   7)班主任類，繼承雇員超類: 重寫1個抽象方法
   ```

   

# 補充：

1. 設計規則：-----------適合初學者

   - 將所有派生類共有的屬性和行為，抽到超類中-------------抽共性

   - 若派生類的行為/代碼都一樣，設計普通方法

     若派生類的行為/代碼不一樣，設計抽象方法

   - 將部分派生類共有的行為，抽到接口中

     - 接口對繼承單根性的擴展------------------------實現多繼承
     - 接口是一種標準、規範，若實現了某接口就具備某個的功能，若不實現接口就不具備那個功能----------後期才能理解得更好

2. 不包含抽象方法的類也可以聲明為抽象類，同樣不能被實例化

3. 類間關系：

   - 類和類-------------------------------繼承
   - 接口和接口-------------------------繼承
   - 類和接口----------------------------實現

4. null：表示空，沒有指向任何對象。

   - 若引用的值為null，則該引用不能再進行任何操作了，若操作則發生NullPointerException空指針異常。

5. 明日單詞：

   ```java
   1)polymorphic:多態
   2)cast:轉換
   3)Master:主人
   4)feed:喂
   5)instanceof:實例
   6)Inner:內部
   7)Outer:外部
   8)anonymous:匿名
   ```

   