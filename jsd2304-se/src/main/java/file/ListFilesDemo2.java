package file;

import java.io.File;
import java.io.FileFilter;


/**
 * 有條件的獲取一個資料夾中的子項
 */
public class ListFilesDemo2 {
    public static void main(String[] args) {
        //獲取當前資料夾中所有文本文件(文件名是以".txt"结尾的)
        File dir = new File(".");
        if (dir.isDirectory()){
//            FileFilter fileFilter = new FileFilter() {
//                public boolean accept(File f) {
//                    return f.getName().endsWith(".txt");
//                }
//            };

            /*
//                重載的listFiles方法要求傳入一個文件過濾器
//                該方法會將File對象表示的文件夾中所有滿足過濾器條件的子項返回
//          */
//            File [] subs = dir.listFiles(fileFilter);

            File[] subs = dir.listFiles(f->f.getName().endsWith(".txt"));

            for(File sub: subs){
                System.out.println(sub.getName());
            }
        }
    }
}
