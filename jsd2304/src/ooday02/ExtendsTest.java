package ooday02;
/** 测试类 */
public class ExtendsTest {
    public static void main(String[] args) {
        /*
        //演示继承:
        Student zs = new Student();
        zs.name = "张三";
        zs.age = 25;
        zs.address = "廊坊";
        zs.className = "jsd2304";
        zs.stuId = "001";
        zs.eat();
        zs.sleep();
        zs.sayHi();
        zs.study();

        Teacher ls = new Teacher("李四",35,"佳木斯",6000.0);
        ls.eat();
        ls.sleep();
        ls.sayHi();
        ls.teach();

        Doctor ww = new Doctor("王五",46,"山东","主任医师");
        ww.eat();
        ww.sleep();
        ww.sayHi();
        ww.cut();
         */

        //重写结果显示:
        Student zs = new Student("张三",25,"廊坊","jsd2304","001");
        zs.sayHi(); //调用Student重写之后的
        Teacher ls = new Teacher("李四",35,"佳木斯",6000.0);
        ls.sayHi(); //调用Teacher重写之后的
        Doctor ww = new Doctor("王五",45,"山东","主任医师");
        ww.sayHi(); //调用Person中的
    }
}












