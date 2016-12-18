package concurrent.philosophereat;

import java.util.*;

/**
 * 哲学家问题死锁范例
 */
public class Restaurant
{
    //5双筷子
    private static Chopstick[] chopsticks;
    private static IPhilosopher[] iPhilosophers;
    private static Server server;

    public static void main(String[] args) throws InterruptedException
    {
        chopsticks = new Chopstick[5];
        iPhilosophers = new IPhilosopher[5];

        for (int i = 1; i <= 5; i++)
        {
            chopsticks[i - 1] = new Chopstick(i, true);
        }
        server = new Server(chopsticks);
        List<Thread> list = new ArrayList<Thread>();

        for (int i = 1; i <= 5; i++)
        {
            iPhilosophers[i - 1] = new PhilosopherServer(i, chopsticks[i - 1], chopsticks[i % 5]);
            list.add(new EatThread(iPhilosophers[i - 1]));
        }

        for (Thread aList : list)
        {
            aList.start();
        }
        while (true)
        {
            String s = "------正在吃饭的人：";
            for (IPhilosopher philosopher : iPhilosophers)
            {
                if (philosopher.getState() == 1)
                {
                    s += " " + philosopher.getName() + "号";
                }
            }
            s += "------可以用的筷子：";
            for (Chopstick chop : chopsticks)
            {
                if (chop.isCanUse())
                {
                    s += " " + chop.getNum();
                }
            }
            if (s.contains("号"))
            {
                System.out.println(s);
            }
            Thread.sleep(3000);
        }
    }

    static class EatThread extends Thread
    {
        IPhilosopher philosopher;

        public EatThread(IPhilosopher philosopher)
        {
            this.philosopher = philosopher;
        }

        public void run()
        {
            while (true)
            {
                try
                {
                    if (philosopher instanceof PhilosopherServer)
                    {
                        //服务生解法
                        if (server.canEat(philosopher.getName()))
                            philosopher.eat();
                        philosopher.think();
                    }
                    else
                    {
                        //非服务生解法
                        philosopher.eat();
                        philosopher.think();
                    }

                }
                catch (Exception e)
                {
                    philosopher.setState(-1);
                    e.printStackTrace();
                }
            }
        }
    }
}

