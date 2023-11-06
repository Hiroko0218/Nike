package io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 向文件中寫入字母a-z
 */
public class Test {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("fos.txt");
        /*
            ASC
            a->97->01100001
         */
        /*
            00000000 00000000 00000000 01100001
         */
        for (int i = 0; i<26;i++){
            fos.write(97+i);
        }
        System.out.println("寫出完畢!");
        fos.close();
    }
}
