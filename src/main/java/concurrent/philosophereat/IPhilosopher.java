package concurrent.philosophereat;

/**
 * todo:
 *
 * @author jw.fang
 * @version 1.0
 */
public interface IPhilosopher
{
    public void think()throws InterruptedException;

    public void eat() throws InterruptedException;

    public void setState(int i);

    public int getState();

    public int getName();
}
