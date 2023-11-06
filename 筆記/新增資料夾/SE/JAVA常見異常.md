# JAVA常見異常

## Java.io.NullPointerException

- null 空的，不存在的
- NullPointer 空指針

空指針異常，該異常出現在我們操作某個對象的屬性或方法時，如果該對象是null時引發。

```java
String str = null;
str.length();//空指針異常
```

上述代碼中引用類型變量str的值為null，此時不能通過它調用字符串的方法或引用屬性，否則就會引發空指針異常。

解決辦法:

找到為什麽賦值為null，確保該對象的值不能為null再操作屬性或方法即可。



## java.lang.NumberFormatException: For input string: "xxxxx"

- Number 數字
- Format 格式

數字格式異常，該異常通常出現在我們使用包裝類將一個字符串解析為對應的基本類型時引發。

```java
String line = "123.123";//小數不能轉換為整數!
int d = Integer.parseInt(line);//拋出異常NumberFormatException
System.out.println(d);
```

上述代碼中由於line的字符串內容是"123.123".而這個數字是不能通過包裝類Integer解析為一個整數因此出現該異常。注:非數字的字符出在解析時也會出現該異常。

解決辦法:

確保解析的字符串正確表達了基本類型可以保存的值

```java
String line = "123";
int d = Integer.parseInt(line);
System.out.println(d);//123
```



## java.lang.StringIndexOutOfBoundsException

- index 索引，下標
- Bounds 邊界
- OutOfBounds 超出了邊界

字符串下標越界異常。該異常通常出現在String對應的方法中，當我們指定的下標小於0或者大於等於字符串的長度時會拋出該異常。

```java
String str = "thinking in java";
char c = str.charAt(20);//出現異常
System.out.println(c);
```

解決辦法:

指定下標時的範圍應當在>=0並且<=字符串的長度。





## java.io.InvalidClassException

- Invalid 無效的
- Class 類

無效的類異常，該異常出現在使用java.io.ObjectInputStream在進行對象反序列化時在readObject()方法中拋出。這通常是因為反序列化的對象版本號與該對象所屬類現有的版本號不一致導致的。

可以通過在類上使用常量:

```java
static final long serialVersionUID = 1L;
```

來固定版本號，這樣序列化的對象就可以進行反序列化了。

JAVA建議我們實現Serializable接口的類主動定義序列化版本號,若不定義編譯器會在編譯時
根據當前類結構生成版本號,但弊端是只要這個類內容發生了改變,那麽再次編譯時版本號就會改變,直接的後果就是之前序列化的對象都無法再進行反序列化.

如果自行定義版本號,那麽可以在改變類內容的同時不改變版本號,這樣一來,反序列化以前的
對象時對象輸入流會采取兼容模式,即:當前類的屬性在反序列化的對象中還存在的則直接還原,不存在的就是用該屬性的默認值

出現該異常的解決辦法:

1. 首先使用上述常量固定版本號
2. 重新序列化對象(將對象通過ObjectOutputStream重新序列化並寫出)
3. 再進行反序列化即可

需要注意，之前沒有定義序列化版本號時序列化後的對象都無法再反序列化回來，所以若寫入了文件，可將之前的那些文件都刪除，避免讀取即可。

## java.io.NotSerializableException

- NotSerializable 不能序列化

不能序列化異常，該異常通常出現在我們使用java.io.ObjectOutputStream進行對象序列化(調用writeObject)時。原因時序列化的對象所屬的類沒有實現java.io.Serializable接口導致

出現該異常的解決辦法:

將序列化的類實現該接口即可



## java.io.UnsupportedEncodingException

- Unsupported 不支持的
- Encoding字符集

不支持的字符集異常，該異常通常出現在使用字符串形式指定字符集名字時，猶豫字符集名字拼寫錯誤導致。例如

```java
PrintWriter pw = new PrintWriter("pw.txt", "UFT-8");
```

上述代碼中，字符集拼寫成"UFT-8"就是拼寫錯誤。

常見的字符集名字:

- GBK:我國的國標編碼，其中英文1個字節，中文2字節
- UTF-8:unicode的傳輸編碼，也稱為萬國碼。其中英文1字節，中文3字節。
- ISO8859-1:歐中的字符集，不支持中文。



## java.io.FileNotFoundException

- File 文件
- NotFound 沒有找到

文件沒有找到異常，該異常通常出現在我們使用文件輸入流讀取指定路徑對應的文件時出現

```java
FileInputStream fis = new FileInputStream("f1os.dat");
```

上述代碼如果指定的文件f1os.dat文件不在當前目錄下，就會引發該異常：

java.io.FileNotFoundException: f1os.dat (系統找不到指定的文件。)

注:

抽象路徑"f1os.dat"等同於"./f1os.dat"。因此該路徑表示當前目錄下應當有一個名為f1os.dat的文件。



還經常出現在文件輸出流寫出文件時，指定的路徑無法將該文件創建出來時出現

```java
FileOutputStream fos = new FileOutputStream("./a/fos.dat");
```

上述代碼中，如果當前目錄下沒有a目錄，那麽就無法在該目錄下自動創建文件fos.dat，此時也會引發這個異常。

其他API上出現該異常通常也是上述類似的原因導致的。

解決辦法:

在讀取文件時，確保指定的路徑正確，且文件名拼寫正確。

在寫出文件時，確保指定的文件所在的目錄存在。





## java.net.ConnectException: Connection refused: connect

- connection 連接
- refused 拒絕

連接異常,連接被拒絕了.這通常是客戶端在使用Socket與遠端計算機建立連接時由於指定的地址或端口無效導致無法連接服務端引起的.



```java
System.out.println("正在連接服務端...");
Socket socket = new Socket("localhost",8088);//這里可能引發異常
System.out.println("與服務端建立連接!");
```



解決辦法:

- 檢查客戶端實例化Socket時指定的地址和端口是否正常
- 客戶端連接前,服務端是否已經啟動了





## java.net.BindException: Address already in use

- bind 綁定
- address 地址
- already 已經
- Address already in use 地址已經被使用了

綁定異常,該異常通常是在創建ServerSocket時指定的服務端口已經被系統其他程序占用導致的.

```java
System.out.println("正在啟動服務端...");
ServerSocket serverSocket = new ServerSocket(8088);//這里可能引發異常
System.out.println("服務端啟動完畢");
```

解決辦法:

- 有可能是重覆啟動了服務端導致的,先將之前啟動的服務端關閉
- 找到該端口被占用的程序,將其進程結束
- 重新指定一個新的服務端口在重新啟動服務端



## java.net.SocketException: Connection reset

- socket 套接字
- net 網絡
- reset 重置

套接字異常,鏈接重置。這個異常通常出現在Socket進行的TCP鏈接時，由於遠端計算機異常斷開(在沒有調用socket.close()的之前直接結束了程序)導致的。

解決辦法:

- 無論是客戶端還是服務端當希望與另一端斷開連接時，應當調用socket.close()方法，此時會進行TCP的揮手斷開動作。
- 這個異常是無法完全避免的，因為無法保證程序在沒有調用socket.close()前不被強制殺死。





## java.lang.InterruptedException

- interrupt 中斷

中斷異常.這個異常通常在一個線程調用了會產生阻塞的方法處於阻塞的過程中,此時該線程的interrupt()方法被調用.那麽阻塞方法會立即拋出中斷異常並停止線程的阻塞使其繼續運行.

例如:

```java
try {
    Thread.sleep(1000);
} catch (InterruptedException e) {
}
```

如果線程t1調用Thread.sleep(1000)處於阻塞的過程中,其他線程調用了t1線程的inerrupt()方法,那麽t1調用的sleep()方法就會立即拋出中斷異常InterruptedException並停止阻塞.



## java.util.NoSuchElementException

- such	這個
- Element	元素

沒有這個元素的異常.該異常通常發生在使用叠代器Iterator遍歷集合元素時由於沒有先通過hasNext()方法判斷存在下一個元素而貿然通過next()獲取下一個元素時產生(當集合所有元素都經過叠代器遍歷一遍後還使用next獲取).

```java
while(it.hasNext()){
            String str = (String)it.next();
			//這里就可能產生NoSuchException異常
            System.out.println(it.next());
        }
```

![image-20220106104254532](image-20220106104254532-16414369783111.png)

上述代碼中循環遍歷時,每次調用hasNext()確定存在下一個元素時,循環里面連續調用過兩次next()方法,這意味著第二次調用next()方法時並沒有判斷是否還存在.所以在最後會出現異常.

解決辦法:

保證每次調用next()方法前都確定hasNext()為true才進行即可.





## java.util.ConcurrentModificationException

Concurrent 並發

Modification 修改

並發修改異常.這個異常也經常出現在使用叠代器遍歷集合時產生.

當我們使用一個叠代器遍歷集合的過程中,通過集合的方法增刪元素時,叠代器會拋出該異常.

```java
while(it.hasNext()){
	//出現ConcurrentModificationException
    String str = (String)it.next();
    if("#".equals(str)){
        c.remove(str);//遍歷過程中不要通過集合方法增或刪元素
    }
    System.out.println(str);
}
```

解決辦法:

使用叠代器提供的remove()方法可以刪除通過next()獲取的元素.

```java
while(it.hasNext()){
            String str = (String)it.next();
            if("#".equals(str)){
//                c.remove(str);
                it.remove();
            }
            System.out.println(str);
        }
```



## java.lang.UnsupportedOperationException

support 支持

unsupported 不支持的

operation 操作

不支持的操作異常.該異常出現在很多的API中.

例如:常出現在我們對數組轉換的集合進行增刪元素操作時拋出.

```java
String[] array = {"one","two","three","four","five"};
System.out.println("array:"+ Arrays.toString(array));
List<String> list = Arrays.asList(array);//將數組轉換為一個List集合
System.out.println("list:"+list);

list.set(0,"six");
System.out.println("list:"+list);
//對該集合的操作就是對原數組的操作
System.out.println("array:"+ Arrays.toString(array));

//由於數組是定長的,因此任何會改變數組長度的操作都是不支持的!
list.add("seven");//UnsupportedOperationException
```