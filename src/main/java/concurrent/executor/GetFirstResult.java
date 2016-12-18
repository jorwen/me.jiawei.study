package concurrent.executor;

import java.util.*;
import java.util.concurrent.*;

/**
 * 第一个任务返回true，invokeAny()方法的结果是第一个完成任务的名称。
 * 第一个任务返回true，第二个任务抛出异常。invokeAny()方法的结果是第一个任务的名称。
 * 第一个任务抛出异常，第二个任务返回true。invokeAny()方法的结果是第二个任务的名称。
 * 两个任务都抛出异常。在本例中，invokeAny()方法抛出一个ExecutionException异常。
 */
public class GetFirstResult
{
    public static void main(String[] args)
    {
        ExecutorService executor = Executors.newCachedThreadPool();
        MyTask taskA = new MyTask("A");
        MyTask taskB = new MyTask("B");

        List<MyTask> taskList = new ArrayList<MyTask>();
        taskList.add(taskA);
        taskList.add(taskB);

        try
        {
            for (int i = 0; i < 100; i++)
            {
                Boolean result = executor.invokeAny(taskList);
                System.out.println(result);
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }
        executor.shutdown();
    }
}

class MyTask implements Callable<Boolean>
{

    String name;

    MyTask(String name)
    {
        this.name = name;
    }

    @Override
    public Boolean call() throws Exception
    {
        boolean random = new Random().nextBoolean();

        if (random)
        {
            System.out.println(name + "正确！");
            return true;
        }
        else
        {
            System.out.println(name + "异常！");
            throw new RuntimeException(name + "任务异常");
        }
    }
}
