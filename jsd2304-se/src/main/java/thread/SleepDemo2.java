package thread;

/**
 * 當一個線程調用 sleep方法進入阻塞狀態的過程中,此時該線程的 interrupt()方法被
 * 調用,那麼就會中斷該線程的睡眠阻塞,sleep()方法會抛出中斷異常
 */
public class SleepDemo2 {
    public static void main(String[] args) {
        Thread lin = new Thread("小林") {
            @Override
            public void run() {
                System.out.println(getName()+": 剛美容完，先睡一下...");
                try {
                    Thread.sleep(1000000);
                } catch (InterruptedException e) {
                    System.out.println(getName()+": 我還想睡，別吵我!!");
                }
                System.out.println(getName()+"醒了!");
            }
        };
        Thread huang = new Thread("小黃"){
            public void run(){
                System.out.println(getName()+":大槌80!小槌40!開始砸牆!");
                for (int i= 0;i<5;i++){
                    System.out.println(getName()+":80");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("匡噹");
                System.out.println(getName()+":大哥!搞定");
                lin.interrupt();//中斷lin線程的睡眠阻塞
            }
        };
        lin.start();
        huang.start();
    }
}
