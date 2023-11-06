package exception;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * JDK7之後,java推出了一個新的特性:自動關閉特性
 * 可以在異常處理機制中更優雅的關閉流這類需要調用close關閉的類
 */
public class AutocloseableDemo {
    public static void main(String[] args) {
        try(
//          try()中可以定義最终要在finally中調用close的那些類
//          實際上只有實現了java.io.AutoCloseable接口的類才可以在這裡定義
//          java中所有的流都實現了AutoCloseable接口
//          該特性是編譯器認可的,最终在try()中定義的類會在finally中被調用close關閉,相當於FinallyDemo2的樣子
                FileOutputStream fos = new FileOutputStream("fos.dat");
        ){
            int a = 1;
            fos.write(a);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
