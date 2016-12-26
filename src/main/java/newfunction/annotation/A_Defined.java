package newfunction.annotation;

/**
 * 定义和使用枚举类
 *
 * @author jw.fang
 * @version 1.0
 */
public class A_Defined {
    //定义Annotation的时候和定义接口的方式很类似，只不过再interface前面加了@
    public @interface UserDefinedAnnotation {

    }

    //如何对其进行使用
    public class UseAnnotation {
        @UserDefinedAnnotation
        public void test() {
            System.out.println("hello");
        }
    }
}