package file;

import java.io.File;

/**
 * java.io.File
 * File類的每一個實例用於表示文件系統中的一個文件或目錄(本質是保存一個路徑)
 * 使用 File我們可以:
 * 1:訪問文件或目錄的屬性(名字，大小等信息)
 * 2:創建/删除 文件或目錄
 * 3:訪問一個目錄中的所有子項
 *
 * 但是不能訪問文件數據
 */
public class FileDemo {
    public static void main(String[] args) {
//       實際開發中，我們不會使用絕對路徑: 因為存在跨平台問題
//       File file = new File("D:/project/JSD2304SE/demo.txt");
//       相對路徑中"./"表示當前目錄，在IDEA中是當前項目目錄
        File file = new File("./demo.txt");
        String name = file.getName();
        System.out.println("名字: "+name);

        /*
            long length
            返回當前File表示的文件的長度(單位是字節)
         */
        long len = file.length();
        System.out.println("大小: "+len);

        //是否可讀
        boolean cr = file.canRead();
        System.out.println("是否可讀: "+cr);

        //是否可寫
        boolean cw = file.canWrite();
        System.out.println("是否可寫: "+cw);

        //是否隱藏
        boolean hidden = file.isHidden();
        System.out.println("是否隱藏: "+hidden);
    }
}
