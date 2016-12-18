package jvm.memerror;

/**
 * 创建一个大数组对象来申请一块比较大的内存 java.lang.OutOfMemoryError: Java heap space，设置-Xms -Xmx调整
 *
 * @author jw.fang
 * @version 1.0
 */
public class TestHeapOverflowError
{
    private static final int MB = 1024 * 1024;

    public static void main(String[] args)
    {
        String[] bigMemory = new String[1024 * MB];
    }
}
