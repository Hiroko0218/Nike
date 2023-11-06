package integer;

public class IntegerDemo3 {
    public static void main(String[] args) {
        /*
            JDK1.5之後，java推出了一個新特性: 自動拆裝箱
            我們可以在源代碼中直接將基本類型與對應的包裝類相互賦值
            Integer in = 123; //基本類型賦值給包裝類
            int i = Integer.valueOf(123); //將包裝類對象賦值给基本類型

            編譯器在編譯代碼時如果發現上述情況，會自動補充換代碼到字節碼文件中
            編譯後的class文件中上述代碼會被編譯器改為:
            Integer in = Integer.valueOf(123);
            上述代碼編譯器補充了基本類型轉換為包裝類的代碼，這被我們稱作:自動裝箱

            int i = Integer.valueOf(123).intValue();
            上述代碼編譯器補充了包裝類轉換為基本類型，該操作稱為:自動拆箱
         */

        Integer in = 123;
        int i = in;
    }
}
