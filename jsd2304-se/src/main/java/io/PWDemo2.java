package io;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 在流連接中使用 PrintWriter
 */
public class PWDemo2 {
    public static void main(String[] args) throws FileNotFoundException {
         /*
            文件流: 字節流，低级流
            作用: 連接文件，並將字節寫入到文件中
         */
        FileOutputStream fos = new FileOutputStream("pw2.txt");
        /*
            轉換流: 字符流，高級流
            作用:
            1:銜接字節與其他字符流
            2:將寫出的字符按照指定的字符集轉換為字節
         */
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        /*
            缓冲字符流:字符流，高级流
            作用:
            塊寫文本數據保證寫出效率的(内部默認有一個長度8192的char數組)
         */
        BufferedWriter bw = new BufferedWriter(osw);
        /*
            PrintWriter: 字符流，高级流
            作用:
            1: 按行寫出字符串
            2: 自動行刷新
         */
        PrintWriter pw = new PrintWriter(bw);
        pw.println("嘿嘿");
        pw.println("呵呵");
        pw.println("哈哈");
        System.out.println("寫出完畢!");
        pw.close();



    }
}
