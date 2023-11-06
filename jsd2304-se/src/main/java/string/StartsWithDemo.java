package string;

/**
 * boolean startsWith(String str)
 * 判斷當前字符串是否是以给定的字符串str開始的
 * boolean endsWith(String str)
 * 判断當前字符串是否是以给定的字符串str结尾的
 */
public class StartsWithDemo {
    public static void main(String[] args) {
        String line = "www.tedu.cn";
        boolean starts = line.startsWith("www.");
        System.out.println("starts: "+starts);
        boolean ends = line.endsWith(".com");
        System.out.println("end: "+ends);
    }
}
