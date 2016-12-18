package concurrent.philosophereat;

/**
 * 每一个工作单元（哲学家）总是先拿起左右两边编号较低的餐叉，再拿编号较高的。
 * 用完餐叉后，他总是先放下编号较高的餐叉，再放下编号较低的。
 */
public class PhilosopherSort extends PhilosopherDead implements IPhilosopher
{
    public PhilosopherSort(int name, Chopstick leftChopstick, Chopstick rightChopstick)
    {
        super(name, leftChopstick, rightChopstick);
    }

    /**
     * 吃饭，需要两只筷子
     */
    public void eat() throws InterruptedException
    {
        Chopstick c1 = (leftChopstick.getNum() < rightChopstick.getNum())?leftChopstick:rightChopstick;
        Chopstick c2 = (leftChopstick.getNum() > rightChopstick.getNum())?leftChopstick:rightChopstick;
        c1.getStick(name);
        c2.getStick(name);
        System.out.println("【" + name + "】开始吃饭");
        state = 1;
        Thread.sleep(10000);
        c2.putStick(name);
        c1.putStick(name);
    }
}
