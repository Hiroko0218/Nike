package file;

import java.io.File;

/**
 * 創建一個資料夾
 */
public class MkDirDemo {
    public static void main(String[] args) {
        //在當前項目資料夾下新建一個資料夾:demo
//        File dir = new File("./demo");

//      創建資料夾f: f資料夾在./a/b/c/d/e/f
        File dir = new File("./a/b/c/d/e/f");
         /*
            make:做
            mkdir是linux中的一個命令，用於創建一個資料夾
         */
//        dir.mkdir();//創建資料夾時要求該資料夾所在的資料夾必須存在，否則創建失败
        dir.mkdirs();//創建資料夾時會自動將所有不存在的父資料夾一同創建，推薦使用
        System.out.println("創建完畢!");
    }
}
