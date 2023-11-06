# 語言基礎第七天：

## 回顧：

1. 數組的覆制：

   ```java
   1)System.arraycopy(a,1,b,0,4);
   2)int[] b = Arrays.copyOf(a,6);
     a = Arrays.copyOf(a,a.length+1);
   ```

2. 方法：

   - 封裝一段特定的業務邏輯功能、只幹一件事、反覆多次調用、減少重覆、有利於維護
   - 何時用：只要是一個獨立的業務功能，就要把它封裝到一個方法中

3. 方法的定義：

   ```java
   修飾詞  返回值類型  方法名(參數列表){
       方法體----具體的業務邏輯功能實現
   }
   ```

4. 方法的調用：

   - 無返回值：方法名(有參傳參);

   - 有返回值：數據類型   變量  =  方法名(有參傳參);   //-------最常規的調用方式

                        System.out.println( 方法名(有參傳參) );  //返回值為基本類型時可以這樣調

5. return：

   - return 值;  //1)結束方法的執行   2)返回結果給調用方
   - return;       //1)結束方法的執行



## 精華筆記：

1. 案例：------培養編程思維能力



## 筆記：

1. 案例：------培養編程思維能力

   ```java
   package day07;
   
   import java.util.Scanner;
   
   /**
    * 需求:猜數字小遊戲
    * 訓練目標: while(true)自造死循環+break
    */
   public class Guessing {
       public static void main(String[] args) {
           Scanner scan = new Scanner(System.in);
           int num = (int)(Math.random()*1000+1); //1到1000
           System.out.println(num); //作弊
   
           while(true){ //自造死循環
               System.out.println("猜吧!");
               int guess = scan.nextInt();
               if(guess>num){
                   System.out.println("猜大了");
               }else if(guess<num){
                   System.out.println("猜小了");
               }else{
                   System.out.println("恭喜你猜對了");
                   break; //跳出循環
               }
           }
       }
   }
   ```

   ```java
   package day07;
   
   import java.util.Random;
   
   /**
    * 需求: 隨機生成N位驗證碼(大寫字母、小寫字母、數字)
    * 訓練目標: 數組+隨機數
    */
   public class VerificationCode {
       public static void main(String[] args) {
           String code = generateVeriCode(6);
           System.out.println("驗證碼:" + code);
       }
   
       /** 生成驗證碼 */
       public static String generateVeriCode(int len){
           String code = ""; //驗證碼
           Random rand = new Random(); //隨機數對象
           char[] chs = {'a','b','c','d','e','f','g','h','i','j','k',
                   'l','m','n','o','p','q','r','s','t','u','v',
                   'w','x','y','z','A','B','C','D','E','F','G',
                   'H','I','J','K','L','M','N','O','P','Q','R',
                   'S','T','U','V','W','X','Y','Z','0','1','2',
                   '3','4','5','6','7','8','9'}; //驗證碼字符可選範圍
           for(int i=1;i<=len;i++) { //len次
               int index = rand.nextInt(chs.length); //隨機下標(0到61)
               code += chs[index]; //根據隨機下標獲取對應字符並拼接到code中
           }
           /*
                            code=""
             i=1  index=0   code="a"
             i=2  index=61  code="a9"
             i=3  index=25  code="a9z"
             i=4  index=28  code="a9zC"
             i=5
            */
           return code;
       }
   }
   ```

   ```java
   package day07;
   
   /**
    * 需求:---------常見面試題
    *   找到2到100之間的所有素數(質數)
    *   素數:除了1和它本身外，不能被其它任何自然數整除的數----只能被1和它本身整除
    * 訓練目標: 通過boolean的flag打標記(3步)
    */
   public class PrimeNumber {
       public static void main(String[] args) {
           //帶數(2/3/4/5/6/7/8)
           for(int num=2;num<=100;num++){
               boolean flag = true; //假設每個num都是true
               for(int i=2;i<=num/2;i++){
                   if(num%i==0){
                       flag = false;
                       break;
                   }
               }
               if(flag){
                   System.out.print(num+"\t");
               }
           }
   		
           /*
           // 7%2/3/4/5/6，但凡有1個為0的，就能說明它不是素數，只有都不為0的，才是素數
           int num = 7; //7,8,9,11
           boolean flag = true; //1)假設num是素數
           for(int i=2;i<=num/2;i++){ //i=2/3
               if(num%i==0){
                   flag = false; //2)修改num為不是素數
                   break;
               }
           }
           if(flag){ //3)判斷flag標記
               System.out.println(num+"是素數");
           }else{
               System.out.println(num+"不是素數");
           }
            */
       }
   }
   ```

   ```java
   package day07;
   
   import java.util.Scanner;
   
   /**
    * 需求:
    *   機票價格按照季節(淡季、旺季)、艙位(頭等艙、商務艙、經濟艙)收費
    * 要求:
    *   輸入機票原價、月份和艙位，實現不同的折扣
    *   ---旺季(5月到10月)時，頭等艙9折，商務艙85折，經濟艙8折
    *   ---淡季(11月到來年4月)時，頭等艙7折，商務艙65折，經濟艙6折
    * 訓練目標: 分支結構
    */
   public class CalAirPrice {
       public static void main(String[] args) {
           Scanner scan = new Scanner(System.in);
           System.out.println("請輸入機票原價:");
           double price = scan.nextDouble();
           System.out.println("請輸入月份:");
           int month = scan.nextInt();
           System.out.println("請選擇艙位: 1.頭等艙 2.商務艙 3.經濟艙");
           int type = scan.nextInt();
   
           double finalPrice = calFinalPrice(price,month,type); //計算折扣後金額
           if(finalPrice!=-1){ //數據合法
               System.out.println("機票的最終價格為:"+finalPrice);
           }
       }
   
       /**
        * 根據原價、月份、艙位，計算飛機票的最終價格
        */
       public static double calFinalPrice(double price,int month,int type){
           double finalPrice = 0.0; //最終價格
           //只要數據輸入錯誤，都統一返回-1
           if(price<0){
               System.out.println("機票原價輸入錯誤");
               return -1;
           }
           if(month<1 || month>12){
               System.out.println("月份輸入錯誤");
               return -1;
           }
           if(type<1 || type>3){
               System.out.println("艙位輸入錯誤");
               return -1;
           }
   
           //程序能走到這，說明數據一定是合法的
           if(month>=5 && month<=10){ //旺季
               switch(type){ //根據艙位類型做不同折扣
                   case 1:
                       finalPrice = price*0.9;
                       break;
                   case 2:
                       finalPrice = price*0.85;
                       break;
                   case 3:
                       finalPrice = price*0.8;
                       break;
               }
           }else{ //淡季
               switch(type){ //根據艙位類型做不同折扣
                   case 1:
                       finalPrice = price*0.7;
                       break;
                   case 2:
                       finalPrice = price*0.65;
                       break;
                   case 3:
                       finalPrice = price*0.6;
                       break;
               }
           }
           return finalPrice;
       }
   }
   ```

   ```java
   package day07;
   
   /**
    * 需求:
    *   <<主持人大賽>>有N名評委給選手打分，要求分數範圍為0到100之間的浮點數
    *   選手的最終得分為: 去掉最高分和最低分後的N-2名評委的平均分
    * 訓練目標: 方法的設計
    */
   public class CalTotalAvg {
       public static void main(String[] args) {
           double[] scores = inputData(6); //1)錄入評委的評分
           double avg = calAvg(scores); //2)計算平均分
           System.out.println("平均分為:"+avg);
       }
   
       /** 錄入N位評委的評分 */
       public static double[] inputData(int count){
           Scanner scan = new Scanner(System.in);
           double[] scores = new double[count]; //評分數組
           for(int i=0;i<scores.length;i++){
               System.out.println("請輸入第"+(i+1)+"個評委的分數:");
               scores[i] = scan.nextDouble();
           }
           return scores;
       }
   
       /** 計算平均分 */
       public static double calAvg(double[] scores){
           double total = calTotal(scores); //獲取去掉最高分和最低分之後的總分
           double avg = total/(scores.length-2); //平均分
           return avg;
       }
   
       /** 計算去掉最高分和最低後的總分 */
       public static double calTotal(double[] scores){
           double total = 0.0; //總分
           double max = scores[0]; //假設第1個元素為最高分
           double min = scores[0]; //假設第1個元素為最低分
           for(int i=0;i<scores.length;i++){
               if(scores[i]>max){ //找最高分
                   max = scores[i];
               }
               if(scores[i]<min){ //找最低分
                   min = scores[i];
               }
               total += scores[i];
           }
           return total-max-min; //返回去掉最高分和最低分之後的總分
       }
   }
   ```



## 補充：

1. 方法的簽名：方法名+參數列表

2. 明日單詞：

   ```java
   1)Student:學生
   2)className:班級名稱
   3)stuId:學號
   4)study:學習
   5)play:玩
   6)another:另一個
   7)Car:小汽車
   8)brand:品牌
   9)color:顏色
   10)price:價格
   11)start:開始、啟動
   12)run:跑
   13)stop:停止
   ```