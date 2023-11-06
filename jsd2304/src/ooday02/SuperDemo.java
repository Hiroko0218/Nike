package ooday02;
//super的演示
public class SuperDemo {
    public static void main(String[] args) {
        Boo o = new Boo();
    }
}

//1)在派生类的构造方法中若没有调用超类的构造方法，则默认super()调用超类的无参构造方法
class Aoo{
    Aoo(){
        System.out.println("父类构造方法");
    }
}
class Boo extends Aoo{
    Boo(){
        super(); //默认的，调用父类的无参构造方法
        System.out.println("子类构造方法");
    }
}

//2)在派生类的构造方法中若自己调用了超类的构造方法，则不再默认提供
class Coo{
    Coo(int a){
    }
}
class Doo extends Coo{
    Doo(){
        super(5); //调用超类的有参构造
    }
    /*
    //如下代码为默认的:
    Doo(){
        super();
    }
     */
}



















