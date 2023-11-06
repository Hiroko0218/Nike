# day05

## File類(續)

###  訪問一個目錄中的所有子項

listFiles方法可以訪問一個目錄中的所有子項

```java
package file;

import java.io.File;

/**
 * 訪問一個目錄中的所有子項
 */
public class ListFilesDemo1 {
    public static void main(String[] args) {
        //獲取當前目錄中的所有子項
        File dir = new File(".");
        /*
            boolean isFile()
            判斷當前File表示的是否為一個文件
            boolean isDirectory()
            判斷當前File表示的是否為一個目錄
         */
        if(dir.isDirectory()){
            /*
                File[] listFiles()
                將當前目錄中的所有子項返回。返回的數組中每個File實例表示其中的一個子項
             */
            File[] subs = dir.listFiles();
            System.out.println("當前目錄包含"+subs.length+"個子項");
            for(int i=0;i<subs.length;i++){
                File sub = subs[i];
                System.out.println(sub.getName());
            }
        }
    }
}
```

###  獲取目錄中符合特定條件的子項

重載的listFiles方法:File[] listFiles(FileFilter)

該方法要求傳入一個文件過濾器，並僅將滿足該過濾器要求的子項返回。

```java
package file;

import java.io.File;
import java.io.FileFilter;

/**
 * 有條件的獲取一個目錄中的子項
 */
public class ListFilesDemo2 {
    public static void main(String[] args) {
        //獲取當前目錄下的所有文本文件(文件名是以".txt"結尾的)
        File dir = new File(".");
        if(dir.isDirectory()){
//            FileFilter fileFilter = new FileFilter() {
//                public boolean accept(File f) {
//                    return f.getName().endsWith(".txt");
//                }
//            };
//            /*
//                重載的listFiles方法要求傳入一個文件過濾器
//                該方法會將File對象表示的目錄中所有滿足過濾器條件的子項返回
//             */
//            File[] subs = dir.listFiles(fileFilter);


            File[] subs = dir.listFiles(f->f.getName().endsWith(".txt"));

            for(File sub : subs){
                System.out.println(sub.getName());
            }
        }
    }
}
```

  

## JAVA IO

### 基本概念

I/O 這里的I和O指的是輸入與輸出

- 輸入:Input	  用來讀取數據的
- 輸出:Output   用來寫出數據的

### 流的概念

java將輸入與輸出比喻為"流"，英文:**Stream**.

就像生活中的"電流","水流"一樣,它是以同一個方向順序移動的過程.只不過這里流動的是字節(2進制數據).所以在IO中有輸入流和輸出流之分,我們理解他們是連接程序與另一端的"管道",用於獲取或發送數據到另一端.

![image-20230406175608460](images\image-20230406175608460.png)

### 超類

- **java.io.InputStream**是所有字節輸入流的超類，里面定義了所有字節輸入流都必須具備的讀取字節的方法

  - int read()

    讀取一個字節，以int形式返回，該int值的”低八位”有效，若返回值為-1則表示EOF

    

  - int read(byte[] data)

    嘗試最多讀取給定數組的length個字節並存入該數組，返回值為實際讀取到的字節量。

- **java.io.OutputStream**是所有字節輸出流的超類，里面定義了所有字節輸出流都必須具備的寫出字節的方法

  - void write(int d)

    寫出一個字節,寫的是給定的int的”低八位”

    

  - void write(byte[] data)

    將給定的字節數組中的所有字節全部寫出

    

  - void write(byte[] data,int offset,int len)

    將給定的字節數組中從offset處開始的連續len個字節寫出

### 文件流

#### 概念

文件流是用來鏈接我們的程序與文件之間的"管道",用來讀寫文件數據的流。

文件流分為

- 文件輸入流java.io.FileInputStream:讀取文件數據的流
- 文件輸出流java.io.FileOutputStream:寫入文件數據的流
- 文件流是繼承自InputStream和OutputStream

#### 文件輸出流

**java.io.FileOutputStream**使用文件輸出流向文件中寫入數據

##### 構造器

```java
FileOutputStream(String path)
創建文件輸出流對指定的path路徑表示的文件進行寫操作，如果該文件不存在則將其創建出來

FileOutputStream(File file)
創建文件輸出流對指定的file對象表示的文件進行寫操作，如果該文件不存在則將其創建出來
```

##### 例

```java
package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * JAVA IO
 *
 */
public class FOSDemo {
    public static void main(String[] args) throws IOException {
        /*
            FileOutputStream(String path)
            FileOutputStream(File file)
         */
        //向當前項目目錄下的文件fos.dat中寫入數據
        FileOutputStream fos = new FileOutputStream("./fos.dat");
//        File file = new File("./fos.dat");
//        FileOutputStream fos = new FileOutputStream(file);
        /*
            void write(int d)
            用來向文件中寫入1個字節

            計算機底層只有2進制。1和0

                        8421
         0000 0         0001    1
         0001 1         0010    2
         0010 2         0100    4
         0011 3         1000    8
         0100 4
         0101 5         1110    14
         0110 6
         0111 7        01111111  +127       1字節  1byte
         1000 8        10000000  -
         1001 9
         1010 10
         1011 11
         1100 12
         1101 13
         1110 14
         1111 15


         write方法會將給定的int值對應的2進制的"低八位"寫出
         fos.write(1)

         int型1的2進制:
         00000000 00000000 00000000 00000001
                                    ^^^^^^^^
                                    寫出的數據
         fos.dat文件中內容:
         00000001

         write(2)
         2的2進制
         00000000 00000000 00000000 00000010
                                    ^^^^^^^^
                                    寫出的數據

         fos.dat文件中內容:
         00000001 00000010
         */
        fos.write(1);
        fos.write(2);

        //當IO操作完畢後要關閉
        fos.close();


    }
}


```

#### 文件輸入流

**java.io.FileInputStream**使用文件輸入流向從文件中**讀取**數據

##### 構造器

```java
FileInputStream(String path)
基於給定的路徑對應的文件創建文件輸入流
    
FileInputStream(File file)
基於給定的File對象所表示的文件創建文件輸入流    
```

##### 例

```java
package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 文件輸入流
 * 用於從文件中讀取字節的流
 */
public class FISDemo {
    public static void main(String[] args) throws IOException {
        /*
            FileInputStream(String path)
            FileInputStream(File file)
         */
        FileInputStream fis = new FileInputStream("fos.dat");
        /*
            int read()
            讀取1個字節，以int形式返回該字節內容。int值只有"低八位"有數據，高24位
            全部補0.
            有一個特殊情況:如果返回的int值為整數-1，則表示EOF。
            EOF:end of file 文件末尾

            fos.dat文件數據
            00000001 00000010

            第一次調用:
            int d = fis.read();

            00000001 00000010
            ^^^^^^^^
            讀取的字節

            返回值d的二進制樣子:
            00000000 00000000 00000000 00000001
            |-----自動補充的24個0------| 讀取的字節


            第二次調用:
            d = fis.read();

            00000001 00000010
                     ^^^^^^^^
                     讀取的字節

            返回值d的二進制樣子:
            00000000 00000000 00000000 00000010
            |-----自動補充的24個0------| 讀取的字節
            
            
            第三次調用:
            d = fis.read();

            00000001 00000010
                              ^^^^^^^^
                              文件末尾了

            返回值d的二進制樣子:
            11111111 11111111 11111111 11111111
            |----------32位2進制都是1-----------|
         */
        int d = fis.read();
        System.out.println(d);//1
        d = fis.read();
        System.out.println(d);//2
        d = fis.read();//文件只有2個字節，因此第三次讀取已經是文件末尾EOF
        System.out.println(d);//-1

        fis.close();
    }
}

```



#### 文件覆制

覆制文件的原理就是使用文件輸入流從原文件中陸續讀取出每一個字節，然後再使用文件輸出流將字節陸續寫入到另一個文件中完成的。



##### 示例

第一次讀取

![image-20230407095006725](C:\Users\TEACHER\IdeaProjects\JSD2303_SE\筆記\image-20230407095006725.png)

第二次讀取

![image-20230407095039597](C:\Users\TEACHER\IdeaProjects\JSD2303_SE\筆記\image-20230407095039597.png)

第三次讀取

![image-20230407095146797](C:\Users\TEACHER\IdeaProjects\JSD2303_SE\筆記\image-20230407095146797.png)

循環進行上述操作，直到某次fis.read()方法返回值為-1，表示讀取到了文件末尾，那麽就不再寫出即可。



##### 例

```java
package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件的覆制
 */
public class CopyDemo {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("image.png");
        FileOutputStream fos = new FileOutputStream("image_cp.png");

        int d;
//        while(true){
//            d = fis.read();
//            if(d==-1){
//                break;
//            }
//            fos.write(d);
//        }

        while((d = fis.read())!= -1){
            fos.write(d);
        }

        System.out.println("覆制完畢!");
        fis.close();
        fos.close();
    }
}

```



##### 效率問題

上述案例在覆制文件時的讀寫效率是很低的。因為硬盤的特性，決定著硬盤的讀寫效率差，而單字節讀寫就是在頻繁調用硬盤的讀寫，從而產生了"木桶效應"。

為了解決這個問題，我們可以采取使用塊讀寫的方式來覆制文件，減少硬盤的實際讀寫的次數，從而提高效率。

##### 塊讀寫

- 塊讀:一次性讀取一組字節的方式

  InputStream中定義了塊讀的方法

  ```java
  int read(byte[] data)
  一次性讀取給定字節數組總長度的字節量並存入到該數組中。
  返回值為實際讀取到的字節數。如果返回值為-1表示本次沒有讀取到任何字節已經是流的末尾了
  ```

  

- 塊寫:一次性寫出一組字節的方式

  OutputStream中定義了塊寫的方法

  ```java
  void write(byte[] data)
  一次性將給定數組中所有字節寫出    
  void write(byte[] data,int offset,int len)
  一次性將data數組中從下標offset處開始的連續len個字節一次性寫出    
  ```

  

##### 例

改為塊讀寫形式後，覆制效率得到了明顯的提升

```java
package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 提高每次讀寫的數據量減少讀寫次數，可以提高讀寫效率
 *
 * 塊讀取:一次性讀取一組字節的方式
 * 塊寫:一次性寫出一組字節
 */
public class CopyDemo2 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("image.png");
        FileOutputStream fos = new FileOutputStream("image_cp2.png");
        /*
            在InputStream中定義了塊讀取的方法
            int read(byte[] data)
            一次性讀取給定字節數組總長度的字節量並存入到該數組中。
            返回值為實際讀取到的字節數。如果返回值為-1表示本次沒有讀取到任何字節已經是流的末尾了


            文件內容(6字節):
            00110011 11001100 10101010 01010101 11110000 00001111


            byte[] data = new byte[4];//4個字節的數組
            data:{00000000,00000000,00000000,00000000} 2進制表示

            第一次調用:int d = fis.read(data);
            一次性從文件中讀取4(data數組的長度為4)個字節
            00110011 11001100 10101010 01010101 11110000 00001111
            ^^^^^^^^ ^^^^^^^^ ^^^^^^^^ ^^^^^^^^
            |----------讀取的數據---------------|

            data:{00110011,11001100,10101010,01010101}
                  數組里的4個字節為本次讀取到的全部數據
            d:4  返回值d為整數4，表示本次實際讀取到了4個字節


            第二次調用:d = fis.read(data);
            一次性從文件中讀取4(data數組的長度為4)個字節
            00110011 11001100 10101010 01010101 11110000 00001111 文件末尾了
                                                ^^^^^^^^ ^^^^^^^^ ^^^^^^^^ ^^^^^^^^
                                               |---讀取的數據-----|

            data:{11110000,00001111,10101010,01010101}
                 |-本次實際讀取字節-| |---舊數據-------|
            d:2  返回值d為整數2，表示本次實際讀取到了2個字節



             第二次調用:d = fis.read(data);
            一次性從文件中讀取4(data數組的長度為4)個字節
            00110011 11001100 10101010 01010101 11110000 00001111 文件末尾
                                                                  ^^^^^^^^ ^^^^^^^^ ^^^^^^^^ ^^^^^^^^
                                                                  已經是文件末尾

            data:{11110000,00001111,10101010,01010101}
                 |-------------舊數據-----------------|
            d:-1  返回值d為整數-1，表示已經是末尾了，本次沒有讀取任何數據



            OutputStream中定義了塊寫方法
            void write(byte[] data)
            一次性將給定數組中所有字節寫出
         */
        /*
            00000000  1byte  1字節
            1024byte  1kb
            1024kb    1mb
            1024mb    1gb
            1024gb    1tb
            1024tb    1pb
         */
        byte[] data = new byte[1024*10];//10kb
        int d;//記錄每次實際讀取的數據量
        long start = System.currentTimeMillis();
        while( (d = fis.read(data)) !=-1){
            fos.write(data);
        }
        long end = System.currentTimeMillis();
        System.out.println("覆制完畢!耗時:"+(end-start)+"ms");
        fis.close();
        fos.close();
    }
}

```

##### 問題

速度問題解決了，但是覆制後的文件比原文件大一些。這是文件不一定是10240的倍數，這會導致最後一次讀取時是讀不夠10240的字節數的，那麽data數組中就不是所有數據都是新數據了。此時如果在寫出時將data數組所有內容寫出就會出現文件最後多出很多舊的數據。

##### 示例

**第一次操作**

![image-20230407111636835](images\image-20230407111636835.png)

**第二次操作**

![image-20230407111702774](images\image-20230407111702774.png)

**第三次讀取操作**

![image-20230407111726791](images\image-20230407111726791.png)

**第四次操作**

![image-20230407111826973](images\image-20230407111826973.png)

##### 解決辦法

使用OutputStream的另一個塊寫操作

```java
void write(byte[] data,int offset,int len)
將給定數組data從offset處開始的連續len個字節一次性寫出
```

##### 例

```java
package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 提高每次讀寫的數據量減少讀寫次數，可以提高讀寫效率
 *
 * 塊讀取:一次性讀取一組字節的方式
 * 塊寫:一次性寫出一組字節
 */
public class CopyDemo2 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("image.png");
        FileOutputStream fos = new FileOutputStream("image_cp2.png");     
        byte[] data = new byte[1024*10];//10kb
        int d;//記錄每次實際讀取的數據量
        long start = System.currentTimeMillis();
        while( (d = fis.read(data)) !=-1){
            fos.write(data,0,d);
        }
        long end = System.currentTimeMillis();
        System.out.println("覆制完畢!耗時:"+(end-start)+"ms");
        fis.close();
        fos.close();
    }
}

```



#### 寫出文本數據

文件中只能保存2進制信息，因此我們要想寫出文本數據，需要先將字符串轉換為2進制。

##### 文字編碼

String提供了將字符串轉換為一組字節的方法

```java
byte[] getBytes(Charset cs)
將當前字符串按照指定的字符集轉換為一組字節
    
例如:
String line = "在小小的花園里面";
byte[] data = line.getBytes(StandardCharsets.UTF_8);//將字符串按照UTF-8編碼轉換為一組字節
```

##### 寫入文本文件

```java
package io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 寫出文本數據
 */
public class WriteStringDemo {
    public static void main(String[] args) throws IOException {
         //向文件fos1.txt中寫入一行中文
        FileOutputStream fos = new FileOutputStream("fos1.txt");
        String line = "在小小的花園里面";
        byte[] data = line.getBytes(StandardCharsets.UTF_8);
        fos.write(data);

        fos.write("，挖呀挖呀挖~".getBytes(StandardCharsets.UTF_8));

        System.out.println("寫出完畢!");
        fos.close();

    }
}

```

#### 讀取文本數據

先將文件中的字節讀取出來，然後再將這些字節按照對應的字符集轉換為字符串即可

##### 文本解碼

String的構造器提供了對字節解碼為字符串的操作

```java
String(byte[] data,Charset cn)
將data數組中的所有字節按照指定的字符集轉換為字符串
```

##### 例

```java
package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 讀取文本數據
 */
public class ReadStringDemo {
    public static void main(String[] args) throws IOException {
        /*
            1:先從fos.txt中讀取所有的字節
            2:再將這些字節轉換為字符串
         */
        //1
        File file = new File("fos.txt");
        long len = file.length();//文件名長度

        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int)len];//創建一個與文件長度等長的字節數組
        fis.read(data);//一口氣將文件所有字節讀入到data數組中(塊讀)

        //2將data數組中所有字節按照UTF-8編碼還原為字符串
        String line = new String(data, StandardCharsets.UTF_8);
        System.out.println(line);

        fis.close();
    }
}

```



## 總結:

### File類

File類的每一個實例可以表示硬盤(文件系統)中的一個文件或目錄(實際上表示的是一個抽象路徑)

使用File可以做到:

- 1:訪問其表示的文件或目錄的屬性信息,例如:名字,大小,修改時間等等
- 2:創建和刪除文件或目錄
- 3:訪問一個目錄中的子項

### 常用構造器:

- File(String pathname)
- File(File parent,String name)可參考文檔了解

### 常用方法:

- length()：返回一個long值，表示占用的磁盤空間，單位為字節。
- canRead()：File表示的文件或目錄是否可讀
- canWrite()：File表示的文件或目錄是否可寫
- isHidden()：File表示的文件或目錄是否為隱藏的
- createNewFile()：創建一個新文件，如果指定的文件所在的目錄不存在會拋出異常java.io.FileNotFoundException
- mkdir：創建一個目錄
- mkdirs：創建一個目錄，並且會將所有不存在的父目錄一同創建出來，推薦使用。
- delete()：刪除當前文件或目錄，如果目錄不是空的則刪除失敗。
- exists()：判斷File表示的文件或目錄是否真實存在。true:存在 false:不存在
- isFile()：判斷當前File表示的是否為一個文件。
- isDirectory()：判斷當前File表示的是否為一個目錄
- listFiles()：獲取File表示的目錄中的所有子項
- listFiles(FileFilter filter)：獲取File表示的目錄中滿足filter過濾器要求的所有子項



### JAVA IO必會概念:

- java io可以讓我們用標準的讀寫操作來完成對不同設備的讀寫數據工作.
- java將IO按照方向劃分為輸入與輸出,參照點是我們寫的程序.
- **輸入**:用來讀取數據的,是從外界到程序的方向,用於獲取數據.
- **輸出**:用來寫出數據的,是從程序到外界的方向,用於發送數據.

java將IO比喻為"流",即:stream. 就像生活中的"電流","水流"一樣,它是以同一個方向順序移動的過程.只不過這里流動的是字節(2進制數據).所以在IO中有輸入流和輸出流之分,我們理解他們是連接程序與另一端的"管道",用於獲取或發送數據到另一端.

**因此流的讀寫是順序讀寫的，只能順序向後寫或向後讀，不能回退。**

##### Java定義了兩個超類(抽象類):

- **java.io.InputStream**:所有字節輸入流的超類,其中定義了讀取數據的方法.因此將來不管讀取的是什麽設備(連接該設備的流)都有這些讀取的方法,因此我們可以用相同的方法讀取不同設備中的數據

  ```
  常用方法:
  
  int read()：讀取一個字節，返回的int值低8位為讀取的數據。如果返回值為整數-1則表示讀取到了流的末尾
  
  int read(byte[] data)：塊讀取，最多讀取data數組總長度的數據並從數組第一個位置開始存入到數組中，返回值表示實際讀取到的字節量，如果返回值為-1表示本次沒有讀取到任何數據，是流的末尾。
  ```

- **java.io.OutputStream**:所有字節輸出流的超類,其中定義了寫出數據的方法.

  常用方法:

  void write(int d)：寫出一個字節，寫出的是給定的int值對應2進制的低八位。

  void write(byte[] data)：塊寫，將給定字節數組中所有字節一次性寫出。

  void write(byte[]data,int off,int len)：塊寫，將給定字節數組從下標off處開始的連續len個字節一次性寫出。

##### java將流分為兩類:節點流與處理流:

- **節點流**:也稱為**低級流**.

  節點流的另一端是明確的,是實際讀寫數據的流,讀寫一定是建立在節點流基礎上進行的.

- **處理流**:也稱為**高級流**.

  處理流不能獨立存在,必須連接在其他流上,目的是當數據流經當前流時對數據進行加工處理來簡化我們對數據的該操作.

##### 實際應用中,我們可以通過串聯一組高級流到某個低級流上以流水線式的加工處理對某設備的數據進行讀寫,這個過程也成為流的連接,這也是IO的精髓所在.

### 文件流

文件流是一對低級流，**用於讀寫文件的流**。

#### java.io.FileOutputStream文件輸出流，繼承自java.io.OutputStream

#### 常用構造器

##### 覆蓋模式對應的構造器

```
覆蓋模式是指若指定的文件存在，文件流在創建時會先將該文件原內容清除。
```

- FileOutputStream(String pathname)：創建文件輸出流用於向指定路徑表示的文件做寫操作

- FileOutputStream(File file)：創建文件輸出流用於向File表示的文件做寫操作。

  注:如果寫出的文件不存在文件流自動創建這個文件，但是如果該文件所在的目錄不存在會拋出異常:java.io.FileNotFoundException

##### 追加寫模式對應的構造器

```
追加模式是指若指定的文件存在，文件流會將寫出的數據陸續追加到文件中。
```

- FileOutputStream(String pathname,boolean append)：如果第二個參數為true則為追加模式，false則為覆蓋模式
- FileOutputStream(File file,boolean append)：同上

##### 常用方法:

```
void write(int d)：向文件中寫入一個字節，寫入的是int值2進制的低八位。

void write(byte[] data)：向文件中塊寫數據。將數組data中所有字節一次性寫入文件。

void write(byte[] data,int off,int len)：向文件中快寫數據。將數組data中從下標off開始的連續len個字節一次性寫入文件。
```

#### java.io.FileInputStream文件輸入流，繼承自java.io.InputStream

##### 常用構造器

FileInputStream(String pathname) 創建讀取指定路徑下對應的文件的文件輸入流，如果指定的文件不存在則會拋出異常java.io.FileNotFoundException

FileInputStream(File file) 創建讀取File表示的文件的文件輸入流，如果File表示的文件不存在則會拋出異常java.io.IOException。

##### 常用方法

```
int read()：從文件中讀取一個字節，返回的int值低八位有效，如果返回的int值為整數-1則表示讀取到了文件末尾。

int read(byte[] data)：塊讀數據，從文件中一次性讀取給定的data數組總長度的字節量並從數組第一個元素位置開始存入數組中。返回值為實際讀取到的字節數。如果返回值為整數-1則表示讀取到了文件末尾。
```