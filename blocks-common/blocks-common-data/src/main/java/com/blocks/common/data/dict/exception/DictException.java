package com.blocks.common.data.dict.exception;

import com.blocks.common.data.exception.BaseException;

/**
 * 字典的异常类型
 *
 * @author luyanan
 * @since 2022/7/17
 **/
public class DictException extends BaseException {
    public DictException(String messageCode, Object... args) {
        super(messageCode, args);
    }

    public DictException(String messageCode, Throwable cause, Object... args) {
        super(messageCode, cause, args);
    }

    public DictException(Throwable cause) {
        super(cause);
    }

    public DictException(String messageCode, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Object... args) {
        super(messageCode, cause, enableSuppression, writableStackTrace, args);
    }
}
