package com.blocks.common.data.mybatis.monitor.core;

import com.blocks.common.data.mybatis.monitor.AbstractDialect;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * MybatisInvocation
 *
 * @author luyanan
 * @since 2022/7/27
 **/
@AllArgsConstructor
@Data
public class MybatisInvocation {

    /**
     * args
     *
     * @since 2022/7/27
     */

    private Object[] args;
    /**
     * mappedStatement
     *
     * @since 2022/7/27
     */

    private MappedStatement mappedStatement;
    /**
     * parameter
     *
     * @since 2022/7/27
     */

    private Object parameter;
    /**
     * 执行器
     *
     * @since 2022/7/27
     */

    private Executor executor;
    /**
     * 数据库方言
     *
     * @since 2022/7/27
     */

    private AbstractDialect dbDialect;
    /**
     * 最大查询条数
     *
     * @since 2022/7/27
     */

    private int maxRowMonitor;
    /**
     * 白名单
     *
     * @since 2022/7/27
     */

    private CopyOnWriteArrayList<String> whiteCopyList;

}
