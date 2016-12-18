package concurrent.executor;

import java.util.*;
import java.util.concurrent.*;

/**
 * 运行多个任务并处理所有结果
 *
 * @author jw.fang
 * @version 1.0
 */
public class MultiExecutorAndResult
{
    public static void main(String[] args)
    {
        //1.使用Executors类的newCachedThreadPool()方法
        ExecutorService executor = Executors.newCachedThreadPool();
        //2.创建Task对象列表。创建3个Task对象并且用这个列表来存储。
        List<Task> taskList = new ArrayList<Task>();
        for (int i = 0; i < 3; i++)
        {
            Task task = new Task("" + i);
            taskList.add(task);
        }
        //3.创建Future对象列表，参数化为Result类型。
        List<Future<Result>> resultList = null;
        //4.调用ThreadPoolExecutor类的invokeAll()方法。这个类将会返回之前创建的Future对象列表。
        try
        {
            resultList = executor.invokeAll(taskList);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        //5.使用shutdown()方法结束执行者。
        executor.shutdown();
        //6.写入处理Future对象列表任务的结果。
        System.out.println("Main: Printing the results");
        for (Future<Result> future : resultList)
        {
            try
            {
                Result result = future.get();
                System.out.println(result.getName() + ": " + result.getValue());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}

class Task implements Callable<Result>
{
    private String name;

    public Task(String name)
    {
        this.name = name;
    }

    @Override
    public Result call() throws Exception
    {
        //1.首先，写入一个信息到控制台，表明任务开始。
        System.out.printf("%s: Staring\n", this.name);
        //2.然后，等待一个随机时间。
        try
        {
            long duration = (long) (Math.random() * 10);
            System.out.printf("%s: Waiting %d seconds for results.\n", this.name, duration);
            TimeUnit.SECONDS.sleep(duration);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        //3.在Result对象中返回一个计算5个随机数的总和的int值。
        int value = 0;
        for (int i = 0; i < 5; i++)
        {
            value += (int) (Math.random() * 100);
        }
        //4.创建Result对象，用任务的名称和前面操作结果来初始化它。
        Result result = new Result();
        result.setName(this.name);
        result.setValue(value);
        //5.写入一个信息到控制台，表明任务已经完成。
        System.out.println(this.name + ": Ends");
        //6.返回Result对象。
        return result;
    }
}

class Result
{
    private String name;
    private int value;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        this.value = value;
    }
}
