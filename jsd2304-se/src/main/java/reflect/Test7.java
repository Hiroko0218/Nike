package reflect;

import reflect.annotations.AutoRunClass;
import reflect.annotations.AutoRunMethod;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URISyntaxException;

/**
 * 调用与当前类Test7在同一个包中被注解@AutoRunClass标注的类中所有被@AutoRunMethod
 * 标注的方法指定次(次数由方法注解@AutoRunMethod上的参数确定)
 */
public class Test7 {
    public static void main(String[] args) throws Exception {
        File dir = new File(
                Test7.class.getResource(".").toURI()
        );
        String packageName = Test7.class.getPackage().getName();
        File[] subs = dir.listFiles(f->f.getName().endsWith(".class"));
        for (File sub : subs){
            String fileName = sub.getName();
            String className = fileName.substring(0,fileName.indexOf("."));
            Class cls = Class.forName(packageName+"."+className);
            if(cls.isAnnotationPresent(AutoRunClass.class)) {
                System.out.println("开始实例化:" + className);
                Object obj = cls.newInstance();

                Method[] methods = cls.getDeclaredMethods();
                for(Method method : methods){
                    if(method.isAnnotationPresent(AutoRunMethod.class)){
                        AutoRunMethod arm = method.getAnnotation(AutoRunMethod.class);
                        int value = arm.value();
                        System.out.println("开始调用方法:" + method.getName() + "()"+value+"次");
                        for(int i=0;i<value;i++) {
                            method.invoke(obj);
                        }
                    }
                }
            }
        }
    }
}
