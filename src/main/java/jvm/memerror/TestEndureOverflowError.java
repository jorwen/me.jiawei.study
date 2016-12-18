package jvm.memerror;

/**
 * 在Java6中，intern非常大的字符串，抛出java.lang.OutOfMemoryError: PermGen space
 *
 * @author jw.fang
 * @version 1.0
 */
public class TestEndureOverflowError
{
    /*
    String的intern()方法就是扩充常量池的一个 方法；当一个String实例str调用intern()方法时，
    Java查找常量池中是否有相同Unicode的字符串常量，如果有，则返回其的引用， 如果没有，
    则在常量池中增加一个Unicode等于str的字符串并返回它的引用；
     */
    public static void main(String[] args)
    {
        String str = "dfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfdsfsdfasfsd";
        for (long i = 0; i < 1000000000000L; i++)
        {
            str = str + str + i;
            str.intern();
        }
    }
}