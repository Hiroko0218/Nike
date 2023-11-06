package io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 完成一個簡易記事本工具
 * 程序啟動後將用戶在控制台上輸入的每一行字符串都按行寫入到文件 note2.txt中
 * 如果用戶單獨输入exit，則退出程序。
 *
 * 創建PrintWriter時要自行完成流連結。
 */
public class NoteDemo2 {
    public static void main(String[] args) throws FileNotFoundException {
//        PrintWriter pw = new PrintWriter(
//          new BufferedWriter(
//            new OutputStreamWriter(
//              new FileOutputStream("note2.txt"), StandardCharsets.UTF_8
//            )
//          )
//        );
        FileOutputStream fos =new FileOutputStream("note2.txt");
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(osw);
        /*
            PrintWriter第一個参數是一個輸出流時，就支持再傳入一個boolean
            類型的参數，表示是否開啟自動行刷新功能。
            當自動行刷新功能開啟後，每當我們調用println方法時就會自動 flush
            注: print方法並不會自動flush
         */
        PrintWriter pw = new PrintWriter(bw,true);
        Scanner scanner = new Scanner(System.in);
        System.out.println("請開始輸入内容，單獨輸入exit退出");
        while (true){
            String line = scanner.nextLine();
            if ("exit".equalsIgnoreCase(line)){
                break;
            }
            pw.println(line);
        }
        System.out.println("再見!");
        pw.close();
    }
}
