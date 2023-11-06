package io;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 將"./jsd2304-se/src/main/java/io"資料夾下的所有.java文件内容都輸出到控制台上
 *
 * 步骤:
 * 1:創建File對象表示io資料夾
 * 2:通過File對象獲取下面所有的.java文件(File[] subs)
 * 3:遍歷subs數組,拿到每一個子項.然後基於 BRDemo案例讀取該文件並输出到控制台
 */
public class Test2 {
    public static void main(String[] args) throws IOException {
        File dir = new File("./jsd2304-se/src/main/java/io");
        File[] subs =dir.listFiles(f->f.getName().endsWith(".java"));
        for (File sub: subs){
            System.out.println(sub.getName());
            prinFile(sub);
        }
    }
    /**
     * 封裝方法可以用一個方法名概括一段代碼的含義,增加整體代碼的可讀性
     * @param file
     * @throws IOException
     */
    public static void prinFile(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        String line;
        while ((line = br.readLine())!=null){
            System.out.println(line);
        }
        br.close();
    }
}

