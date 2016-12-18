package concurrent.noblocking;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用 AtomicInteger 的 compareAndSet() （CAS）方法的计数
 * compareAndSet() 方法规定 “将这个变量更新为新值，但是如果从我上次看到这个变量之后其他线程修改了它的值，那么更新就失败”
 *
 * @author jw.fang
 * @version 1.0
 */
public class CasAlgorithm
{
    private AtomicInteger value;

    public int getValue()
    {
        return value.get();
    }

    /**
     * 等同于addAndGet()
     * @return
     */
    public int increment()
    {
        int v;
        do
        {
            v = value.get();
        }
        while (!value.compareAndSet(v, v + 1));
        return v + 1;
    }
}
