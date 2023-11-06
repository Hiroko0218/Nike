package reflect;

import reflect.demo.Singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * 暴力反射，在反射机制中访问一个类的私有成员
 */
public class ReflectDemo6 {
    public static void main(String[] args) throws Exception {
        Person p = new Person();
//        p.hehe();//编译不通过，类的外部不可以访问类的私有成员


        Class cls = Class.forName("reflect.Person");
        Object obj = cls.newInstance();
        /*
            类对象:
            getMethod()和getMethods()
            只能获取当前类对象所表示的类的所有公开方法，包括从超类继承的

            getDeclaredMethod()和getDeclaredMethods()
            可以获取当前类对象所表示的类自己定义的方法，不含继承的方法。

         */
//        Method method = cls.getMethod("hehe");
        Method method =  cls.getDeclaredMethod("hehe");
        method.setAccessible(true);//强行打开其访问权限
        method.invoke(obj);//obj.hehe()
        method.setAccessible(false);//用完后要关闭访问权限


//        Singleton s1 = new Singleton();
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        System.out.println(s1);
        System.out.println(s2);

        Class cls2 = Class.forName("reflect.demo.Singleton");
        Constructor c = cls2.getDeclaredConstructor();
        c.setAccessible(true);
        Object s3 =c.newInstance();
        System.out.println(s3);
    }
}
