package file;

import java.io.File;

/**
 * 刪除一個文件
 */
public class DeleteFileDemo {
    public static void main(String[] args) {
        //將當前目錄下的test.txt文件删除
        //相對路徑中"./"是可以忽略不寫的，默認就是從"./"開始
        File file = new File("test.txt");//等同於"./test.txt"
        /*
            boolean delete()
            當且僅當File表示的文件或目錄被成功删除返回true
         */
        boolean delete =file.delete();
        System.out.println("刪除"+(delete?"成功":"失敗"));
    }
}
