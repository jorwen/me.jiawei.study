package concurrent.philosophereat;

/**
 * 服务生，他知道谁能就餐.负责锁
 */
public class Server
{
    private Chopstick[] chopsticks;

    public Server(Chopstick[] chopsticks)
    {
        this.chopsticks = chopsticks;
    }

    public synchronized boolean canEat(int name)
    {
        Chopstick left = chopsticks[name-1];
        Chopstick right = chopsticks[((name)==5?0:(name))];
        if(left.isCanUse() && right.isCanUse())
        {
            left.getStick(name);
            right.getStick(name);
            System.out.println("服务生同意"+name+"号就餐");
            return true;
        }
        else
        {
            return false;
        }
    }
}
