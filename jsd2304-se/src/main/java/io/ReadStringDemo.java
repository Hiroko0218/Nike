package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 從文本文件中讀取文本數據
 */
public class ReadStringDemo {
    public static void main(String[] args) throws IOException {
//        File file = new File("./fos1.txt");
        File file = new File("./jsd2304-se/src/main/java/io/ReadStringDemo.java");
        FileInputStream fis = new FileInputStream(file);
        //創建與文件等長的字節數組
        byte[] data = new byte[(int) file.length()];
        fis.read(data);

        String line = new String(data, StandardCharsets.UTF_8);
        System.out.println(line);

        fis.close();
    }
}
