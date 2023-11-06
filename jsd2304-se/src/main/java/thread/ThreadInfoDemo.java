package thread;

/**
 * 獲取線程相關信息的一组方法
 */
public class ThreadInfoDemo {
    public static void main(String[] args) {
        //獲取主線程,並查看其相關信息
        Thread main = Thread.currentThread();
        String name = main.getName();
        System.out.println("線程的名字:"+name);
        long id = main.getId();
        System.out.println("id:"+id);
        int priority = main.getPriority();
        System.out.println("優先級:"+priority);
        boolean daemon = main.isDaemon();
        System.out.println("是否為守護線程:"+ daemon);
        boolean isAlive = main.isAlive();
        System.out.println("是否活著:"+isAlive);
        boolean isInterrupted = main.isInterrupted();
        System.out.println("是否被中斷:"+isInterrupted);
    }
}
