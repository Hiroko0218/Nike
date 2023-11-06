package thread;

/**
 * java中所有的代碼都是線程運行的.main方法也不例外.
 * 當JVM啟動後,就會自動創建一條線程来執行main方法.這條運行 main方法的線程被
 * JVM取名為"main",因此也被我們稱為"主線程"
 *
 * Thread提供了一個静態方法:
 * static Thread currentThread()
 *
 * current:當前的
 *
 * 該方法可以在代碼的任意位置獲取執行該方法的線程
 */
public class CurrentThreadDemo {
    public static void main(String[] args) {
        Thread main = Thread.currentThread();//在main方法中可以用來獲取主線程
        System.out.println("執行main方法的線程是:"+main);
        dosome();
    }
    public static void dosome(){
        Thread t = Thread.currentThread();
        System.out.println("運行dosome方法的線程是:"+t);
    }
}
