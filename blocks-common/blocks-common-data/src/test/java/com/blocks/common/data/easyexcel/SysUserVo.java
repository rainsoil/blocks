package com.blocks.common.data.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.blocks.common.data.easyexcel.annotation.DictStrFormat;
import com.blocks.common.data.easyexcel.conver.DictStrFormatConverter;
import lombok.Data;
import lombok.ToString;

/**
 * 用户vo
 *
 * @author luyanan
 * @since 2022/7/16
 **/

@Data
public class SysUserVo {

    /**
     * 姓名
     *
     * @since 2022/7/17
     */

    private String name;

    /**
     * 性别
     *
     * @since 2022/7/17
     */

    @DictStrFormat(value = "0=男,1=女")
    @ExcelProperty(value = "性别", converter = DictStrFormatConverter.class)
    private String sex;
}
