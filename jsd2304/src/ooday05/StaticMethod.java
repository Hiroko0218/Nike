package ooday05;
//静态方法的演示
public class StaticMethod {
    int a; //实例变量(对象来访问)-------------属于对象的
    static int b; //静态变量(类名来访问)------属于类的

    //何时用:方法的操作与对象无关(不需要访问对象的属性/方法)
    //在say()中需要访问对象的属性a，所以认为say的操作与对象有关，不适合设计为静态方法
    void say(){
        System.out.println(a);
    }
    //在plus()中不需要访问对象的属性/行为，所以认为plus的操作与对象无关，可以设计为静态方法
    static int plus(int num1,int num2){
        int num = num1+num2;
        return num;
    }


    void show(){ //有隐式this
        System.out.println(this.a);
        System.out.println(StaticMethod.b);
    }
    static void test(){ //没有隐式this
        //静态方法中没有隐式this传递
        //没有this就意味着没有对象
        //而实例变量a必须通过对象来访问
        //所以如下语句发生编译错误
        //System.out.println(a); //编译错误，静态方法中不能直接访问实例变量
        System.out.println(StaticMethod.b);
    }
}
















