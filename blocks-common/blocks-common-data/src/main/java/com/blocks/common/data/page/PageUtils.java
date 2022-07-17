package com.blocks.common.data.page;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.experimental.UtilityClass;

/**
 * 分页工具类
 *
 * @author luyanan
 * @since 2022/7/17
 **/
@UtilityClass
public class PageUtils {


    /**
     * 转换为IPage
     *
     * @param requestParam 分页参数
     * @return com.baomidou.mybatisplus.core.metadata.IPage
     * @since 2021/12/1
     */
    public Page toPage(PageRequestParam requestParam) {
        Page page = new Page<>(requestParam.getCurrent(), requestParam.getSize());
        return page;
    }


    /**
     * 转换为PageInfo对象
     *
     * @param page
     * @return PageInfo<T>
     * @since 2021/12/2
     */
    public <T> PageInfo<T> toPageInfo(IPage<T> page) {
        if (null == page) {
            return new PageInfo<>();
        }
        PageInfo<T> pageInfo = new PageInfo();
        pageInfo.setPages(page.getPages());
        pageInfo.setRecords(page.getRecords());
        pageInfo.setCurrent(page.getCurrent());
        pageInfo.setSize(page.getSize());
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }
}
