package com.blocks.common.data.log.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.aspectj.lang.Signature;

import java.io.Serializable;

/**
 * 日志事件传输对象
 *
 * @author luyanan
 * @since 2022/7/3
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class LoggerEventDto implements Serializable {


    private static final long serialVersionUID = -3572786267372160478L;
    /**
     * 返回结果
     *
     * @since 2022/7/3
     */

    private Object result;


    /**
     * 开始时间
     *
     * @since 2022/7/3
     */

    private Long startTime;


    /**
     * 结束时间
     *
     * @since 2022/7/3
     */

    private Long endTime;


    /**
     * 异常
     *
     * @since 2022/7/3
     */

    private Throwable throwable;


    /**
     * 方法签名
     *
     * @since 2022/7/3
     */

    private Signature signature;


    /**
     * 参数
     *
     * @since 2022/7/3
     */

    private Object[] args;


}
