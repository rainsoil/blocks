package com.blocks.common.data.log.config;

import com.blocks.common.data.log.core.LoggerAspect;
import com.blocks.common.data.log.core.WebPrintLoggerEventListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 日志模块自动化配置
 *
 * @author luyanan
 * @since 2022/7/3
 **/
@Slf4j
@EnableConfigurationProperties(LogProperties.class)
@Configuration
@ConditionalOnProperty(value = LogProperties.PREFIX + ".enable", havingValue = "true", matchIfMissing = false)
@Import(WebPrintLoggerEventListener.class)
public class LogAutoConfiguration {

    /**
     * 日志切面
     *
     * @return com.blocks.common.data.log.core.LoggerAspect
     * @since 2022/7/5
     */
    @Bean
    public LoggerAspect loggerAspect() {
        log.debug("开启日志功能");
        return new LoggerAspect();
    }

}
