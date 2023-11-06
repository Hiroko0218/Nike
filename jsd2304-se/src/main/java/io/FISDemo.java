package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 使用文件輸入流從文件中讀取字節數據
 */
public class FISDemo {
    public static void main(String[] args) throws IOException {
        //將fos.dat文件中的字節讀取出来
        FileInputStream fis = new FileInputStream("./fos.dat");

        /*
            InputStream的超類中定義了讀取一個字節的方法
            int read()
            通過流讀取1個字節到程序中,返回的int值對應的2進制"低八位"有效.
            如果返回的int值直接表示整數-1,則表示流讀取到了末尾EOF
            EOF: end of file

---------------------------------------------------------------------
            fos.dat内容:
            00000001 00000010
---------------------------------------------------------------------
            第一次調用:
            int d = fis.read();

            fos.dat内容:
            00000001 00000010
            ^^^^^^^^
            讀取的字節

            讀去後變數d的2進制樣子:
            00000000 00000000 00000000 00000001
            |-------自動補充24個0------| ^^^^^^^^
                                       讀取到的數據
         */

        int d = fis.read();
        System.out.println(d);//1

        /*
            第二次調用:
            d = fis.read()

            fos.dat内容:
            00000001 00000010
                     ^^^^^^^^
                     讀取的字節

            讀取後變量d的2進制樣子:
            00000000 00000000 00000000 00000010
            |-------自動補充24個0------| ^^^^^^^^
                                       讀取到的數據
         */
        d = fis.read();
        System.out.println(d);//2

        /*
            第三次調用:
            d = fis.read()

            fos.dat内容:
            00000001 00000010
                               ^^^^^^^^
                               文件末尾

            讀取後變量d的2進制樣子:
            11111111 11111111 11111111 11111111
            |----------自動補充32個1-------------|
         */

        d = fis.read();//-1
        System.out.println(d);

        System.out.println("讀取完畢");
        fis.close();
    }
}
