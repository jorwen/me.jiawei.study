package concurrent.philosophereat;

/**
 * 导致饥饿死锁的哲学家
 */
public class PhilosopherDead implements IPhilosopher
{
    protected int name;

    protected Chopstick leftChopstick;

    protected Chopstick rightChopstick;

    protected int state;

    public PhilosopherDead(int name, Chopstick leftChopstick, Chopstick rightChopstick)
    {
        this.name = name;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
    }

    /**
     * 思考
     */
    public void think() throws InterruptedException
    {
        //思考5秒
        System.out.println("【" + name + "】开始思考");
        state = 0;
        Thread.sleep(5000);
    }

    public void setState(int state)
    {
        this.state = state;
    }

    /**
     * 吃饭，需要两只筷子
     */
    public void eat() throws InterruptedException
    {
        leftChopstick.getStick(name);
        rightChopstick.getStick(name);
        System.out.println("【" + name + "】开始吃饭");
        state = 1;
        Thread.sleep(10000);
        leftChopstick.putStick(name);
        rightChopstick.putStick(name);
    }

    @Override
    public int getState()
    {
        return state;
    }

    @Override
    public int getName()
    {
        return name;
    }
}
