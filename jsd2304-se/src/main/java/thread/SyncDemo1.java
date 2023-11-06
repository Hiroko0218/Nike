package thread;

/**
 * 多線程並發的安全問題
 * 當多個線程並發操作同一臨界資源，由於線程切換實際不確定，會導致操作順序未按照
 * 程序設計過程執行，導致操作出現混亂，從而產生不良後果。
 *
 * 臨界資源:操作該資源的完整步驟同一時刻只能由單一一個線程進行。
 */
public class SyncDemo1 {
    public static void main(String[] args) {
        Table table = new Table();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                while (true){
                    int bean =table.getBeams();
                    Thread.yield();//主動讓運行該方法的線程放棄本次剩余時間片
                    System.out.println(getName()+":"+bean);
                }
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                while (true){
                    int bean =table.getBeams();
                    Thread.yield();//主動讓運行該方法的線程放棄本次剩余時間片
                    System.out.println(getName()+":"+bean);
                }
            }
        };
        t1.start();
        t2.start();
    }
}
class Table{
    private int beams = 20;//桌上有20個豆子
    /**
     * 當一個方法使用synchronized關鍵字修飾後，該方法稱為"同步方法"
     * 即: 多個線程不能同時在方法内部執行。
     *
     * 當多個線程並發調用同一個方法改為有先後順序的同步執行時可有效的解決
     * 多線程並發安全問題。
     */
    public synchronized int getBeams(){
        if(beams==0){//桌上是否還有豆子?
            throw new RuntimeException("沒有豆子了");
        }
        Thread.yield();//主動讓運行該方法的線程放棄本次剩余時間片
        return beams--;
    }
}
