package collection;

import java.util.*;

/**
 * 集合的排序
 * java.util.Collections是集合的工具類，提供了很多static方法用於操作集合
 * 其中提供了一個名為sort的方法，可以對 List集合進行自然排序(從小到大)
 */
public class SortListDemo {
    public static void main(String[] args) {
        Random random = new Random();
        List<Integer> list = new ArrayList<>();
        for (int i = 0;i<10;i++){
            list.add(random.nextInt(100));
        }
        System.out.println(list);
//        Collections.sort(list);
//        System.out.println(list);

        Collections.sort(list, new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o1-o2;//由小到大，o2-o1:由大到小
            }
        });
        System.out.println("排序後: "+list);

        Collections.shuffle(list);//shuffle 洗牌
        System.out.println("打亂後: "+list);
    }
}
