package collection;

import java.util.ArrayList;
import java.util.Collection;


/**
 * 集合受元素equals影響操作:
 * contains判斷包含
 * remove删除元素
 */
public class CollectionDemo2 {
    public static void main(String[] args) {
        Collection c = new ArrayList();
        c.add(new Point(1, 2));
        c.add(new Point(3, 4));
        c.add(new Point(5, 6));
        c.add(new Point(7, 8));
        c.add(new Point(9, 0));
        c.add(new Point(1, 2));
         /*
            集合重寫了toString，格式為:
            [元素1.toString(), 元素2.toString(), 元素3.toString(), ...]

            由此可見，想更直觀的體現元素時，元素也需要妥善重寫toString方法。
         */
        System.out.println(c);
        // 未重寫toString前:[collection.Point@1b6d3586,.....]，
        // 重寫toString後:[(1,2), (3,4), (5,6), (7,8), (9,0), (1,2)]


           /*
            contains: 包含
            boolean contains(Object o)
            判斷當前集合是否包含给定元素，若包含則返回true，否則返回false
            這裡判斷包含的邏輯是: 给定元素是否與集合現有元素存在equals比較為
            true的情況，如果有則認為包含該元素。否則為不包含。
         */
        Point p = new Point(1, 2);
        boolean contains = c.contains(p);
        System.out.println("是否包含p: " + contains);
        //未重寫equals前: false
        //重寫equals後: true


        /*
            boolean remove(Object o)
            删除當前集合中给定元素。
            删除的邏輯也是删除集合中與参數给定元素equals比較為true的元素。

            注意:如果集合存在重複元素，那麼在删除重複元素時，remove方法
            僅會删除一次。
         */
        c.remove(p);
        System.out.println(c);//[(3,4), (5,6), (7,8), (9,0), (1,2)]-->僅會刪除第一個(1,2)
    }
}
