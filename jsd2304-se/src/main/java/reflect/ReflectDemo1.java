package reflect;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * JAVA的反射機制
 * 反射機制是java的動態機制，允許我們在程序【運行期間】再確定對象的實例化，方法的調用屬性的操作等。
 * 反射機制大大的提高了代碼的靈活度和可擴展性，但是也存在較慢的運行效率，和較多的資源開銷。
 * 因此反射機制不能被過度的使用。
 */
public class ReflectDemo1 {
    public static void main(String[] args) throws ClassNotFoundException {
        /*
            反射機制第一步:獲取類對象 Class的實例
            Class類的每一個實列用於表示一個類，所以我們也稱它為"類對象"
            在JVM内部，每個被加載的類都有且只有一個Class的實例與之對應，通過
            這個類對象可以得知其表示的類的一切信息: 包信息，類名，構造器，方法，屬性等

            反射操作的第一件事就是獲取要操作的類的類對象
            獲取一個類的類對象方式:
            1:類名.class
            Class cls = String.class;
            Class cls = int.class;//基本類型獲取它對應的類對象方式只有這一種

            2:Class.forName(String className)
            通過指定類的完全限定名(包名.類名)来加載並獲取該類的類對象
            Class cls = Class.forName("java.lang.String");

            3:ClassLoader獲取類對象
         */

        //获取String的類對象
//        Class cls = String.class;//該cls對象表示的就是String類的信息
//        Class cls = ArrayList.class;

        //使用Class.forName()加载类对象
//        Class cls = Class.forName("java.util.ArrayList");
        /*
            程序不再修改，可以在程序运行期间根据输入信息来加载不同的类
            java.lang.String
            java.util.ArrayList
            java.util.HashMap
            java.io.FileInputStream
         */
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入类名:");
        String className = scanner.nextLine();
        Class cls = Class.forName(className);



        //获取类对象所表示的类的完全限定名
        String name = cls.getName();
        System.out.println(name);
        //仅获取类名
        name = cls.getSimpleName();
        System.out.println(name);
        //获取包信息
        /*
            Package类:包对象，该类的每一个实例用于表示一个包的信息
         */
        Package p = cls.getPackage();//获取包
        String pname = p.getName();//通过包对象可以获取包名
        System.out.println(pname);
        /*
            Class的getMethods()方法可以获取其表示的类的所有【公开】方法，包含从
            超类继承下来的

            Method类:方法对象，该类的每一个实例用于表示一个方法
            通过方法对象我们可以:
            获取该方法的信息(方法名，返回值类型，参数类型，参数个数等)
            还可以调用该方法
         */
        Method[] methods = cls.getMethods();
        for(Method method : methods){
            String mname = method.getName();//通过方法对象获取该方法的名字
            System.out.println(mname);
        }
    }
}

