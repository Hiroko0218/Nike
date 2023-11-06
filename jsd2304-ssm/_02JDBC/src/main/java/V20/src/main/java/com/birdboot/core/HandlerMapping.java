package V20.src.main.java.com.birdboot.core;

import V20.src.main.java.com.birdboot.annotation.Controller;
import V20.src.main.java.com.birdboot.annotation.RequestMapping;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * 使用當前類維護請求路徑與對應的Controller處理方法
 */
public class HandlerMapping {
    /*
        key:請求路徑，例如"/regUser"
        value:方法對象，例如Method實例，表示UserController的reg方法
     */
    private static Map<String, Method> mapping = new HashMap<>();

    static{
        initMapping();
    }

    private static void initMapping(){
        try {
            File dir = new File(
                    HandlerMapping.class.getClassLoader().getResource(".").toURI()
            );
            File controllerDir = new File(dir,"com/birdboot/controller");

            File[] subs = controllerDir.listFiles(f->f.getName().endsWith(".class"));
            for(File sub : subs){
                String fileName = sub.getName();
                String className = fileName.substring(0,fileName.indexOf("."));

                Class cls = Class.forName("com.birdboot.controller."+className);
                if(cls.isAnnotationPresent(Controller.class)){
                    Method[] methods = cls.getDeclaredMethods();
                    for(Method method : methods){
                        if(method.isAnnotationPresent(RequestMapping.class)){
                            RequestMapping rm = method.getAnnotation(RequestMapping.class);
                            String value = rm.value();//注解参数
                            //將方法與其處理的請求路徑分别作為Map的key，value存入mapping
                            mapping.put(value,method);
                        }
                    }
                }
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根據請求路徑獲取對應的處理方法。如果返回值为null說明該路徑不是處理業務
     * @param path
     * @return
     */
    public static Method getMethod(String path){
        return mapping.get(path);
    }

    public static void main(String[] args) {
        System.out.println(mapping);
    }
}
