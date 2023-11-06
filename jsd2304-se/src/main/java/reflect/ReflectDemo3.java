package reflect;

import java.lang.reflect.Constructor;

/**
 * 使用特定的构造器实例化对象
 */
public class ReflectDemo3 {
    public static void main(String[] args) throws Exception {
        Person p = new Person("王克晶",18);
        System.out.println(p);

        /*
            步骤:
            1:加载类对象
            2:通过类对象获取指定的构造器
            3:通过构造器对象实例化
            Constructor类:构造器对象，该类的每一个实例用于表示某个类中的指定构造器
         */
        Class cls = Class.forName("reflect.Person");
//        Constructor con = cls.getConstructor();//获取无参构造器
        //Person(String,int)
//        Constructor con = cls.getConstructor(String.class,int.class);
        Constructor con = cls.getConstructor(new Class[]{String.class,int.class});
        //new Person("范传奇",22);
        Object obj = con.newInstance("范传奇",22);
        System.out.println(obj);
    }
}
