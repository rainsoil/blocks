package com.blocks.common.data.page;

import lombok.Data;

/**
 * 分页请求参数
 *
 * @author luyanan
 * @since 2022/7/17
 **/
@Data
public class PageRequestParam<T> {


    /**
     * 当前页数
     *
     * @since 2021/12/1
     */

    private long current;


    /**
     * 每页条数
     *
     * @since 2021/12/1
     */

    private long size;


    /**
     * 参数
     *
     * @since 2021/12/1
     */

    private T param;
}
