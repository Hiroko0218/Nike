package file;

import java.io.File;
import java.io.IOException;

/**
 * 創建一個新文件
 */
public class CreateNewFileDemo {
    public static void main(String[] args) throws IOException {
        //在當前項目文件夾下新建一個名為test.txt的文件
        File file = new File("./test.txt");

        /*
            create:創建
            file:文件

            boolean createNewFile()
            當且僅當File表示的路徑下不存在且實際創建出該文件時返回true

            boolean exists()
            判斷當前File表示的路徑下是否真實存在該文件或文件夾，如果存在則返回true
         */
        if (file.exists()){
            System.out.println("該文件已存在!");
        }else {
            file.createNewFile();
            System.out.println("建檔完畢!");
        }
    }
}
