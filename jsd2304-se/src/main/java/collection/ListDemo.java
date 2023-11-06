package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * java.util.List接口
 * List繼承自Collection。
 * List的特點:有序且可以重複。List行業禮稱為:線性表
 * List常用的實現類:
 * java.util.ArrayList: 内部使用數組時現，特點:查詢性能好，增删元素性能弱
 * java.util.LinkedList: 内部使用數組時現，特點:首尾增删元素性能好，查詢性能弱
 *
 * List接口中定義了一套可以通過下標操作元素的方法
 *
 */
public class ListDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        list.add("five");
        System.out.println(list);

        /*
            E get(int index)
            獲取List集合中指定下標處對應的元素
         */
        String e = list.get(2);//獲取第三個元素
        System.out.println(e);

        //List集合可以使用普通的for循環遍歷
        for (int i = 0; i<list.size();i++){
            e = list.get(i);
            System.out.println(e);
        }

        /*
            E set(int index, E e)
            將给定元素e設置到指定下標index處，返回值為被替換的元素
            該方法是替换元素操作
         */
        String old = list.set(3,"six");
        System.out.println(list);
        System.out.println("被替換元素:"+old);

        /*
            集合元素反轉
            兩兩交換。第一個和最後一個換，第二個和倒數第二個換。。。
            下標的計算可以参考曾經的回文案例
            [five, six, three, two, one]
               0    1          3    4
         */

        /** 第一種 */
//        for (int i =0; i<list.size()/2;i++){
//            //獲取正數位置的元素
//            String s = list.get(i);//one
//            //將正數位置的元素設置到對應的倒數位置上
//            s = list.set(list.size()-i-1,s);//s為被替換的元素five
//            //再將原倒數位置的元素設置到正數位置上
//            list.set(i,s);
//        }
//        System.out.println(list);//[five, six, three, two, one]
        /** 第二種 */
//        for (int i = 0; i < list.size() / 2; i++) {
//            list.set(i, list.set(list.size() - 1 - i, list.get(i)));
//        }
//        System.out.println(list);//[five, six, three, two, one]
        /** 第三種 */
//        for(int i=0;i<list.size()/2;list.set(i,list.set(list.size()-1-i,list.get(i++))));
//        System.out.println(list);//[five, six, three, two, one]
        /** Collections是集合的工具類，提供了很多静態方法操作集合 */
        Collections.reverse(list);//反轉List集合
        System.out.println(list);//[five, six, three, two, one]
    }
}
