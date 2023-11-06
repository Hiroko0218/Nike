package file;

import java.io.File;

/**
 * 訪問一個資料夾中的所有子項
 */
public class ListFilesDemo {
    public static void main(String[] args) {
        //訪問當前目錄中的所有子項
        File dir = new File(".");

        /*
            boolean isFile()
            判斷當前File對象表示的是否為一個實際存在的文件

            boolean isDirectory()
            判斷當前File對象表示的是否為一個實際存在的資料夾
         */
        if (dir.isDirectory()){
            /*
                File[] listFiles()
                獲取當前File對象表示的文件夾中的所有子項.
                返回一個File數組,數組中每個元素(一個File對象)表示該文件夾中的一個子項
             */
            File[] subs = dir.listFiles();
            for (File sub:subs){
                System.out.println(sub.getName());
            }

        }
    }
}
