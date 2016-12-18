package concurrent.test;

import junit.framework.TestCase;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试BoundedBuffer生产者-消费者
 *
 * @author jw.fang
 * @version 1.0
 */
public class PutTakeTest extends TestCase
{
    private static final ExecutorService pool = Executors.newCachedThreadPool();
    private final AtomicInteger putSum = new AtomicInteger(0);
    private final AtomicInteger takeSum = new AtomicInteger(0);
    private final CyclicBarrier barrier;
    private final BoundedBuffer<Integer> bb;
    private final int nTrials, nPairs;

    public static void main(String[] args)
    {
        new PutTakeTest(10,10,100000).test();
        pool.shutdown();
    }

    PutTakeTest(int capacity, int npairs, int ntrials)
    {
        this.bb = new BoundedBuffer<Integer>(capacity);
        this.nTrials = ntrials;
        this.nPairs = npairs;
        this.barrier = new CyclicBarrier(npairs * 2 + 1);
    }

    void test()
    {
        try
        {
            for (int i = 0; i < nPairs; i++)
            {
                pool.execute(new Producer());
                pool.execute(new Consumer());
            }
            barrier.await();//等待所有线程就绪
            barrier.await();//等待所有线程执行完毕
            assertEquals(putSum.get(), takeSum.get());
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * 生产者
     */
    class Producer implements Runnable
    {
        @Override
        public void run()
        {
            try
            {
                int seed = (this.hashCode() ^ (int) System.nanoTime());
                int sum = 0;
                barrier.await();
                for (int i = nTrials; i > 0; --i)
                {
                    bb.put(seed);
                    sum += seed;
                    seed = xorShift(seed);
                }
                putSum.getAndAdd(sum);
                barrier.await();
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 消费者
     */
    class Consumer implements Runnable
    {
        @Override
        public void run()
        {
            try
            {
                barrier.await();
                int sum = 0;
                for (int i = nTrials; i > 0; --i)
                {
                    sum += bb.take();
                }
                takeSum.getAndAdd(sum);
                barrier.await();
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 测试中适合的随机数
     *
     * @param y
     * @return 随机数
     */
    static int xorShift(int y)
    {
        y ^= (y << 6);
        y ^= (y >>> 21);
        y ^= (y << 7);
        return y;
    }
}
