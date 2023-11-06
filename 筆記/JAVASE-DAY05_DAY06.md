### 1. File 類

File 類的每一個實例都可以表示硬盤(文件系統)中的一個文件或者目錄(實際上表示的是一個抽象的路徑)

File 類只能用於表示文件(目錄)的信息（名稱，大小等），換句話說，File 只能訪問文件的相關屬性，不能對文章的內容進行操作。

使用 File 可以做到

1. 訪問其表示的文件或者目錄的屬性信息：例如，名字，大小，修改時間等
2. 創建和刪除文件或者目錄
3. 訪問一個目錄中的子項

  獲取 File 文件的路徑

  ```java
  package cn.tedu.demo;
  
  import java.io.File;
  import java.io.IOException;
  import java.sql.SQLOutput;
  public class FileDemo {
     public static void main(String[] args) throws IOException {
         //使用 File 訪問當前項目目錄下的 demo。txt 文件
        /**
          *創建 File 時要指定路徑，而路徑通常使用相對路徑
          * 相對路徑的好處在於有良好的跨平台性
          * 相對路徑： ./demo.txt
          * 可以使用./開頭表示當前目錄
          * 這個"當前目錄"是哪里取決於程序運行環境而定，在 Idea 中，這里
            就是指
          * 當前程序所在的項目目錄。
          *
          * 絕對路徑：
            /Users/xiujia.zhao/IdeaProjects/demo01/./demo/demo.txt
          * 絕對路徑移植性差
          * 相同操作系統需要路徑存在，不同的操作系統就無法使用
          */
        File file = new File("./demo.txt");
        System.out.println(file.getAbsolutePath()); //查看絕對路徑
        System.out.println(file.getPath()); //查看相對路徑
        System.out.println(file.getParentFile()); //查看上一級路徑
     }
  }
  ```

對文件操作常用方法

- length()：返回一個long 值，表示占用的磁盤空間，單位為字節。
- canRead()：File 表示的文件或目錄是否可讀
- canWrite()：File 表示的文件或目錄是否可寫
- isHidden()：File 表示的文件或目錄是否為隱藏的
- createNewFile()：創建一個新文件，如果指定的文件所在的目錄不存在會拋出異常。java.io.FileNotFoundException
- mkdir：創建一個目錄
- mkdirs：創建一個目錄，並且會將所有不存在的父目錄一同創建出來，推薦使用。demo/a/b/c
- delete()：刪除當前文件或目錄，如果目錄不是空的則刪除失敗。
- exists()：判斷File 表示的文件或目錄是否真實存在。true:存在 false:不存在
- isFile()：判斷當前File 表示的是否為一個文件。
- isDirectory()：判斷當前File 表示的是否為一個目錄
- listFiles()：獲取File 表示的目錄中的所有子項
- listFiles(FileFilter filter)：獲取File 表示的目錄中滿足filter 過濾器要求的所有子項

1. 創建多級文件夾

   ```java
   package cn.tedu.demo;
   
   import java.io.File;
   
   public class MkdirsDemo {
      public static void main(String[] args) {
          File dir = new File("./demo/a/b/c/d/e");
          if(dir.exists()){
              System.out.println("已存在");
          }else{
              System.out.println("創建文件夾");
              /*
               * 如果使用 mkdir 創建多級目錄,會無效
               * 只要是多級目錄必須使用 mkdirs 方法
               * mkdirs 會將所有不存在的父目錄一同創建出來
               */
               dir.mkdirs();
           }
       }
   }
   ```

   2. 刪除多級文件夾
      a. 例如： 刪除 demo/a/b/c/d
      b. 所用方法：
      	i. delete() 刪除文件夾，必須是空文件夾
      	ii. File[] listFiles（） 獲取當前目錄下的所有子項
      c. 思路：
      	i. demo--a
      	ii. 先取出 demo 下的所有子項，然後遍歷刪除a
      a--b
      iii. 再取出 a 下的所有子項，刪除b

      b--c
      iv. 再取出 b 下的所有子項，刪除 c

      C--d
      v. 再取出 c 下的所有子項， 刪除 d

```Java
package cn.tedu.demo;

import java.io.File;

public class DeleteFiles {
		public static void main(String[] args) {
        File dir = new File("./demo");
        deteleDirs(dir);
		}
		public static void deteleDirs(File dir){
        //先獲取當前文件夾下的所有子項
        File [] dirs=dir.listFiles();
        System.out.println("dirs 的長度："+dirs.length);
        //遍歷 dirs 數組，獲取子項
        for (int i = 0; i <dirs.length ; i++) {
            File d=dirs[i];
            System.out.println("路徑:"+d.getPath());
            //再獲取當前文件夾下的所有子項---直接調用 deteleDirs 方法
            deteleDirs(d);
        }
        dir.delete();
    }
}
```

3. 文件過濾器
   - File[] listFiles(FileFilter filter) 該方法在獲取該目錄中子項的過程中利用參數給定的過濾器將滿足其要求的子項返回，其余的忽略。

```java
package cn.tedu.demo;

import java.io.File;
import java.io.FileFilter;

public class FileterDemo {
		public static void main(String[] args) {
				//獲取當前項目下的所有文件
				File dir = new File("./src/cn/tedu");

        //判斷是否是一個文件夾
        if(dir.isDirectory()){
            /*
              FileFilter filter=new FileFilter(){
                  //只獲取以 .java 為後綴的文件
                  @Override
                  public boolean accept(File pathname) {
                      return pathname.getName().endsWith(".java");
                  }
              };
              //獲取當前項目下的所有子項
              //重載的 listFiles 方法要求傳入一個文件過濾器
              //該方法會將 File 對象表示的目錄中所有滿足過濾器條件的子項返回

              File[] dirs = dir.listFiles(filter);
            */

            //使用 Lambda 表達式
            File[] dirs = dir.listFiles(f->f.getName().endsWith(".java"));
            System.out.println(dirs.length);
            for (File sub:dirs) {
                System.out.println(sub.getName());
            }
        }
    }
}
```

### 2. I O 流

I/O 這里的 I 和 O 是指輸入和輸出

- 輸入：Input 用來讀取數據
- 輸出：Output 用來寫出數據

java 將輸入與輸出比喻為"流"，英文:Stream。就像生活中的"電流"，"水流"一樣，它是以同一個方向順序移動的過程。只不過這里流動的是字節(2 進制數據)。所以在IO 中有輸入流和輸出流之分，我們理解他們是連接程序與另一端的"管道"，用於獲取或發送數據到另一端。
![6](/Users/hiroko/Downloads/筆記/images/6.png)

#### IO 流的分類

按方向分為：輸入流和輸出流

- 輸入流：從本地文件讀取數據到內存
- InputStream 是所有字節輸入流的父類，規定了讀取字節的相關方法
- 輸出流：從內存寫出數據到本地文件
- OutputStream 是所有字節輸出流的父類，規定了寫出字節的相關方法

按照使用方式分為：低級流和高級流

- 節點流（低級流）：低級流是直接與底層I/O 設備（如文件、網絡套接字）進行交互的流。它們是基於字節（byte）的，用於讀取和寫入字節數據。低級字節流的常見例子是
- 處理流（高級流）：不能獨立存在,必須連接在其它流上,是對一個已存在的流的連接和封裝，通過所封裝的流的功能調用實現數據讀寫。

按照讀寫單位分為：字節流和字符流

- 字節流：讀寫單位為字節，頂級父類為 InputStream，OutputStream
- 字符流：讀寫單位為字符（所有的字符流都是高級流），頂級父類為 Reader，Writer

Java 定義了兩個父類

- OutputStream:所有字節輸出流的超類,其中定義了寫出數據的方法.

  - void write(int d) 寫出一個字節，寫的是給定的 int 的"低八位"

  - void void write(byte[] data) 將給定的字節數組中的所有字節全部寫出

  - void write(byte[] data,int offset,int len) 將給定的字節數組中從offset 處開始的連續len個字節寫出

- InputStream:所有字節輸入流的超類,其中定義了讀取數據的方法.因此將來不管讀取的是什麽設備(連接該設備的流)都有這些讀取的方法，因此我們可以用相同的方法讀取不同設備中的數據
  - int read（） 一次讀取一個字節，以 int 形式返回，該 int 值的"低八位"有效，如果返回-1，則表示讀到文件的末尾 EOF
  - int read (byte[] data) 嘗試最多讀取給定數組的length 個字節並存入該數組，返回值為實際讀取到的字節量。

#### 文件流

文件流是一對低級流，用於讀寫文件的流。

- 文件輸出流：FileOutputStream 用於寫出文件數據，以字節形式寫出數據(一個字節 8個位)

- 常用構造器：

  覆蓋模式對應的構造器

  覆蓋模式是指若指定的文件存在，文件流在創建時會先將該文件原內容清除。

  - FileOutputStream(String pathname)：創建文件輸出流用於向指定路徑表示的文件做寫操作。
  - FileOutputStream(File file)：創建文件輸出流用於向File 表示的文件做寫操作。
  - 注:如果寫出的文件不存在文件流自動創建這個文件，但是如果該文件所在的目錄不存在會拋出異常:java.io.FileNotFoundException

  追加寫模式對應的構造器

  追加模式是指若指定的文件存在，文件流會將寫出的數據陸續追加到文件中。

  - FileOutputStream(String pathname,boolean append)：如果第二個參數為true 則為追加模式，false 則為覆蓋模式
  - FileOutputStream(File file,boolean append)：同上

- 常用方法

  - void write(int d) 寫出一個字節，寫的是給定的 int 的"低八位"
  - void void write(byte[] data) 將給定的字節數組中的所有字節全部寫出
  - void write(byte[] data,int offset,int len) 將給定的字節數組中從offset 處開始的連續len個字節寫出

```java
package cn.tedu.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class OutputStreamDemo {
		public static void main(String[] args) throws IOException {
				//FileOutputStream("./fos.dat");會判斷文件是否為空，如果為空則會創建這個文件
				FileOutputStream fos = new FileOutputStream("./fos.dat");
        /*
          void write(int d) 用來向文件中寫入 1 字節

          寫入的是 int 值對應的 2 進制的"低八位"

          int--4 字節 1 字節-8 位

          int 型 1 的 2 進制 二進制數的最右邊（低位）表示最小的權值，而最左邊（高位）表示最大的權值。

          fos.write(1)

          00000000 00000000 00000000 00000001

          ^^^^^^^^

          低八位寫出

          fos.dat 文件中的內容 00000001

          fos.write(2)

          2 的 2 進制

          00000000 00000000 00000000 00000010

          ^^^^^^^^

          fos.dat 文件中的內容 00000001 00000010

        */

        fos.write(1);
        fos.write(2);
    }
}
```

*int 型 1 的二進制表示示例：00000000 00000000 00000000 00000001在一個 32 位的 int 數據類型中，使用 32 個比特（ bits）來表示整數值。二進制數的最右邊（低位）表示最小的權值，而最左邊（高位）表示最大的權值。*

- 文件輸入流：FileInputStream 用於讀取文件數據，以字節形式讀取文件
- 常用構造器
  - FileInputStream(String pathname) 創建讀取指定路徑下對應的文件的文件輸入流，如果指定的文件不存在則會拋出異常java.io.FileNotFoundException
  - FileInputStream(File file) 創建讀取File 表示的文件的文件輸入流，如果File 表示的文件不存在則會拋出異常java.io.IOException。
- 常用方法
  - int read（） 一次讀取一個字節，以 int 形式返回，該 int 值的"低八位"有效，如果返回-1.則表示讀到文件的末尾 EOF
  - int read (byte[] data) 嘗試最多讀取給定數組的length 個字節並存入該數組，返回值為實際讀取到的字節量。

```java
package cn.tedu.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class InputStreamDemo {
		public static void main(String[] args) throws IOException {

				FileInputStream fis = new FileInputStream("./fos.dat");
        /*
          fis 對象是按照順序讀取文件內容

          int read() 讀取 1 個字節，以 int 形式返回該字節內容。int 值只
          有"低八位"有數據，高 24 位
          全部補 0.
          fos.dat 中的數據 00000001 00000010
          第一次調用 int d=fis.read()
          00000001 00000010
          ^^^^^^^^
          讀取的字節
          返回值 d 的二進制樣子:
          00000000 00000000 00000000 00000001
          |-----自動補充的 24 個 0------| 讀取的字節
        */
        int d= fis.read(); //1
        System.out.println(d);

        /*
          第二次調用:
          d = fis.read();
          00000001 00000010
          ^^^^^^^^
          讀取的字節
          返回值 d 的二進制樣子:
          00000000 00000000 00000000 00000010
          |-----自動補充的 24 個 0------| 讀取的字節
        */
        d = fis.read(); //2
        System.out.println(d);

        /*
          d = fis.read();
          00000001 00000010
          ^^^^^^^^
          文件末尾了
        */
        d=fis.read();//-1
        System.out.println(d);

        fis.close();
    }
}
```

- int read()和 int read(byte[] data)的區別

|                           | 方法功能                                                     | 方法返回值                                   | 方法參數                                        |
| ------------------------- | ------------------------------------------------------------ | -------------------------------------------- | ----------------------------------------------- |
| int read()                | 一次讀取一個字節，以 int 形式返回                            | 每次讀取到的一個字節<br/>（以 int 形式返回） | 不帶參，默認讀取 1 字節                         |
| int read<br/>(byte[]data) | 嘗試最多讀取給定數組的length 個字<br/>節並存入該數組，<br/>返回值為實際讀取到的字節量 | 實際讀取到的字節量<br/>(以 int 形式返回)     | 自定義字節數組，<br/>每次讀取字節數組長度的數據 |

#### 緩沖流

- BufferedInputStream 緩沖字節輸入流，內部維護著一個緩沖區(字節數組)，使用該流在讀取一個字節時，該流會盡可能多的一次性讀取若幹字節並存入緩沖區，然後逐一的將字節返回，直到緩沖區中的數據被全部讀取完畢，會再次讀取若幹字節。
  - 好處：減少讀取的次數，提高了讀取效率

- BufferedOutputStream 緩沖字節輸出流，緩沖輸出流內部也維護著一個緩沖區，每當我們向該流寫數據時，都會先將數據存入緩沖區，當緩沖區已滿時，緩沖流會將數據一次性全部寫出。
  - 好處：減少寫出的次數，提高了寫出效率

![7](/Users/hiroko/Downloads/筆記/images/7.png)緩沖流和字節數組的區別是都是緩存區，但是一個是自己定義的，可以更改大小，另一個是緩沖流內部維護的。無法更改大小。

- 緩沖區 flush 方法的作用

```java
void flush()
```

使用緩沖輸出流可以提高寫出效率，但是這也存在著一個問題，就是寫出數據缺乏即時性。有時我們需要在執行完某些寫出操作後，就希望將這些數據確實寫出，而非在緩沖區中保存直到緩沖區滿後才寫出。

- 清空緩沖區，將緩沖區中的數據強制寫出

```java
package cn.tedu.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class BufferedOutputStreamDemo {
		public static void main(String[] args) throws IOException {
        BufferedOutputStream bos =new BufferedOutputStream(newFileOutputStream("./fos.dat"));
        String str = "今天是周五了";
        bos.write(str.getBytes());
        /**
          * void flush();
          * 優點:強制將當前緩沖區的字節一次性寫出
          * 同時清空緩沖區,即時的寫出
          * 缺點:實際上也會增加寫出的次數,降低寫出效率
          */
        // bos.flush();
        System.out.println("寫出完畢");
        bos.close(); //close 方法中自動調用了 flush 方法
    }
}
```

