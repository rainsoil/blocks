package com.blocks.common.data.cache.caffeine;

import com.blocks.common.data.cache.CacheCondition;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.CaffeineSpec;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.cache.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Caffeine 缓存自动化配置
 *
 * @author luyanan
 * @since 2022/7/10
 **/
@EnableConfigurationProperties(CacheProperties.class)
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass({Caffeine.class, CaffeineCacheManager.class})
@ConditionalOnMissingBean(CacheManager.class)
@Conditional({CacheCondition.class})
public class CaffeineAutoCacheConfiguration {

    /**
     * cacheManagerCustomizers
     *
     * @param customizers customizers
     * @return org.springframework.boot.autoconfigure.cache.CacheManagerCustomizers
     * @since 2022/7/10
     */
    @Bean
    @ConditionalOnMissingBean
    public CacheManagerCustomizers cacheManagerCustomizers(ObjectProvider<CacheManagerCustomizer<?>> customizers) {
        return new CacheManagerCustomizers(customizers.orderedStream().collect(Collectors.toList()));
    }

    /**
     * cacheManager
     *
     * @param cacheProperties cacheProperties
     * @param customizers     customizers
     * @param caffeine        caffeine
     * @param caffeineSpec    caffeineSpec
     * @param cacheLoader     cacheLoader
     * @return org.springframework.cache.CacheManager
     * @since 2022/7/10
     */
    @Bean("cacheResolver")
    public CacheManager cacheManager(CacheProperties cacheProperties,
                                     CacheManagerCustomizers customizers,
                                     ObjectProvider<Caffeine<Object, Object>> caffeine,
                                     ObjectProvider<CaffeineSpec> caffeineSpec,
                                     ObjectProvider<CacheLoader<Object, Object>> cacheLoader) {
        CaffeineAutoCacheManager cacheManager = createCacheManager(cacheProperties, caffeine, caffeineSpec, cacheLoader);
        List<String> cacheNames = cacheProperties.getCacheNames();
        if (!CollectionUtils.isEmpty(cacheNames)) {
            cacheManager.setCacheNames(cacheNames);
        }
        return customizers.customize(cacheManager);
    }

    /**
     * 创建CacheManage
     *
     * @param cacheProperties cacheProperties
     * @param caffeine        caffeine
     * @param caffeineSpec    caffeineSpec
     * @param cacheLoader     cacheLoader
     * @return org.springframework.boot.autoconfigure.cache.caffeine.CaffeineAutoCacheManager
     * @since 2022/7/10
     */
    private static CaffeineAutoCacheManager createCacheManager(CacheProperties cacheProperties,
                                                               ObjectProvider<Caffeine<Object, Object>> caffeine, ObjectProvider<CaffeineSpec> caffeineSpec,
                                                               ObjectProvider<CacheLoader<Object, Object>> cacheLoader) {
        CaffeineAutoCacheManager cacheManager = new CaffeineAutoCacheManager();
        setCacheBuilder(cacheProperties, caffeineSpec.getIfAvailable(), caffeine.getIfAvailable(), cacheManager);
        cacheLoader.ifAvailable(cacheManager::setCacheLoader);
        return cacheManager;
    }

    /**
     * 设置缓存
     *
     * @param cacheProperties cacheProperties
     * @param caffeineSpec    caffeineSpec
     * @param caffeine        caffeine
     * @param cacheManager    cacheManager
     * @since 2022/7/10
     */
    private static void setCacheBuilder(CacheProperties cacheProperties,
                                        @Nullable CaffeineSpec caffeineSpec,
                                        @Nullable Caffeine<Object, Object> caffeine,
                                        CaffeineCacheManager cacheManager) {
        String specification = cacheProperties.getCaffeine().getSpec();
        if (StringUtils.hasText(specification)) {
            cacheManager.setCacheSpecification(specification);
        } else if (caffeineSpec != null) {
            cacheManager.setCaffeineSpec(caffeineSpec);
        } else if (caffeine != null) {
            cacheManager.setCaffeine(caffeine);
        }
    }

}