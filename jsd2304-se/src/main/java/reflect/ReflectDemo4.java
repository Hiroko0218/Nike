package reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * 使用反射机制调用方法
 */
public class ReflectDemo4 {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Person per = new Person();
        per.sayHello();


        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入类名:");
        String className = scanner.nextLine();
        System.out.println("请输入方法名:");
        String methodName = scanner.nextLine();

        /*
            步骤:
            1:获取类对象
            2:通过类对象实例化
            3:通过类对象获取要调用的方法
            4:通过方法对象执行该方法
         */
//        Class cls = Class.forName("reflect.Person");
        Class cls = Class.forName(className);
        Object p = cls.newInstance();//利用无参构造器实例化
        //通过类对象获取sayHello()
//        Method method = cls.getMethod("sayHello");//sayHello()
        Method method = cls.getMethod(methodName);
        /*
            Method对象，表示一个方法
            重要方法:
            Object invoke(Object p,Object... args)
            该方法用于执行当前方法对象所表示的方法。
            参数1:该方法的所属对象
         */
        method.invoke(p);//p.sayHello()

    }
}
