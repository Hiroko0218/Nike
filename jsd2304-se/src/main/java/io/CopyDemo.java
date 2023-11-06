package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 複製文件
 */
public class CopyDemo {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("./image.png");
        FileOutputStream fos = new FileOutputStream("./image_cp.png");
        /*
            原文件内容:
            11001100 01010101 10101010 00111100 11001111 ....
            -----------------------------------------------------
            第一次調用:
            int d = fis.read();
            原文件内容:
            11001100 01010101 10101010 00111100 11001111 ....
            ^^^^^^^^
            讀取的字節

            d的二進制:00000000 00000000 00000000 11001100

            fos.write(d);
            d:00000000 00000000 00000000 11001100
                                         ^^^^^^^^
                                         寫出的字節
            複製的文件内容:
            11001100
            ----------------------------------------------------
            第二次調用:
            d = fis.read();
            原文件内容:
            11001100 01010101 10101010 00111100 11001111 ....
                     ^^^^^^^^
                     讀取的字節

            d的二進制:00000000 00000000 00000000 01010101

            fos.write(d);
            d:00000000 00000000 00000000 01010101
                                         ^^^^^^^^
                                         寫出的字節
            複製的文件内容:
            11001100 01010101
         */

        int d;
//        while (true){
//            d= fis.read();
//            if(d==-1){
//                break;
//            }
//            fos.write(d);
//        }
        /*
            System.currentTimeMillis()
            返回當前系统時間，時間是一個long值，表示當前系统的UTC時間
            UTC:世界協調時
            計算標準:自1970-01-01 00:00:00到表達的時間所經過的毫秒
         */
        long start = System.currentTimeMillis();
        while ((d=fis.read())!=-1){
            fos.write(d);
        }
        long end = System.currentTimeMillis();
        System.out.println("複製完畢! 花了:"+(end-start)+"ms");
        fis.close();
        fos.close();
    }
}
