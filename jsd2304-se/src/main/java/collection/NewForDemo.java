package collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * JDK5之后，java推出了一个新的特性:增强型for循环，也成为了新循环
 * 新循环不取代传统for循环的工作，【它只用来遍历集合或数组】
 * 语法:
 * for(元素类型 变量名 : 数组或集合){
 *     循环体...
 * }
 *
 *
 * JDK5推出的另一个新特性:泛型
 * 泛型又称为参数化类型，是当我们使用一个类时，可以去指定该类中某个属性或者方法的
 * 参数或者方法的返回值的类型。使得我们使用该类更灵活。
 * 泛型在集合中应用十分广泛，用于规定集合中的元素类型。
 *
 */
public class NewForDemo {
    public static void main(String[] args) {
        String[] arr = {"one","two","three","four","five","six"};
        for(int i=0;i<arr.length;i++){
            String e = arr[i];
            System.out.println(e);
        }

        /*
            新循環是編譯器認可的，在編譯時會將其改為傳統for循環遍歷數組
         */
        for(String e : arr){
            System.out.println(e);
        }

        //泛型在使用時可以指定確定類型，若不指定則為默認原型:Object
        Collection<String> c = new ArrayList<>();
        c.add("one");
        c.add("two");
        c.add("three");
        c.add("four");
        c.add("five");
//        c.add(123);//編譯不通過，add(E e)在這裡E被當作String類型。

        /*
            新循環遍歷集合會被編譯器改為選迭代器遍歷
            因此，使用新循環遍歷集合的過程中，不要通過集合的方法增删元素
            否則迭代器依然會抛出異常!
         */
        for(String s : c){
            System.out.println(s);
        }

        //迭代器也支持泛型，定義時與其遍歷的集合指定的泛型一致即可。
        Iterator<String> it = c.iterator();
        while(it.hasNext()){
//            String s = it.next();//獲取元素時無須再造型
            System.out.println( it.next());
        }
    }
}
