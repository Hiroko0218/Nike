package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 排序字符串
 */
public class SortListDemo3 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("tom");
        list.add("jerry");
        list.add("jack");
        list.add("mike");
        list.add("Ada");
        list.add("marray");
        list.add("kobe");
        list.add("James");
        list.add("hanmeimei");
        Collections.sort(list);
        System.out.println(list);//先依大小寫，再依A-Z排序


        List<String> list2 =new ArrayList<>();
        list2.add("王克晶");
        list2.add("傳奇老師");
        list2.add("劉桑");
        System.out.println(list2);
//        Collections.sort(list2);////字符串自身的比較規則不滿足對中文的排序
//        System.out.println(list2);

        //可以為String自定義一種比較規則對集合排序
        Collections.sort(list,new Comparator<String>(){
            public int compare(String o1, String o2) {
                return o1.length()-o2.length();//字多的大，字少的小
            }
        });
        System.out.println(list2);

        Collections.reverse(list2);//反轉
        System.out.println(list2);
    }
}
