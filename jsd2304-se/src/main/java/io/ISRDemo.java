package io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 使用InputStreamReader讀取文本數據
 */
public class ISRDemo {
    public static void main(String[] args) throws IOException {
        //讀取osw.txt中的文本内容
        FileInputStream fis = new FileInputStream("osw.txt");
        InputStreamReader isr = new InputStreamReader(fis);
        /*
            int read()
            對於字符流而言，該方法用於讀取1個字符，返回的int值對應的2進制
            是"低16位有效"，實際返回的就是一個char。
            但是如果返回的int值為整數-1，依然表達流讀取到了末尾
         */
//        int d = isr.read();
//        System.out.println((char) d);

        int d ;
        while ((d = isr.read())!=-1){
            System.out.print((char) d);
        }
        isr.close();
    }
}
