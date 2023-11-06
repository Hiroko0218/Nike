package file;

import java.io.File;

/**
 * 將 Test1中創建的 100個文件删除
 */
public class Test2 {
    public static void main(String[] args) {
        for (int i = 0 ; i<=100;i++){
            File file = new File("./test"+i+".txt");
            file.delete();
        }
        System.out.println("刪除完畢!");
    }
}
