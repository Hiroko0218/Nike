package reflect;

import reflect.annotations.AutoRunClass;

import java.io.File;

/**
 * 仅实例化与当前类Test5在同一个包中被@AutoRunClass标注的类
 */
public class Test5 {
    public static void main(String[] args) throws Exception {
        File dir = new File(
                Test5.class.getResource(".").toURI()
        );
        String packageName = Test5.class.getPackage().getName();
        File[] subs = dir.listFiles(f->f.getName().endsWith(".class"));
        for (File sub : subs){
            String fileName = sub.getName();
            String className = fileName.substring(0,fileName.indexOf("."));
            Class cls = Class.forName(packageName+"."+className);
            if(cls.isAnnotationPresent(AutoRunClass.class)) {
                System.out.println("开始实例化:" + className);
                Object obj = cls.newInstance();
                System.out.println(obj);
            }
        }
    }
}
