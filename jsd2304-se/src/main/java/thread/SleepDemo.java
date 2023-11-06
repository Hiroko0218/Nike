package thread;

import java.util.Scanner;

/**
 * Thread提供了主动阻塞的方法:
 * static void sleep(long ms)
 * 允许当前线程主动进入阻塞状态指定毫秒,超时后线程会自动回到RUNNABLE状态再次开始
 * 并发操作
 */
public class SleepDemo {
    public static void main(String[] args) {
        System.out.println("程序開始了!");
        /*
            簡易的倒計時程序
            程序開始後在控制台輸入一個數字,從該數字開始每秒遞减.到0時輸出時間到
         */
        Scanner scanner = new Scanner(System.in);
        System.out.println("請輸入一個數字:");
        for(int i = scanner.nextInt(); i>0 ;i-- ) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("程序结束了");
    }
}
