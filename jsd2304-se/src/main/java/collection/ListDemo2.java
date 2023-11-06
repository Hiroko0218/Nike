package collection;

import java.util.ArrayList;
import java.util.List;

/**
 * List重載了一對add,remove方法
 */
public class ListDemo2 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        list.add("five");
        System.out.println(list);//[one, two, three, four, five]

        /*
            void add(int index,E e)
            將给定元素插入到集合指定位置
            [one, two, six, three, four, five]
         */
        list.add(2,"six");
        System.out.println(list);//[one, two, six, three, four, five]

        /*
            E remove(int index)
            删除並返回指定位置上的元素

            [one, two, six, four, five]
         */
        String old = list.remove(3);
        System.out.println(list);//[one, two, six, four, five]
        System.out.println("被刪除的元素是: "+old);//被刪除的元素是: three
    }
}
