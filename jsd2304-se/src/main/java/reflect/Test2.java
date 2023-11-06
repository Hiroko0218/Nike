package reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 使用反射机制调用Person类中所有无参的公开的名字以s开始的方法(本类定义的，不含超类继承的)
 * 1:加载类对象
 * 2:通过类对象获取本类定义的所有方法
 * 3:遍历每一个方法对象并判断是否为无参的，公开的方法
 * 4:使用方法对象的invoke调用
 */
public class Test2 {
    public static void main(String[] args) throws Exception {
        Class cls = Class.forName("reflect.Person");
        Object obj = cls.newInstance();//实例化

        Method[] methods = cls.getDeclaredMethods();
        for(Method method : methods){
            if(method.getParameterCount()==0            //无参方法
                    &&
                    method.getModifiers()==Modifier.PUBLIC   //公开方法
                    &&
                    method.getName().startsWith("s")
            ){
                System.out.println("调用方法:"+method.getName());
                method.invoke(obj);
            }

        }


    }
}

