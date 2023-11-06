package collection;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 集合可以保存一组相同類型的元素。
 * 但是集合只能存放【引用類型】元素,並且存放的是該引用類型對象的地址
 */
public class CollectionDemo3 {
    public static void main(String[] args) {
        Collection c = new ArrayList();
//        c.add(123);//c.add(Integer.valueOf(123))

        Point p = new Point(1,2);
        c.add(p);
        System.out.println("p:"+p);//p:(1,2)
        System.out.println("c:"+c);//c:[(1,2)]

        p.setX(2);//將p對象的屬性x的值改為2
        System.out.println("p:"+p);//p:(2,2)
        System.out.println("c:"+c);//c:[(2,2)]
    }
}
