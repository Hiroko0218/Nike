package file;

import java.io.File;
import java.io.IOException;

/**
 * 在當前項目目錄下自動生成100個文件，名字為test1.txt--test100.txt
 */
public class Test {
    public static void main(String[] args) throws IOException {
        for (int i = 1 ; i<=100; i++){
            File file = new File("./test"+i+".txt");
            file.createNewFile();
        }
        System.out.println("建檔完畢!");
    }
}
