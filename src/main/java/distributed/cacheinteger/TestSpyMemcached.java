package distributed.cacheinteger;

import com.google.code.ssm.providers.CacheException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import net.spy.memcached.CASResponse;
import net.spy.memcached.CASValue;
import net.spy.memcached.MemcachedClient;
import java.util.*;
import java.util.concurrent.*;

public class TestSpyMemcached
{
    public static MemcachedClient  cache;

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException, CacheException
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("/applicationContext.xml");
        cache = (MemcachedClient) context.getBean("spyMemcachedClient");
        int all = 1000;
        initCount(cache, all);
        long start = System.currentTimeMillis();
        List<Thread> ts = new ArrayList<Thread>(all);

        for (int i = 0; i < all; i++)
        {
            Thread thread = new Worker(cache);
            thread.start();
            ts.add(thread);
        }
        System.out.println(all+"个线程全部启动");
        // 等待所有线程执行完成

        for (Thread t : ts)
        {
            try
            {
                t.join();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        System.out.println("共" + all + "张票，执行" + (System.currentTimeMillis() - start) + "ms, 最后剩下票子" + getCount(cache) + "张");
        cache.shutdown();
    }

    public static void initCount(MemcachedClient cache, Integer count) throws TimeoutException, CacheException
    {
        cache.set("count", 360, count);
    }

    public static Integer getCount(MemcachedClient cache) throws ExecutionException, TimeoutException, CacheException
    {
        return (Integer) cache.get("count");
    }

    static class Worker extends Thread
    {
        MemcachedClient cache;
        String uuid;

        public Worker(MemcachedClient cache)
        {
            this.cache = cache;
            this.uuid = UUID.randomUUID().toString();
        }

        public void run()
        {
            //读取缓存，获取数字减一
            try
            {
                while (true)
                {
                    CASValue<Object> uniqueValue = cache.gets("count");
                    CASResponse response = cache.cas("count", uniqueValue.getCas(), (Integer) uniqueValue.getValue() - 1);
                    if (response.toString().equals("OK"))
                    {
                        System.out.println(uniqueValue.getValue());
                        break;
                    }
                    else{
                        sleep(50);
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
