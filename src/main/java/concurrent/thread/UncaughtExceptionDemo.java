package concurrent.thread;

/**
 * 在一个线程 对象的 run() 方法里抛出一个检查异常，我们必须捕获并处理他们。
 * 因为 run() 方法不接受 throws 子句。当一个非检查异常被抛出，默认的行为是在控制台写下stack trace并退出程序。
 *
 * @author jw.fang
 * @version 1.0
 */
public class UncaughtExceptionDemo
{
    public static void main(String[] args)
    {
        ExceptionTask task=new ExceptionTask();
        Thread thread=new Thread(task);
        thread.setUncaughtExceptionHandler(new ExceptionHandler());
        thread.start();
    }
}

class ExceptionTask implements Runnable
{
    @Override
    public void run()
    {
        int numero = Integer.parseInt("TTT");
    }
}
class ExceptionHandler implements Thread.UncaughtExceptionHandler
{
    public void uncaughtException(Thread t, Throwable e) {
      System.out.printf("An exception has been captured\n");
      System.out.printf("Thread: %s\n",t.getId());
      System.out.printf("Exception: %s: %s\n",e.getClass().getName(),e.getMessage());
      System.out.printf("Stack Trace: \n");
      e.printStackTrace(System.out); System.out.printf("Thread status: %s\n",t.getState());
    }
}
