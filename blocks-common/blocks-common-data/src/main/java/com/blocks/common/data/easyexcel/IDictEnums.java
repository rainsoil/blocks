package com.blocks.common.data.easyexcel;

/**
 * 字典枚举接口
 *
 * @author luyanan
 * @since 2022/7/14
 **/
public interface IDictEnums {


    /**
     * 获取字典的值
     *
     * @return java.lang.String
     * @since 2022/7/14
     */
    String getValue();

    /**
     * 获取字典的名称
     *
     * @return java.lang.String
     * @since 2022/7/14
     */
    String getLabel();


    /**
     * 字典的type
     *
     * @return java.lang.Enum<? extends com.blocks.common.data.easyexcel.IDictEnums.IDictTypeEnums>
     * @since 2022/7/16
     */
    Enum<? extends IDictTypeEnums> getType();


    /**
     * 字典type的接口类
     *
     * @author luyanan
     * @since 2022/7/16
     */
    static interface IDictTypeEnums {


    }

}
