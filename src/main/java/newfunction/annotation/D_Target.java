package newfunction.annotation;

import java.lang.annotation.*;

/**
 * Target 标记
 * 对annotation的位置进行设置呢
 *
 * @author jw.fang
 * @version 1.0
 */
public class D_Target
{
    @Target(ElementType.METHOD)
    public @interface UserdefinedAnnotation
    {
        String name() default "zhangsan";
    }

    public class UseAnnotation
    {
        @UserdefinedAnnotation()
        public void test()
        {
            System.out.println("hello");
        }
    }
}
