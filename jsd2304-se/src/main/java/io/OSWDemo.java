package io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/**
 * java將流按照讀寫的數據單位劃分為字節與字符流
 * java.io.InputStream 和 OutputStream是字節流的超類
 * java.io.Reader 和 Writer是字符流的超類
 *
 * 字符流的最小讀寫單位是一個 char(字符)
 * 字符流都是高級流，本質上底層最後還是要讀寫字節，只不過字符與字節的轉換由字符流完成
 *
 *
 * 轉換流
 * 轉換流是字符流常用的一對實現類
 * 他們在流連接中是重要的一環，但是實際開發中我們不會直接操作這對流
 * java.io.InputStreamReader 和 OutputStreamWriter
 *
 * 轉換流是唯一一對可以連接在字節流上的字符流，這樣一來其他的字符流就可以通過
 * 連接轉換流最終和字節流搭配使用
 */
public class OSWDemo {
    public static void main(String[] args) throws IOException {
        //向文件osw.txt中寫入文本數據
        FileOutputStream fos = new FileOutputStream("osw.txt");
        /*
            轉換流的主要工作之一就是將寫出的字符按照指定的字符集轉換為字節
            因此，構造器創建時除了指明連接在哪個流上之外，還可以明確字符集
         */
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        osw.write("ところで平凡な俺よ");
        osw.write(",下を向いている暇はあるのか");
        System.out.println("寫出完畢!");
        osw.close();
    }
}
