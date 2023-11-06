package thread;

/**
 * 互斥鎖
 *
 * 當使用多個 synchronized鎖定多個代碼片段，並且指定的同步監視器對象相同時
 * 這些代碼片段之間就是互斥的
 */
public class SyncDemo4 {
    public static void main(String[] args) {
        Foo foo = new Foo();
//        Thread t1 = new Thread(()->foo.methodA());
        Thread t1 =new Thread(foo::methodA);
        Thread t2 = new Thread(foo::methodB);
        t1.start();
        t2.start();
    }
}
class Foo{
    public synchronized void methodA(){
        Thread t = Thread.currentThread();
        System.out.println(t.getName()+":正在執行A方法...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t.getName()+":執行A方法完畢!");
    }
    public synchronized void methodB(){
        synchronized (this) {
            Thread t = Thread.currentThread();
            System.out.println(t.getName()+":正在執行B方法...");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(t.getName()+":執行B方法完畢!");
        }
    }
}