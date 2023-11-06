package collection;

import java.util.ArrayList;
import java.util.List;

/**
 * List集合支持獲取子集操作
 *
 * List subList(int start,int end)
 * 截取當前集合中指定範圍内的子集。兩個下標含頭不含尾
 *
 */
public class ListDemo3 {
    public static void main(String[] args) {
        List<Integer>list = new ArrayList<>();
        for (int i = 0; i< 10;i++ ){
            list.add(i);
        }
        System.out.println(list);//[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

        //獲取list集合中[3,4,5,6,7]
        List<Integer> subList = list.subList(3,8);
        System.out.println(subList);//[3,4,5,6,7]

        //將集合中每個元素擴大10倍
        for (int i = 0 ; i<subList.size();i++){
            int num =subList.get(i);
            num = num*10;
            subList.set(i,num);
        }
        System.out.println(subList);//[30, 40, 50, 60, 70]
        System.out.println(list);//[0, 1, 2, 30, 40, 50, 60, 70, 8, 9]

        //將list集合中的2-8删除
        list.subList(2,9).clear();
        System.out.println(list);//[0, 1, 9]
    }
}
