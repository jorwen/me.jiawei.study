package concurrent.waitnotify;

/**
 * 建立三个线程，A线程打印10次A，B线程打印10次B,C线程打印10次C，要求线程同时运行，交替打印10次ABC。
 * 这个问题用Object的wait()，notify()就可以很方便的解决。
 *
 * @author jw.fang
 * @version 1.0
 */
public class WaitNotifyTest implements Runnable
{
    private String name;
    private final Object prev;
    private final Object self;

    private WaitNotifyTest(String name, Object prev, Object self)
    {
        this.name = name;
        this.prev = prev;
        this.self = self;
    }

    @Override
    public void run()
    {
        int count = 10;
        while (count > 0)
        {
            synchronized (prev)
            {
                synchronized (self)
                {
                    System.out.print(name);
                    count--;
                    try
                    {
                        Thread.sleep(1);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }

                    self.notify();
                }
                try
                {
                    prev.wait();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception
    {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        WaitNotifyTest pa = new WaitNotifyTest("A", c, a);
        WaitNotifyTest pb = new WaitNotifyTest("B", a, b);
        WaitNotifyTest pc = new WaitNotifyTest("C", b, c);


        new Thread(pa).start();
        Thread.sleep(10);
        new Thread(pb).start();
        Thread.sleep(10);
        new Thread(pc).start();
        Thread.sleep(10);
    }
}
