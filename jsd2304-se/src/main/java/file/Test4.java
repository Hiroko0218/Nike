package file;

import java.io.File;
import java.util.Scanner;

public class Test4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("請輸入一個資料夾的路徑: ");
        String path = scanner.nextLine();
        File dir = new File(path);
        if (dir.isDirectory()){
            System.out.println("請輸入要獲取資料的檔案類型:");
            String ext = scanner.nextLine();//".exe"
            File[] sub = dir.listFiles(f->f.getName().endsWith(ext));
            for (File subs: sub){
                System.out.println(subs.getName());
            }
        }else {
            System.out.println("不是一個資料夾，獲此資料夾不存在!");
        }
    }
}
