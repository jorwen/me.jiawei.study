package concurrent.test;

import junit.framework.*;

/**
 * 单元测试
 *
 * @author jw.fang
 * @version 1.0
 */
public class BoundedBufferTest extends TestCase
{
    /**
     * 测试正确性
     */
    public void testIsEmptyWhenConstructed()
    {
        BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
        assertTrue(bb.isEmpty());
        assertFalse(bb.isFull());
    }

    /**
     * 测试正确性
     * @throws InterruptedException
     */
    public void testIsFullAfterPuts() throws InterruptedException
    {
        BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
        for (int i = 0; i < 10; i++)
        {
            bb.put(i);
        }
        assertTrue(bb.isFull());
        assertFalse(bb.isEmpty());
    }

    /**
     * 测试阻塞行为以及对中断的响应性
     */
    public void testTakeBlocksWhenEmpty()
    {
        final BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
        Thread taker = new Thread(){
            @Override
            public void run()
            {
                try
                {
                    int unused = bb.take();
                    fail();
                }
                catch (InterruptedException ignored){}
            }
        };
        try
        {
            taker.start();
            Thread.sleep(1000);
            taker.interrupt();//等待1秒后中断
            taker.join(1000);//等待线程执行完毕，最多等1秒
            assertFalse(taker.isAlive());
        }
        catch (InterruptedException e)
        {
            fail();
        }
    }
}