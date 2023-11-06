# 語言基礎第五天：

## 回顧：

1. Scanner接收用戶輸入的數據：3步，先背下來

2. 分支結構(下)：

   - switch...case結構：多條路
     - 優點：效率高、結構清晰
     - 缺點：只能對整數判斷相等
     - break：跳出switch

3. 循環：反覆多次執行一段相同或相似的代碼

4. 循環三要素：

   - 循環變量的初始化
   - 循環條件(以循環變量為基礎)
   - 循環變量的改變

5. 循環結構：

   - while結構：先判斷後執行，有可能一次都不執行

   - do...while結構：先執行後判斷，至少執行一次

     > 若要素1與要素3的代碼相同，首選do...while

   - for結構：應用率最高，與次數相關的



## 精華筆記：

1. for的練習：

2. break：跳出循環

   continue：跳過循環體中剩余語句而進入下一次循環

3. 嵌套循環：

   - 循環中套循環，常常多行多列時使用，外層控制行，內層控制列
   - 執行規則：外層循環走一次，內層循環走所有次
   - 建議：嵌套層數越少越好，能用一層就不用兩層，能用兩層就不用三層
   - break默認只能跳出當前一層循環

4. 數組(上)：

   - 是一種引用數據類型
   - 相同數據類型元素的集合
   - 定義：
   - 初始化：初始化數組中的元素
   - 訪問：
     - 通過(數組名.length)可以獲取數組的長度(元素個數)
     - 通過下標/索引來訪問元素，下標從0開始，最大到(數組的長度-1)
   - 遍歷/叠代：從頭到尾挨個走一遍
   - 排序：



## 筆記：

1. for練習：

   ```java
   public class ForDemo {
       public static void main(String[] args) {
           //累加和:1+2+3+4+...+99+100=?
           int sum = 0; //存和
           for(int i=1;i<=100;i++){
               sum = sum+i; //累加
           }
           System.out.println("sum="+sum);
           /*
             執行過程:
                   sum=0
             i=1   sum=1
             i=2   sum=1+2
             i=3   sum=1+2+3
             i=4   sum=1+2+3+4
             ...   sum=1+2+3+4+...
             i=99  sum=1+2+3+4+...+99
             i=100 sum=1+2+3+4+...+99+100
             i=101 false
            */
       }
   }
   
   package day05;
   import java.util.Scanner;
   //隨機加法運算器
   public class Addition {
       public static void main(String[] args) {
           Scanner scan = new Scanner(System.in);
           int score = 0; //總分
           for(int i=1;i<=10;i++){ //10次     (1)25+14=?
               int a = (int)(Math.random()*100); //加數a(0到99)
               int b = (int)(Math.random()*100); //加數b(0到99)
               int result = a+b; //存正確答案
               System.out.println("("+i+")"+a+"+"+b+"=?"); //1)出題
               System.out.println("算吧!");
               int answer = scan.nextInt(); //2)答題
               if(answer==result){ //3)判題
                   System.out.println("答對了");
                   score += 10; //答對1題，加10分
               }else{
                   System.out.println("答錯了");
               }
           }
           System.out.println("總分為:"+score);
       }
   }
   ```

2. break：跳出循環

   ```java
   for(int num=1;num<=9;num++){
       if(num==4){ //在某種特定條件下，提前結束循環
           break; //跳出循環
       }
       System.out.println(num+"*9="+num*9);
   }
   /*
      num=1  1*9=9
      num=2  2*9=18
      num=3  3*9=27
      num=4
   */
   ```

   continue：跳過循環體中剩余語句而進入下一次循環

   ```java
   //輸出9的乘法表，跳過能被3整除的
   for(int num=1;num<=9;num++){
       if(num%3==0){
           continue; //跳過循環體中剩余語句而進入下一次循環
       }
       System.out.println(num+"*9="+num*9);
   }
   /*
      num=1  1*9=9
      num=2  2*9=18
      num=3
      num=4  4*9=36
      num=5  5*9=45
      num=6
      num=7  7*9=63
      num=8  8*9=72
      num=9
      num=10
   */
   
   //輸出9的乘法表，只要不能被3整除的
   for(int num=1;num<=9;num++){
       if(num%3!=0){
           System.out.println(num+"*9="+num*9);
       }
   }
   ```

3. 嵌套循環：

   - 循環中套循環，常常多行多列時使用，外層控制行，內層控制列

   - 執行規則：外層循環走一次，內層循環走所有次

   - 建議：嵌套層數越少越好，能用一層就不用兩層，能用兩層就不用三層

   - break默認只能跳出當前一層循環

     ```java
     public class MultiTable {
         public static void main(String[] args) {
             for(int num=1;num<=9;num++){ //控制行
                 for(int i=1;i<=num;i++){   //控制列
                     System.out.print(i+"*"+num+"="+i*num+"\t");
                 }
                 System.out.println(); //換行
             }
             /*
               執行過程:
                 num=3
                   i=1  1*3=3
                   i=2  2*3=6
                   i=3  3*3=9
                   i=4  false
                   換行
                 num=2
                   i=1  1*2=2
                   i=2  2*2=4
                   i=3  false
                   換行
                 num=1
                   i=1  1*1=1
                   i=2  false
                   換行
              */
         }
     }
     ```

4. 數組(上)：

   - 是一種引用數據類型

   - 相同數據類型元素的集合

   - 定義：

     ```java
     //聲明整型數組a，包含5個元素，每個元素都是int類型，默認值為0
     int[] a = new int[5];
     //聲明浮點型數組d，包含10個元素，每個元素都是double類型，默認值為0.0
     double[] d = new double[10];
     //聲明布爾型數組b，包含26個元素，每個元素都是boolean類型，默認值為false
     boolean[] b = new boolean[26]; 
     ```

   - 初始化：初始化數組中的元素

     ```java
     int[] arr1 = new int[3]; //0,0,0
     int[] arr2 = {2,5,8}; //2,5,8
     int[] arr3 = new int[]{2,5,8}; //2,5,8
     int[] arr4;
     //arr4 = {2,5,8}; //編譯錯誤，此方式只能聲明同時初始化
     arr4 = new int[]{2,5,8}; //正確
     ```

   - 訪問：

     - 通過(數組名.length)可以獲取數組的長度(元素個數)

       ```java
       int[] arr = new int[3];
       System.out.println("數組的長度:"+arr.length); //3
       ```

     - 通過下標/索引來訪問元素，下標從0開始，最大到(數組的長度-1)

       ```java
       int[] arr = new int[3];
       System.out.println("數組的長度:"+arr.length); //3
       System.out.println(arr[0]); //0，輸出第1個元素的值
       arr[0] = 100; //給第1個元素賦值為100
       arr[1] = 200; //給第2個元素賦值為200
       arr[2] = 300; //給第3個元素賦值為300
       //arr[3] = 400; //運行時會發生數組下標越界異常
       System.out.println(arr[arr.length-1]); //300，輸出最後一個元素的值
       ```

   - 遍歷/叠代：從頭到尾挨個走一遍

     ```java
     int[] arr = new int[10];
     for(int i=0;i<arr.length;i++){ //遍歷arr數組
         arr[i] = (int)(Math.random()*100); //給每個元素賦值為0到99的隨機數
         System.out.println(arr[i]); //輸出每個元素的值
     }
     ```

     ```java
     public class MaxOfArray {
         public static void main(String[] args) {
             int[] arr = new int[10];
             for(int i=0;i<arr.length;i++){
                 arr[i] = (int)(Math.random()*100);
                 System.out.println(arr[i]);
             }
     
             //                  0, 1, 2, 3
             //假設:int[] arr = {12,56,89,8};
             //max=12/56/89
             int max = arr[0]; //假設第1個元素為最大值
             for(int i=1;i<arr.length;i++){ //遍歷剩余元素
                 if(arr[i]>max){   //若剩余元素大於max
                     max = arr[i]; //將max修改為較大的
                 }
             }
             System.out.println("最大值為:"+max);
     
         }
     }
     ```

   - 排序：

     ```java
     package day05;
     import java.util.Arrays;
     //數組的演示
     public class ArrayDemo {
         public static void main(String[] args) {
             //5)數組的排序:
             Random rand = new Random(); //隨機數對象
             int[] arr = new int[10];
             for(int i=0;i<arr.length;i++){
                 arr[i] = rand.nextInt(100); //0到99的隨機整數
                 System.out.println(arr[i]);
             }
     
             Arrays.sort(arr); //對arr數組做升序排列
     
             System.out.println("排序後:");
             for(int i=0;i<arr.length;i++){
                 System.out.println(arr[i]);
             }
             
             System.out.println("倒著輸出:");
             for(int i=arr.length-1;i>=0;i--){ //數據還是升序的，只是倒著展示
                 System.out.println(arr[i]);
             }
             System.out.println("第1個元素為:"+arr[0]);
         }
     }
     ```

     

## 補充：

1. \t：水平制表位，固定占8位

2. 默認值：

   ```java
   byte,short,int,long,char-------------0
   float,double-------------------------0.0
   boolean------------------------------false
   ```

3. ArrayIndexOutOfBoundsException：數組下標越界異常

   > 數組下標一定在0到(數組長度-1)之間，若超出範圍，在運行時會發生數組下標越界異常

4. 明日單詞：

   ```java
   1)copy:覆制
   2)arraycopy/copyOf:數組覆制
   3)max:最大值
   4)min:最小值
   5)sort:順序、排序
   6)method:方法
   7)public static:公開靜態的
   8)void:空，沒有返回結果的
   9)return:返回
   10)say:說
   11)sayHi/sayHello:問好
   12)getNum:獲取數字
   13)plus:加法
   14)test:測試
   ```