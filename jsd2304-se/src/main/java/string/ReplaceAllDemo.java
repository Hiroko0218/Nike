package string;

/**
 * 將當前字符串中所有滿足正則表達式的部分替換為给定的内容
 * String replaceAll(String regex,String replacement)
 *        替換    所有
 *
 */
public class ReplaceAllDemo {
    public static void main(String[] args) {
        String line = "abc123def456ghi789jkl";
        /*
            abc123def456ghi789jkl
                      V 將每個數字部分替換為"#NUMBER#"
            abc#NUMBER#def#NUMBERghi#NUMBERjkl
         */

        line = line.replaceAll("[0-9]+","#NUMBER#");
        System.out.println(line);

        String regex = "(wqnmlgb|dsb|nc|tmd|nmd|wdnmd|cnm|djb)";
        String message = "wqnmlgb,你這個dsb,你怎麼這麼的nc!你絕對是一個djb";
        message = message.replaceAll(regex,"***");
        System.out.println(message);

    }
}