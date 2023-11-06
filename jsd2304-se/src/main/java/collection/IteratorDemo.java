package collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 集合的遍歷
 *              die(二声)
 * 集合的遍歷是採取迭代器模式
 *
 * 集合提供了一個方法
 * Iterator iterator()
 * 該方法可以獲取一個用於遍歷當前集合的迭代器
 *
 * 迭代器遍歷的過程必須遵從：問->取->删(可選)
 */
public class IteratorDemo {
    public static void main(String[] args) {
        Collection c = new ArrayList();
        c.add("one");
        c.add("#");
        c.add("two");
        c.add("#");
        c.add("three");
        c.add("#");
        c.add("four");
        c.add("#");
        c.add("five");
        Iterator iterator = c.iterator();//獲取一個用於遍歷當前集合的迭代器

        /*
            boolean hasNext()
            詢問集合是否還有下一個元素
            E next()
            獲取集合下一個元素
         */
        while (iterator.hasNext()) {//詢問集合是否還有下一個元素
            String str = (String) iterator.next();//獲取集合下一個元素
            //書寫習慣: 字符串變量與字面比較時用字面量.equals(變量)，避免空指针的產生(因變量可能有null)
            //if(str.equals("#")){}
            if ("#".equals(str)){
//              迭代器要求遍歷的過程中不可以用集合的方法增删元素，否則會抛出異常
//              c.remove(str);
//              迭代器提供的remove可以删除集合中本次next獲取的元素
                iterator.remove();
            }
            System.out.println(str);
        }
        System.out.println(c);//[one, two, three, four, five]
    }
}
