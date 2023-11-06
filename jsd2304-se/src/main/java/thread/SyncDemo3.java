package thread;

/**
 * 靜態方法上使用 synchronized時，該方法一定具有同步效果
 *
 * 静態方法上鎖對象不可選的，默認為當前類的類對象(Class類的實例)
 * Class類:它的每一個實例被稱為類對象。
 * JVM加載一個類時，就會同時實例化一個Class的實例並保存加載類的信息，而且該
 * Class的實例對於每個被加載的類都有且只有一個實例對應。
 *
 * Class具體的知識會在後續反射機制中被介绍
 */
public class SyncDemo3 {
    public static void main(String[] args) {
        Boo b1 = new Boo();
        Boo b2 = new Boo();
        Thread t1 =new Thread(){
            @Override
            public void run() {
//                Boo.dosome();
                b1.dosome();

            }
        };
        Thread t2 =new Thread(){
            public void run() {
//                Boo.dosome();
               b2.dosome();
            }
        };
        t1.start();
        t2.start();
    }
}

class Boo{
//    public static synchronized void dosome(){
    public static void dosome(){
        //在静態方法中使用同步塊時，也使用類對象作为同步監視器對象
        synchronized (Boo.class) {
            Thread t =  Thread.currentThread();
            System.out.println(t.getName()+"正在執行dosome方法");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(t.getName()+":執行dosome方法完畢!");
        }
    }
}