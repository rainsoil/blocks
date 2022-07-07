package com.blocks.common.data.log.config;

import com.blocks.common.core.constant.Constants;
import lombok.Data;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 日志配置文件
 *
 * @author luyanan
 * @since 2022/7/3
 **/
@Data
@ConfigurationProperties(prefix = LogProperties.PREFIX)
public class LogProperties {
    public static final String PREFIX = Constants.PROPERTIES_PREFIX + "log";

    /**
     * 是否开启
     *
     * @since 2022/7/3
     */

    private Boolean enable;


    /**
     * 是否 打印
     *
     * @since 2022/7/3
     */

    private Boolean print;
}
