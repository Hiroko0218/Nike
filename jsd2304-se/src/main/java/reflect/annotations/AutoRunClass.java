package reflect.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * JAVA提供了很多元注解(元注解是说明我们定义的注解的一些附加功能使用的)
 * @Target用于说明我们定义的注解可以被应用在什么位置。不指定时注解可以被应用在任何
 *        允许的位置。
 *        @Target的参数表示被允许使用的位置，使用ElementType来定义可用位置
 *        ElementType.TYPE:注解可以被应用于类上
 *        ElementType.METHOD:注解可以被应用于方法上
 *        ElementType.FIELD:注解可以被应用于属性上
 *        ...
 *        同时指定多个时，可以传入一个数组:{ElementType.TYPE,ElementType.METHOD,...}
 *
 * @Retention注解用于表示当前注解的保留级别。保留级别有三个选项:
 *        RetentionPolicy.SOURCE:注解仅保留在源代码中，编译后的class文件中没有
 *        RetentionPolicy.CLASS:注解保留在字节码文件中，但是反射机制不可访问(默认的保留级别)
 *        RetentionPolicy.RUNTIME:注解保留在字节码文件中且可以被反射机制访问
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoRunClass {
}
