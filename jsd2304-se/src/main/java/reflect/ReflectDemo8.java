package reflect;

import reflect.annotations.AutoRunClass;
import reflect.annotations.AutoRunMethod;

import java.lang.reflect.Method;

/**
 * 反射機制訪問註解
 * 所有的反射對象:
 * Class,Method,Field,Constructor,Package...
 * 它們都提供了方法:
 * boolean isAnnotationPresent(Class cls)
 * 用於判斷是否被指定的註解標註了
 */
public class ReflectDemo8 {
    public static void main(String[] args) throws Exception {
        //Person类上的sayHi方法是否被注解@AutoRunMethod标注
        Class cls = Class.forName("reflect.Person");
        boolean mark = cls.isAnnotationPresent(AutoRunClass.class);
        if(mark){
            Method method = cls.getDeclaredMethod("sayHi");
            mark = method.isAnnotationPresent(AutoRunMethod.class);
            if(mark){
                System.out.println(method.getName()+"()被标注了");
            }else{
                System.out.println(method.getName()+"()没有被标注");
            }
        }else{
            System.out.println(cls.getSimpleName()+"类没有被标注");
        }
    }
}
