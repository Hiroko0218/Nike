package thread;

/**
 * 守護線程
 *
 * 守護線程是通過用戶線程(普通線程,線程創建出来默認就是用戶線程)調用方法:
 * setDaemon(true)設置而来的.
 *
 * 守護線程與用戶線程的主要區别是進程结束:
 * 當java進程中所有的用戶線程都结束時,該進程就會结束,此時在進程结束前會强行殺死
 * 所有還在運行的守戶線程
 *
 * GC就是運行在守護線程上的。
 * 當一個任務需要單獨並發執行，但是該任務何時停止沒有確切時間，只要我們主要的
 * 工作都執行完畢它可以跟著结束時，那麼該任務就非常適合放在守護線程上執行了。
 */
public class DaemonThreadDemo {
    public static void main(String[] args) {
        Thread rose = new Thread("rose"){
            public void run(){
                for (int i=0;i<5;i++){
                    System.out.println(getName()+":let me go!");
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(getName()+":啊啊啊啊啊AAAAAAAAAAAaaaaaa....");
                System.out.println("pai!");
            }
        };

        Thread jack = new Thread("jack"){
            public void run(){
                while (true){
                    System.out.println(getName()+":you jump!i jump!");
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        //設置守護線程必須在該線程啟動前進行
        jack.setDaemon(true);//守護線程守護所有線程，未指定固定線程

        rose.start();
        jack.start();

//        while (true);//因main線程未執行完畢，所以jack線程會繼續執行
    }
}
