package concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 继承一个原子对象和如何实现遵从原子对象机制的两个操作，来保证所有的操作在一个步骤内完成。
 *
 * @author jw.fang
 * @version 1.0
 */
public class ParkingCounter extends AtomicInteger
{
    private int maxNumber;

    public ParkingCounter(int maxNumber)
    {
        set(0);
        this.maxNumber = maxNumber;
    }

    public boolean carIn()
    {
        for (; ; )
        {
            int value = get();
            if (value == maxNumber)
            {
                System.out.printf("ParkingCounter: The parking lot is full.\n");
                return false;
            }
            else
            {
                int newValue = value + 1;
                boolean changed = compareAndSet(value, newValue);
                if (changed)
                {
                    System.out.printf("ParkingCounter: A car has entered.\n");
                    return true;
                }
            }
        }
    }

    public boolean carOut()
    {
        for (; ; )
        {
            int value = get();
            if (value == 0)
            {
                System.out.printf("ParkingCounter: The parking lot is empty.\n");
                return false;
            }
            else
            {
                int newValue = value - 1;
                boolean changed = compareAndSet(value, newValue);
                if (changed)
                {
                    System.out.printf("ParkingCounter: A car has gone out.\n");
                    return true;
                }
            }
        }
    }
}
