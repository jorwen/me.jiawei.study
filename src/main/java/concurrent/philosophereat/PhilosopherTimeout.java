package concurrent.philosophereat;

import java.util.Random;

/**
 * 这个哲学家超时后如果发现手上拿有一个筷子，放下来
 */
public class PhilosopherTimeout extends PhilosopherDead implements IPhilosopher
{
    private long timeout;

    public PhilosopherTimeout(int name, Chopstick leftChopstick, Chopstick rightChopstick)
    {
        super(name, leftChopstick, rightChopstick);
        timeout = (new Random().nextInt(10) + 1) * 1000;
    }

    /**
     * 吃饭，需要两只筷子
     */
    public void eat() throws InterruptedException
    {
        leftChopstick.getStick(name);
        //如果超时返回false，成功返回true
        boolean isSuccess = rightChopstick.getStickOther(name, leftChopstick, timeout);
        if (isSuccess)
        {
            System.out.println("【" + name + "】开始吃饭");
            state = 1;
            Thread.sleep(20000);
            leftChopstick.putStick(name);
            rightChopstick.putStick(name);
        }
    }
}
