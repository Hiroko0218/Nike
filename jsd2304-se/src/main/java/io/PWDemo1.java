package io;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * 缓冲字符流
 * java.io.BufferedWriter 與 BufferedReader
 *
 * 具有自動行刷新功能的缓冲字符输出流
 * java.io.PrintWriter
 * 其内部總是連接 BufferedWriter作為缓冲功能
 *
 * 缓冲流以塊讀寫形式保證讀寫效率。並且可以按行讀寫字符串
 */
public class PWDemo1 {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        //向文件pw.txt中按行寫出字符串
        /*
            PrintWriter 提供了直接對文件進行寫操作的構造器
            PrintWriter(File file)
            PrintWriter(String path)
         */

        PrintWriter pw = new PrintWriter("pw.txt","UTF-8");
        pw.println("老人が君に言いました");
        pw.println("「残りの寿命を買わせてよ,50年を50億で買おう」");
        pw.println("人生をやり直したいと");
        System.out.println("寫出完畢!");
        pw.close();
    }
}
