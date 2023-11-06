package string;

/**
 * StringBuilder修改字符串的性能
 */
public class StringBuilderDemo2 {
    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder("a");
        for (int i =0; i<1000000;i++){
            builder.append("a");
        }
        System.out.println("修改完畢!");

        /*
        String str = "hello";
        str = str + "world";//class文件中没有"+"連接字符串
        //它只在源代碼中出現，編譯器將其改為:
        str = new StringBuilder(str).append("world").toString();
        System.out.println(str);
        */

    }
}
