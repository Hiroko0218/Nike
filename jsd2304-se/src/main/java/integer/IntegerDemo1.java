package integer;

/**
 *  包裝類
 *  java為8個基本類型提供了8個包裝類。
 *  包裝類的出現是為了解決8個基本類型不能直接参與面向對象開發的問題。本質上講
 *  就是可以通過包裝類讓基本類型以"對象"的形式出現。
 *
 *  int->Integer
 *  char->Character
 *  其他包妝類的類名與基本類型一致，只是首字母大寫。
 *  double->Double
 *  ...
 */
public class IntegerDemo1 {
    public static void main(String[] args) {
        //基本類型轉換為包裝類
        int i = 128;
//        Integer i1 = new Integer(i);
//        Integer i2 = new Integer(i);
        /*
            包裝類提供了静態方法valueOf,可以將對應的基本類型轉換為包裝類
            推薦這種方式，而不是直接new。
            Integer的valueOf會像字符串String一样，去緩存對象。
            但是它只缓存-128到127之内的數字。其餘的全部為new。
         */

        Integer i1 = Integer.valueOf(i);
        Integer i2 = Integer.valueOf(i);
        System.out.println(i1==i2);//false

        //包裝類java提供時已經重寫過equals方法了，用來比較對象内容
        System.out.println(i1.equals(i2));//true


        double d = 123.123;
        //Double的valueOf就沒有做任何優化，内部就是new
        Double d1 = Double.valueOf(d);
        Double d2 = Double.valueOf(d);
        System.out.println(d1==d2);//是否為同一個對象
        System.out.println(d1.equals(d2));//内容是否相同

        /*
            數字類型的包裝類:
            Byte,Short,Integer,Long,Float,Double
            上述6個包裝類統一繼承自Number的類
            Number中定義了一组方法:
            int intValue()
            byte byteValue()
            short shortValue()
            ...
         */

        //獲取該包裝類對象保存的基本類型數據
        int in = i1.intValue();
        System.out.println(in);//128
        long lon = i1.longValue();//可以以其他基本類型返回
        System.out.println(lon);//128

        //溢出現象仍然存在
        byte b = i1.byteValue();//-128
        System.out.println(b);

        //小數的包裝類如果轉換為整數，會丢失精度
        in = d1.intValue();//d1:123.123 --> int:123
        System.out.println(in);//123

        double dou = d1.doubleValue();
        System.out.println(dou);//123.123
    }
}
