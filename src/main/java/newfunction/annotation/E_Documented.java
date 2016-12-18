package newfunction.annotation;

import java.lang.annotation.*;

/**
 * 告诉jdk让它也将annotation生成到doc中去
 *
 * @author jw.fang
 * @version 1.0
 */
public class E_Documented
{
    @Target(ElementType.METHOD)
    @Documented
    public @interface UserdefinedAnnotation
    {
        String name() default "zhangsan";
    }
}