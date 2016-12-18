package jvm.memerror;

import net.sf.cglib.proxy.*;

import java.lang.reflect.*;

/**
 * 当我们加载过多的类时就会导致方法区溢出。在这里我们通过CGLIB代理两种方式来试图使方法区溢出。java.lang.OutOfMemoryError，-XX:MaxPermSize=50M设置
 * 实例用cglib动态代理试图使方法区溢出
 *
 * @author jw.fang
 * @version 1.0
 */
public class TestMethodAreaOverflowError
{
    static class OOMObject
    {}

    public static void main(String[] args)
    {
        while (true)
        {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor()
            {
                @Override
                public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable
                {
                    return method.invoke(obj, args);
                }
            });
            OOMObject proxy = (OOMObject) enhancer.create();
            System.out.println(proxy.getClass());
        }
    }
}
