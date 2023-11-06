package ooday05;

//接口补充
public class InterfaceMore {
}

interface Inter{
    public static final int NUM = 5; //常量(一般不用)
    public default void test(){ } //默认方法(一般不用)
    public static void say(){ } //静态方法(一般不用)
    //private void sayHi(){ } //私有方法----在JDK1.9时才支持
}













