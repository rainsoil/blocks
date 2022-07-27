package com.blocks.common.data.mybatis.monitor;

import lombok.Data;

/**
 * 数据修改的Vo类
 *
 * @author luyanan
 * @since 2022/7/27
 **/
@Data
public class ChangeData {


    /**
     * 表名
     *
     * @since 2022/7/27
     */

    private String tableName;

    /**
     * 数据库的名称
     *
     * @since 2022/7/27
     */

    private String schemaName;

    /**
     * 事件(insert/update/delete)
     *
     * @since 2022/7/27
     */

    private String eventType;

}
