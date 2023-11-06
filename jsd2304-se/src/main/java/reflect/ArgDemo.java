package reflect;

/**
 * JDK5之後推出了一個特性:變長參數
 */
public class ArgDemo {
    public static void main(String[] args) {
        /*
            所有邊長参的實参都會被編譯器改為數组
            dosome(new String[]{"1"});
            dosome(new String[]{"1","2"});
            dosome(new String[]{"1","2","2","2","2","2","2","2","2","2","2","2","2","2","2"});
         */
        dosome("1");
        dosome("1","2");
        dosome("1","2","2","2","2","2","2","2","2","2","2","2","2","2","2");
    }

    /**
     * 編譯後
     * public static void dosome(String[] s){
     *         System.out.println(s.length);
     * }
     *
     * 變長參數一個方法只能有一個，且必須是最後一個參數
     */
    public static void dosome(String... s){
        System.out.println(s.length);
    }
}
