package concurrent.executor;

import java.util.Date;
import java.util.concurrent.*;

/**
 * 执行者延迟运行一个任务
 *
 * @author jw.fang
 * @version 1.0
 */
public class DelyExecutor
{
    public static void main(String[] args)
    {
        ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
        System.out.printf("Main: Starting at: %s\n", new Date());
        for (int i = 0; i < 5; i++)
        {
            DelayTask task = new DelayTask("Task " + i);
            executor.schedule(task, i + 1, TimeUnit.SECONDS);
        }
        executor.shutdown();
        try
        {
            executor.awaitTermination(1, TimeUnit.DAYS);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.printf("Main: Ends at: %s\n", new Date());
    }
}

class DelayTask implements Callable<String>
{
    private String name;

    public DelayTask(String name)
    {
        this.name = name;
    }

    public String call() throws Exception
    {
        System.out.printf("%s: Starting at : %s\n", name, new Date());
        return "Hello, world";
    }
}
