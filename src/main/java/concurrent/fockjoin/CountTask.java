package concurrent.fockjoin;

import java.util.concurrent.*;

/**
 * 计算1+2+3+4...结果
 *
 * @author jw.fang
 * @version 1.0
 */
public class CountTask extends RecursiveTask<Integer>
{
    private static final int THRESHOLD = 2;//阀值
    private int start;
    private int end;

    public CountTask(int start, int end)
    {
        System.out.println("任务"+ Thread.currentThread().getId()+":" + start + "," + end);
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute()
    {
        int sum = 0;

        //如果任务足够小就计算任务
        boolean canCompute = (end - start) <= THRESHOLD;
        if (canCompute)
        {
            for (int i = start; i <= end; i++)
            {
                sum += i;
            }
        }
        else
        {
            int middle = (start + end) / 2;
            CountTask leftTask = new CountTask(start, middle);
            CountTask rightTask = new CountTask(middle + 1, end);
            //执行子任务
            leftTask.fork();
            rightTask.fork();
            //等待子任务完成，并得到结果
            int left = leftTask.join();
            int right = rightTask.join();
            //合并子任务
            sum = left + right;
        }
        return sum;
    }

    public static void main(String[] args)
    {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask task = new CountTask(1,100);
        //执行任务
        Future<Integer> result = forkJoinPool.submit(task);
        try{
            System.out.println("最终结果:"+result.get());
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }

        //异常处理
        if(task.isCompletedAbnormally())
        {
            System.out.println(task.getException());
        }
    }
}
