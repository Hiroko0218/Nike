package string;

public class ToUpperCaseDemo {
    public static void main(String[] args) {
        String str = "我愛Java";
        String upper = str.toUpperCase();
        System.out.println(upper);//我愛JAVA
        String lower = str.toLowerCase();
        System.out.println(lower);//我愛java

        String img = "2Y5jZa";
        String input = "2y5jza";
       //直接匹配會因為大小寫失敗
        System.out.println(img.equals(input));//false

        /*
            批量注释快捷键
            将需要注释的代码选中后:
            ctrl+/  每行都会被注释，使用单行注释
            ctrl+shift+/ 整体使用多行注释
         */

        img = img.toLowerCase();//將生成驗證碼轉換為全小寫(全大寫)
        input = input.toLowerCase();//將用戶輸入的也轉換為全小寫(全大寫)
        //匹配會成功，達到忽略大小寫目的
        System.out.println(img.equals(input));//true

        //String提供的方法可以忽略大小寫比较字符串内容
        boolean match = img.equalsIgnoreCase(input);
        //性能:三目>switch>if
        System.out.println("输入"+(match?"正确":"错误"));
    }

}
