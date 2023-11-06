package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 排序包含自定義類型元素的集合
 */
public class SortListDemo2 {
    public static void main(String[] args) {
        List<Point> list = new ArrayList<>();
        list.add(new Point(1, 2));
        list.add(new Point(15, 28));
        list.add(new Point(91, 52));
        list.add(new Point(13, 68));
        list.add(new Point(4, 8));
        System.out.println(list);

        /*
            sort方法在排序時要求集合元素必須實現:Comparable接口
            compare:比較

            開發中我們定義的類不會去實現Comparable接口。原因:
            該排序功能對我們的代碼具有侵入性。

            侵入性:
            當我們使用某個API功能時，其放過来要求我們為它實現接口或做某些繼承
            並定義相關方法時，可以認定，該API功能對我們的代碼具有侵入性。
         */
//        Collections.sort(List);//編譯不通過

//        MyComparator comparator = new MyComparator();//此方法有侵略性
//        Comparator<Point> comparator = new Comparator<Point>() {
//            public int compare(Point o1, Point o2) {
//                int olen1 = o1.getX() * o1.getX() + o1.getY() * o1.getY();
//                int olen2 = o2.getX() * o2.getX() + o2.getY() * o2.getY();
//                return olen1 - olen2;
//            }
//        };
//        Collections.sort(list, comparator);

        //因只有使用一次可使用匿名內部類，無須再創一個類，比上面更簡短。
        Collections.sort(list, new Comparator<Point>() {
            public int compare(Point o1, Point o2) {
                int olen1 = o1.getX() * o1.getX() + o1.getY() * o1.getY();
                int olen2 = o2.getX() * o2.getX() + o2.getY() * o2.getY();
                return olen1 - olen2;
            }
        });
        System.out.println(list);
    }
}
    class MyComparator implements Comparator<Point>{
        @Override
        public int compare(Point o1, Point o2) {
            /*
                比較標準:點到原點的距離長的大小
             */
            int olen1 = o1.getX()* o1.getX()+ o1.getY()*o1.getY();
            int olen2 = o2.getX()* o2.getX()+ o2.getY()*o2.getY();
             /*
                判斷大小的返回值:
                當返回值>0時，表達o1>o2
                當返回值<0時，表達o1<o2
                當返回值=0時，表達o1=o2
            */
            return olen1-olen2;
        }
    }

