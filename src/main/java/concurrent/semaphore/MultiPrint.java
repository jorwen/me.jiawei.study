package concurrent.semaphore;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

/**
 * 在3个不同的打印机上打印文件。
 *
 * @author jw.fang
 * @version 1.0
 */
public class MultiPrint
{
    private final Semaphore semaphore;
    private boolean freePrinters[];
    private Lock lockPrinters;

    public MultiPrint()
    {
        semaphore = new Semaphore(3);
        freePrinters = new boolean[3];

        for (int i = 0; i < 3; i++)
        {
            freePrinters[i] = true;
        }
        lockPrinters = new ReentrantLock();
    }

    public void printJob(Object document)
    {

        //5.   首先，调用acquire()方法获得semaphore的访问。由于此方法会抛出 InterruptedException异常，所以必须加入处理它的代码。
        try
        {
            semaphore.acquire();
            //6.   接着使用私有方法 getPrinter()来获得被安排打印任务的打印机的号码。
            int assignedPrinter = getPrinter();
            //7.	然后， 随机等待一段时间来实现模拟打印文档的行。
            long duration = (long) (Math.random() * 10);
            System.out.printf("%s: PrintQueue: Printing a Job in Printer%d during %d seconds\n", Thread.currentThread().getName(), assignedPrinter, duration);
            TimeUnit.SECONDS.sleep(duration);
            //8.   最后，调用release() 方法来解放semaphore并标记打印机为空闲，通过在对应的freePrinters array引索内分配真值。
            freePrinters[assignedPrinter] = true;
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        finally
        {
            semaphore.release();
        }

    }

    private int getPrinter()
    {
        //10. 首先，声明一个int变量来保存printer的引索值。
        int ret = -1;
        //11. 然后， 获得lockPrinters对象 object的访问。
        try
        {
            lockPrinters.lock();
            //12. 然后，在freePrinters array内找到第一个真值并在一个变量中保存这个引索值。修改值为false，因为等会这个打印机就会被使用。
            for (int i = 0; i < freePrinters.length; i++)
            {
                if (freePrinters[i])
                {
                    ret = i;
                    freePrinters[i] = false;
                    break;
                }
            }
            //13. 最后，解放lockPrinters对象并返回引索对象为真值。
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            lockPrinters.unlock();
        }
        return ret;
    }
}