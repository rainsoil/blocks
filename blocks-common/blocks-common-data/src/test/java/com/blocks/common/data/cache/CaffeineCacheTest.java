package com.blocks.common.data.cache;

import cn.hutool.core.lang.Assert;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * caffeine 缓存测试类
 *
 * @author luyanan
 * @since 2022/7/10
 **/
@Slf4j
@EnableCaching
@SpringBootTest
@RunWith(SpringRunner.class)
public class CaffeineCacheTest {


    @Autowired
    private CacheManager cacheManager;

    @SneakyThrows
    @Test
    public void cache() {

        Assert.notNull(cacheManager);
        log.info(cacheManager.getClass().getName());
        Cache cache = cacheManager.getCache("cache#2");
        String key = "aaa";
        cache.put(key, "aaaa");
        Assert.notNull(cache.get(key));

        Thread.sleep(3 * 1000);
        Assert.isNull(cache.get(key));
    }

}
