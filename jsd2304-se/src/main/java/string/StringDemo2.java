package string;

/**
 * 字符串是不變對象，修改字符串内容會產生新對象
 * 當遇到頻繁拼接字符串時，會產生性能問題
 */
public class StringDemo2 {
    public static void main(String[] args) {
        String str = "a";
        for (int i = 0; i<1000000;i++){
            str=str+"a";
        }
        System.out.println("拼接完畢!");
    }
}
