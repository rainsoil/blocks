package com.blocks.common.data.mybatisplus;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 扩展通用 Mapper
 *
 * @author luyanan
 * @since 2022/7/13
 **/
public interface BlocksBaseMapper<T> extends BaseMapper<T> {


    /**
     * 批量插入 仅适用于 mysql
     *
     * @param entityList 实体列表
     * @return 影响行数
     */
    Integer insertBatchSomeColumn(List<T> entityList);
}
