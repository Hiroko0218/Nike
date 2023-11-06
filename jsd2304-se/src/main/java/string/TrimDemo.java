package string;

/**
 * String trim()
 * 去除字符串兩側的空白字符
 */
public class TrimDemo {
    public static void main(String[] args) {
        String line = "   hello world   ";
        System.out.println(line);
        String trim = line.trim();
        System.out.println(trim);
    }
}
