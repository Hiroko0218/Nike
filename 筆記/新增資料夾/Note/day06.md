# 語言基礎第六天：

## 回顧：

1. break：跳出循環

   continue：跳過循環體中剩余語句而進入下一次循環

2. 嵌套循環：

   - 循環中套循環，外層循環走一次，內層循環走所有次，嵌套層數越少越好，
   - break只能跳出當前一層循環

3. 數組：

   - 引用數據類型、相同數據類型元素的集合

     ```java
     int[] arr = new int[3]; //0,0,0
     int[] arr = {2,3,7};
     int[] arr = new int[]{2,3,7};
     arr[0] = 100;
     System.out.println(arr[arr.length-1]);
     for(int i=0;i<arr.length;i++){
         arr[i] = (int)(Math.random()*100);
         System.out.println(arr[i]);
     }
     Arrays.sort(arr); //升序
     ```

## 精華筆記：

1. 數組(下)：

   - 覆制：

     - System.arraycopy(a,1,b,0,4);

     - int[] b = Arrays.copyOf(a,6);

       a = Arrays.copyOf(a,a.length+1);  //數組的擴容

2. 方法：函數、過程

   - 作用：用於封裝一段特定的業務邏輯功能
   - 建議：盡可能獨立，一個方法只幹一件事
   - 方法可以被反覆多次調用
   - 好處：可以減少代碼重覆，有利於代碼維護
   - 何時用：只要是一個獨立的業務功能，就得封裝到一個方法中

3. 方法的定義：五要素

   ```java
   修飾詞  返回值類型  方法名(參數列表){
       方法體---------具體的業務邏輯功能實現
   }
   ```

4. 方法的調用：

   - 無返回值：方法名(有參傳參);
   - 有返回值：數據類型  變量  = 方法名(有參傳參);

5. return：

   - return 值;   //1)結束方法的執行   2)返回結果給調用方------------用在有返回值的方法中
   - return;        //1)結束方法的執行------------------------------------------用在無返回值的方法中

6. 方法的重載(overloading)：

   - 發生在同一類中，方法名相同，參數列表不同

   - 編譯器在編譯時會根據方法的簽名自動綁定方法

   

## 筆記：

1. 數組(下)：

   - 覆制：

     ```java
     int[] a = {10,20,30,40,50};
     int[] b = new int[6]; //0,0,0,0,0,0
     //a:源數組
     //1:源數組的起始下標
     //b:目標數組
     //0:目標數組的起始下標
     //4:要覆制的元素個數
     System.arraycopy(a,1,b,0,4); //靈活性好
     for(int i=0;i<b.length;i++){
         System.out.println(b[i]);
     }
     ```

     ```java
     int[] a = {10,20,30,40,50};
     //a:源數組
     //b:目標數組
     //6:目標數組的長度
     //  --若目標數組長度>源數組長度，則末尾補默認值
     //  --若目標數組長度<源數組長度，則將末尾的截掉
     int[] b = Arrays.copyOf(a,6); //靈活性差
     for(int i=0;i<b.length;i++){
         System.out.println(b[i]);
     }
     
     int[] a = {10,20,30,40,50};
     //數組的擴容(創建了一個更大的新的數組，並將數據覆制進去了)
     a = Arrays.copyOf(a,a.length+1);
     for(int i=0;i<a.length;i++){
         System.out.println(a[i]);
     }
     ```

     ```java
     package day06;
     import java.util.Arrays;
     //求數組元素的最大值，並將其存儲到數組最後一個元素的下一個位置
     public class MaxOfArray {
         public static void main(String[] args) {
             int[] arr = new int[10];
             for(int i=0;i<arr.length;i++){
                 arr[i] = (int)(Math.random()*100);
                 System.out.println(arr[i]);
             }
     
             int max = arr[0]; //假設第1個元素為最大值
             for(int i=1;i<arr.length;i++){ //遍歷剩余元素
                 if(arr[i]>max){   //若剩余元素大於max
                     max = arr[i]; //將max修改為較大的
                 }
             }
             System.out.println("最大值為:"+max);
     
             arr = Arrays.copyOf(arr,arr.length+1); //擴容
             arr[arr.length-1] = max; //將最大值max賦值到最後一個元素上
             for(int i=0;i<arr.length;i++){
                 System.out.println(arr[i]);
             }
     
         }
     }
     ```

2. 方法：函數、過程

   - 作用：用於封裝一段特定的業務邏輯功能
   - 建議：盡可能獨立，一個方法只幹一件事
   - 方法可以被反覆多次調用
   - 好處：可以減少代碼重覆，有利於代碼維護
   - 何時用：只要是一個獨立的業務功能，就得封裝到一個方法中

3. 方法的定義：五要素

   ```java
   修飾詞  返回值類型  方法名(參數列表){
       方法體---------具體的業務邏輯功能實現
   }
   ```

   ```java
   //無參無返回值
   public static void say(){
       System.out.println("大家好，我叫WKJ，今年38歲了");
   }
   
   //有參無返回值
   public static void sayHi(String name){ //----------------------形參
       System.out.println("大家好，我叫"+name+"，今年38歲了");
   }
   
   //有參無返回值
   public static void sayHello(String name,int age){ //-----------形參
       if(age>=60){ //在某種特定條件下，提前結束方法
           return; //結束方法
       }
       System.out.println("大家好，我叫"+name+"，今年"+age+"歲了");
   }
   
   //無參有返回值
   public static double getNum(){
       //在有返回值的方法中，必須得通過return來返回數據
       //return; //編譯錯誤，return後必須跟一個數據
       //return "abc"; //編譯錯誤，return後數據的類型必須與返回值類型匹配
       return 8.88; //1)結束方法的執行  2)返回結果給調用方
   }
   
   //有參有返回值
   public static int plus(int num1,int num2){
       int num = num1+num2;
       return num; //返回的是num里面的那個數
       //return num1+num2; //返回的是num1與num2的和
   }
   
   //生成一個整型數組，並填充隨機數據
   public static int[] generateArray(int len,int max){
       Random rand = new Random();
       int[] arr = new int[len];
       for(int i=0;i<arr.length;i++){
           arr[i] = rand.nextInt(max+1); //包括max
       }
       return arr;
   }
   ```

4. 方法的調用：

   - 無返回值：方法名(有參傳參);

     ```java
     public static void main(String[] args) {
         say(); //調用say()方法
     
         //sayHi(); //編譯錯誤，有參則必須傳參
         //sayHi(250); //編譯錯誤，參數類型必須匹配
         sayHi("zhangsan"); //String name="zhangsan" //-----實參
         sayHi("lisi"); //String name="lisi"  //------------實參
     
         sayHello("zhangsan",25); //----------實參
         sayHello("lisi",27); //--------------實參
     }
     ```

   - 有返回值：數據類型  變量  = 方法名(有參傳參);

     ```java
     public static void main(String[] args) {
         double a = getNum(); //getNum()的值就是return後的那個數
         System.out.println(a); //8.88---模擬對返回值的後續操作
     
         int b = plus(5,6);
         System.out.println(b); //11---模擬對返回值的後續操作
     
         int m=5,n=6;
         int c = plus(m,n); //傳的是m,n里面的數
         System.out.println(c); //11---模擬對返回值的後續操作
     
         int[] d = generateArray(5,100); //模擬第1個人的訪問
         System.out.println("數組的長度為:"+d.length); //10---模擬對返回值的後續操作
         for(int i=0;i<d.length;i++){ //---模擬對返回值的後續操作
             System.out.println(d[i]);
         }
     
         int[] e = generateArray(8,20); //模擬第2個人的訪問
         System.out.println("第1個元素的值:"+e[0]); //?---模擬對返回值的後續操作
         for(int i=0;i<e.length;i++){ //---模擬對返回值的後續操作
             System.out.println(e[i]);
         }
     }
     ```

5. return：

   - return 值;   //1)結束方法的執行   2)返回結果給調用方------------用在有返回值的方法中
   - return;        //1)結束方法的執行------------------------------------------用在無返回值的方法中

6. 方法的重載(overloading)：

   - 發生在同一類中，方法名相同，參數列表不同

   - 編譯器在編譯時會根據方法的簽名自動綁定方法

     ```java
     package day06;
     public class MethodDemo {
         public static void main(String[] args) {
             say(); //自動綁定無參show
             say("zhangsan"); //自動綁定String參的
             say("lisi",25); //自動綁定String+int參的
             //say(3.456); //編譯錯誤，沒有double參的say方法
         }
     
         //無參無返回值
         public static void say(){
             System.out.println("大家好，我叫WKJ，今年39歲了");
         }
     
         //有參無返回值
         public static void say(String name){ //-----------------形參
             System.out.println("大家好，我叫"+name+"，今年39歲了");
         }
         
         //有參無返回值
         public static void say(String name,int age){ //------形參
             if(age>=50){ //在某種特定條件下，提前結束方法
                 return; //結束方法的執行
             }
             System.out.println("大家好，我叫"+name+"，今年"+age+"歲了");
         }
     
         public static void say(int age) {} //正確的重載
         public static void say(int age,String name){} //正確的重載
         //public static int say(){ return 1; } //編譯錯誤，重載與返回值類型無關
         //public static void say(String address) { } //編譯錯誤，重載與參數名稱無關
     }
     ```

     



## 補充：

1. 形參：形式參數，定義方法時的參數為形參

   實參：實際參數，調用方法時的參數為實參

2. 方法的簽名：方法名+參數列表

3. 明日單詞：

   ```java
   1)price:價格
   2)score:分數
   3)total:總共
   4)avg:平均
   5)discount:折扣
   6)generate:生成
   7)index:下標/索引
   ```



## 練習：

```java
public static void main(String[] args){
    say();
    say("zhangsan");
    say("zhangsan",25);
    double a = getNum(); //輸出a--模擬對返回值的後續操作
    int b = plus(5,6); //輸出b--模擬對返回值的後續操作
    int m=50,n=60; int c = plus(m,n); //輸出c--模擬對返回值的後續操作
    int[] d = generateArray(5,100); //輸出d的長度，輸出每個元素的值-模擬對返回值的後續操作
    int[] e = generateArray(3,10); //輸出e中每個元素的值-模擬對返回值的後續操作
}
public static void say(){ 固定的問好 }
public static void say(String name){ 問好，名字寫活了 }
public static void say(String name,int age){ 問好，名字和年齡都寫活了}
public static double getNum(){ return 8.88; }
public static int plus(int num1,int num2){ return num1+num2; }
public static int[] generateArray(int len,int max){
    int[] arr = new ...; ...; return arr;
}
```