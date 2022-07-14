package com.blocks.common.data.easyexcel.annotation;

import com.blocks.common.data.easyexcel.IDictEnums;
import com.blocks.common.data.easyexcel.VoidDictEnums;

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
public @interface DictFormat {


    /**
     * 枚举的class类
     *
     * @return java.lang.Class<? extends com.blocks.common.data.easyexcel.IDictEnums>
     * @since 2022/7/14
     */
    Class<? extends IDictEnums> enumsClass() default VoidDictEnums.class;


    /**
     * 字典的map
     * <p>
     * 0=成功,1=失败
     * </p>
     *
     * @return java.lang.String
     * @since 2022/7/14
     */
    String dictMap() default "";
}
