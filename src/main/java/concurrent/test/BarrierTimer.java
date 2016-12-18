package concurrent.test;

/**
 * todo:
 *
 * @author jw.fang
 * @version 1.0
 */
public class BarrierTimer implements Runnable
{
    private boolean started;
    private long startTime, endTime;

    @Override
    public synchronized void run()
    {
        long t = System.nanoTime();
        if(!started)
        {
            started = true;
            startTime = t;
        }
        else
            endTime = t;
    }

    public synchronized void clear()
    {
        started = false;
    }

    public synchronized long getTime()
    {
        return endTime - startTime;
    }
}
