package collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * java集合框架
 * java.util.Collection接口，是所有集合的頂級接口，規定了集合所必需的功能
 * <p>
 * 集合與數組一樣，可以保存一组具有相同類型元素數據結構
 * 並且提供了對於元素的維護操作(方法)。
 * 集合有多種不同的數據結構可供日後開發選擇使用。
 * <p>
 * Collection下面有很多细分的集合類
 * 常見的兩個子類别(下述兩個也是接口):
 * java.util.List: List代表的是可重複且有序的集合
 * java.util.Set: Set代表的是不可重複的集合
 */
public class CollectionDemo1 {
    public static void main(String[] args) {
        Collection c = new ArrayList();
//        Collection c= new HashSet();

        /*
            boolean add(E e)
            集合提供了添加元素的方法，如果元素成功存入集合則返回true。
         */
        boolean success = c.add("one");
        System.out.println("是否成功存入:" + success);//true

        c.add("two");
        c.add("three");
        c.add("four");
        c.add("five");
        c.add("six");
        System.out.println(c);//true
        success = c.add("one");//對於HashSet而言，本次返回值就是false，没有存入集合
        System.out.println("是否成功存入:" + success);
        System.out.println(c);
        // ArrayList(): [one, two, three, four, five, six, one]
        // HashSet(): [six, four, one, two, three, five]

        /*
            int size()
            返回當前集合元素個素
         */
        int size = c.size();
        System.out.println("集合元素數:" + size);

         /*
            boolean isEmpty()    empty:空的
            判斷當前集合是否為空集
            當當前集合size為0時，該方法返回true。
         */
        boolean isEmpty = c.isEmpty();
        System.out.println("是否為空集" + isEmpty);//false

        /*
            void clear()
            清空集合
         */
        c.clear();
        System.out.println(c);//[]
        System.out.println("集合的元素個數: " + c.size());//0
        System.out.println("是否為空集: " + c.isEmpty());//true
    }
}
