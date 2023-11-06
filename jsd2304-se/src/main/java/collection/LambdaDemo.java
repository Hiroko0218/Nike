package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Lambda表達式-JDK8之後推出的新特性
 * 語法:
 * (參數列表)->{
 *     方法體
 * }
 *
 * 當使用匿名内部類創建時，如果實現的接口只有一個抽象方法，則可以使用 lambda表達式代替，使代碼更簡潔優雅。
 *
 * 在 java中可以使用 lambda表達式代替匿名内部类創建所需要實現的
 * 接口時，該接口上都有一個註解: @FunctionalInterface
 *
 */
public class LambdaDemo {
    public static void main(String[] args) {
        //自定義比較器的匿名内部類寫法
        Comparator<String> c1 = new Comparator<String>() {
            public int compare(String o1, String o2) {
                return o1.length()-o2.length();
            }
        };

        //使用 lambda表達式
        //lambda表達式就是省去了匿名内部類創建是接口與方法名部分
        Comparator<String> c2 = (String o1, String o2)->{
                return o1.length()-o2.length();
        };

        //lambda表達式可以忽略參數的類型
        Comparator<String> c3 = ( o1, o2)->{
                return o1.length()-o2.length();
        };

        //如果方法中只有一句代碼時，該方法體的"{}"可以忽略不寫
        //如果這句代碼含有return關鍵字時，也要一同忽略return
        Comparator<String> c4 = (o1,o2) -> o1.length()-o2.length();

        List<String> list = new ArrayList<>();
        list.add("王克晶");
        list.add("傳奇老師");
        list.add("劉桑");
        System.out.println(list);
        //lambda表達式實際上是編譯器認可的，最终會被改回為内部類方式創建
        //源代碼中使用lambda可以更突出重点，原匿名内部類中重寫方法的邏輯。
//        Collections.sort(list,(o1, o2)->o1.length()-o2.length());

        /*
            JDK8之後，List集合自己推出了一個sort方法，可以排序自身元素
            並且需要傳入一個比較器來定義比較規則。
         */
        list.sort((o1,o2)->o1.length()-o2.length());
        System.out.println(list);

    }
}
