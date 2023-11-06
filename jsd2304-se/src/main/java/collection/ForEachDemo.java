package collection;

import java.util.ArrayList;
import java.util.Collection;

/**
 * JDK8之後，java在集合Collection接口中添加了一個用於遍歷集合元素的 forEach
 * 方法。可以基於 lambda表達式遍歷集合元素。
 */
public class ForEachDemo {
    public static void main(String[] args) {
        Collection<String> c = new ArrayList<>();
        c.add("one");
        c.add("two");
        c.add("three");
        c.add("four");
        c.add("five");
        //新循環方式(迭代器方式)
        for (String e:c){
            System.out.println(e);
        }

        //tips:當lambda中只有一個參數時，參數列表的"()"可以忽略不寫
        c.forEach(e-> System.out.println(e));

         /*
            JDK8中出现的lambda表達式的變種寫法:方法引用
            對象::方法
            當lambda表達式的參數與方法體中調用方法的參數一致時
            例如:
            (e)->System.out.println(e);
            那麼就可以寫作:
            System.out::println;
                  對象::方法
            現在以了解為主即可
         */
        c.forEach(System.out::println);
    }
}
