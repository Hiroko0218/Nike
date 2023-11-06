package exception;

/**
 * 異常常見的方法
 */
public class ExceptionApiDemo {
    public static void main(String[] args) {
        System.out.println("程序開始了!");

        try {
            String line = "abc";
            System.out.println(Integer.parseInt(line));
        } catch (NumberFormatException e) {
            e.printStackTrace();//向控制台輸出當前異常的堆栈追踪信息
            String massage = e.getMessage();//獲取錯誤消息
            System.out.println(massage);
        }
        System.out.println("程序結束了");
    }
}
