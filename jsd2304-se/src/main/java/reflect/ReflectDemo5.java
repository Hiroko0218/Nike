package reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 使用反射机制调用有参方法
 */
public class ReflectDemo5 {
    public static void main(String[] args) throws Exception {
        Person p = new Person();
        p.say("你好!");

        Class cls = Class.forName("reflect.Person");
        Object obj = cls.newInstance();
        //参数1是方法名，从参数2开始是方法的参数列表
        Method method = cls.getMethod("say",String.class);//say(String)
        //调用该方法时:参数1为方法所属对象，参数2开始为实参列表
        method.invoke(obj,"大家好!");

        Method method2 = cls.getMethod("say",String.class,int.class);//say(String,int)
        method2.invoke(obj,"嘿嘿",5);
        /*
            Method的常用方法
            int getModifiers()
            返回当前方法对象所表示的方法的语言修饰符

            int getParameterCount()
            返回当前方法对象所表示的方法的参数个数
         */
        int modifier = method.getModifiers();
        switch (modifier){
            case Modifier.PUBLIC:
                System.out.println("公开方法");
                break;
            case Modifier.PRIVATE:
                System.out.println("私有方法");
                break;
        }

        int count = method.getParameterCount();
        System.out.println(method.getName()+"方法有"+count+"个参数");


    }
}
