package integer;

/**
 * 包裝類中定義了常量表達其表示的基本類型的取值範圍
 *
 * 包裝類提供了一個靜態方法: parseXXX(String str)
 * 將字符串解析為對應的基本類型
 * String->基本類型
 *
 */
public class IntegerDemo2 {
    public static void main(String[] args) {
        int imax = Integer.MAX_VALUE;
        System.out.println(imax); //int能容納的最大值
        int imin = Integer.MIN_VALUE;
        System.out.println(imin); //int能容納的最小值

        long lmax = Long.MAX_VALUE;
        System.out.println(lmax); //long能容納的最大值


        String s1 = "123";
        int i = Integer.parseInt(s1);//字符串轉為int類型
        System.out.println(i+1);

        double d = Double.parseDouble(s1);//字符串轉為double類型
        System.out.println(d);//123.0

        String s2 = "123.123";
        /*
            包裝類將字符串轉換為基本類型時，要求該字符串要正確描述基本類型
            可以保存的值，否則會抛出NumberFormatException
                                數字   格式  異常
         */

//        i = Integer.parseInt(s2);
//        System.out.println(i);
        d = Double.parseDouble(s2);
        System.out.println(d);//123.123

    }
}
