package reflect;

import java.io.File;
import java.net.URISyntaxException;

/**
 * 实例化与当前类Test3在同一个包中的所有类
 */
public class Test3 {
    public static void main(String[] args) throws URISyntaxException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        //如何定位Test3所在的目录?
        /*
            类加载路径:
            类名.class.getClassLoader().getResource(".").toURI()

            当前类的字节码文件所在的目录:
            类名.class.getResource(".").toURI()
         */
        File dir = new File(
                Test3.class.getResource(".").toURI()
        );
        /*
            提示1:其他类的包名应当与Test3的包名一致(因为要求是同一个包中的类)
            提示2:java中类的文件名与该类的类名一致
                 例如Test3.class文件中记录的是Test3这个类

            Class.forName("包名.类名")
                          提示1.提示2
         */
        //根据Test3的类对象获取包名
        String packageName = Test3.class.getPackage().getName();

        //获取Test3.class文件所在的目录中的所有.class文件
        File[] subs = dir.listFiles(f->f.getName().endsWith(".class"));
        for (File sub : subs){
            String fileName = sub.getName();
            String className = fileName.substring(0,fileName.indexOf("."));
            Class cls = Class.forName(packageName+"."+className);
            System.out.println("开始实例化:"+className);
            Object obj = cls.newInstance();
            System.out.println(obj);
        }
    }
}
