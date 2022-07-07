package com.blocks.common.data.xss.core;

import com.blocks.common.data.spring.SpringContextHolder;
import com.blocks.common.data.xss.config.XssProperties;
import com.blocks.common.data.xss.utils.XssUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * jackson xss 处理
 * @author luyanan
 * @since 2022/7/4
 **/
@Slf4j
public class XssCleanDeserializer extends XssCleanDeserializerBase {

    @Override
    public String clean(String text) throws IOException {
        // 读取 xss 配置
        XssProperties properties = SpringContextHolder.getBean(XssProperties.class);
        if (properties == null) {
            return text;
        }
        // 读取 XssCleaner bean
        XssCleaner xssCleaner = SpringContextHolder.getBean(XssCleaner.class);
        if (xssCleaner == null) {
            return XssUtil.trim(text, properties.isTrimText());
        }
        String value = xssCleaner.clean(XssUtil.trim(text, properties.isTrimText()));
        log.debug("Json property value:{} cleaned up by mica-xss, current value is:{}.", text, value);
        return value;
    }

}