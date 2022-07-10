package com.blocks.common.data.datascope;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 数据权限过滤
 *
 * @author luyanan
 * @since 2022/7/10
 **/
@Configuration
public class DataScopeAutoConfiguration {


    /**
     * 数据权限过滤拦截器
     *
     * @return com.blocks.common.data.datascope.DataScopeAspect
     * @since 2022/7/10
     */
    @Bean
    public DataScopeAspect dataScopeAspect(DataScopeHandler dataScopeHandler) {
        return new DataScopeAspect(dataScopeHandler);
    }
}
