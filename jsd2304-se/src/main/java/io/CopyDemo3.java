package io;

import java.io.*;

/**
 * java將流分為兩類:
 * 節點流與處理流
 * 節點流:又稱為低級流,特點:實際連接程序與另一端的"管道",負責實際讀寫數據的流.
 *       IO一定是建立在某個低級流的基礎上進行的.
 *       文件流就是低級流,他們是實際連接程序與文件的管道,負責讀寫文件數據
 *
 * 處理流:又稱為高级流,特點:不能獨立存在,必須連接在其他流上,目的是當數據經過該
 *       流時對數據進行某種加工處理,簡化我們的同等操作
 * 實際開發中我們經常串聯一组高級流最终到某個低級流上,是對讀寫數據的過程中以流水線
 * 式的操作對數據加工處理,這個過程也稱為"流的連接"
 *
 *
 * 緩沖流
 * java.io.BufferedInputStream 和 BufferedOutputStream
 * 缓冲流是一對高級流,在流連接中的作用: 保證讀寫效率
 */
public class CopyDemo3 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("./image.png");
        BufferedInputStream bis = new BufferedInputStream(fis);
        FileOutputStream fos = new FileOutputStream("./image_cp3.png");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        int d;
        long start = System.currentTimeMillis();
        while ((d=bis.read())!=-1){
            bos.write(d);
        }
        long end = System.currentTimeMillis();
        System.out.println("複製完畢!花了"+(end-start)+"ms");
        bis.close();//只需要關閉高级流即可(關閉高级流時它通常會關閉其連結的流)
        bos.close();
    }
}
