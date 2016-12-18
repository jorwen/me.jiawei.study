package concurrent.semaphore;

/**
 * todo:
 *
 * @author jw.fang
 * @version 1.0
 */
public class PrintJob implements Runnable
{
    //9.   声明一个对象为PrintQueue，名为printQueue。
    private MultiPrint printQueue;

    //10. 实现类的构造函数，初始化这个类里的PrintQueue对象。
    public PrintJob(MultiPrint printQueue)
    {
        this.printQueue = printQueue;
    }

    //11. 实现方法run()。
    @Override
    public void run()
    {

        //12. 首先， 此方法写信息到操控台表明任务已经开始执行了。
        System.out.printf("%s: Going to print a job\n", Thread.currentThread().getName());

        //13. 然后，调用PrintQueue 对象的printJob()方法。
        printQueue.printJob(new Object());

        //14. 最后， 此方法写信息到操控台表明它已经结束运行了。
        System.out.printf("%s: The document has been printed\n", Thread.currentThread().getName());
    }

    //15. 实现例子的main类，创建名为 Main的类并实现main()方法。
    public static void main(String args[])
    {
        //16. 创建PrintQueue对象名为printQueue。
        MultiPrint printQueue = new MultiPrint();

        //17. 创建10个threads。每个线程会执行一个发送文档到print queue的Job对象。
        Thread thread[] = new Thread[10];
        for (int i = 0; i < 10; i++)
        {
            thread[i] = new Thread(new PrintJob(printQueue), "Thread" + i);
        }

        //18. 最后，开始这10个线程们。
        for (int i = 0; i < 10; i++)
        {
            thread[i].start();
        }
    }
}
