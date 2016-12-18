package distributed.cacheinteger;

import com.google.code.ssm.*;
import com.google.code.ssm.api.format.SerializationType;
import com.google.code.ssm.providers.CacheException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;
import java.util.concurrent.*;

public class TestXMemcached
{
    public static Cache cache;

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException, CacheException
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("/applicationContext.xml");
        cache = (Cache) context.getBean("memcachedClient");
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

    public static void initCount(Cache cache, Integer count) throws TimeoutException, CacheException
    {
        cache.set("count", 360, count, SerializationType.PROVIDER);
        cache.delete("lock");
    }

    public static Integer getCount(Cache cache) throws ExecutionException, TimeoutException, CacheException
    {
        return (Integer) cache.get("count", SerializationType.PROVIDER);
    }

    static class Worker extends Thread
    {
        Cache cache;
        String uuid;

        public Worker(Cache cache)
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
                    //随机等待时间，如果都是统一的容易造成一起拿一起放的死锁
                    //此值大小要适合，太大和太小都影响性能
                    int ran = new Random().nextInt(50);
                    if (isLock(cache))
                    {
                        sleep(ran);
                        continue;
                    }
                    lock(cache);
                    boolean isSelfLock = true;
                    try
                    {
                        sleep(3);//等一下可能同时在设置的人,此值越大越可靠，但越小性能越好，设置成比lock()执行快一点就行，不设置有可能最后不为0
                        isSelfLock = isSelfLock(cache);
                        if (!isSelfLock)
                        {
                            isSelfLock = false;
                            sleep(ran);
                            continue;
                        }
                        Integer current = getCount(cache);
                        Integer next = current - 1;
                        countDown(cache, next);
                        break;
                    }
                    catch (Throwable t)
                    {
                        t.printStackTrace();
                        throw new RuntimeException(t);
                    }
                    finally
                    {
                        if (isSelfLock) unLock(cache);
                    }
                }
            }
            catch (InterruptedException e1)
            {
                e1.printStackTrace();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        //是否锁定
        public Boolean isLock(Cache cache) throws ExecutionException, TimeoutException, CacheException
        {
            return cache.get("lock", SerializationType.PROVIDER) != null;
        }

        //是否自己锁定的
        public Boolean isSelfLock(Cache cache) throws TimeoutException, CacheException
        {
            String l = cache.get("lock", SerializationType.PROVIDER);
            return uuid.equals(l);
        }

        //加锁
        public void lock(Cache cache) throws TimeoutException, CacheException, InterruptedException
        {
            cache.set("lock", 360, uuid, SerializationType.PROVIDER);
        }

        //获得当前数量
        public Integer getCount(Cache cache) throws ExecutionException, TimeoutException, CacheException
        {
            return (Integer) cache.get("count", SerializationType.PROVIDER);
        }

        //减1操作
        public void countDown(Cache cache, Integer next) throws ExecutionException, CacheException, TimeoutException
        {
            System.out.println(next);
            cache.set("count", 360, next, SerializationType.PROVIDER);
        }

        //解锁
        public void unLock(Cache cache) throws TimeoutException, CacheException
        {
            cache.delete("lock");
        }
    }
}
