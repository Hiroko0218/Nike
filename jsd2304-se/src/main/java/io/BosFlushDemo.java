package io;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 缓冲寫缓冲區問題
 */
public class BosFlushDemo {
    public static void main(String[] args) throws IOException {
        //向文件中寫入一個字符串
        FileOutputStream fos = new FileOutputStream("bos.txt");
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        String line = "ところで平凡な俺よ,下を向いている暇はあるのか";
        byte[] data = line.getBytes(StandardCharsets.UTF_8);
        bos.write(data);
         /*
            flush:冲水
            void flush()
            強制將缓冲區中已經缓存的數據全部寫出


            flush 方法是在接口 Flushable上定義的,而OutputStream實現了該接口
            java中所有的輸出流都有flush方法.
            並非所有输出流的flush都有實質作用,而更多的是為了將flush動作傳遞下去,
            最終傳遞给缓冲流来真正完成清空缓冲區操作.
         */
//        bos.flush();
        System.out.println("寫出完畢");
        bos.close();
    }
}
