package string;

import java.util.Locale;

public class Test {
    public static void main(String[] args) {
        String s1 = "www.tedu.cn";
        String s2 = "http://www.tarena.com.cn";
        String name1 = getHostName(s1);
        System.out.println(name1);//tedu
        String name2 = getHostName(s2);
        System.out.println(name2);//tarena
    }
    public static String getHostName(String url){
        int startIndex = url.indexOf(".")+1;
        int endIndex = url.indexOf(".",startIndex);
        return url.substring(startIndex,endIndex);
    }
}
