package newfunction.annotation;

/**
 * 基本串类型|数组类型|枚举类型|其它注解
 *
 * @author jw.fang
 * @version 1.0
 */
public class B_Type
{
    public enum DateEnum {
        Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday
    }

    public @interface OtherAnnotation
    {

    }
    public @interface UserDefinedAnnotation
    {
        int value();
        String name() default "jiawei"; //默认值，可为空
        String address();
        int[] array();
        DateEnum week();
        OtherAnnotation other();
    }

    public class UseAnnotation
    {
        @UserDefinedAnnotation(
                value = 123,
                name = "wangwenjun",
                address = "火星",
                array = {123},
                week=DateEnum.Sunday,
                other = @OtherAnnotation)
        public void test()
        {
            System.out.println("hello");
        }
    }
}
