package ooday05;
//static的演示
public class StaticDemo {
    public static void main(String[] args) {
        StaticVar o1 = new StaticVar();
        o1.show();
        StaticVar o2 = new StaticVar();
        o2.show();
        StaticVar o3 = new StaticVar();
        o3.show();
        System.out.println(StaticVar.b); //常常通过类名点来访问

        StaticBlock o4 = new StaticBlock(); //加载类时自动执行静态块
        StaticBlock o5 = new StaticBlock();
        StaticBlock o6 = new StaticBlock();

        StaticMethod.test(); //常常通过类名点来访问
    }
}




























