package com.blocks.common.data.easyexcel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字典值注解
 *
 * @author luyanan
 * @since 2022/7/14
 **/
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DictStrFormat {


    /**
     * 字典的map
     * <p>
     * 0=成功,1=失败
     * </p>
     *
     * @return java.lang.String
     * @since 2022/7/14
     */
    String value();


}
