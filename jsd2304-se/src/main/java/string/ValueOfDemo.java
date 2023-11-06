package string;

/**
 * String提供了一套重载的静態方法:valueOf
 * 作用是將java中其他類型轉換為字符串
 */

public class ValueOfDemo {
    public static void main(String[] args) {
        int i = 123;
        String s1 = String.valueOf(i);//s1="123";
        System.out.println(s1);//123

        double d =123.123;
        String s2 = String.valueOf(d);//s2="123.123"
        System.out.println(s2);//123.123

        String s3 =i+"";
        System.out.println(s3);//123
    }
}
