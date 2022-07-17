package com.blocks.common.data.page;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 响应信息主体
 *
 * @author luyanan
 * @since 2022/7/17
 **/
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "响应信息主体")
public class AjaxResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 成功的标识
     *
     * @since 2022/7/17
     */

    private static final int SUCCESS_CODE = 0;

    /**
     * 警告的状态码
     *
     * @since 2022/7/17
     */

    private static final int WARN_CODE = 301;


    /**
     * 默认的错误的状态码
     *
     * @since 2022/7/17
     */

    private static final int DEFAULT_ERROR_CODE = 500;
    /**
     * 返回标记,0:成功
     *
     * @since 2022/7/17
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "返回标记")
    private int code;


    /**
     * 错误信息
     *
     * @since 2022/7/17
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "错误信息")
    private String msg;


    /**
     * 返回数据
     *
     * @since 2022/7/17
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "返回数据")
    private T data;

    /**
     * 时间戳
     *
     * @since 2022/7/17
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "时间戳")
    private Long timestamp;

    /**
     * 链路id
     *
     * @since 2022/7/17
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "链路id")/**/
    private String traceId;


    /**
     * 初始化R
     *
     * @param code 错误码
     * @param data 数据
     * @param msg  错误信息
     * @return com.blocks.common.data.page.R<T>
     * @since 2022/7/17
     */
    public static <T> AjaxResult<T> restResult(int code, T data, String msg) {
        AjaxResult<T> apiResult = new AjaxResult<>();
        apiResult.setCode(code);
        apiResult.setData(data);

        apiResult.setMsg(msg);
        if (code != SUCCESS_CODE) {
            apiResult.setTimestamp(System.currentTimeMillis());
        } else {
            if (StrUtil.isBlank(msg)) {
                apiResult.setMsg("操作成功");
            }
        }
        return apiResult;
    }


    /**
     * 成功
     *
     * @return com.blocks.common.data.page.AjaxResult
     * @since 2022/7/17
     */
    public static AjaxResult success() {
        return restResult(SUCCESS_CODE, null, null);
    }

    /**
     * 成功
     *
     * @param data
     * @return com.blocks.common.data.page.AjaxResult<T>
     * @since 2022/7/17
     */
    public static <T> AjaxResult<T> success(T data) {
        return restResult(SUCCESS_CODE, data, null);
    }


    /**
     * 警告
     *
     * @param data 返回数据
     * @param msg  消息内容
     * @return com.blocks.common.data.page.AjaxResult<T>
     * @since 2022/7/17
     */
    public static <T> AjaxResult<T> warn(T data, String msg) {
        return restResult(WARN_CODE, data, msg);
    }


    /**
     * 警告
     *
     * @param msg 消息
     * @return com.blocks.common.data.page.AjaxResult<T>
     * @since 2022/7/17
     */
    public static <T> AjaxResult<T> warn(String msg) {
        return warn(null, msg);
    }


    /**
     * 错误
     *
     * @return com.blocks.common.data.page.AjaxResult
     * @since 2022/7/17
     */
    public static AjaxResult error() {
        return restResult(DEFAULT_ERROR_CODE, null, null);
    }


    /**
     * 错误
     *
     * @param code 状态码
     * @return com.blocks.common.data.page.AjaxResult
     * @since 2022/7/17
     */
    public static AjaxResult error(int code) {
        return restResult(code, null, null);
    }


    /**
     * 错误返回
     *
     * @param code 状态码
     * @param msg  错误消息
     * @return com.blocks.common.data.page.AjaxResult
     * @since 2022/7/17
     */
    public static AjaxResult error(int code, String msg) {
        return restResult(code, null, msg);
    }

    /**
     * 错误返回
     *
     * @param msg 错误消息
     * @return com.blocks.common.data.page.AjaxResult
     * @since 2022/7/17
     */
    public static AjaxResult error(String msg) {
        return restResult(DEFAULT_ERROR_CODE, null, msg);
    }
}

