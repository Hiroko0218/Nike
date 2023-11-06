package collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * 集合轉換為數組
 * Collection提供了一個方法toArray。可以將當前集合轉換為一個數組
 */
public class CollectionToArrayDemo {
    public static void main(String[] args) {
        Collection<String> c =new ArrayList<>();
        c.add("one");
        c.add("two");
        c.add("three");
        c.add("four");
        c.add("five");
        System.out.println(c);

        /*
            重載的toArray方法要求我們傳入一個需要轉換的數組，
            長度通常與集合size一致即可。
            如果數組長度小於集合size,那麼toArray方法内部會創建
            一個與該數組類型且與集合size等長的數組將其返回
         */

//        Object[] array = c.toArray();//返回對象數組不方便
        String[] array = c.toArray(new String[c.size()]);
//        String[] array = c.toArray(new String[0]);
        System.out.println("數組長度: "+array.length);
        System.out.println(Arrays.toString(array));
    }
}
