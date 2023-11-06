package string;

public class StringDemo {
    public static void main(String[] args) {
        String s1 = "123abc";
        String s2 = "123abc";
        System.out.println(s1==s2);//true
        String s3 = "123abc";
        System.out.println(s1==s3);//true

        s1 =s1 +"!";
        System.out.println("s1:"+s1);//123abc!
        System.out.println("s2:"+s2);//123abc
        System.out.println(s1==s2);//false

        String s4 = new String("123abc");
        System.out.println("s4:"+s4);//123abc
        System.out.println(s2==s4);//false

        String s5 ="123"+"abc";
        System.out.println("s5"+s5);//123abc
        System.out.println(s2==s5);//true

        String s ="123";
        String s6 = s+"abc";
        System.out.println("s6:"+s6);//123abc
        System.out.println(s2==s6);//false
    }
}
