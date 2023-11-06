package string;

import java.util.Arrays;

/**
 * split:拆分
 *
 * String[] split(String regex)
 * 將當前字符串按照滿足正則表達式要求的部分拆分，將拆分後的美部分字符串最終以
 * 數组的形式返回。
 *
 */
public class SplitDemo {
    public static void main(String[] args) {
        String line = "abc123def456ghi789jkl";
        //將line按照數字(0-9)部分進行拆分，得到其中所有的字母部分(a-z,A-Z)
        String[] data = line.split("[0-9]+");
        System.out.println(data.length);//4
//        for(int i=0;i<data.length;i++) {
//            System.out.println(data[i]);
//        }
        //Arrays.toString方法可以將一個數组轉換為一個字符串，便於輸出内容
        System.out.println(Arrays.toString(data));//[abc, def, ghi, jkl]

            /*
            abc123def456ghi789jkl
            ["", "123", "456", "789", ""]

            split拆分規則，如果字符串開始就可以出現可拆分項，則拆分出的第一個字符串為空字符串。
            str = "@a@b@c@d@e@f@"
            data = str.split("@");
            data:["", a, b, c, d, e, f]

            如果連續匹配到兩個可拆分項，則中間會拆分出一個空字符串
            str = "a@@b@c@d@e@f@"
            data = str.split("@");
            data:[a,"", b, c, d, e, f]

            如果是默認拆分出空字符串時，會被【全部忽略】
            str = "a@b@c@d@e@f@@@@@@@@"
            data = str.split("@");
            data:[a, b, c, d, e, f]
            */

        data = line.split("[a-z]+");
        System.out.println(data.length);//4
        System.out.println(Arrays.toString(data));//[, 123, 456, 789]


        String line2 = "a=b=c=d=e=f";
        //將line2按照"="進行拆分
        String[] data2 = line2.split("=");
        System.out.println(data2.length);//6
        System.out.println(Arrays.toString(data2));//[a, b, c, d, e, f]
        line2 = "===a=b=c=d=e=f";//最開始會拆分出三個空字符串
        data2 = line2.split("=");
        System.out.println(data2.length);//9
        System.out.println(Arrays.toString(data2));//[, , , a, b, c, d, e, f]
        line2 = "a=b=c=d=e=f======================";
        data2 = line2.split("=");//末尾的空字符串全部被忽略
        System.out.println(data2.length);//6
        System.out.println(Arrays.toString(data2));//[a, b, c, d, e, f]
    }
}
