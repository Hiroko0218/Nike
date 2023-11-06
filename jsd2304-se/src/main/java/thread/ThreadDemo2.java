package thread;

/**
 * 第二種創建線程的方式:
 * 實現Runnable接口單獨定義線程任務
 */
public class ThreadDemo2 {
    public static void main(String[] args) {
        //1.實例化任務
        Runnable r1 = new MyRunnable1();
        Runnable r2 = new MyRunnable2();
        //2.實例化線程並指派任務
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        //3.啟動線程
        t1.start();
        t2.start();
    }
}
/*
    第二種創建方式優點:
    1:没有繼承冲突問題
    2:與線程没有必然耦合關係

    缺点:
    創建上會麻烦一些
 */
class MyRunnable1 implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i<1000;i++){
            System.out.println("你是誰啊?");
        }
    }
}
class MyRunnable2 implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i<1000;i++){
            System.out.println("開門!查水表的!");
        }
    }
}
