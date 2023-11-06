package collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * 集合之間的操作
 */
public class CollectionDemo4 {
    public static void main(String[] args) {
        Collection c1 = new ArrayList();
//        Collection c1 = new HashSet();
        c1.add("java");
        c1.add("c++");
        c1.add(".net");
        System.out.println("c1" + c1);
        //ArrayList(): c1:[java, c++, .net]
        //HashSet(): c1[c++, java, .net]

        Collection c2 = new ArrayList();
        c2.add("android");
        c2.add("ios");
        c2.add("java");//c1存在的元素
        System.out.println("c2:" + c2);//c2:[android, ios, java]

        /*
            boolean addAll(Collection c)
            將给定集合c中的所有元素添加到當前集合中。添加後當前集合元素發生了變化則返回true。
         */
        boolean addAll = c1.addAll(c2);
        System.out.println("c2集合是否有添加至c1集合: " + addAll);//true
        System.out.println("c1:" + c1);
        //ArrayList(): c1:[java, c++, .net, android, ios, java]
        //HashSet(): c1:[c++, java, android, .net, ios]--> 因java相同無法重複加入集合

        System.out.println("c2:" + c2);//c2:[android, ios, java]

        Collection c3 = new ArrayList();
        c3.add("java");
        c3.add("android");
        c3.add("php");
        System.out.println("c3:" + c3);//c3:[java, android, php]

        /*
            boolean containsAll(Collection c)
            判斷當前集合是否包含给定集合中的所有元素。
            全部包含則返回true，否則返回false
         */
        boolean containsAll = c1.containsAll(c3);
        System.out.println("c1是否包含c3的所有元素: " + containsAll);//false


        /*
            取交集操作
            boolean retainAll(Collection c)
            僅保留當前集合中與给定集合c的共有元素。
         */
        c1.retainAll(c3);
        System.out.println("c1:" + c1);
        //ArrayList(): c1:[java, android, java]
        //HashSet(): c1:[java, android]


         /*
            差集操作(删除交集部分)
            removeAll可以删除當前集合中與给定集合的公有元素
         */
        c1.removeAll(c3);
        System.out.println("c1:" + c1);//c1:[]
    }
}