package concurrent.phaser;

import java.util.concurrent.*;

/**
 * 4个人一起跑，等4个人都跑到终点了，才可以去吃饭……
 *
 * @author jw.fang
 * @version 1.0
 */
public class CyclicBarrierPhaser
{
    public static void main(String[] args) throws InterruptedException
    {
        ExecutorService exec = Executors.newCachedThreadPool();
        final Phaser phaser = new Phaser(4)
        {
            @Override
            protected boolean onAdvance(int phase, int registeredParties)
            {
                System.out.println("好了，大家可以去吃饭了……");
                return true;
            }
        };

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
                        phaser.arriveAndAwaitAdvance();
                    }
                    catch (Exception e)
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
