package com.blocks.common.data.page;

import lombok.Data;

import java.util.List;

/**
 * 分页返回
 *
 * @author luyanan
 * @since 2022/7/17
 **/
@Data
public class PageInfo<T> {


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
     * 返回内容
     *
     * @since 2021/12/1
     */

    private List<T> records;


    /**
     * 统计
     *
     * @since 2021/12/1
     */

    private long total;

    /**
     * 页数
     *
     * @since 2021/12/1
     */

    private long pages;


}