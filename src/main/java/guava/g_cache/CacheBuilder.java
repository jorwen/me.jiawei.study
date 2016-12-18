package guava.g_cache;

import com.google.common.cache.*;

import java.util.concurrent.TimeUnit;

/**
 * 方法链范例，模仿guava，精简一下
 */
public class CacheBuilder<K, V> {
    static final int UNSET_INT = -1;
    long expireAfterWriteNanos = UNSET_INT;

    //私有构造函数
    private CacheBuilder() {}

    //真正的额构造函数
    public static CacheBuilder<Object, Object> newBuilder() {
        return new CacheBuilder<Object, Object>();
    }

    public CacheBuilder<K, V> expireAfterWrite(long duration, TimeUnit unit) {
        //略：参数检查
        this.expireAfterWriteNanos = unit.toNanos(duration);
        return this;
    }

    public <K1 extends K, V1 extends V> LoadingCache<K1, V1> build(CacheLoader<? super K1, V1> loader) {
        return new LocalCache.LocalLoadingCache<K1, V1>(this, loader);
    }

    public <K1 extends K, V1 extends V> Cache<K1, V1> build() {
        return new LocalCache.LocalManualCache<K1, V1>(this);
    }
}
