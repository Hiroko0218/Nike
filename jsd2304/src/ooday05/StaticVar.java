package ooday05;
//静态变量的演示
public class StaticVar {
    int a; //实例变量
    static int b; //静态变量
    StaticVar(){
        a++;
        b++;
    }
    void show(){
        System.out.println("a="+a+",b="+b);
    }
}
















