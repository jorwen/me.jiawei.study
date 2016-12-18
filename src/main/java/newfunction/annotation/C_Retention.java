package newfunction.annotation;

import java.lang.annotation.*;

/**
 * Retention
 * 告知编译器如何来处理我们自定义的annotation，指示注释类型的注释要保留多久。
 * 如果注释类型声明中不存在 Retention 注释，则保留策略默认为 RetentionPolicy.CLASS。
 *
 * @author jw.fang
 * @version 1.0
 */
public class C_Retention
{
    @Retention(RetentionPolicy.RUNTIME)
    public @interface UserDefinedAnnotation
    {
        String name() default "zhangsan";
    }
}
