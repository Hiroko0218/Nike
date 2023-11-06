package ooday02;
/** final的演示 */
public class FinalDemo {
}

//演示final修饰变量
class Eoo{
    final int a = 5;
    int b = 6;
    void test(){
        //a = 55; //编译错误，final修饰的变量不能被改变
        b = 66;
    }
}

//演示final修饰方法
class Foo{
    final void show(){}
    void test(){}
}
class Goo extends Foo{
    //void show(){} //编译错误，final修饰的方法不能被重写
    void test(){}
}

//演示final修饰类
final class Hoo{}
//class Ioo extends Hoo{} //编译错误，final修饰的类不能被继承
class Joo{}
final class Koo extends Joo{} //正确，不能当老爸，但能当儿子



















