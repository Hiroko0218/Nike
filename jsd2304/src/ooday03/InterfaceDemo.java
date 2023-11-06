package ooday03;
//接口的演示
public class InterfaceDemo {
    public static void main(String[] args) {
        //Inter o = new Inter(); //编译错误，接口不能被实例化
    }
}

//演示接口的语法和实现
interface Inter{
    abstract void show();
    void test(); //接口中的方法默认都是抽象的，前面默认有abstract
    //void say(){} //编译错误，抽象方法不能有方法体
}
class InterImpl implements Inter{
    public void show(){ //重写接口中的抽象方法时，必须加public
    }
    public void test(){
    }
}

//演示接口的多实现:
interface Inter1{
    void show();
}
interface Inter2{
    void test();
}
abstract class Aoo{
    abstract void say();
}
class Boo extends Aoo implements Inter1,Inter2{
    public void show(){}
    public void test(){}
    void say(){}
}

//演示接口继承接口
interface Inter3{
    void show();
}
interface Inter4 extends Inter3{
    void test();
}
class Coo implements Inter4{
    public void test(){}
    public void show(){}
}


















