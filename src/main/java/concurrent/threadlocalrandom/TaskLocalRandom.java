package concurrent.threadlocalrandom;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 它使用线程局部变量。每个线程希望以不同的生成器生成随机数，但它们是来自相同类的管理，
 * 这对程序员是透明的。在这种机制下，你将获得比使用共享的Random对象为所有线程生成随机数更好的性能。
 *
 * @author jw.fang
 * @version 1.0
 */
public class TaskLocalRandom implements Runnable
{
    public TaskLocalRandom()
    {
        ThreadLocalRandom.current();
    }

    @Override
    public void run()
    {
        String name = Thread.currentThread().getName();
        for (int i = 0; i < 10; i++)
        {
            System.out.printf("%s: %d\n", name, ThreadLocalRandom.current().nextInt(10));
        }
    }

    public static void main(String[] args)
    {
        Thread threads[] = new Thread[3];
        for (int i = 0; i < 3; i++)
        {
            TaskLocalRandom task = new TaskLocalRandom();
            threads[i] = new Thread(task);
            threads[i].start();
        }
    }
}
