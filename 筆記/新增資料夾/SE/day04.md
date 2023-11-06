# day04

## 集合與數組的轉換

### 集合轉換為數組

Collection提供了一個方法:**toArray**,可以將當前集合轉換為一個數組

```java
package collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 集合轉換為數組
 * Collection提供了方法toArray可以將當前集合轉換為一個數組
 */
public class CollectionToArrayDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        list.add("five");
        System.out.println(list);

//        Object[] array = list.toArray();
        /*
            重載的toArray方法要求傳入一個數組，內部會將集合所有元素存入該數組
            後將其返回（前提是該數組長度>=集合的size）。如果給定的數組長度不足，
            則方法內部會自行根據給定數組類型創建一個與集合size一致長度的數組並
            將集合元素存入後返回。
         */
        String[] array = list.toArray(new String[list.size()]);
        System.out.println(array.length);
        System.out.println(Arrays.toString(array));
    }
}
```



### 數組轉換為List集合

數組的工具類Arrays提供了一個靜態方法**asList()**,可以將一個數組轉換為一個List集合

```java
package collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 數組轉換為List集合
 * 數組的工具類Arrays提供了一個靜態方法asList，可以將數組轉換為一個List集合。
 */
public class ArrayToListDemo {
    public static void main(String[] args) {
        String[] array = {"one","two","three","four","five"};
        System.out.println(Arrays.toString(array));
        List<String> list = Arrays.asList(array);
        System.out.println(list);

        list.set(1,"six");
        System.out.println(list);
        //數組跟著改變了。注意:對數組轉換的集合進行元素操作就是對原數組對應的操作
        System.out.println(Arrays.toString(array));

        /*
            由於數組是定長的，因此對該集合進行增刪元素的操作是不支持的，會拋出
            異常:java.lang.UnsupportedOperationException
         */
//        list.add("seven");

        /*
            若希望對集合進行增刪操作，則需要自行創建一個集合，然後將該集合元素
            導入。
         */
//        List<String> list2 = new ArrayList<>();
//        list2.addAll(list);
        /*
            所有的集合都支持一個參數為Collection的構造方法，作用是在創建當前
            集合的同時包含給定集合中的所有元素
         */
        List<String> list2 = new ArrayList<>(list);
        System.out.println("list2:"+list2);
        list2.add("seven");
        System.out.println("list2:"+list2);
    }
}
```



### 集合的排序

#### java.util.Collections類

Collections是集合的工具類,里面定義了很多靜態方法用於操作集合.

#### Collections.sort(List list)方法

可以對List集合進行自然排序(從小到大)

```java
package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 集合的排序
 * 集合的工具類:java.util.Collections提供了一個靜態方法sort,可以對List集合
 * 進行自然排序
 */
public class SortListDemo1 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for(int i=0;i<10;i++){
            list.add(random.nextInt(100));
        }
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);
    }
}
```

#### java.util.Collections類

Collections是集合的工具類,里面定義了很多靜態方法用於操作集合.

#### Collections.sort(List list)方法

可以對List集合進行自然排序(從小到大)

```java
package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 集合的排序
 * 集合的工具類:java.util.Collections提供了一個靜態方法sort,可以對List集合
 * 進行自然排序
 */
public class SortListDemo1 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for(int i=0;i<10;i++){
            list.add(random.nextInt(100));
        }
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);
    }
}
```

#### 排序自定義類型元素

```java
package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 排序自定義類型元素
 */
public class SortListDemo2 {
    public static void main(String[] args) {
        List<Point> list = new ArrayList<>();
        list.add(new Point(3,5));
        list.add(new Point(7,9));
        list.add(new Point(1,1));
        list.add(new Point(8,3));
        list.add(new Point(2,6));
        System.out.println(list);
        /*
            編譯不通過的原因:
            Collections.sort(List list)該方法要求集合中的元素類型必須實現接口:
            Comparable,該接口中有一個抽象方法compareTo,這個方法用來定義元素之間比較
            大小的規則.所以只有實現了該接口的元素才能利用這個方法比較出大小進而實現排序
            操作.
         */
        Collections.sort(list);//編譯不通過 compare比較  comparable可以比較的
        System.out.println(list);
    }
}
```

實際開發中,我們並不會讓我們自己定義的類(如果該類作為集合元素使用)去實現Comparable接口,因為這對我們的程序有**侵入性**.

侵入性:當我們調用某個API功能時,其要求我們為其修改其他額外的代碼,這個現象就是侵入性.侵入性越強的API越不利於程序的後期可維護性.應當盡量避免.



#### 重載的Collections.sort(List list,Comparator c)方法

```java
package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 排序自定義類型元素
 */
public class SortListDemo2 {
    public static void main(String[] args) {
        List<Point> list = new ArrayList<>();
        list.add(new Point(3,5));
        list.add(new Point(7,9));
        list.add(new Point(1,1));
        list.add(new Point(8,3));
        list.add(new Point(2,6));
        System.out.println(list);
        /*
            編譯不通過的原因:
            Collections.sort(List list)該方法要求集合中的元素類型必須實現接口:
            Comparable,該接口中有一個抽象方法compareTo,這個方法用來定義元素之間比較
            大小的規則.所以只有實現了該接口的元素才能利用這個方法比較出大小進而實現排序
            操作.

            當我們調用某個API時,它反過來要求我們為其修改其他額外的代碼時就是侵入性.
            侵入性不利於程序後期的維護,應當在實際開發中盡量避免.
         */
//        Collections.sort(list);//編譯不通過 compare比較  comparable可以比較的

        /*
            Collections.sort(List list,Comparator c)
            重載的sort方法要求我們再傳入一個Comparator"比較器",該比較器用來為集合元素
            臨時定義一種比較規則,從而將List集合中的元素通過該比較器比較大小後進行排序.
            Comparator是一個接口,實際應用中我們需要實現該接口為集合元素提供比較規則.
         */
        Comparator<Point> c = new Comparator<Point>() {
            /**
             * compare方法用來定義兩個參數o1,o2的大小關系
             * 返回值用來表示o1與o2的大小關系
             * 當返回值>0時,應當表示的含義是o1>o2
             * 當返回值<0時,表示o1<o2
             * 當返回值=0時,表示o1與o2相等
             */
            public int compare(Point o1, Point o2) {
                int olen1 = o1.getX()*o1.getX()+o1.getY()*o1.getY();
                int olen2 = o2.getX()*o2.getX()+o2.getY()*o2.getY();
                return olen1-olen2;
            }
        };
        Collections.sort(list,c);
        System.out.println(list);
    }
}
```



##### 最終沒有侵入性的寫法

```java
package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 排序自定義類型元素
 */
public class SortListDemo2 {
    public static void main(String[] args) {
        List<Point> list = new ArrayList<>();
        list.add(new Point(3,5));
        list.add(new Point(7,9));
        list.add(new Point(1,1));
        list.add(new Point(8,3));
        list.add(new Point(2,6));
        System.out.println(list);
        /*
            編譯不通過的原因:
            Collections.sort(List list)該方法要求集合中的元素類型必須實現接口:
            Comparable,該接口中有一個抽象方法compareTo,這個方法用來定義元素之間比較
            大小的規則.所以只有實現了該接口的元素才能利用這個方法比較出大小進而實現排序
            操作.

            當我們調用某個API時,它反過來要求我們為其修改其他額外的代碼時就是侵入性.
            侵入性不利於程序後期的維護,應當在實際開發中盡量避免.
         */
//        Collections.sort(list);//編譯不通過 compare比較  comparable可以比較的

        /*
            Collections.sort(List list,Comparator c)
            重載的sort方法要求我們再傳入一個Comparator"比較器",該比較器用來為集合元素
            臨時定義一種比較規則,從而將List集合中的元素通過該比較器比較大小後進行排序.
            Comparator是一個接口,實際應用中我們需要實現該接口為集合元素提供比較規則.
         */
//        Comparator<Point> c = new Comparator<Point>() {
//            /**
//             * compare方法用來定義兩個參數o1,o2的大小關系
//             * 返回值用來表示o1與o2的大小關系
//             * 當返回值>0時,應當表示的含義是o1>o2
//             * 當返回值<0時,表示o1<o2
//             * 當返回值=0時,表示o1與o2相等
//             */
//            public int compare(Point o1, Point o2) {
//                int olen1 = o1.getX()*o1.getX()+o1.getY()*o1.getY();
//                int olen2 = o2.getX()*o2.getX()+o2.getY()*o2.getY();
//                return olen1-olen2;
//            }
//        };
//        Collections.sort(list,c);
        
        Collections.sort(list,new Comparator<Point>() {
            public int compare(Point o1, Point o2) {
                int olen1 = o1.getX()*o1.getX()+o1.getY()*o1.getY();
                int olen2 = o2.getX()*o2.getX()+o2.getY()*o2.getY();
                return olen1-olen2;
            }
        });
        System.out.println(list);
    }
}
```



#### 排序字符串

java中提供的類,如:String,包裝類都實現了Comparable接口,但有時候這些比較規則不能滿足我們的排序需求時,同樣可以臨時提供一種比較規則來進行排序.

```java
package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortListDemo3 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
//        list.add("Tom");
//        list.add("jackson");
//        list.add("rose");
//        list.add("jill");
//        list.add("ada");
//        list.add("hanmeimei");
//        list.add("lilei");
//        list.add("hongtaoliu");
//        list.add("Jerry");

        list.add("傳奇老師");
        list.add("王克晶");
        list.add("劉桑");
        System.out.println(list);
        
        //按照字符多少排序
//        Collections.sort(list);
//        Collections.sort(list, new Comparator<String>() {
//            public int compare(String o1, String o2) {
////                return o1.length()-o2.length();
//                return o2.length()-o1.length();//反過來減就是降序
//            }
//        });

        Collections.sort(list,(o1,o2)->o2.length()-o1.length());
        System.out.println(list);
    }
}
```

### lambda表達式

Lambda表達式-JDK8之後推出的新特性

* 語法:

  ```java
  (參數列表)->{
  	方法體
  }
  ```

* 當使用匿名內部類創建時，如果實現的接口只有一個抽象方法，則可以使用lambda表達

  式代替，使代碼更簡潔優雅。

* 在java中可以使用lambda表達式代替匿名內部類創建所需要實現的接口時，該接口上都有一個注解:**@FunctionalInterface**



#### lambda創建比較器用於排序集合

```java
public class LambdaDemo {
    public static void main(String[] args) {
        //自定義比較器的匿名內部類寫法
        Comparator<String> c1 = new Comparator<String>() {
            public int compare(String o1, String o2) {
                return o1.length()-o2.length();
            }
        };
        //使用lambda表達式
        //lambda表達式就是省去了匿名內部類創建是接口與方法名部分
        Comparator<String> c2 = (String o1, String o2)->{
                return o1.length()-o2.length();
        };

        //lambda表達式可以忽略參數的類型
        Comparator<String> c3 = (o1,o2)->{
            return o1.length()-o2.length();
        };

        //如果方法中只有一句代碼時，該方法體的"{}"可以忽略不寫
        //如果這句代碼含有return關鍵字時，也要一同忽略return
        Comparator<String> c4 = (o1,o2)->o1.length()-o2.length();


        List<String> list = new ArrayList<>();
        list.add("王克晶");
        list.add("傳奇老師");
        list.add("劉桑");
        //lambda表達式實際上是編譯器認可的，最終會被改回為內部類方式創建
        //源代碼中使用lambda可以更突出重點-原匿名內部類中重寫方法的邏輯。
//        Collections.sort(list,(o1,o2)->o1.length()-o2.length());
        /*
            JDK8之後，List集合自己推出了一個sort方法，可以排序自身元素
            並且需要傳入一個比較器來定義比較規則。
         */
        list.sort((o1,o2)->o1.length()-o2.length());
        System.out.println(list);
    }
}
```



#### 基於lambda表達式的集合遍歷

```java
package collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

/**
 * JDK8之後，java在集合Collection接口中添加了一個用於遍歷集合元素的forEach
 * 方法。可以基於lambda表達式遍歷集合元素。
 */
public class ForEachDemo {
    public static void main(String[] args) {
        Collection<String> c = new ArrayList<>();
        c.add("one");
        c.add("two");
        c.add("three");
        c.add("four");
        c.add("five");
        //新循環方式(叠代器方式)
        for(String e : c){
            System.out.println(e);
        }

        //tips:當lambda中只有一個參數時，參數列表的"()"可以忽略不寫
        c.forEach(e->System.out.println(e));
        /*
            JDK8中出現的lambda表達式的變種寫法:方法引用
            對象::方法
            當lambda表達式的參數與方法體中調用方法的參數一致時
            例如:
            (e)->System.out.println(e);
            那麽就可以寫作:
            System.out::println;
                  對象::方法
            現在以了解為主即可
         */
        c.forEach(System.out::println);


    }
}

```



## File類

File類的每一個實例可以表示硬盤(文件系統)中的一個文件或目錄(實際上表示的是一個抽象路徑)

使用File可以做到:

- 1:訪問其表示的文件或目錄的屬性信息,例如:名字,大小,修改時間等等
- 2:創建和刪除文件或目錄
- 3:訪問一個目錄中的子項

但是File不能訪問文件數據.

```java
public class FileDemo {
    public static void main(String[] args) {
        //使用File訪問當前項目目錄下的demo.txt文件
        /*
            創建File時要指定路徑，而路徑通常使用相對路徑。
            相對路徑的好處在於有良好的跨平台性。
            "./"是相對路徑中使用最多的，表示"當前目錄"，而當前目錄是哪里
            取決於程序運行環境而定，在idea中運行java程序時，這里指定的
            當前目錄就是當前程序所在的項目目錄。
         */
//        File file = new File("c:/xxx/xxx/xx/xxx.txt");
        File file = new File("./demo.txt");
        //獲取名字
        String name = file.getName();
        System.out.println(name);
        //獲取文件大小(單位是字節)
        long len = file.length();
        System.out.println(len+"字節");
        //是否可讀可寫
        boolean cr = file.canRead();
        boolean cw = file.canWrite();
        System.out.println("是否可讀:"+cr);
        System.out.println("是否可寫:"+cw);
        //是否隱藏
        boolean ih = file.isHidden();
        System.out.println("是否隱藏:"+ih);

    }

}
```

###  創建一個新文件

createNewFile()方法，可以創建一個新文件

```java
package file;

import java.io.File;
import java.io.IOException;

/**
 * 使用File創建一個新文件
 */
public class CreateNewFileDemo {
    public static void main(String[] args) throws IOException {
        //在當前目錄下新建一個文件:test.txt
        File file = new File("./test.txt");
        //boolean exists()判斷當前File表示的位置是否已經實際存在該文件或目錄
        if(file.exists()){
            System.out.println("該文件已存在!");
        }else{
            file.createNewFile();//將File表示的文件創建出來
            System.out.println("文件已創建!");
        }

    }
}
```

###  刪除一個文件

delete()方法可以將File表示的文件刪除

```java
package file;

import java.io.File;

/**
 * 使用File刪除一個文件
 */
public class DeleteFileDemo {
    public static void main(String[] args) {
        //將當前目錄下的test.txt文件刪除
        /*
            相對路徑中"./"可以忽略不寫，默認就是從當前目錄開始的。
         */
        File file = new File("test.txt");
        if(file.exists()){
            file.delete();
            System.out.println("文件已刪除!");
        }else{
            System.out.println("文件不存在!");
        }
    }
}
```

###  創建目錄

mkDir():創建當前File表示的目錄

mkDirs():創建當前File表示的目錄，同時將所有不存在的父目錄一同創建

```java
package file;

import java.io.File;

/**
 * 使用File創建目錄
 */
public class MkDirDemo {
    public static void main(String[] args) {
        //在當前目錄下新建一個目錄:demo
//        File dir = new File("demo");
        File dir = new File("./a/b/c/d/e/f");

        if(dir.exists()){
            System.out.println("該目錄已存在!");
        }else{
//            dir.mkdir();//創建目錄時要求所在的目錄必須存在
            dir.mkdirs();//創建目錄時會將路徑上所有不存在的目錄一同創建
            System.out.println("目錄已創建!");
        }
    }
}
```

### 刪除目錄

delete()方法可以刪除一個目錄，但是只能刪除空目錄。

```java
package file;

import java.io.File;

/**
 * 刪除一個目錄
 */
public class DeleteDirDemo {
    public static void main(String[] args) {
        //將當前目錄下的demo目錄刪除
        File dir = new File("demo");
//        File dir = new File("a");
        if(dir.exists()){
            dir.delete();//delete方法刪除目錄時只能刪除空目錄
            System.out.println("目錄已刪除!");
        }else{
            System.out.println("目錄不存在!");
        }
    }
}
```