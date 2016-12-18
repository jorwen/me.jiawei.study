package jvm.memerror;

/**
 * java程序代码启动一个新线程时，没有足够的内存空间为该线程分配java栈，-Xss参数确定
 *
 * @author jw.fang
 * @version 1.0
 */
public class TestThreadStackOverflowError
{
    public static void main(String[] args)
    {
        int count = 0;
        while (true)
        {
            Thread thread = new Thread(new Runnable()
            {
                public void run()
                {
                    while (true)
                    {
                        try
                        {
                            Thread.sleep(5000);
                        }
                        catch (Exception e)
                        {
                        }
                    }
                }
            });
            thread.start();
            System.out.println(++count);
        }
    }
}
