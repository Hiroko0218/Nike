package string;

/**
 * java.lang.StringBuilder
 * 專門用來修改字符串内容的工具類
 * 其提供了對字符串内容编辑的: 增删 改插 的主要功能
 *
 * StringBuilder内部維護一個可變char數組，所以修改内容不會創建新的String對象
 */
public class StringBuilderDemo1 {
    public static void main(String[] args) {
        String str = "好好學習java";

        //默認的無參構造器創建時内部表示空字符串:""
        StringBuilder b = new StringBuilder();
        System.out.println(b);
        //該構造器可以基於一個给定的字符串進行编辑
        StringBuilder builder = new StringBuilder(str);

         /*
            StringBuffer與StringBuilder功能一致
            區别:
            StringBuffer出现於JDK1.0   StringBuilder出现於1.5
            最主要的區别:StringBuffer是開發安全的。StringBuilder不是
         */
        StringBuffer builderBuffer = new StringBuffer(str);

        /*
            增:
            append()方法，用来將给定内容添加到字符串末尾
            "好好學習java"
                 V
            "好好學習java,為了找個好工作!"
         */
        builder.append(",為了找個好工作!");

        String line = builder.toString();
        System.out.println(line);//好好學習java,為了找個好工作!

        /*
            改:
            replace()
            將當前字符串中指定範圍内的内容替換為新内容
            "好好學習java,為了找個好工作!"
                        V
            "好好學習java,就是為了改變世界!"
         */
        //              注意，下標含頭不含尾
        builder.replace(9,16,"就是為了改變世界");
        System.out.println(builder.toString());//好好學習java,為了改變世界!

        /*
            删:
            delete()
            將當前字符串中指定範圍内的字符串删除

            "好好學習java,就是為了改變世界!"
                        V
            ",就是為了改變世界!"
         */
        builder.delete(0,8);//下標含頭不含尾
        System.out.println(builder);//不加toString默認也是輸出toString返回值

        /*
            插:
            insert()
            將给定内容插入到當前字符串中的指定位置
            ",就是為了改變世界!"
                    V
            "活著,就是为了改變世界!"
         */
        builder.insert(0,"活著");
        System.out.println(builder);//活著,就是為了改變世界!

        //      反轉
        builder.reverse();
        System.out.println(builder);//!界世變改了為是就,著活
    }
}
