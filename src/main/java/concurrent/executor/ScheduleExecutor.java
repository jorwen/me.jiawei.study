package concurrent.executor;

import java.util.Date;
import java.util.concurrent.*;

/**
 * 执行者周期性地运行一个任务
 *
 * @author jw.fang
 * @version 1.0
 */
public class ScheduleExecutor
{
    public static void main(String[] args)
    {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        System.out.printf("Main: Starting at: %s\n", new Date());
        ScheduleTask task = new ScheduleTask("Task");
        ScheduledFuture<?> result = executor.scheduleAtFixedRate(task, 1, 2, TimeUnit.SECONDS);
        //创建10个循环步骤，写入任务下次执行的剩余时间。在循环中，使用ScheduledFuture对象的getDelay()方法，获取任务下次执行的毫秒数。
        for (int i = 0; i < 10; i++)
        {
            System.out.printf("Main: Delay: %d\n", result.getDelay(TimeUnit.MILLISECONDS));
            //线程睡眠500毫秒
            try
            {
                TimeUnit.MILLISECONDS.sleep(500);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        executor.shutdown();
        //使线程睡眠5秒，检查周期性任务是否完成。
        try
        {
            TimeUnit.SECONDS.sleep(5);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.printf("Main: Finished at: %s\n", new Date());
    }
}

class ScheduleTask implements Runnable
{
    private String name;

    public ScheduleTask(String name)
    {
        this.name = name;
    }

    @Override
    public void run()
    {
        System.out.printf("%s: Starting at : %s\n", name, new Date());
    }
}