package reflect;

import reflect.annotations.AutoRunClass;

/**
 * 反射机制访问注解
 */
public class ReflectDemo7 {
    public static void main(String[] args) throws Exception {
        //Person类是否被注解@AutoRunClass标注?
        Class cls = Class.forName("reflect.Person");
        boolean mark = cls.isAnnotationPresent(AutoRunClass.class);
        if(mark){
            System.out.println(cls.getSimpleName()+"被标注了");
        }else{
            System.out.println(cls.getSimpleName()+"没有被标注");
        }

    }
}
