package concurrent.executor;

import java.util.concurrent.*;

/**
 * 执行者分离任务的启动和结果的处理
 *
 * @author jw.fang
 * @version 1.0
 */
public class CompletionServiceExecutor
{
    public static void main(String[] args)
    {
        //1.使用Executors类的newCachedThreadPool()方法创建ThreadPoolExecutor
        ExecutorService executor = Executors.newCachedThreadPool();
        //2.创建CompletionService，使用前面创建的执行者作为构造器的参数。
        CompletionService<String> service = new ExecutorCompletionService<String>(executor);
        //3.创建两个ReportRequest对象，并用线程执行它们。
        ReportRequest faceRequest = new ReportRequest("Face", service);
        ReportRequest onlineRequest = new ReportRequest("Online", service);
        Thread faceThread = new Thread(faceRequest);
        Thread onlineThread = new Thread(onlineRequest);
        //4.创建一个ReportProcessor对象，并用线程执行它。
        ReportProcessor processor = new ReportProcessor(service);
        Thread senderThread = new Thread(processor);
        //5.启动这3个线程。
        System.out.printf("Main: Starting the Threads\n");
        faceThread.start();
        onlineThread.start();
        senderThread.start();
        //6.等待ReportRequest线程的结束。
        try
        {
            System.out.printf("Main: Waiting for the report generators.\n");
            faceThread.join();
            onlineThread.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        //7.使用shutdown()方法关闭执行者，使用awaitTermination()方法等待任务的结果。
        System.out.printf("Main: Shutting down the executor.\n");
        executor.shutdown();
        try
        {
            executor.awaitTermination(1, TimeUnit.DAYS);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        //8.设置ReportSender对象的end属性值为true，结束它的执行。
        processor.setEnd(true);
        System.out.println("Main: Ends");
    }
}

class ReportGenerator implements Callable<String>
{
    private String sender;
    private String title;

    public ReportGenerator(String sender, String title)
    {
        this.sender = sender;
        this.title = title;
    }

    @Override
    public String call() throws Exception
    {
        try
        {
            Long duration = (long) (Math.random() * 10);
            System.out.printf("%s_%s: ReportGenerator: Generating a report during %d seconds\n", this.sender, this.title, duration);
            TimeUnit.SECONDS.sleep(duration);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        String ret = sender + ": " + title;
        return ret;
    }
}

class ReportRequest implements Runnable
{
    private String name;
    private CompletionService<String> service;

    public ReportRequest(String name, CompletionService<String> service)
    {
        this.name = name;
        this.service = service;
    }

    @Override
    public void run()
    {
        ReportGenerator reportGenerator = new ReportGenerator(name, "Report");
        service.submit(reportGenerator);
    }
}

class ReportProcessor implements Runnable
{
    private CompletionService<String> service;
    private boolean end;

    public ReportProcessor(CompletionService<String> service)
    {
        this.service = service;
        end = false;
    }

    @Override
    public void run()
    {
        while (!end)
        {
            try
            {
                Future<String> result = service.poll(20, TimeUnit.SECONDS);
                if (result != null)
                {
                    String report = result.get();
                    System.out.printf("ReportReceiver: Report Received:%s\n", report);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        System.out.printf("ReportSender: End\n");
    }

    public void setEnd(boolean end)
    {
        this.end = end;
    }
}
