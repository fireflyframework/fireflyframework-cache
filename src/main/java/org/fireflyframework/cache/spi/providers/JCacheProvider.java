package org.fireflyframework.cache.spi.providers;

import org.fireflyframework.cache.core.CacheAdapter;
import org.fireflyframework.cache.core.CacheType;
import org.fireflyframework.cache.factory.JCacheCacheHelper;
import org.fireflyframework.cache.spi.CacheProviderFactory;

import java.time.Duration;

public class JCacheProvider implements CacheProviderFactory {
    @Override public CacheType getType() { return CacheType.JCACHE; }
    @Override public int priority() { return 30; }

    @Override
    public boolean isAvailable(ProviderContext ctx) {
        try {
            try { Class.forName("javax.cache.CacheManager"); }
            catch (ClassNotFoundException ex) { Class.forName("jakarta.cache.CacheManager"); }
            return ctx.jcacheManager != null;
        } catch (ClassNotFoundException e) { return false; }
    }

    @Override
    public CacheAdapter create(String cacheName, String keyPrefix, Duration defaultTtl, ProviderContext ctx) {
        try {
            Class<?> helperClass = Class.forName("org.fireflyframework.cache.factory.JCacheCacheHelper");
            Class<?> cm;
            try { cm = Class.forName("javax.cache.CacheManager"); }
            catch (ClassNotFoundException ex) { cm = Class.forName("jakarta.cache.CacheManager"); }
            var m = helperClass.getMethod("createJCacheAdapter",
                    String.class, String.class, Duration.class, cm);
            return (CacheAdapter) m.invoke(null, cacheName, keyPrefix, defaultTtl, ctx.jcacheManager);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to create JCache cache via SPI", e);
        }
    }
}
