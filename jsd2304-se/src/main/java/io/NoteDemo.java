package io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 *  程序啟動後，將用戶在控制台上輸入的每一行字符串都寫入到文件 note.txt中
 *  如果用戶在控制台上單獨輸入了"exit"就停止程序
 *
 *  思路:
 *  1:Scanner用於獲取控制台輸入内容
 *  2:FileOutputStream用於將數據寫入到文件中
 *
 *  步驟:
 *  1:創建文件輸出流，用於向note.txt中寫入數據
 *  2:死循環
 *      2.1:先獲取用戶輸入的一行字符串
 *      2.2:判断是否為"exit",如果是則结束循環，如果不是則執行2.3
 *      2.3:先將字符串轉換為一组字節
 *      2.4:將轉換的這组字節寫入到文件中
 *  3:出了死循環，將流關閉，程序结束
 *
 */
public class NoteDemo {
    public static void main(String[] args) throws IOException {
        /*
            FileOutputStream(File file)
            FileOutputStream(String path)
            上述兩種構造器創建文件流為覆蓋模式,
            即: 如果指定的文件存在會將該文件原有内容全部删除

            FileOutputStream(File file,boolean append)
            FileOutputStream(String path,boolean append)
            如果第二個参數為true,則指定為追加模式.此時創建文件流時如果指定的
            文件存在則内容會全部保留,新寫入的内容會被追加到文件中
         */
        FileOutputStream fos = new FileOutputStream("note.txt",true);
        Scanner scanner = new Scanner(System.in);
        System.out.println("請開始輸入內容，單獨輸入exit退出");
        while (true) {
            String line = scanner.nextLine();
            if ("exit".equalsIgnoreCase(line)) {
                break;
            }
            byte[] data = line.getBytes(StandardCharsets.UTF_8);
            fos.write(data);
        }
        System.out.println("再見!");
        fos.close();
    }
}
