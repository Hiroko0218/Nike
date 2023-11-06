package thread;

/**
 * Thread類  線程
 * Thread允許我們在java程序中"同時"執行多段代碼
 * 實際上CPU在執行代碼時會快速在這些線程之間切換執行，造成一種感官錯覺是這些
 * 線程中的代碼在"同時"執行，這種執行現象成為"併發運行"
 *
 * 創建線程的常見方式之一:
 * 1:繼承Thread類
 * 2:重寫run方法
 *   在run方法中定義該線程需要的代碼
 */
public class ThreadDemo1 {
    public static void main(String[] args) {
        Thread t1 = new MyThread1();
        Thread t2 = new MyThread2();

        t1.start();
        t2.start();

    }
}

/**
 * 第一種創建線程的方式
 * 優點:結構簡單，利於匿名内部類方式創建
 * 缺點:
 * 1: java是單繼承的，這意味著如果繼承了 Thread就無法再繼承其他類去複用方法
 * 2: 繼承Thread的同時將任務定義在線程中，會導致線程與任務存在必然的耦合關係
 *   不利於線程的重用
 */
class MyThread1 extends Thread{
    public void run(){
        for(int i=0;i<1000;i++){
            System.out.println("你是谁啊?");
        }
    }
}
class MyThread2 extends Thread{
    public void run(){
        for(int i=0;i<1000;i++){
            System.out.println("開門!查水表的!");
        }
    }
}