# 面向對象第二天：

## 回顧：

1. 什麽是類？什麽是對象？

2. 如何創建類？如何創建對象？如何訪問成員？

3. this：指代當前對象，哪個對象調用方法它指的就是哪個對象

   - this.成員變量名---------------訪問成員變量

     > 當成員變量與局部變量同名時，若想訪問成員變量，則this不能省略

4. 構造方法：

   - 給成員變量賦初值、與類同名、沒有返回值類型(連void都沒有)，創建(new)對象時被自動調用。若自己不寫構造方法，則默認一個無參構造，若自己寫了構造方法，則不再默認提供。



## 精華筆記：

1. 繼承：

   - 作用：代碼覆用

   - 通過extends實現繼承

   - 超類/基類/父類：共有的屬性和行為

     派生類/子類：特有的屬性和行為

   - 派生類可以訪問：超類的+派生類的，超類不能訪問派生類的

   - 一個超類可以有多個派生類，一個派生類只能有一個超類---------單一繼承

   - 具有傳遞性

   - java規定：構造派生類之前必須先構造超類

     - 在派生類的構造方法中若沒有調用超類的構造方法，則默認super()調用超類的無參構造方法

     - 在派生類的構造方法中若自己調用了超類的構造方法，則不再默認提供

       > 注意：super()調用超類構造方法，必須位於派生類構造方法中的第1行

2. super：指代當前對象的超類對象

   - super.成員變量名-------------------------訪問超類的成員變量

     > 當超類成員變量和派生類成員變量同名時，super指超類的，this指派生類的
     >
     > 若沒有同名現象，則寫super和this是一樣的

   - super.方法名()------------------------------調用超類的方法

   - super()----------------------------------------調用超類的構造方法

3. 方法的重寫(override/overriding)：重新寫、覆蓋

   - 發生在父子類中，方法名相同，參數列表相同

   - 重寫方法被調用時，看對象的類型---------------new誰就調誰的，這是規定

     ```java
     class 餐館{
         void 做餐(){ 做中餐 }
     }
     //1)我還是想做中餐----------------------不需要重寫
     class Aoo extends 餐館{
     }
     //2)我想改做西餐------------------------需要重寫
     class Boo extends 餐館{
         void 做餐(){ 做西餐 }
     }
     //3)我想在中餐基礎之上加入西餐------------需要重寫(先super中餐，再加入西餐)
     class Coo extends 餐館{
         void 做餐(){
             super.做餐();
             做西餐
         }
     }
     ```

4. final：最終的、不能改變的------------單獨應用幾率低

   - 修飾變量：變量不能被改變

   - 修飾方法：方法不能被重寫

   - 修飾類：類不能被繼承




## 筆記：

1. 繼承：

   - 作用：代碼覆用

   - 通過extends實現繼承

   - 超類/基類/父類：共有的屬性和行為

     派生類/子類：特有的屬性和行為

   - 派生類可以訪問：超類的+派生類的，超類不能訪問派生類的

   - 一個超類可以有多個派生類，一個派生類只能有一個超類---------單一繼承

   - 具有傳遞性

   - java規定：構造派生類之前必須先構造超類

     - 在派生類的構造方法中若沒有調用超類的構造方法，則默認super()調用超類的無參構造方法

     - 在派生類的構造方法中若自己調用了超類的構造方法，則不再默認提供

       > 注意：super()調用超類構造方法，必須位於派生類構造方法中的第1行

       ```java
       public class Person {
           String name;
           int age;
           String address;
           Person(String name,int age,String address){
               this.name = name;
               this.age = age;
               this.address = address;
           }
       
           void eat(){
               System.out.println(name+"正在吃飯...");
           }
           void sleep(){
               System.out.println(name+"正在睡覺...");
           }
           void sayHi(){
               System.out.println("大家好，我叫"+name+"，今年"+age+"歲了，家住"+address);
           }
       }
       
       public class Student extends Person{
           String className;
           String stuId;
           Student(){
           }
           Student(String name,int age,String address,String className,String stuId){
               super(name,age,address); //傳遞的是name/age/address的值
               this.className = className;
               this.stuId = stuId;
           }
       
           void study(){
               System.out.println(name+"正在學習...");
           }
       }
       
       public class Teacher extends Person{
           double salary;
           Teacher(){
           }
           Teacher(String name,int age,String address,double salary){
               super(name,age,address);
               this.salary = salary;
           }
       
           void teach(){
               System.out.println(name+"正在講課...");
           }
       }
       
       public class Doctor extends Person {
           String title;
           Doctor(){
           }
           Doctor(String name,int age,String address,String title){
               super(name,age,address);
               this.title = title;
           }
           void cut(){
               System.out.println(name+"正在做手術...");
           }
       }
       
       public class ExtendsTest {
           public static void main(String[] args) {
               Student zs = new Student("張三",25,"廊坊","jsd2302","001");
               zs.eat();
               zs.sleep();
               zs.sayHi();
               zs.study();
       
               Teacher ls = new Teacher("李四",35,"佳木斯",6000.0);
               ls.eat();
               ls.sleep();
               ls.sayHi();
               ls.teach();
       
               Doctor ww = new Doctor("王五",46,"山東","主任醫師");
               ww.eat();
               ww.sleep();
               ww.sayHi();
               ww.cut();
           }
       }
       ```

2. super：指代當前對象的超類對象

   - super.成員變量名-------------------------訪問超類的成員變量

     > 當超類成員變量和派生類成員變量同名時，super指超類的，this指派生類的
     >
     > 若沒有同名現象，則寫super和this是一樣的

   - super.方法名()------------------------------調用超類的方法

   - super()----------------------------------------調用超類的構造方法

     ```java
     public class SuperDemo {
         public static void main(String[] args) {
             Boo o = new Boo();
         }
     }
     
     //1)在派生類的構造方法中若沒有調用超類的構造方法，則默認super()調用超類的無參構造方法
     class Aoo{
         Aoo(){
             System.out.println("父類構造方法");
         }
     }
     class Boo extends Aoo{
         Boo(){
             super(); //默認的，調用父類的無參構造方法
             System.out.println("子類構造方法");
         }
     }
     
     //2)在派生類的構造方法中若自己調用了超類的構造方法，則不再默認提供
     class Coo{
         Coo(int a){
         }
     }
     class Doo extends Coo{
         Doo(){
             super(5); //調用超類的有參構造
         }
         /*
         //如下代碼為默認的:
         Doo(){
             super();
         }
          */
     }
     ```

3. 方法的重寫(override/overriding)：重新寫、覆蓋

   - 發生在父子類中，方法名相同，參數列表相同

   - 重寫方法被調用時，看對象的類型---------------new誰就調誰的，這是規定

     ```java
     class 餐館{
         void 做餐(){ 做中餐 }
     }
     //1)我還是想做中餐----------------------不需要重寫
     class Aoo extends 餐館{
     }
     //2)我想改做西餐------------------------需要重寫
     class Boo extends 餐館{
         void 做餐(){ 做西餐 }
     }
     //3)我想在中餐基礎之上加入西餐------------需要重寫(先super中餐，再加入西餐)
     class Coo extends 餐館{
         void 做餐(){
             super.做餐();
             做西餐
         }
     }
     ```

     ```java
     public class Person {
         String name;
         int age;
         String address;
         Person(String name,int age,String address){
             this.name = name;
             this.age = age;
             this.address = address;
         }
     
         void eat(){
             System.out.println(name+"正在吃飯...");
         }
         void sleep(){
             System.out.println(name+"正在睡覺...");
         }
         void sayHi(){
             System.out.println("大家好，我叫"+name+"，今年"+age+"歲了，家住"+address);
         }
     }
     
     public class Student extends Person{
         String className;
         String stuId;
         Student(String name,int age,String address,String className,String stuId){
             super(name,age,address); //傳遞的是name/age/address的值
             this.className = className;
             this.stuId = stuId;
         }
     
         void study(){
             System.out.println(name+"正在學習...");
         }
         
         void sayHi(){
             System.out.println("大家好，我叫"+name+"，今年"+age+"歲了，家住"+address+"，所在班級為:"+className+"，學號為："+stuId);
         }
     }
     
     public class Teacher extends Person{
         double salary;
         Teacher(String name,int age,String address,double salary){
             super(name,age,address);
             this.salary = salary;
         }
     
         void teach(){
             System.out.println(name+"正在講課...");
         }
         
         void sayHi(){
             System.out.println("大家好，我叫"+name+"，今年"+age+"歲了，家住"+address+"，工資為:"+salary);
         }
     }
     
     public class Doctor extends Person {
         String title;
         Doctor(String name,int age,String address,String title){
             super(name,age,address);
             this.title = title;
         }
         void cut(){
             System.out.println(name+"正在做手術...");
         }
     }
     
     public class ExtendsTest {
         public static void main(String[] args) {
             /*
             //演示繼承:
             Student zs = new Student();
             zs.name = "張三";
             zs.age = 25;
             zs.address = "廊坊";
             zs.className = "jsd2304";
             zs.stuId = "001";
             zs.eat();
             zs.sleep();
             zs.sayHi();
             zs.study();
     
             Teacher ls = new Teacher("李四",35,"佳木斯",6000.0);
             ls.eat();
             ls.sleep();
             ls.sayHi();
             ls.teach();
     
             Doctor ww = new Doctor("王五",46,"山東","主任醫師");
             ww.eat();
             ww.sleep();
             ww.sayHi();
             ww.cut();
              */
     
             //重寫結果顯示:
             Student zs = new Student("張三",25,"廊坊","jsd2304","001");
             zs.sayHi(); //調用Student重寫之後的
             Teacher ls = new Teacher("李四",35,"佳木斯",6000.0);
             ls.sayHi(); //調用Teacher重寫之後的
             Doctor ww = new Doctor("王五",45,"山東","主任醫師");
             ww.sayHi(); //調用Person中的
         }
     }
     ```

4. final：最終的、不能改變的------------單獨應用幾率低

   - 修飾變量：變量不能被改變

     ```java
     class Eoo{
         final int a = 5;
         int b = 6;
         void test(){
             //a = 55; //編譯錯誤，final修飾的變量不能被改變
             b = 66;
         }
     }
     ```

   - 修飾方法：方法不能被重寫

     ```java
     class Foo{
         final void show(){}
         void test(){}
     }
     class Goo extends Foo{
         //void show(){} //編譯錯誤，final修飾的方法不能被重寫
         void test(){}
     }
     ```

   - 修飾類：類不能被繼承

     ```java
     final class Hoo{}
     //class Ioo extends Hoo{} //編譯錯誤，final修飾的類不能被繼承
     class Joo{}
     final class Koo extends Joo{} //正確，不能當老爸，但能當兒子
     ```

5. 綜合練習：

   ```java
   public class Animal {
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
       void eat(){ //---------------明天繼續改造
           System.out.println(color+"色的"+age+"歲的"+name+"正在吃飯...");
       }
   }
   
   public class Dog extends Animal {
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
   
   public class Test {
       public static void main(String[] args) {
           Dog dog1 = new Dog("小黑",2,"黑");
           dog1.eat();
           dog1.drink();
           dog1.lookHome();
   
           Dog dog2 = new Dog("小白",1,"白");
           dog2.eat();
           dog2.drink();
           dog2.lookHome();
   
           Dog dog3 = new Dog("小強",2,"黑白");
           dog3.eat();
           dog3.drink();
           dog3.lookHome();
   
           Chick chick = new Chick("小花",1,"棕");
           chick.eat();
           chick.drink();
           chick.layEggs();
   
           Fish fish = new Fish("金金",2,"金");
           fish.eat();
           fish.drink();
       }
   }
   ```

   

## 補充：

1. 泛化：從程序設計角度而言叫泛化，從代碼實現角度而言叫繼承，泛化就是繼承

2. 繼承要符合is(是)的關系

3. 繼承的是父類的成員變量和普通方法，不包括構造方法，父類的構造方法是被子類通過super()來調用的

   ```java
   class Aoo{
       int a;
       Aoo(){
       }
       void show(){
       }
   }
   class Boo extends Aoo{
       繼承了Aoo類的a+show()，並沒有繼承Aoo類的構造方法
   }
   ```

4. 明日單詞：

   ```java
   1)swim:遊泳
   2)abstract:抽象的
   3)interface:接口
   4)implements:實現
   ```