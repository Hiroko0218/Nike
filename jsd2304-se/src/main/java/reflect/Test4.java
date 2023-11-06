package reflect;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URISyntaxException;

/**
 * 实例化与当前类Test4在同一个包中的所有类，并调用他们所有公开的，无参方法。
 */
public class Test4 {
    public static void main(String[] args) throws Exception{
        File dir = new File(
                Test4.class.getResource(".").toURI()
        );
        String packageName = Test4.class.getPackage().getName();
        File[] subs = dir.listFiles(f->f.getName().endsWith(".class"));
        for (File sub : subs){
            String fileName = sub.getName();
            String className = fileName.substring(0,fileName.indexOf("."));
            Class cls = Class.forName(packageName+"."+className);
            System.out.println("开始实例化:"+className);
            Object obj = cls.newInstance();
            System.out.println(obj);

            Method[] methods = cls.getDeclaredMethods();
            for(Method method : methods){
                if(method.getModifiers()== Modifier.PUBLIC
                        &&
                        method.getParameterCount()==0
                ){
                    System.out.println("开始调用:"+method.getName()+"()");
                    method.invoke(obj);
                }
            }
        }
    }
}
