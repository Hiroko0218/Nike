package ooday05_vis;
import ooday05.Aoo;
public class Coo { //--------------演示同包的
    void show(){
        Aoo o = new Aoo();
        o.a = 1;
//        o.b = 2; //编译错误
//        o.c = 3; //编译错误
        //o.d = 4; //编译错误
    }
}

class Doo extends Aoo{ //跨包继承--------演示protected
    void show(){
        a = 1;
        b = 2;
        //c = 3; //编译错误
        //d = 4; //编译错误
    }
}
















