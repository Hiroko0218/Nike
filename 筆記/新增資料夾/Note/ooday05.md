# 面向對象第五天：

## 回顧：

1. 多態：

   - 向上造型：

     - 超類型的引用指向派生類的對象
     - 能點出來什麽，看引用的類型
     - 能造型成為的數據類型有：超類+所實現的接口

   - 向下轉型/強制類型轉換，成功的條件只有如下兩種：

     - 引用所指向的對象，就是該類型
     - 引用所指向的對象，繼承了該類或實現了該接口

   - 強轉時若不符合如上條件，則發生ClassCastException類型轉換異常

     建議：強轉之前先通過instanceof來判斷引用的對象是否是該類型

     > 若想訪問派生類所特有的屬性/行為時，需要強制類型轉換/向下轉型

2. 成員內部類：應用率低

   - 類中套類，內部類對外不具備可見性，內部類對象一般在外部類中創建
   - 內部類中可以直接訪問外部類的成員----外部類名.this指代創建它的外部類對象
   - 何時用：類A只讓類B用，在類A中還需要訪問類B的東西，可以將類A設計為成員內部類

3. 匿名內部類：應用率高------------------大大簡化代碼

   - 若想創建一個派生類的對象，並且對象只被創建一次，可以設計為匿名內部類

4. package和import：



## 精華筆記：

1. 訪問控制修飾符：--------------保護數據的安全(隱藏數據、暴露行為)，實現封裝

   - public：公開的，任何類

   - private：私有的，本類

   - protected：受保護的，本類、派生類、同包類

   - 默認的：什麽也不寫，本類、同包類--------------------------java不建議

     > 注意：
     >
     > 1. 訪問權限由低到高依次為：private<默認的<protected<public
     > 2. 類的訪問權限只能是public或默認的，類中成員的訪問權限如上4種都可以。

2. static：靜態的

   - 靜態變量：
     - 由static修飾
     - 屬於類，存儲在方法區中，只有一份
     - 常常通過類名點來訪問
     - 何時用：對象所共享的數據
   - 靜態塊：
     - 由static修飾
     - 屬於類，在類被加載期間自動執行，一個類只被加載一次，所以靜態塊也只執行一次
     - 何時用：初始化/加載靜態資源/靜態變量
   - 靜態方法：
     - 由static修飾
     - 屬於類，存儲在方法區中，只有一份
     - 常常通過類名點來訪問
     - 靜態方法中沒有隱式this傳遞，所以靜態方法中不能直接訪問實例成員(實例變量/實例方法)
     - 何時用：方法的操作與對象無關(不需要訪問對象的屬性/行為)

3. static final常量：應用率高

   - 必須聲明同時初始化
   - 常常通過類名點來訪問，不能被改變
   - 建議：常量名所有字母都大寫，多個單詞之間用_分隔
   - 編譯器在編譯時，會將常量直接替換為具體的數，效率高
   - 何時用：在程序運行過程中數據永遠不變，並且經常使用

4. 枚舉：

   - 是一種引用數據類型

   - 特點：枚舉類型的對象數目是固定的，常常用於定義一組常量

     > 例如：季節、星期、月份、訂單狀態、性別......

   - 所有枚舉都繼承自Enum類，其中提供了一組方法供我們使用

   - 枚舉的構造方法都是私有的

     

## 筆記：

1. 訪問控制修飾符：--------------保護數據的安全(隱藏數據、暴露行為)，實現封裝

   - public：公開的，任何類

   - private：私有的，本類

   - protected：受保護的，本類、派生類、同包類

   - 默認的：什麽也不寫，本類、同包類--------------------------java不建議

     > 注意：
     >
     > 1. 訪問權限由低到高依次為：private<默認的<protected<public
     > 2. 類的訪問權限只能是public或默認的，類中成員的訪問權限如上4種都可以。

     ```java
     package ooday05;
     public class Aoo {
         public int a;    //任何類
         protected int b; //本類、派生類、同包類
         int c;           //本類、同包類
         private int d;   //本類
     
         void show(){
             a = 1;
             b = 2;
             c = 3;
             d = 4;
         }
     }
     
     class Boo{ //-------------------演示private
         void show(){
             Aoo o = new Aoo();
             o.a = 1;
             o.b = 2;
             o.c = 3;
             //o.d = 4; //編譯錯誤
         }
     }
     
     package ooday05_vis;
     import ooday05.Aoo;
     public class Coo { //-------------------演示同包的
         void show(){
             Aoo o = new Aoo();
             o.a = 1;
             //o.b = 2; //編譯錯誤
             //o.c = 3; //編譯錯誤
             //o.d = 4; //編譯錯誤
         }
     }
     
     class Doo extends Aoo{ //跨包繼承-------------演示protected
         void show(){
             a = 1;
             b = 2; //編譯錯誤
             //c = 3; //編譯錯誤
             //d = 4; //編譯錯誤
         }
     }
     ```

2. static：靜態的

   - 靜態變量：

     - 由static修飾

     - 屬於類，存儲在方法區中，只有一份

     - 常常通過類名點來訪問

     - 何時用：對象所共享的數據

       ```java
       public class StaticVar {
           int a; //實例變量
           static int b; //靜態變量
           StaticVar(){
               a++;
               b++;
           }
           void show(){
               System.out.println("a="+a+"，b="+b);
           }
       }
       
       public class StaticDemo {
           public static void main(String[] args) {
               StaticVar o1 = new StaticVar();
               o1.show();
               StaticVar o2 = new StaticVar();
               o2.show();
               StaticVar o3 = new StaticVar();
               o3.show();
               System.out.println(StaticVar.b); //常常通過類名點來訪問
           }
       }
       ```

   - 靜態塊：

     - 由static修飾

     - 屬於類，在類被加載期間自動執行，一個類只被加載一次，所以靜態塊也只執行一次

     - 何時用：初始化/加載靜態資源/靜態變量

       ```java
       public class StaticBlock {
           static{
               System.out.println("靜態塊");
           }
           StaticBlock(){
               System.out.println("構造方法");
           }
       }
       
       public class StaticDemo {
           public static void main(String[] args) {
               StaticBlock o4 = new StaticBlock(); //加載類時自動執行靜態塊
               StaticBlock o5 = new StaticBlock();
               StaticBlock o6 = new StaticBlock();
           }
       }
       ```

   - 靜態方法：

     - 由static修飾

     - 屬於類，存儲在方法區中，只有一份

     - 常常通過類名點來訪問

     - 靜態方法中沒有隱式this傳遞，所以靜態方法中不能直接訪問實例成員(實例變量/實例方法)

     - 何時用：方法的操作與對象無關(不需要訪問對象的屬性/行為)

       ```java
       public class StaticMethod {
           int a; //實例變量(對象來訪問)--------------屬於對象的
           static int b; //靜態變量(類名來訪問)-------屬於類的
       
           //方法的操作與對象無關(不需要訪問對象的屬性/行為)
       
           //在say()中需要訪問對象的屬性a，所以認為say的操作與對象有關，不適合設計為靜態方法
           void say(){
               System.out.println(a);
           }
           //在plus()中不需要訪問對象的屬性/行為，所以認為plus的操作與對象無關，可以設計為靜態方法
           static int plus(int num1,int num2){
               int num = num1+num2;
               return num;
           }
       
           void show(){ //有隱式this
               System.out.println(this.a);
               System.out.println(StaticMethod.b);
           }
           static void test(){ //沒有隱式this
               //靜態方法中沒有隱式this傳遞
               //沒有this就意味著沒有對象
               //而實例變量a必須通過對象來訪問
               //所以如下語句發生編譯錯誤
               //System.out.println(a); //編譯錯誤，靜態方法中不能直接訪問實例成員
               System.out.println(StaticMethod.b);
           }
       }
       
       public class StaticDemo {
           public static void main(String[] args) {
               StaticMethod.test(); //常常通過類名點來訪問
           }
       }
       ```

3. static final常量：應用率高

   - 必須聲明同時初始化

   - 常常通過類名點來訪問，不能被改變

   - 建議：常量名所有字母都大寫，多個單詞之間用_分隔

   - 編譯器在編譯時，會將常量直接替換為具體的數，效率高

   - 何時用：在程序運行過程中數據永遠不變，並且經常使用

     ```java
     public class StaticFinalDemo {
         public static void main(String[] args) {
             System.out.println(Loo.PI); //常常通過類名點來訪問
             //Loo.PI = 3.1415926; //編譯錯誤，常量不能被改變
     
             //1)加載Loo.class到方法區中
             //2)靜態變量num一並存儲到方法區中
             //3)到方法區中獲取num的值並輸出
             System.out.println(Loo.num);
     
             //編譯器在編譯時會將常量直接替換為具體的數，效率高
             //相當於System.out.println(5);
             System.out.println(Loo.COUNT);
         }
     }
     
     class Loo{
         public static int num = 5; //靜態變量
         public static final int COUNT = 5; //常量(靜態常量)
     
         public static final double PI = 3.14159;
         //public static final int NUM; //編譯錯誤，常量必須聲明同時初始化
     }
     ```

4. 枚舉：

   - 是一種引用數據類型

   - 特點：枚舉類型的對象數目是固定的，常常用於定義一組常量

   - 所有枚舉都繼承自Enum類，其中提供了一組方法供我們使用

   - 枚舉的構造方法都是私有的

     ```java
     //簡單版:
     package ooday05;
     /**
      * 季節枚舉
      */
     public enum Seasons {
         SPRING,SUMMER,AUTUMN,WINTER //表示Seasons的固定的4個對象，都是常量
     }
     
     package ooday05;
     /**
      * 枚舉的測試類
      */
     public class EnumTest {
         public static void main(String[] args) {
             Seasons[] seasons = Seasons.values(); //獲取所有枚舉對象
             for(int i=0;i<seasons.length;i++){
                 System.out.println(seasons[i]);
             }
             /*
             Seasons s = Seasons.WINTER; //獲取WINTER對象
             switch(s){
                 case SPRING:
                     System.out.println("春天到了...");
                     break;
                 case SUMMER:
                     System.out.println("夏天到了...");
                     break;
                 case AUTUMN:
                     System.out.println("秋天到了...");
                     break;
                 case WINTER:
                     System.out.println("冬天到了...");
                     break;
             }
              */
         }
     }
     ```

     

     ```java
     //覆雜版:
     package ooday05_vis;
     /**
      * 季節枚舉
      */
     public enum Seasons {
         SPRING("春天","暖和"),
         SUMMER("夏天","熱"),
         AUTUMN("秋天","涼爽"),
         WINTER("冬天","冷");
         private String seasonName;
         private String seasonDesc;
         Seasons(String seasonName, String seasonDesc) {
             this.seasonName = seasonName;
             this.seasonDesc = seasonDesc;
         }
     
         public String getSeasonName() {
             return seasonName;
         }
         public void setSeasonName(String seasonName) {
             this.seasonName = seasonName;
         }
         public String getSeasonDesc() {
             return seasonDesc;
         }
         public void setSeasonDesc(String seasonDesc) {
             this.seasonDesc = seasonDesc;
         }
     }
     
     package ooday05_vis;
     
     /**
      * 枚舉的演示
      */
     public class EnumTest {
         public static void main(String[] args) {
             Seasons s = Seasons.WINTER;
             System.out.println(s.getSeasonName()+","+s.getSeasonDesc());
     
             Seasons[] seasons = Seasons.values();
             for(int i=0;i<seasons.length;i++){
                 System.out.println(seasons[i]);
                 System.out.println(seasons[i].getSeasonName());
                 System.out.println(seasons[i].getSeasonDesc());
             }
         }
     }
     ```



## 補充：

1. 數據(成員變量)私有化(private)、行為(方法)大部分公開化(public)

2. getter/setter：行業標準

   ```java
   package ooday05;
   /*
     標準JavaBean的規範:
     1.成員變量私有，同時提供對應的公開的getter/setter
     2.包含公開的無參構造方法
   
     設計getter/setter的原因:
     1.很多框架的配置操作都是基於getter/setter，沒有它就獲取不到數據，
       可以將設計getter/setter理解為一種行為標準
     2.可以更好的保證數據的合法性(因為方法中可以做條件控制)
     3.getter/setter可以選擇性存在(只有getter(只讀)，或者只有setter(只寫))
    */
   public class Point {
       private int x;
       private int y;
       
       public int getX(){ //getter獲取
           return x;
       }
       public void setX(int x){ //setter設置
           this.x = x;
       }
       
       public int getY(){
           return y;
       }
       public void setY(int y){
           this.y = y;
       }
   }
   ```

3. 接口中的方法默認為公開的抽象類，重寫方法時派生類方法的訪問權限必須大於或等於超類方法的

4. 成員變量分兩種：

   - 實例變量：沒有static修飾，屬於對象的，存儲在堆中，有幾個對象就有幾份，通過引用/對象打點來訪問
   - 靜態變量：有static修飾，屬於類的，存儲在方法區中，只有一份，通過類名打點來訪問

5. 明日單詞：

   ```java
   1)last:最後的
   2)trim:剪去、截掉
   3)start:開始
   4)end:結束
   5)uppercase:大寫字母
   6)lowercase:小寫字母
   7)value:值
   ```
