package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 提高每次讀寫的數據量，減少實際讀寫的次數可以提高讀寫效率
 * 一组一组字節的讀寫稱為: 塊讀寫
 */
public class CopyDemo2 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("./image.png");
        FileOutputStream fos = new FileOutputStream("./image_cp2.png");
        /*
            InputStream 定義了塊讀方法
            int read(byte[] data)
            一次性讀取给定字節數组data總長度的字節量並存入到該數组中。
            返回值表達實際讀取到了多少字節。如果返回值為-1 表達流讀取到末尾了

            原文件内容:
            11001100 01010101 10101010 00111100 11001111 00110011 11101100
            --------------------------------------------------------------

            byte[] data = new byte[3]; //長度為3的字節數組
            data:{00000000,00000000,00000000}
            int len=0;

            第一次調用:
            len = fis.read(data);//因為data長度為3，因此一次性讀取3字節並存入該數組
            原文件内容:
            11001100 01010101 10101010 00111100 11001111 00110011 11101100
            ^^^^^^^^ ^^^^^^^^ ^^^^^^^^
            連續讀取3個字節
            data:{11001100,01010101,10101010} 讀取後數组内容就是讀取到的内容
            len:3 len 接收的返回值為整數，表示本次實際讀取到了3個字節

            -----------------------------------------------------------------
            第二次調用:
            len = fis.read(data);//因為data長度為3，因此一次性讀取3字節並存入該數組
            原文件内容:
            11001100 01010101 10101010 00111100 11001111 00110011 11101100
                                       ^^^^^^^^ ^^^^^^^^ ^^^^^^^^
            連續讀取3個字節
            data:{00111100,11001111,00110011}讀取後數组内容就是讀取到的内容
            len:3 len 接收的返回值為整數，表示本次實際讀取到了3個字節

            ----------------------------------------------------------------
            第三次調用:
            len = fis.read(data);//因為data長度為3，因此一次性讀取3字節並存入該數組
            原文件内容:
            11001100 01010101 10101010 00111100 11001111 00110011 11101100
                                                                  ^^^^^^^^ ^^^^^^^^ ^^^^^^^^
                                                                  應當讀取3個，實際讀取到1個
            連續讀取3個字節
            data:{11101100,11001111,00110011}
                  本次讀取  |--上次的舊數據---|

            len:1   len接收的返回值為整數，表示本次實際讀取到了1個字節

            -----------------------------------------------------------------
            第四次調用:
            len = fis.read(data);//因為data長度為3，因此一次性讀取3字節並存入該數組
            原文件内容:
            11001100 01010101 10101010 00111100 11001111 00110011 11101100
                                                                           ^^^^^^^^ ^^^^^^^^ ^^^^^^^^
                                                                           本次沒有數據可讀(EOF)
            data:{11101100,11001111,00110011}
                  |------上次的舊數據--------|
            len:-1   已經是文件末尾了



            OutputStream中定義了塊寫操作
            void write(byte[] data)
            一次性將给定字節數组data中的所有字節寫出

            void write(byte[] data,int offset,int len)
            一次性將给定字節數组data從下標offset處開始的連續len個字節寫出

         */
        /*
            1和0         1bit
            00000000     1byte(8位2進制) 1字節
            1024byte     1kb
            1024kb       1mb
            1024mb       1gb
            1024gb       1tb
         */
        byte[] data = new  byte[1024*10];//10kb
        int len = 0;//每次實際讀取到的字節數
        long start = System.currentTimeMillis();
        while ((len=fis.read(data))!=-1){
            fos.write(data,0,len);
        }
        long end = System.currentTimeMillis();
        System.out.println("複製完畢!花了"+(end-start)+"ms");
        fis.close();
        fos.close();
    }
}
