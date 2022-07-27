package com.blocks.old.mybatis;

import com.blocks.common.data.mybatis.monitor.ChangeMonitorInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 数据监控
 *
 * @author luyanan
 * @since 2022/7/27
 **/
@Configuration
public class MybatisMonitor {


    @Bean
    public DataChangeMonitor dataChangeMonitor() {
        return new DataChangeMonitor();
    }


    @Bean
    public ChangeMonitorInterceptor changeMonitorInterceptor(DataChangeMonitor dataChangeMonitor) {
        ChangeMonitorInterceptor interceptor = new ChangeMonitorInterceptor();
        interceptor.setMonitor(dataChangeMonitor);
        return interceptor;
    }
}
