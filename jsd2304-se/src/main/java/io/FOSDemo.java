package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * JAVA IO
 * IO:Input和Output 即:輸入與輸出
 *
 * 輸入是從外界到程序中的方向,是我們程序用於獲取外界信息的過程,是"讀"的過程
 * 輸出是從程序到外界的方法,是我們程序向外界發送信息的過程,是"寫"的過程
 *
 * java.io.InputStream 與 OutputStream
 * 輸入流與輸出流   流
 * 這裡的流相當於是程序與外界相連的一根管道,用於傳輸數據.而數據就是字節(2進制)
 *
 * InputStream 和 OutputStream是兩個超類,抽象類.
 * java中實際用於讀寫數據的流都繼承自他們.
 *
 * 文件流
 * java.io.FileInputStream 和 FileOutputStream
 * 這兩個流就繼承自 InputStream 和 OutputStream
 * 這兩個流是用於連接我們程序和文件之間的管道,讀寫文件數據(讀寫硬盤)
 */
public class FOSDemo {
    public static void main(String[] args) throws IOException {
        //像當前項目資料夾下的文件fos.dat中寫入數據
        /*
            FileOutputStream是用於連接程序與程序的管道,負責將程序的數據發送到文件中
            常用的構造方法:
            FileOutputStream(File file)
            FileOutputStream(String path)
            注:如果指定的文件不存在,文件流會自動創建它.前提是該文件所在的資料夾要存在
         */
//        File file = new File("./fos.dat");
//        FileOutputStream fos = new FileOutputStream(file);

        FileOutputStream fos = new FileOutputStream("./fos.dat");
        /*
            OutputStream上定義的方法:
            void write(int d)
            通過當前輸出流向目標位置寫出1個字節,寫出给定int值對應2進制的"低八位"

            fos.write(1);
            1個int值4個字節,32位2進制
            00000000 00000000 00000000 00000001
                                       ^^^^^^^^
                                       實際寫出的數據
            fos.dat文件中:
            00000001

            ---------------------------------------------
            fos.write(2);
            1個int值4個字節,32位2進制
            00000000 00000000 00000000 00000010
                                       ^^^^^^^^
                                       實際寫出的數據
            fos.dat文件中:
            00000001 00000010
         */

        fos.write(1);
        fos.write(2);
        System.out.println("寫出完畢");
        fos.close();//流使用完畢後要關閉
    }
}
