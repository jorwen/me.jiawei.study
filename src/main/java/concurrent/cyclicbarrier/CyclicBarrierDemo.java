package concurrent.cyclicbarrier;

import java.util.concurrent.*;

/**
 * 4个人一起跑，等4个人都跑到终点了，才可以去吃饭……
 *
 * @author jw.fang
 * @version 1.0
 */
public class CyclicBarrierDemo
{
    public static void main(String[] args) throws InterruptedException
    {
        ExecutorService exec = Executors.newCachedThreadPool();
        final CyclicBarrier barrier = new CyclicBarrier(4, new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println("好了，大家可以去吃饭了……");
            }
        });

        System.out.println("要吃饭，必须所有人都到终点，oK?");

        for (int i = 0; i < 4; i++)
        {
            exec.execute(new Runnable()
            {

                @Override
                public void run()
                {
                    System.out.println(Thread.currentThread().getName() + ":Go");
                    try
                    {
                        Thread.sleep((long) (2000 * Math.random()));
                    }
                    catch (InterruptedException e1)
                    {
                        e1.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ":我到终点了");
                    try
                    {
                        barrier.await();
                    }
                    catch (InterruptedException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    catch (BrokenBarrierException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });

        }
        exec.shutdown();

    }
}
