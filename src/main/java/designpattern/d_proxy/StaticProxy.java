package designpattern.d_proxy;

/**
 * 静态代理
 *
 * @author jw.fang
 * @version 1.0
 */
public class StaticProxy implements Count
{
    private CountImpl countImpl;

    /**
     * 覆盖默认构造器
     *
     * @param countImpl
     */
    public StaticProxy(CountImpl countImpl)
    {
        this.countImpl = countImpl;
    }

    @Override
    public void queryCount()
    {
        System.out.println("事务处理之前");
        // 调用委托类的方法;
        countImpl.queryCount();
        System.out.println("事务处理之后");
    }

    @Override
    public void updateCount()
    {
        System.out.println("事务处理之前");
        // 调用委托类的方法;
        countImpl.updateCount();
        System.out.println("事务处理之后");
    }

    public static void main(String[] args)
    {
        CountImpl countImpl = new CountImpl();
        StaticProxy countProxy = new StaticProxy(countImpl);
        countProxy.updateCount();
        countProxy.queryCount();
    }
}
