package thread;

/**
 * 同步塊
 * 有效的缩小同步範圍可以在保證併發安全的前提下提高併發效率
 * 語法:
 * synchronized(同步監視器對象){
 *     需要同步執行的代碼片段
 * }
 */
public class SyncDemo2 {
    public static void main(String[] args) {
        Shop shop = new Shop();
//        Shop shop1 = new Shop();
//        Shop shop2 = new Shop();
        Thread t1 = new Thread("王克晶"){
            @Override
            public void run() {
                shop.buy();
//                shop1.buy();//與t2不存在競争，buy方法中的同步塊應失效
            }
        };
        Thread t2 = new Thread("范傳奇"){
            @Override
            public void run() {
                shop.buy();
//                shop2.buy();//與t1不存在競争，buy方法中的同步塊應失效
            }
        };
        t1.start();
        t2.start();
    }
}
class Shop{
    /*
        在方法上使用synchronized時，同步監視器對象不可選，只能是默認的this
     */
//    public synchronized void buy(){
    public void buy(){
        try {
            Thread t = Thread.currentThread();
            System.out.println(t.getName()+": 正在挑衣服...");
            Thread.sleep(5000);
            /*
                使用同步塊可以更準確的鎖定需要多個線程同步執行的代碼片段
                這裡使用時要指定同步監視器對象，"()"中的内容。
                它的要求:
                1:必須是一個引用類型的實例
                2:多個需要同步執行該同步塊的線程看到的必须是同一個對象

                合適的所對象:
                應當是當多個線程存在"搶"的問题時才限制他們必須排隊進行，
                不存在"搶"的問題時可以同時進行。
             */
            synchronized (this) {
//            synchronized (new Object()) { //new一定無效
//            synchronized ("a") { //不是合適的鎖對象，當線程調用不同商店的buy方法時，它也要求排隊
                System.out.println(t.getName() + ":正在試衣服...");
                Thread.sleep(5000);
                System.out.println(t.getName()+":結帳離開...");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
