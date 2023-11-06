package file;

import java.io.File;

/**
 * 獲取當前項目資料夾下所有名字以"."開始的資料夾
 */
public class Test3 {
    public static void main(String[] args) {
        File dir = new File(".");
        if (dir.isDirectory()) {
            File[] subs = dir.listFiles(f -> f.getName().startsWith(".") && f.isDirectory());
            for (File sub : subs) {
                System.out.println(sub.getName());
            }
        }
    }
}
