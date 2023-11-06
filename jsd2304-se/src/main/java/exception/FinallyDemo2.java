package exception;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * IO處理中的異常處理
 */
public class FinallyDemo2 {
    public static void main(String[] args) {
        FileOutputStream fos =null;
        try { //自動生成try-catch的快捷键 ctrl+alt+t
            fos = new FileOutputStream("fos.dat");
            fos.write(1);
        } catch (IOException e) {
            System.out.println("出錯了!");
        }finally {
            try {
                if (fos!=null){
                    fos.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
