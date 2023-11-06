package ooday05;
//static final常量的演示
public class StaticFinalDemo {
    public static void main(String[] args) {
        System.out.println(Loo.PI); //常常通过类名点来访问
        //Loo.PI = 3.1415926; //编译错误，常量不能被改变

        //1)加载Loo.class到方法区中
        //2)静态变量num一并存储到方法区中
        //3)到方法区中获取num的值并输出
        System.out.println(Loo.num);

        //编译器在编译时会将常量直接替换为具体的数，效率高
        //相当于System.out.println(5);
        System.out.println(Loo.COUNT);
    }
}

class Loo{
    public static int num = 5; //静态变量
    public static final int COUNT = 5; //常量(静态常量)

    public static final double PI = 3.14159;
    //public static final int NUM; //编译错误，常量必须声明同时初始化
}















