package designpattern.c_singleton;

/**
 * 静态工厂单例
 *
 * @author jw.fang
 * @version 1.0
 */
public class StaticFactorySingleton
{
    private static final StaticFactorySingleton INSTANCE = new StaticFactorySingleton();

    private StaticFactorySingleton() { }

    public static StaticFactorySingleton getInstance() { return INSTANCE; }

    public void leaveTheBuilding()
    {
        System.out.println("Whoa baby, I'm outta here!");
    }

    // This code would normally appear outside the class!
    public static void main(String[] args)
    {
        StaticFactorySingleton elvis = StaticFactorySingleton.getInstance();
        elvis.leaveTheBuilding();
    }
}
