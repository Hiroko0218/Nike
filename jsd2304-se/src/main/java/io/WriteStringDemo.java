package io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 向文件中寫入文本數據
 */
public class WriteStringDemo {
    public static void main(String[] args) throws IOException {
        //向文件fos1.txt中写入一行中文
        FileOutputStream fos = new FileOutputStream("fos1.tex");
        String line = "ところで平凡な俺よ";
        byte[] data = line.getBytes(StandardCharsets.UTF_8);
        fos.write(data);

        fos.write(",下を向いている暇はあるのか".getBytes(StandardCharsets.UTF_8));

        System.out.println("寫出完畢!");
        fos.close();
    }
}
