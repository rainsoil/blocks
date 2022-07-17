package com.blocks.common.data.easyexcel.annotation;

import com.blocks.common.data.easyexcel.VoidDictEnums;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字典枚举转换
 *
 * @author luyanan
 * @since 2022/7/16
 **/
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DictEnumsFormat {


    /**
     * 枚举的class类
     *
     * @return java.lang.Class<? extends com.blocks.common.data.easyexcel.IDictEnums>
     * @since 2022/7/14
     */
    Class<? extends Enum> enumsClass() default VoidDictEnums.class;


    /**
     * 字典的type
     *
     * @return java.lang.String
     * @since 2022/7/16
     */
    String type() default "";
}
