package concurrent.philosophereat;

/**
 * 服务生解法
 * 一个简单的解法是引入一个餐厅服务生，哲学家必须经过他的允许才能拿起餐叉。
 * 因为服务生知道哪只餐叉正在使用，所以他能够作出判断避免死锁。
 */
public class PhilosopherServer extends PhilosopherDead implements IPhilosopher
{
    public PhilosopherServer(int name, Chopstick leftChopstick, Chopstick rightChopstick)
    {
        super(name, leftChopstick, rightChopstick);
    }

    /**
     * 吃饭，由于服务生分配了，直接吃
     */
    public void eat() throws InterruptedException
    {
        System.out.println("【" + name + "】开始吃饭");
        state = 1;
        Thread.sleep(10000);
        leftChopstick.putStick(name);
        rightChopstick.putStick(name);
    }
}
