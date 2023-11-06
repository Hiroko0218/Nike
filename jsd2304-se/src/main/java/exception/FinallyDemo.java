package exception;

/**
 * finally塊
 * finally塊是異常處理機制中的最後一塊.它可以直接跟在try之後或者最後一個catch之後
 *
 * finally的特點:只要程序執行到 try當中,無論 try中的代碼是否出現異常,最终都要執行 finally中的代碼
 *
 * 通常使用finally用於完成資源釋放這類操作,比如 IO後的關閉操作.
 */
public class FinallyDemo {
    public static void main(String[] args) {
        System.out.println("程序開始了!");
        try {
//        String line = null;
            String line = "abc";
            System.out.println(line.length());
            //當try中某句代碼出现異常後,try中剩余代碼就不會被執行了
            return; //try中就算執行到return,也要在结束方法前先走完下面的finally
        } catch (Exception e) {
            System.out.println("出錯了!");
        } finally {
            System.out.println("finally中的代碼執行了!");
        }
        System.out.println("程序結束了");
    }
}
