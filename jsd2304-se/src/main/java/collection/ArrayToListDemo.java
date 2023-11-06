package collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 數組轉換為集合
 * 數組的工具類 Arrays提供了一個靜態方法 asList
 * 可以將一個數組轉換為一個 List集合
 */
public class ArrayToListDemo {
    public static void main(String[] args) {
        String[] array = {"one","two","three","four","five"};
        System.out.println("array: "+ Arrays.toString(array));

        /*
            asList方法内部:
            創建一個名為ArrayList的實例(注意，它不是java.util.ArrayList
            而是Arrays自己定義的一個内部類)。該實例會直接引用傳入的數組。
            因此對該集合的操作就是對該數組的操作。
         */
        List<String> list = Arrays.asList(array);
        System.out.println("list: "+list);

        //注意，對該集合的元素操作就是對原數組的元素操作
        list.set(2,"six");
        System.out.println("list: "+ list);
        System.out.println("array: "+ Arrays.toString(array));

        //因為數組定長，所以試圖改變長度的操作都是不支持的，會抛出不支持操作異常
        //java.lang.UnsupportedOperationException
//        list.add("seven");

        /*
            可以自行創建一個新集合，然後包含前面數組轉換的集合，這樣就可以
            隨意的修改元素了且不會對原數組產生影響。
         */

         /*
            所有的集合都支持一種参數為Collection的構造器
            作用是在創建該集合的同時就包含参數傳入的集合中所有元素
            List<String> list2 = new ArrayList<>(list);
            上面的實例化等同於：
            List<String> list2 = new ArrayList<>();
            list2.addAll(list);
         */
        List<String> list2 =new ArrayList<>(list);
        list2.add("seven");
        System.out.println("list2: "+list2);
    }
}
