package com.blocks.common.data.cache;

import com.blocks.common.data.cache.caffeine.CaffeineAutoCacheConfiguration;
import com.blocks.common.data.cache.redis.RedisAutoCacheManager;
import com.blocks.common.data.cache.redis.RedisCacheAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.*;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

/**
 * Mappings between {@link CacheType} and {@code @Configuration}.
 *
 * @author luyanan
 * @since 2022/7/10
 **/
public class CacheConfigurations {
    private static final Map<CacheType, String> MAPPINGS;

    static {
        Map<CacheType, String> mappings = new EnumMap<>(CacheType.class);

        mappings.put(CacheType.REDIS, RedisCacheAutoConfiguration.class.getName());
        mappings.put(CacheType.CAFFEINE, CaffeineAutoCacheConfiguration.class.getName());
        MAPPINGS = Collections.unmodifiableMap(mappings);
    }

    private CacheConfigurations() {
    }

    static String getConfigurationClass(CacheType cacheType) {
        String configurationClassName = MAPPINGS.get(cacheType);
        Assert.state(configurationClassName != null, () -> "Unknown cache type " + cacheType);
        return configurationClassName;
    }

    static CacheType getType(String configurationClassName) {
        for (Map.Entry<CacheType, String> entry : MAPPINGS.entrySet()) {
            if (entry.getValue().equals(configurationClassName)) {
                return entry.getKey();
            }
        }
        throw new IllegalStateException("Unknown configuration class " + configurationClassName);
    }

}

