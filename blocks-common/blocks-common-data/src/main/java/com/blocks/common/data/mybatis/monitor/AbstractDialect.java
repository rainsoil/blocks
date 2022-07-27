package com.blocks.common.data.mybatis.monitor;

/**
 * 数据库方言
 *
 * @author luyanan
 * @since 2022/7/27
 **/
public abstract class AbstractDialect {


    /**
     * 获取查询的sql
     *
     * @param sql       sql
     * @param limitSize limit 条数
     * @return java.lang.String
     * @since 2022/7/27
     */
    public abstract String getLimitSql(String sql, Integer limitSize);
}
