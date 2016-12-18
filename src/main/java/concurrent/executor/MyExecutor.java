package concurrent.executor;

import java.util.*;
import java.util.concurrent.*;

/**
 * 通过继承ThreadPoolExecutor类和覆盖它的4个方法来实现我们自己定制的执行者。
 *
 * @author jw.fang
 * @version 1.0
 */
public class MyExecutor extends ThreadPoolExecutor
{
    public static void main(String[] args)
    {
        MyExecutor myExecutor = new MyExecutor(2, 4, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>());
        List<Future<String>> results = new ArrayList<Future<String>>();
        for (int i = 0; i < 10; i++)
        {
            SleepTwoSecondsTask task = new SleepTwoSecondsTask();
            Future<String> result = myExecutor.submit(task);
            results.add(result);
        }
        for (int i = 0; i < 5; i++)
        {
            try
            {
                String result = results.get(i).get();
                System.out.printf("Main: Result for Task %d : %s\n", i, result);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        myExecutor.shutdown();
        for (int i = 5; i < 10; i++)
        {
            try
            {
                String result = results.get(i).get();
                System.out.printf("Main: Result for Task %d : %s\n", i, result);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        try
        {
            myExecutor.awaitTermination(1, TimeUnit.DAYS);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.printf("Main: End of the program.\n");
    }

    private ConcurrentHashMap<String, Date> startTimes;

    public MyExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue)
    {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        startTimes = new ConcurrentHashMap<String, Date>();
    }

    @Override
    public void shutdown()
    {
        System.out.printf("MyExecutor: Going to shutdown.\n");
        System.out.printf("MyExecutor: Executed tasks: %d\n", getCompletedTaskCount());
        System.out.printf("MyExecutor: Running tasks: %d\n", getActiveCount());
        System.out.printf("MyExecutor: Pending tasks: %d\n", getQueue().size());
        super.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow()
    {
        System.out.printf("MyExecutor: Going to immediately shutdown.\n");
        System.out.printf("MyExecutor: Executed tasks: %d\n", getCompletedTaskCount());
        System.out.printf("MyExecutor: Running tasks: %d\n", getActiveCount());
        System.out.printf("MyExecutor: Pending tasks: %d\n", getQueue().size());
        return super.shutdownNow();
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r)
    {
        System.out.printf("MyExecutor: A task is beginning: %s : %s\n", t.getName(), r.hashCode());
        startTimes.put(String.valueOf(r.hashCode()), new Date());
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t)
    {
        Future<?> result = (Future<?>) r;
        try
        {
            System.out.printf("*********************************\n");
            System.out.printf("MyExecutor: A task is finishing.\n");
            System.out.printf("MyExecutor: Result: %s\n", result.get());
            Date startDate = startTimes.remove(String.valueOf(r.
                    hashCode()));
            Date finishDate = new Date();
            long diff = finishDate.getTime() - startDate.getTime();
            System.out.printf("MyExecutor: Duration: %d\n", diff);
            System.out.printf("*********************************\n");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

class SleepTwoSecondsTask implements Callable<String>
{
    public String call() throws Exception
    {
        TimeUnit.SECONDS.sleep(2);
        return new Date().toString();
    }
}



