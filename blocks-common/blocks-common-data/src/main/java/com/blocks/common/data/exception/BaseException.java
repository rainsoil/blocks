package com.blocks.common.data.exception;

import cn.hutool.core.lang.Opt;
import com.blocks.common.data.message.MessageUtils;
import org.springframework.context.MessageSource;

/**
 * 基础异常类
 *
 * @author luyanan
 * @since 2022/7/17
 **/
public class BaseException extends RuntimeException {


    public BaseException(String messageCode, Object... args) {

        super(getMessage(messageCode, args));
    }

    public BaseException(String messageCode, Throwable cause, Object... args) {
        super(getMessage(messageCode, args), cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String messageCode, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Object... args) {
        super(getMessage(messageCode, args), cause, enableSuppression, writableStackTrace);
    }

    /**
     * 获取消息提示, 先从MessageSource 中获取,如果不存在,则返回当前消息
     *
     * @param messageCode 消息编码
     * @param args        参数
     * @return java.lang.String
     * @since 2022/7/17
     */
    private static String getMessage(String messageCode, Object... args) {
        return Opt.ofNullable(MessageUtils.message(messageCode, args)).orElseGet(() -> messageCode);
    }

}
