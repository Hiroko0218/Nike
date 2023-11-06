package reflect;

import java.lang.reflect.Constructor;

/**
 * 利用反射机制
 * 使用无参构造器实例化Student
 * 使用有参构造器实例化Student
 */
public class Test {
    public static void main(String[] args) throws Exception {
        Class cls = Class.forName("reflect.Student");

        Object stu1 = cls.newInstance();
        System.out.println(stu1);

        Constructor c = cls.getConstructor(String.class,int.class,char.class);
        Object stu2 = c.newInstance("李四",22,'女');
        System.out.println(stu2);
    }
}
