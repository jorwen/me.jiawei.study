package designpattern.c_singleton;

/**
 * 内部类单例模式
 *
 * @author jw.fang
 * @version 1.0
 */
public class InnerClassSingleton
{
    private static class SingletonHolder
    {
        //静态初始化器，由JVM来保证线程安全
        private static InnerClassSingleton instance = new InnerClassSingleton();
    }

    //私有化构造方法
    private InnerClassSingleton()
    {
    }

    public static InnerClassSingleton getInstance()
    {
        return SingletonHolder.instance;
    }
}
