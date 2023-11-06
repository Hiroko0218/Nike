package reflect;

import java.util.Scanner;

/**
 * 使用反射机制实例化对象
 */
public class ReflectDemo2 {
    public static void main(String[] args) throws Exception {
        Person p = new Person();
        System.out.println(p);
        /*
            反射机制实例化:
            1:获取要实例化对象的类的类对象
            2:可以直接通过类对象的newInstance()方法调用
              其表示的类的【公开的无参构造器】实例化
         */
//        Class cls = Class.forName("reflect.Person");
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入类名:");
        String className = scanner.nextLine();
        Class cls = Class.forName(className);

        Object obj = cls.newInstance();//new Person();
        System.out.println(obj);
    }
}
