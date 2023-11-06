package reflect;

import reflect.annotations.AutoRunMethod;

import java.lang.reflect.Method;

/**
 * 反射机制获取注解参数
 */
public class ReflectDemo9 {
    public static void main(String[] args) throws Exception {
        Class cls = Class.forName("reflect.Person");
        Method method = cls.getMethod("sayHi");
        if(method.isAnnotationPresent(AutoRunMethod.class)){
            /*
                【所有反射对象都支持获取注解的操作】:
                getAnnotation(Class cls)
             */
            //1:获取该方法上的注解对象
            AutoRunMethod arm = method.getAnnotation(AutoRunMethod.class);
            //2:通过注解对象获取其表示的注解上的参数
            int value = arm.value();//获取value参数
            System.out.println("参数值:"+value);
        }
    }
}
