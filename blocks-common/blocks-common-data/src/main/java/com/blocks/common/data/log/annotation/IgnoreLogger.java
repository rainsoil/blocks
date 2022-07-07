package com.blocks.common.data.log.annotation;

import java.lang.annotation.*;

/**
 * 忽略日志注解
 *
 * @author luyanan
 * @since 2022/7/3
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreLogger {
    /**
     * 忽略类型
     *
     * @since 2022/7/3
     */

    Type type() default Type.ALL;

    enum Type {
        /**
         * 参数
         *
         * @since 2022/7/3
         */

        PARAMS,
        /**
         * 结果
         *
         * @author luyanan
         * @since 2022/7/3
         */
        RESULT,
        /**
         * 全部
         *
         * @since 2022/7/3
         */

        ALL;
    }
}
