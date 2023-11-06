# day02

### StringBuilder類

由於String是不變對象,每次修改內容都要創建新對象,因此String不適合做頻繁修改操作.為了解決這個問題,java提供了StringBuilder類.

```java
package string;

/**
 * 頻繁修改字符串帶來的性能損耗.
 */
public class StringDemo2 {
    public static void main(String[] args) {
        String str = "a";
        for(int i=0;i<10000000;i++){
            str = str + str;
        }
        System.out.println("執行完畢!");
    }
}
```

StringBuilder是專門用來修改String的一個API,內部維護一個可變的char數組,修改都是在這個數組上進行的,
內部會自動擴容.修改速度和性能開銷優異.並且提供了修改字符串的常見操作對應的方法:增刪改插

```java
package string;

/**
 * java.lang.StringBuilder
 * 專門用來修改String的一個API,內部維護一個可變的char數組,修改都是在這個數組上進行的,
 * 內部會自動擴容.修改速度和性能開銷優異.並且提供了修改字符串的常見操作對應的方法:增刪改插
 */
public class StringBuilderDemo1 {
    public static void main(String[] args) {
        String str = "好好學習java";

        //內部默認表示一個空字符串
//        StringBuilder builder = new StringBuilder();

        //覆制給定字符串到StringBuilder內部
//        StringBuilder builder = new StringBuilder(str);//不是線程安全的
        StringBuffer builder = new StringBuffer(str);//是線程安全的
        /*
            好好學習java
            好好學習java,為了找個好工作!
            append:追加內容
         */
        builder.append(",為了找個好工作!");
        System.out.println(builder);//輸出StringBuilder的內容

        //通過調用toString方法將StringBuilder內容以字符串形式返回.
        str = builder.toString();
        System.out.println(str);

        /*
            好好學習java,為了找個好工作!
            好好學習java,就是為了改變世界!
            replace:替換部分內容
         */
        builder.replace(9,16,"就是為了改變世界");
        System.out.println(builder);

        /*
            好好學習java,就是為了改變世界!
            ,就是為了改變世界!
            delete:刪除部分內容
         */
        builder.delete(0,8);
        System.out.println(builder);

        /*
            ,就是為了改變世界!
            活著,就是為了改變世界!
            insert:插入操作
         */
        builder.insert(0,"活著");
        System.out.println(builder);

        //翻轉字符串
        builder.reverse();
        System.out.println(builder);
    }
}
```

StringBuffer 和StringBuilder 

– StringBuffer是線程安全的，同步處理的，性能稍慢

–StringBuilder是非線程安全的，並發處理的，性能稍快



```java
package string;

/**
 * StringBuilder修改字符串的性能
 */
public class StringBuilderDemo2 {
    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder("a");
        for(int i=0;i<10000000;i++){
            builder.append("a");
        }
        System.out.println("執行完畢!");
    }
}

```



### 正則表達式

正則表達式是用來描述一個字符串的內容格式,使用它通常用來匹配一個字符串的內容是否符合格式要求.

#### 基本語法

[]:表示一個字符,該字符可以是[]中指定的內容

例如:

[abc]:這個字符可以是a或b或c
[a-z]:表示任意一個小寫字母
[a-zA-Z]:表示任意一個字母
[a-zA-Z0-9_]:表示任意一個數字字母下劃線

[^abc]: 該字符只要不是a或b或c


##### 預定義字符

.:"."表示任意一個字符,沒有範圍限制

\d:表示任意一個數字,等同於[0-9]

\w:表示任意一個單詞字符,等同於[a-zA-Z0-9_]

\s:表示任意一個空白字符.

\D:表示不是數字

\W:不是單詞字符

\S:不是空白字符


##### 量詞:

 * ?:表示前面的內容出現0-1次

   例如:

   [abc]? 可以匹配:a 或 b 或 c 或什麽也不寫

 * +:表示前面的內容出現1次以上

   [abc]+ 可以匹配:aaaaaaaaaa...或abcabcbabcbabcbabcbabbabab....

   但是不能匹配:什麽都不寫或abcfdfsbbaqbb34bbwer...

 * *:表示前面的內容出現任意次(0-多次)

   匹配內容與+一致,只是可以一次都不寫.

 * {n}:表示前面的內容出現n次

   例如:

   [abc]{3} 可以匹配:aaa 或 bbb 或 aab

   不能匹配:aaaa或aad

 * {n,m}:表示前面的內容出現最少n次最多m次

   [abc]{3,5} 可以匹配:aaa 或  abcab 或者 abcc

   不能匹配:aaaaaa 或 aabbd

 * {n,}:表示前面的內容出現n次以上(含n次)

   [abc]{3,} 可以匹配:aaa 或 aaaaa.... 或 abcbabbcbabcbabcba....

   不能匹配:aa 或 abbdaw...

 * ()用於分組,是將括號內的內容看做是一個整體

   例如:

   (abc){3} 表示abc整體出現3次. 可以匹配abcabcabc.

   不能匹配aaa 或abcabc


   (abc|def){3}表示abc或def整體出現3次.

   可以匹配: abcabcabc 或 defdefdef 或 abcdefabc



### String支持正則表達式的相關方法

##### matches方法

boolean matches(String regex)

使用給定的正則表達式驗證當前字符串是否滿足格式要求,滿足則返回true.否則返回false

```java
package string;

public class MatchesDemo {
    public static void main(String[] args) {
        /*
            郵箱的正則表達式
            用戶名@域名
            fancq@tedu.cn
            [a-zA-Z0-9_]+@[a-zA-Z0-9]+(\.[a-zA-Z]+)+
         */
        String mail = "fancq@tedu.cn";
        String regex = "[a-zA-Z0-9_]+@[a-zA-Z0-9]+(\\.[a-zA-Z]+)+";
        boolean match = mail.matches(regex);
        if(match){
            System.out.println("是郵箱");
        }else{
            System.out.println("不是郵箱");
        }
    }
}
```

##### split方法

String[] split(String regex)

將當前字符串按照滿足正則表達式的部分進行拆分,將拆分後的每部分以數組形式返回.

```java
package string;

import java.util.Arrays;

public class SplitDemo {
    public static void main(String[] args) {
        String str = "abc123def456ghi";
        //按照數字部分拆分,獲取其中每部分字母
        String[] arr = str.split("[0-9]+");
        System.out.println(arr.length);
        System.out.println(Arrays.toString(arr));

        str = "123,456,789,023";
        //拆分出所有的數字部分
        arr = str.split(",");
        System.out.println(Arrays.toString(arr));
        //如果連續遇到拆分項,則會拆分出一個空字符串.但是在字符串末尾連續遇到則忽略.
        str = ",,,123,,,456,789,023,,,,";
        //拆分出所有的數字部分
        arr = str.split(",");
        System.out.println(Arrays.toString(arr));

        str = "123.456.789.023";
        //拆分出所有的數字部分
        arr = str.split("\\.");//.在正則表達式中表示任意字符,要注意轉意!
        System.out.println(Arrays.toString(arr));
    }
}
```

##### replaceAll方法

String replaceAll(String regex,String str)

將當前字符串中滿足正則表達式的部分替換為給定內容

```java
package string;

public class ReplaceAllDemo {
    public static void main(String[] args) {
        String str = "abc123def456ghi";
        //將當前字符串中的數字部分替換為#NUMBER#
        str = str.replaceAll("[0-9]+","#NUMBER#");
        System.out.println(str);
    }
}
```



### Object類

Object是所有類的頂級超類,其中有兩個經常被子類重寫的方法:

toString()與equals().

#### 編寫Point類進行測試

```java
package object;

import java.util.Objects;

/**
 * 使用當前類測試超類Object中經常被子類重寫的方法:equals與toString
 *
 * Point類設計目的是表示直角坐標系中的一個點
 */
public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
```



編寫測試類

```java
package object;

public class TestPoint {
    public static void main(String[] args) {
        Point p = new Point(1,2);
        /*
            System.out.println(Object o)
            將給定對象toString方法返回返回的字符串輸出到控制台

            toString方法是Object提供的方法，該方法默認返回的字符串為:
            類名@地址
         */
        System.out.println(p);
//        System.out.println(p.toString());
        /*
            任何類型與字符串鏈接結果都是字符串
            這意味著鏈接的類型會被轉為字符串再進行鏈接。其他類型是如何
            轉換為String?就是依靠toString方法
         */
        String line = "當前對象是:" + p;
        System.out.println(line);


        Point p2 = new Point(1,2);
        System.out.println("p2:"+p2);
        //==對於引用類型的意義是比較是否為同一個對象
        System.out.println(p==p2);//false  兩個不同的對象
        //equals則是比較兩個對象"像不像"(對象內容，特征是否一致)
        System.out.println(p.equals(p2));//true 兩個對象的內容相同

    }
}
```



### 包裝類

java定義了8個包裝類,目的是為了解決基本類型不能直接參與面向對象開發的問題,使得基本類型可以通過包裝類的實例以對象的形式存在.

* 其中數字類型的包裝類都繼承自java.lang.Number,而char和boolean的包裝類直接繼承自Object
* Number是一個抽象類,定義了一些方法,目的是讓包裝類可以將其表示的基本類型轉換為其他數字類型.

```java
package integer;

public class IntegerDemo1 {
    public static void main(String[] args) {
        //基本類型轉換為包裝類
        int i = 123;
        //java推薦我們使用包裝類的靜態方法valueOf將基本類型轉換為包裝類,而不是直接new
        Integer i1 = Integer.valueOf(i);//Integer會重用-128-127之間的整數對象
        Integer i2 = Integer.valueOf(i);
        System.out.println(i1==i2);//true
        System.out.println(i1.equals(i2));//true

        double dou = 123.123;
        Double dou1 = Double.valueOf(dou);//Double則是直接new
        Double dou2 = Double.valueOf(dou);
        System.out.println(dou1==dou2);//false
        System.out.println(dou1.equals(dou2));//true

        //包裝類轉換為基本類型
        int in = i1.intValue();//獲取包裝類對象中表示的基本類型值
        double doub = i1.doubleValue();
        System.out.println(in);//123
        System.out.println(doub);//123.0

        in = dou1.intValue();//大類型轉小類型可能存在丟精度!
        doub = dou1.doubleValue();
        System.out.println(in);//123
        System.out.println(doub);//123.123
    }
}
```

##### 包裝類常用功能

```
package integer;

public class IntegerDemo2 {
    public static void main(String[] args) {
        //1可以通過包裝類獲取其表示的基本類型的取值範圍
        //獲取int的最大值和最小值?
        int imax = Integer.MAX_VALUE;
        System.out.println(imax);
        int imin = Integer.MIN_VALUE;
        System.out.println(imin);

        long lmax = Long.MAX_VALUE;
        System.out.println(lmax);
        long lmin = Long.MIN_VALUE;
        System.out.println(lmin);

        /*
            2字符串轉換為基本類型的前提是該字符串正確描述了基本類型可以保存的值,否則
            會拋出異常:NumberFormatException
         */
        String str = "123";
//        String str = "123.123";//這個字符串不能解析為int值!
        int d = Integer.parseInt(str);
        System.out.println(d);//123
        double dou = Double.parseDouble(str);
        System.out.println(dou);//123.123
    }
}
```

##### 自動拆裝箱特性

JDK5之後推出了一個新的特性:自動拆裝箱

該特性是編譯器認可的.當編譯器編譯源代碼時發現有基本類型和引用類型相互賦值使用時會自動補充代碼來完成他們的轉換工作,這個過程稱為自動拆裝箱.

```java
package integer;

public class IntegerDemo3 {
    public static void main(String[] args) {
        /*
            觸發自動拆箱特性,編譯器會補充代碼將包裝類轉換為基本類型,下面的代碼會變為:
            int i = new Integer(123).intValue();
         */
        int i = new Integer(123);
        /*
            觸發編譯器自動裝箱特性,代碼會被編譯器改為:
            Integer in = Integer.valueOf(123);
         */
        Integer in = 123;
    }
}
```