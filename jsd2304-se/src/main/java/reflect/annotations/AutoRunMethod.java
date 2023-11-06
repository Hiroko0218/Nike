package reflect.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoRunMethod {
        /*
        注解中可以定义参数
        格式:
        类型 参数名() [default 默认值]     注:"[]"中的内容可选

        当使用注解时，传参方式:
        @AutoRunMethod(参数1名=参数1值,参数2名=参数2值,...)
        注意:参数的顺序可以与定义时不一致

        以两个参数为例：
        public @interface AutoRunMethod {
            int num();
            String name();
        }

        实际使用时，传参可以:
        @AutoRunMethod(num=1,name="张三")
        @AutoRunMethod(name="张三",num=1)



        有默认值时:
        public @interface AutoRunMethod {
            int num() default 1;
            String name();
        }

        实际使用时可以忽略有默认值的参数，此时该参数使用默认值，传参可以:
        @AutoRunMethod(name="张三")


        若注解只有一个参数时:
        public @interface AutoRunMethod {
            int num();
        }
        实际使用时，传参可以:
        @AutoRunMethod(num=1)   此时如果指定参数名会比较累赘

        【因此，建议当注解只有一个参数时，参数名使用value】
         public @interface AutoRunMethod {
            int value();
        }
        实际使用时，传参可以:
        @AutoRunMethod(1)  使用时可以忽略参数名

     */
//    int num() default 1;
//    String name();

    int value() default 1;
}
