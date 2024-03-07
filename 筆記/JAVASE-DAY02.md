### 2.1 StringBuffer 和 StringBuilder 的區別（需要背）

StringBuffer 和 StringBuilder 是 Java 中用於處理可變字符串的類，它們之間的主要區別在於它們的線程安全性和性能特性。

1. 線程安全性
   ￮  StringBuffer 是線程安全的，它的方法是同步的，可以在多線程環境下安全地使用。這意味著多個線程可以同時訪問和修改同一個 StringBuffer 對象，而不會導致數據不一致的問題。
   ￮  StringBuilder 不是線程安全的，它的方法是非同步的。在多線程環境下使用StringBuilder 需要額外的同步措施，否則可能導致數據不一致的問題。

2. 性能特性
   ￮ StringBuffer 的方法是同步的，這使得它在執行線程安全的操作時會產生一定的性能開銷。因為它的方法會進行同步處理，確保在多線程環境下的數據一致性，但在單線程環境下可能會比較耗費性能。
   ￮ StringBuilder 的方法是非同步的，它沒有線程安全的保證，因此在單線程環境下執行操作時比 StringBuffer 更快速和高效。

3. 注意：
   a. 除了線程安全性和性能特性之外，StringBuffer 和 StringBuilder 的其他方法和用法是相同的，它們都提供了用於修改和操作字符串的一系列方法。
   b. 如果代碼在多線程環境下被訪問或者修改，或者需要線程安全的操作，那麼應該使用StringBuffer
   c. 如果代碼在單線程環境下執行，或者不需要線程安全的操作，並且對性能有較高的要求，那麼應該使用 StringBuilder

### 2.2 包裝類

- 每一個八大基本數據類型都有對應的包裝類
- 在 JDK1.5 之後提出了自動拆裝箱的概念，用於基本數據類型和包裝類之間的相互轉換的過程。
- 拆箱：

​		￮ 拆箱是指將包裝類對象轉換為對應的基本數據類型，同樣，Java 也提供了自動拆箱的功能，也就在需要使		用基本數據類型的地方，可以直接使用包裝類對象，而 Java 會自動將其拆箱為對應的基本數據類型
​		￮ 例如： 將 Integer 對象拆箱為基本數據類型 int

```java
Integer integerObj = 20;
int num = integerObj; // 自動拆箱
```

- 裝箱：

  ​	￮ 裝箱是指將基本數據類型轉換為對應的包裝類對象。Java 提供了自動裝箱的功能，即在需要使用包裝類對		象的地方，可以直接使用基本數據類型，而Java 會自動將其轉換為對應的包裝類對象。

  ​	￮ 例如，將基本數據類型 int 裝箱為 Integer 對象：

```java
int num = 10;
Integer integerObj = num; // 自動裝箱
```

### 2.3 Object

- equals 方法

  ￮ 在 Object 類中默認的 equlas 方法是通過 == 來進行比較的，默認比較的是對象的地址，如果想要比較對象的內容，則需要在自定義類中重寫 equals 方法

- toString 方法

  ￮ 在輸出對象的時候，如果不重寫 toString 方法，則默認輸出的是當前對象的地址，如果需要輸出當前對象中屬性的值，則需要重寫 toString 方法。

### 2.4 正則表達式

正則表達式能夠描述字符串的格式，通常用於驗證字符串內容

- 一個字符

​	[ ] 用於描述單一字符，方括號內部可以定義這個字符的內容，也可以描述一個範圍

![1](/images/1.png)

- 預定義字符


​	" . " 點 在正則表達式中表示任意一個字符除了換行符

​	" \ " 在正則表達式中是轉義字符，當我們需要描述一個已經被正則表達式使用的特殊字符時，我們就可以使用 " \ " 將其轉變為原本的意思

![2](/images/2.png)

- 數量詞


通常我們需要描述的字符串會出現很多重覆出現的元素，但是又不需要嚴格的限制出現的次數時，我們就可以使用 " * "，“ + ” 這些量詞。

​		“ + ” ：表示內容可以連續出現至少 1 次以上

​		“ * ” ：表示內容出現 0~若幹次

​		“ ？”：表示內容出現 0~1 次

​		{ n }：表示內容必須出現 n 次

​		{ n, }：表示內容出現至少 n 次

​		{ n,m }：表示內容出現n-m 次

![3](/images/3.png)

### **2.5** 案例：文本處理器

- 案例描述：


​		￮ 編寫一個程序，用於處理用戶輸入的文本。程序將驗證文本是否符合特定的格式要求，並進行相應的處理和轉換。

- 驗證規則：


​		￮ 驗證文本是否包含字母和數字

​			▪ 如果包含字母和數字，則將字符串倒序輸出

​			▪ 如果不包含字母和數字，則將元音字母替換為星號，並輸出替換後的字符串

​			▪ 最終將文本拆分為單詞，並輸出每個單詞

​		￮ 步驟

​			▪ 先接收用戶所輸入的數據 String input

​			▪ 驗證是否包含字母和數字 String 驗證正則的方法 mathchs(" [a-zA-Z0-9]*")

​					• true ---倒序輸出 StringBuilder--reverse()

​					• false---將數字（本身是元音字母，為了簡單需求，先寫為數字）替換為星號 replace() 不支持正則的								　使用，replaceAll()支持正則的使用

​							￮ input.replaceAll("\\d","*");

​			▪ 將文本拆分為單獨的字符

​					　• String[] words=input.split("\\s");

• 代碼實現：

```java
package cn.tedu.demo01;

import java.util.Scanner;

public class TextProcessor {
		public static void main(String[] args) {
				Scanner scanner = new Scanner(System.in);

				//用戶輸入文本
				System.out.println("請輸入文本");
				String input = scanner.next();

				//驗證文本格式 如果包含字母和數字，則將字符串倒序輸出
				if(input.matches("[0-9A-Za-z]*")){
						//使用 StringBuffer 構建反轉字符串
						StringBuilder reversed = new StringBuilder(input);
						reversed.reverse();
						System.out.println("反轉字符串："+reversed.toString());
				}else{
						//如果不包含字母和數字，則將數字替換為星號，並輸出替換後的字符串
						input=input.replaceAll("\\d","*");
						System.out.println("替換後的字符串：" + input);
				}
				// 使用 split() 方法將文本拆分為單詞
				String[] words = input.split("\\s+");
				System.out.println("單詞列表：");
				for (String word : words) {
						System.out.println(word);
				}
		}
}
```
