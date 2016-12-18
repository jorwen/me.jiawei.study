package concurrent.philosophereat;

public class Chopstick
{
    protected int num;
    protected boolean canUse;

    public Chopstick(int num, boolean canUse)
    {
        this.num = num;
        this.canUse = canUse;
    }

    public int getNum()
    {
        return num;
    }

    public void setNum(int num)
    {
        this.num = num;
    }

    public boolean isCanUse()
    {
        return canUse;
    }

    public void setCanUse(boolean canUse)
    {
        this.canUse = canUse;
    }

    public synchronized void getStick(int name)
    {
        while (!isCanUse())
        {
            try
            {
                System.out.println(name + "等待筷子：" + getNum());
                wait();//让操作此资源的线程（哲学家）等待
            }
            catch (InterruptedException ex)
            {
                ex.printStackTrace();
            }
        }
        setCanUse(false);
    }

    public synchronized boolean getStickOther(int name, Chopstick first, long timeout)
    {
        long start = System.currentTimeMillis();
        while (!isCanUse())
        {
            try
            {
                System.out.println(name + "等待筷子：" + getNum() + "," + timeout + "ms");
                if (System.currentTimeMillis() - start > timeout)
                {
                    first.putStick(name);
                    return false;
                }
                else
                {
                    wait(500);
                }
            }
            catch (InterruptedException ex)
            {
                ex.printStackTrace();
            }
        }
        setCanUse(false);
        return true;
    }

    public synchronized void putStick(int name)
    {
        if (!isCanUse())
        {
            System.out.println(name + "放下筷子：" + getNum());
            setCanUse(true);
            notify();//唤醒等待的线程
        }
    }
}
