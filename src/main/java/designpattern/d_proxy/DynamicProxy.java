package designpattern.d_proxy;

import java.lang.reflect.*;

public class DynamicProxy implements InvocationHandler
{
    private Object target;
    public Object bind(Object target)
    {
        this.target = target;
        //取得代理对象
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);   //要绑定接口(这是一个缺陷，cglib弥补了这一缺陷)
    }

    @Override
    /**
     * 调用方法
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        Object result = null;
        System.out.println("事物开始");
        //执行方法
        result = method.invoke(target, args);
        System.out.println("事物结束");
        return result;
    }

    public static void main(String[] args)
    {
        DynamicProxy dynamicProxy = new DynamicProxy();
        Count count = (Count)dynamicProxy.bind(new CountImpl());
        count.queryCount();
        count.updateCount();
    }
}
