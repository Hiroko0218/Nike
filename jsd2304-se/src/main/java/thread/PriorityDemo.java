package thread;

/**
 * 線程的優先級
 * 線程有10個優先级,分别用整数1-10表示
 * 其中1為最低優先级,5為默認優先級,10為最高優先级
 *
 * 線程提供的方法:
 * void setPriority(int priority)
 * 設置線程的優先级
 *
 * 當我們調用一個線程start方法後該線程就會纳入到線程調度器中被统一管理.此時
 * 線程只能被動的被分配時間片並發執行.線程不能主動索取時間片.
 * 調整線程的優先級先級可以最大程度的改善獲取時間片的概率,優先級高的線程獲取時間片的次數多.
 */
public class PriorityDemo {
    public static void main(String[] args) {
        Thread min = new Thread(){
            public void run(){
                for (int i = 0;i<10000;i++){
                    System.out.println("min");
                }
            }
        };
        Thread max = new Thread(){
            public void run(){
                for (int i = 0;i<10000;i++){
                    System.out.println("max");
                }
            }
        };
        Thread norn = new Thread(){
            public void run(){
                for(int i=0;i<10000;i++){
                    System.out.println("nor");
                }
            }
        };
//        min.setPriority(1);
        min.setPriority(Thread.MIN_PRIORITY);
//        min.setPriority(10);
        min.setPriority(Thread.MAX_PRIORITY);
        min.start();
        norn.start();
        max.start();
    }
}
