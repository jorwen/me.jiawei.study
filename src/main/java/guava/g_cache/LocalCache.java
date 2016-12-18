package guava.g_cache;

import com.google.common.cache.*;
import com.google.common.collect.ImmutableMap;
import com.sun.istack.internal.Nullable;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.*;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 模拟LocalCache
 */
public class LocalCache<K, V> {
    LocalCache(CacheBuilder<? super K, ? super V> builder, @Nullable CacheLoader<? super K, V> loader) {
    }

    static class LocalManualCache<K, V> implements Cache<K, V>, Serializable {
        final LocalCache<K, V> localCache;

        LocalManualCache(CacheBuilder<? super K, ? super V> builder) {
            this(new LocalCache<K, V>(builder, null));
        }

        private LocalManualCache(LocalCache<K, V> localCache) {
            this.localCache = localCache;
        }

        @Override
        public V getIfPresent(Object o) {
            return null;
        }

        @Override
        public V get(K k, Callable<? extends V> callable) throws ExecutionException {
            return null;
        }

        @Override
        public ImmutableMap<K, V> getAllPresent(Iterable<?> iterable) {
            return null;
        }

        @Override
        public void put(K k, V v) {

        }

        @Override
        public void putAll(Map<? extends K, ? extends V> map) {

        }

        @Override
        public void invalidate(Object o) {

        }

        @Override
        public void invalidateAll(Iterable<?> iterable) {

        }

        @Override
        public void invalidateAll() {

        }

        @Override
        public long size() {
            return 0;
        }

        @Override
        public CacheStats stats() {
            return null;
        }

        @Override
        public ConcurrentMap<K, V> asMap() {
            return null;
        }

        @Override
        public void cleanUp() {

        }
    }

    static class LocalLoadingCache<K, V> extends LocalManualCache<K, V> implements LoadingCache<K, V> {

        LocalLoadingCache(CacheBuilder<? super K, ? super V> builder, CacheLoader<? super K, V> loader) {
            super(new LocalCache<K, V>(builder, checkNotNull(loader)));
        }

        @Override
        public V get(K k) throws ExecutionException {
            return null;
        }

        @Override
        public V getUnchecked(K k) {
            return null;
        }

        @Override
        public ImmutableMap<K, V> getAll(Iterable<? extends K> iterable) throws ExecutionException {
            return null;
        }

        @Override
        public V apply(K k) {
            return null;
        }

        @Override
        public void refresh(K k) {

        }
    }
}