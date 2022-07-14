package com.blocks.common.data.easyexcel;

import lombok.Getter;

/**
 * Void 字典枚举实现
 *
 * @author luyanan
 * @since 2022/7/14
 **/
@Getter
public enum VoidDictEnums implements IDictEnums {

    ;


    VoidDictEnums(String label, String value) {
        this.label = label;
        this.value = value;
    }

    /**
     * 名称
     *
     * @since 2022/7/14
     */

    private String label;
    /**
     * 值
     *
     * @since 2022/7/14
     */

    private String value;
}
