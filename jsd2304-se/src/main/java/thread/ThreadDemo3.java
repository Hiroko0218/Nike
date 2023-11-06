package thread;

/**
 * 使用匿名内部類完成線程的两種創建方式
 */
public class ThreadDemo3 {
    public static void main(String[] args) {
        Thread t1 = new Thread(){
            public void run(){
                for (int i = 0; i<1000;i++){
                    System.out.println("你是谁啊?");
                }
            }
        };
//        Runnable r2 = new Runnable() {
//            public void run() {
//                for(int i=0;i<1000;i++){
//                    System.out.println("開門!查水表的!");
//                }
//            }
//        };
//        Thread t2 = new Thread(r2);

        //lambda创建Runnable
        Thread t2 = new Thread(()->{
                for (int i = 0; i<1000;i++){
                    System.out.println("開門!查水表的!");
                }
        });

        t1.start();
        t2.start();
    }
}
