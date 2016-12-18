package designpattern.c_singleton;

/**
 * 枚举类单例
 *
 * @author jw.fang
 * @version 1.0
 */
public enum EnumSingleton
{
    INSTANCE;

    public void leaveTheBuilding()
    {
        System.out.println("Whoa baby, I'm outta here!");
    }

    // This code would normally appear outside the class!
    public static void main(String[] args)
    {
        EnumSingleton elvis = EnumSingleton.INSTANCE;
        elvis.leaveTheBuilding();
    }
}
