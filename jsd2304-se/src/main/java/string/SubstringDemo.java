package string;

public class SubstringDemo {
    public static void main(String[] args) {
        //             01234567890
        String line = "www.tedu.cn";
        String sub = line.substring(4,8);
        System.out.println(sub);//tedu

        sub = line.substring(4);
        System.out.println(sub);//tedu.cn
    }
}
