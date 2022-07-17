package com.blocks.common.data.dict.exception;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 字典vo类
 *
 * @author luyanan
 * @since 2022/7/17
 **/
@Data
public class DictVo {

    /**
     * 字典的type
     *
     * @since 2022/7/17
     */
    @ApiModelProperty(value = "字典的tyoe")
    private String type;


    /**
     * 字典的值
     *
     * @since 2022/7/17
     */
    @ApiModelProperty(value = "字典的值")
    private String value;


    /**
     * 字典的名称
     *
     * @since 2022/7/17
     */
    @ApiModelProperty(value = "字典的名称")
    private String label;
}
