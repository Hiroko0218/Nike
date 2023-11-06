package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 線程池
 * 線程池是管理線程的機制
 * 主要解決兩個問題:
 * 1:重複使用線程
 * 2:控制線程數量
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        //創建線程池
        //創建一個固定大小的線程池，容量為2
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        for(int i=0;i<5;i++){
            Runnable r = new Runnable() {
                public void run() {
                    try {
                        Thread t = Thread.currentThread();
                        System.out.println(t+":正在執行任務...");
                        Thread.sleep(5000);
                        System.out.println(t+":執行任務完畢!");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            threadPool.execute(r);//將任務交给線程池去執行
            System.out.println("將一個任務交给了線程池");
        }
        //關閉線程池，線程池不再接收新任務，等線程池中所有任務都執行後结束
        threadPool.shutdown();
        //立即關閉線程池，此時線程池會調用線程的中斷方法
//        threadPool.shutdownNow();
        System.out.println("線程值關閉了!");
    }
}
