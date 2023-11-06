package day02;
//数据类型的演示
public class DataTypeDemo {
    public static void main(String[] args) {
        //类型间的转换:
        byte b1 = 5;
        byte b2 = 6;
        byte b3 = (byte)(b1+b2);

        System.out.println(2+2);      //4
        System.out.println(2+'2');    //52，2加上'2'的码50
        System.out.println('2'+'2');  //100，'2'的码50，加上'2'的码50
        System.out.println('2');      //2

        int m = 'a';
        System.out.println(m); //97，查看字符对应的码
        char n = 97;
        System.out.println(n); //'a'，查看码对应的数字






        /*
        //两种方式
        int a = 5;
        long b = a; //自动类型转换
        int c = (int)b; //强制类型转换

        long d = 5; //自动类型转换
        double e = 5; //自动类型转换

        long f = 10000000000L;
        int g = (int)f;
        System.out.println(g); //1410065408，强转有可能发生溢出

        double h = 25.987;
        int i = (int)h;
        System.out.println(i); //25，强转有可能丢失精度
        */


















        /*
        //5)char:字符型，2个字节
        char c1 = '女'; //字符女
        char c2 = 'f';  //字符f
        char c3 = '6';  //字符6
        char c4 = ' ';  //空格符
        //char c5 = 女; //编译错误，字符型直接量必须放在单引号中
        //char c6 = ''; //编译错误，必须有字符
        //char c7 = '10'; //编译错误，只能存储1个字符

        char c8 = 65; //0到65535之间
        System.out.println(c8); //println()会依据变量的类型来做输出展示
                                //c8为char类型，所以会以字符的形式输出

        char c9 = '\\';
        System.out.println(c9); //\
        */

        /*
        //4)boolean类型:布尔型，1个字节
        boolean a = true;  //true为布尔型直接量
        boolean b = false; //false为布尔型直接量
        //boolean c = 250; //编译错误，布尔型变量中只能存储true或false
        */

        /*
        //3)double类型:双精度浮点型，8个字节
        double a = 3.14; //3.14为小数直接量，默认为double型
        float b = 3.14F; //3.14F为float型直接量

        double c=3.0,d=2.9;
        System.out.println(c-d); //0.10000000000000009，有可能发生舍入误差
        */

        /*
        //2)long类型:长整型，8个字节，-2^63到2^63-1
        long a = 25; //25L为长整型直接量
        //long b = 10000000000; //编译错误，100亿默认int类型，但超出int范围了
        long c = 10000000000L; //100亿L为长整型直接量
        //long d = 3.14L; //编译错误，长整型变量中只能装整数
        //运算时若有可能溢出，建议在第1个数字后加L
        long e = 1000000000*2*10L;
        System.out.println(e); //200亿
        long f = 1000000000*3*10L;
        System.out.println(f); //不是300亿
        long g = 1000000000L*3*10;
        System.out.println(g); //300亿
        */

        /*
        //1)int类型:整型，4个字节，-2^31到2^31-1(正负21个多亿)
        int a = 25; //25为整数直接量，默认为int类型
        //int b = 10000000000; //编译错误，100亿默认为int类型，但超出范围了
        //int c = 3.14; //编译错误，整型变量中只能装整数
        System.out.println(5/2);   //2
        System.out.println(2/5);   //0
        System.out.println(5/2.0); //2.5
        int d = 2147483647; //int的最大值
        d = d+1;
        System.out.println(d); //-2147483648(int的最小值)，发生溢出了，需要避免
        */

    }
}















