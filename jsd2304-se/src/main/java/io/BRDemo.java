package io;

import java.io.*;

/**
 * 使用缓冲字符輸入流按行讀取字符串
 */
public class BRDemo {
    public static void main(String[] args) throws IOException {
        //將當前源代碼按行讀取出来並輸出到控制台上
        FileInputStream fis = new FileInputStream( "./jsd2304-se/src/main/java/io/BRDemo.java");
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        /*
            BufferedReader提供的獨有方法:
            String readLine()
            當第一次調用readLine()方法時,緩沖字符輸入流會一次性讀取8192個字符(塊讀)
            並存入内部的char數組中,然後從數組一個字符開始直到找到換行字符位置,然後將
            之前的内容轉換為一個字符串返回.(看起来像是讀取了一行字符串)
            當第二次調用readLine()則直接從上次遇到換行符的位置開始再繼續尋找下一個
            換行符為止,將之間的内容轉換為一個字符串返回

            如果流讀取到了末尾,此時該方法會返回null
         */

//        String line = br.readLine();
//        System.out.println(line);
        String line;
        while ((line=br.readLine())!=null){
            System.out.println(line);
        }
        br.close();
    }
}
