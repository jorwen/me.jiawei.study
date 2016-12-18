package newfunction.annotation;

import java.lang.annotation.*;

/**
 * 该标记的意思就是说，比如有一个类A，在他上面有一个标记annotation，
 * 那么A的子类B是否不用再次标记annotation就可以继承得到呢？答案是肯定的
 *
 * @author jw.fang
 * @version 1.0
 */
public class F_Inherited
{
    @Documented
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Inherited
    public @interface UserdefinedAnnotation
    {
        String name() default "zhangsan";
    }

    @UserdefinedAnnotation
    public class ParentClass
    {

    }

    public class ChildClass extends ParentClass
    {

    }

    public static void main(String[] args)
    {
        Class<ChildClass> clazz = ChildClass.class;
        boolean isExist = clazz.isAnnotationPresent(UserdefinedAnnotation.class);
        if (isExist)
        {
            System.out.println("子类继承了父类的annotation");
        }
        else
        {
            System.out.println("子类没有继承父类的annotation");
        }
    }
}
