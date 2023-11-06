package object;

public class TestPoint {
    public static void main(String[] args) {
        Point p = new Point(1,2);
        /*
            System.out.println(Object o)
            將給定對象toString方法返回的字符串輸出到控制台
            toString方法是Object提供的方法，該方法默認返回的字符串為: 類名@地址

         */
        System.out.println(p);//Point{x=1, y=2}

        System.out.println(p.toString());//Point{x=1, y=2}
        /*
            任何類型與字符串連接结果都是字符串
            這意味着連接的類型會被轉為字符串再進行連接。其他類型是如何
            轉換為String?就是依靠toString方法
         */
        String line = "當前對象是:" + p;
        System.out.println(line);


        Point p2 = new Point(1,2);
        System.out.println("p2:"+p2);
        //==對於引用類型的意義是比較是否為同一個對象
        System.out.println(p==p2);//false  兩個不同的對象
        //equals則是比較兩個對象"像不像"(對象内容，特徵是否一致)
        System.out.println(p.equals(p2));//true 兩個對象的内容相同
    }
}
