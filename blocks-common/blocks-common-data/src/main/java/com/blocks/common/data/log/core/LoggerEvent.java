package com.blocks.common.data.log.core;

import org.springframework.context.ApplicationEvent;

/**
 * 日志事件
 *
 * @author luyanan
 * @since 2022/7/3
 **/
public class LoggerEvent extends ApplicationEvent {
    public LoggerEvent(Object source) {
        super(source);
    }
}
