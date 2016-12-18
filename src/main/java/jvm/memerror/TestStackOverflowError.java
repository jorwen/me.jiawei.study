package jvm.memerror;

/**
 * 当JVM方法栈控件不足是，会抛出StackOverflowError错误，通过-Xss来指定
 */
public class TestStackOverflowError
{
    private static long count = 0;

    public static void main(String[] args)
    {
        infinitelyRecursiveMethod(1);
    }

    public static void infinitelyRecursiveMethod(long a)
    {
        System.out.println(count++);
        infinitelyRecursiveMethod(a);
    }
}
