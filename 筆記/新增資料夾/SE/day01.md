# day01

### 1.文檔注釋

文檔注釋是功能級注釋，用來說明一個類，一個方法或一個常量的，因此只在上述三個地方使用。

文檔注釋可以使用java自帶的命令javadoc來對這個類生成手冊。

```java
package api;

/**
 * 文檔注釋是功能級注釋，用來說明一個類，一個方法或一個常量的，因此只在上述三個地方使用。
 * 文檔注釋可以使用java自帶的命令javadoc來對這個類生成手冊。
 *
 * 在類上使用時用來說明當前類的整體功能。
 * @author FAN
 */
public class dd.Demo {
    /**
     * sayHello中使用的問候語
     */
    public static final String INFO = "Hello!";

    /**
     * 為給定的用戶添加一個問候語
     * @param name 指定的用戶的名字
     * @return  返回了含有問候語的字符串
     */
    public String sayHello(String name){
        return "Hello!"+ name;
    }
}
```



## 2.String類

String用來表示一個字符串。具有以下特點:

•java.lang.String使用了final修飾，不能 被繼承；

•字符串底層封裝了字符數組及針對字符數組的操作算法；

•字符串一旦創建，對象永遠無法改變，但字符串引用可以重新賦值；

•Java字符串在內存中采用Unicode編碼方式，任何一個字符對應兩個字節的定長編碼。



### 字符串常量池

java在堆內存中開辟了一段空間用於緩存所有使用字面量形式創建的字符串對象，並在後期再次使用該字面量創建字符串時重用對象，避免內存中堆積大量內容一樣的字符串對象來減小內存開銷。

對於重覆出現的字符串直接量，JVM會首先在常量池中查找，如果存在即返回該對象地址。

```java
package string;
/**
 *  字符串String
 *  內部使用一個char數組保存所有字符，每個字符為2字節，存的是該字符unicode編碼。
 *  字符串是不變對象，一旦創建內容不可改變，若改變會創建新對象
 */
public class StringDemo {
    public static void main(String[] args) {
        /*
            字符串常量池
            java在堆內存中開辟了一段空間用於緩存所有使用字面量形式創建的字符串對象，
            並在後期再次使用該字面量創建字符串時重用對象，避免內存中堆積大量內容一樣
            的字符串對象來減小內存開銷。
         */
        String s1 = "123abc";//字面量
        String s2 = "123abc";//與s1字面量相同，重用對象
        //地址相同，說明s2重用了s1對象
        System.out.println(s1==s2);//true
        String s3 = "123abc";
        System.out.println(s1==s3);//true

        String s4 = new String("123abc");//new會產生新對象
        System.out.println("s4:"+s4);
        System.out.println(s1==s4);//false
        /*
            通常我們判斷字符串都是比較內容，因此應當使用字符串的equals方法
         */
        System.out.println(s1.equals(s4));//true
        /*
            由於字符串是不變對象，改變內容會產生新對象
         */
        s1 = s1 + "!";//生成一個新的字符串對象123abc!.
        System.out.println("s1:"+s1);//123abc!
        System.out.println("s2:"+s2);//123abc
        System.out.println(s1==s2);//false s1,s2已經不再指向同一個對象了

        /*
            這里觸發了一個編譯器的特性:
            編譯器在編譯期間若遇到幾個計算表達式，發現在編譯期可以確定結果時就會進行計算
            並將結果編譯到class文件中，這樣以來JVM每次執行字節碼文件就無需再計算了。
            下面的代碼會被編譯器改為:
            String s5 = "123abc";
            也因此s5會重用常量池中的對象，所以地址與s2相同
         */
        String s5 = "123" + "abc";
        System.out.println("s5:"+s5);
        System.out.println(s2==s5);

        String s = "123";
        String s6 = s + "abc";
        System.out.println("s6:"+s6);
        System.out.println(s2==s6);

        String s7 = 1+2+3+"abc";//6abc
        System.out.println(s2==s7);//false


        String s8 = 1+'2'+3+"abc";
        System.out.println(s2==s8);//false

        String s9 = 1+"2"+3+"abc";
        System.out.println(s2==s9);//true

    }
}
```

#### 字符串常用方法

##### int length()

返回當前字符串的長度(字符個數)

```java
package string;

public class LengthDemo {
    public static void main(String[] args) {
        String str = "我愛java!";
        int len = str.length();
        System.out.println("len:"+len);
    }
}
```

##### indexOf()

檢索給定字符串在當前字符串中的位置，若當前字符串不含有給定內容則返回值為-1

```java
package string;

public class IndexOfDemo {
    public static void main(String[] args) {
        //            0123456789012345
        String str = "thinking in java";

        int index = str.indexOf("in");//2
        System.out.println(index);

        //重載的方法可以從指定位置開始檢索第一次出現給定字符串的位置
        index = str.indexOf("in",3);//5
        System.out.println(index);

        //檢索最後一次出現in的位置
        index = str.lastIndexOf("in");
        System.out.println(index);


    }
}
```

##### substring()

截取當前字符串中指定範圍內的字符串。兩個參數分別為開始位置的下標和結束位置的下標。

```java
package string;

/**
 * String substring(int start,int end)
 * 截取當前字符串中指定範圍內的字符串。兩個參數分別為開始位置的下標和結束位置的下標。
 * 注:在JAVA API中通常使用兩個數字表示範圍時是"含頭不含尾"的。
 */
public class SubstringDemo {
    public static void main(String[] args) {
        //             01234567890
        String line = "www.tedu.cn";
        //截取域名tedu
        String str = line.substring(4,8);
        System.out.println(str);
        
        //重載的方法是從指定位置開始截取到字符串末尾
        str = line.substring(4);
        System.out.println(str);

    }
}
```

##### trim()

去除一個字符串兩邊的空白字符

```java
package string;

/**
 * String trim()
 * 去除一個字符串兩邊的空白字符
 */
public class TrimDemo {
    public static void main(String[] args) {
        String line = "   hello         ";
        System.out.println(line);
        
        String trim = line.trim();
        System.out.println(trim);
    }
}
```



##### charAt()

返回當前字符串中指定位置上的字符

```java
package string;

/**
 * char charAt(int index)
 * 返回當前字符串中指定位置上的字符
 */
public class CharAtDemo {
    public static void main(String[] args) {
        //            0123456789012345
        String str = "thinking in java";
        //獲取第10個字符
        char c = str.charAt(9);
        System.out.println(c);

    }
}
```

##### startsWith()和endsWith()

判斷當前字符串是否是以給定的字符串開始或結束的。

```java
package string;

/**
 * boolean startsWith(String str)
 * boolean endsWith(String str)
 * 判斷當前字符串是否是以給定的字符串開始或結束的。
 */
public class StartsWithDemo {
    public static void main(String[] args) {
        String line = "http://www.tedu.com";

        boolean starts = line.startsWith("http");
        System.out.println("starts:"+starts);

        boolean ends = line.endsWith(".com");
        System.out.println("ends:"+ends);
    }
}
```

##### toLowerCase()和toUpperCase()

```java
package string;

/**
 * String toUpperCase()
 * String toLowerCase()
 * 將當前字符串中的英文部分轉換為全大寫或全小寫
 */
public class ToUpperCaseDemo {
    public static void main(String[] args) {
        String line = "我愛Java";

        String lower = line.toLowerCase();
        System.out.println(lower);

        String upper = line.toUpperCase();
        System.out.println(upper);

    }
}
```

##### valueOf()

String提供了一組重載的靜態方法:valueOf,作用是將其他類型轉換為String

```java
package string;

/**
 * String提供了一組重載的靜態方法:valueOf
 * 作用是將其他類型轉換為String
 */
public class ValueOfDemo {
    public static void main(String[] args) {
        int a = 123;
        String s1 = String.valueOf(a);
        System.out.println("s1:"+s1);

        double d = 123.123;
        String s2 = String.valueOf(d);
        System.out.println("s2:"+s2);

        String s3 = a+"";//任何內容和字符串鏈接結果都是字符串
        System.out.println("s3:"+s3);

    }
}
```