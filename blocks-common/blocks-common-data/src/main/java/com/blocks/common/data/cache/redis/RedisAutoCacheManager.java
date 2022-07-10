package com.blocks.common.data.cache.redis;

import org.jsoup.internal.StringUtil;
import org.springframework.boot.convert.DurationStyle;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.lang.Nullable;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Map;

/**
 * redis cache 扩展cache name自动化配置
 *
 * @author luyanan
 * @since 2022/7/10
 **/
public class RedisAutoCacheManager extends RedisCacheManager {

    public RedisAutoCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration,
                                 Map<String, RedisCacheConfiguration> initialCacheConfigurations, boolean allowInFlightCacheCreation) {
        super(cacheWriter, defaultCacheConfiguration, initialCacheConfigurations, allowInFlightCacheCreation);
    }

    @Override
    protected RedisCache createRedisCache(String name, @Nullable RedisCacheConfiguration cacheConfig) {
        if (StringUtil.isBlank(name) || !name.contains("#")) {
            return super.createRedisCache(name, cacheConfig);
        }
        String[] cacheArray = name.split("#");
        if (cacheArray.length < 2) {
            return super.createRedisCache(name, cacheConfig);
        }
        String cacheName = cacheArray[0];
        if (cacheConfig != null) {
            // 转换时间，支持时间单位例如：300ms，第二个参数是默认单位
            Duration duration = DurationStyle.detectAndParse(cacheArray[1], ChronoUnit.SECONDS);
            cacheConfig = cacheConfig.entryTtl(duration);
        }
        return super.createRedisCache(cacheName, cacheConfig);
    }

}