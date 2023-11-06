package file;

import java.io.File;

/**
 * 删除一個資料夾
 */
public class DeleteDirDemo {
    public static void main(String[] args) {
        //將當前項目文件夾下的demo資料夾删除
        File dir = new File("./demo");
        /*
            delete方法在删除資料夾時要求必須是空的資料夾，否則會刪除失敗
         */
        dir.delete();
        System.out.println("資料夾已刪除");
    }
}
