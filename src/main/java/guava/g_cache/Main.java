package guava.g_cache;

import com.google.common.cache.*;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        //简单
        Cache<Long, String> sample = CacheBuilder.newBuilder().build();

        //LoadingCache
        LoadingCache<Long, String> graphs = CacheBuilder.newBuilder()
                .expireAfterWrite(100, TimeUnit.MINUTES)
                .build(new CacheLoader<Long, String>() {
                    @Override
                    public String load(Long key) throws Exception {
                        return null;
                    }
                });

        //callable，这个方法简便地实现了模式"如果有缓存则返回；否则运算、缓存、然后返回"
        try {
                sample.get(2L, new Callable<String>() {
                @Override
                public String call() throws Exception {
                    //无缓存就计算再返回
                    return null;
                }
            });
        }
        catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
