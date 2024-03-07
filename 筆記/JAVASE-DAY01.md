## 1.1 String 常用的 API

- length（） 方法用於返回字符串的長度，也就是字符串中字符的個數
- indexOf（） 方法用於查找指定字符或者子字符串在字符串中第一次出現的位置，如果找到，則返回該字符串或者子字符串的索引，如果未找到，則返回 -1
- chatAt（）方法用於獲取字符串中指定位置的字符，它接受一個整數參數，表示要獲取字符的索引，返回該位置的字符
- subString（）方法用於提取字符串中的子串，它接受一個或者兩個整數參數，表示子串的起始位置和結束位置，包頭不包尾
-  toUpperCase（）方法用於將字符串中的所有字符轉換為大寫形式
-  trim（）方法用於去除字符串兩端的空白字符（空格，制表符，換行符等）返回一個新的字符串參數，該字符串是原字符串去除空白字符後的結果
- startsWith（）方法用於檢查字符串是否以指定的前綴開始，它接受一個字符串參數，如果原字符串以該前綴開始，則返回 true，否則，返回 false

- valueOf（）方法用於將其他類型的值轉換為字符串表示，它接受一個值作為參數，並返回表示該值的字符串。

## 1.2 案例：URL 格式驗證器

##### 案例描述：

- 編寫一個程序，用於驗證用戶輸入的字符串是否符合 URL 的格式要求。程序將檢查字符串的長度、是否以特定前綴開頭、是否包含特定字符，並輸出驗證結果給用戶。
- URL 格式要求：http://baidu.COM
- 要求 URL 的長度必須在 10 到 100 個字符之間 //true
- length()方法檢查字符串的長度
- 兩端不允許有空格 //去除空格
- trim() 方法去除字符串兩端的空格
- 並且是以 http:// 或者 https:// 開頭，以.com 結束 //true
- startsWith()方法檢查是否是以 http://或者 https://開頭
- endsWith()方法檢查字符串是否以 .com 結尾
- 不允許包含特殊的字符（&，#，？）// true
- 使用 indexOf()方法檢查是否包含特殊字符
- 要求全部都由小寫字母組成 //xiaoxie
  
- 代碼實現：

```java
 package cn.tedu.demo01;

 import java.util.Scanner;

 public class URLValidator {
  	public static void main(String[] args) {
  		Scanner scanner = new Scanner(System.in);
  		//用戶輸入字符串
  		System.out.println("請輸入字符串");
  		String next = scanner.next();

  		//驗證字符串 URL
  		boolean b = validateURL(next);

  		//輸出驗證結果
  		if(b){
  			System.out.println("URL 驗證通過");
  		}else{
  			System.out.println("URL 驗證失敗");
  		}
  	}

  	public static boolean validateURL(String input){
  		// 1. 要求 URL 的長度必須在 10 到 100 個字符之間 || 或
  		// 檢查字符串長度
  		if(input.length()<10 || input.length()>100){
  				return false;
  		}

  		//2. 兩端不允許有空格
  		input = input.trim();

  		//3. 並且是以 http:// 或者 https:// 開頭，以.com 結束 && 與
  		if(!input.startsWith("http://") || !input.startsWith("https://") && !input.endsWith(".com")){
  				return false;
  		}
  		//4.不允許包含特殊的字符（&，#，？）
  		if(input.indexOf("&")!=-1 || input.indexOf("#")!=-1 || input.indexOf("?")!=-1){
  				return false;
  		}

  		//5. 全部都是以小寫字母組成
  		input = input.toLowerCase();

  		return true;
  	}
 }
```
