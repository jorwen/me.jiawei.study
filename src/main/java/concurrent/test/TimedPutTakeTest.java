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
public class TimedPutTakeTest extends TestCase
{
    private static final ExecutorService pool = Executors.newCachedThreadPool();
    private final AtomicInteger putSum = new AtomicInteger(0);
    private final AtomicInteger takeSum = new AtomicInteger(0);
    private final CyclicBarrier barrier;
    private final BoundedBuffer<Integer> bb;
    private final int nTrials, nPairs;
    private BarrierTimer timer;

    public static void main(String[] args) throws InterruptedException
    {
        int tpt = 100000;//每个线程中的测试次数
        for (int cap = 1; cap <=1000; cap*=10)
        {
            System.out.println("Capacity:" + cap);
            for(int pairs = 1; pairs <=16; pairs*=2)
            {
                TimedPutTakeTest t = new TimedPutTakeTest(cap, pairs , tpt);
                System.out.print("Pairs:" + pairs + "\t");
                Thread.sleep(1000);
                t.test();
                System.out.println();
                Thread.sleep(1000);
            }
        }
        pool.shutdown();
    }

    TimedPutTakeTest(int capacity, int npairs, int ntrials)
    {
        this.bb = new BoundedBuffer<Integer>(capacity);
        this.nTrials = ntrials;
        this.nPairs = npairs;
        this.timer = new BarrierTimer();
        this.barrier = new CyclicBarrier(npairs * 2 + 1);
    }

    void test()
    {
        try
        {
            timer.clear();
            timer.run();
            for (int i = 0; i < nPairs; i++)
            {
                pool.execute(new Producer());
                pool.execute(new Consumer());
            }
            barrier.await();//等待所有线程就绪
            barrier.await();//等待所有线程执行完毕0
            timer.run();
            long nsPerItem = timer.getTime() / (nPairs * nTrials);
            System.out.print("Throughput:"+ nsPerItem + "ns/item");
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
