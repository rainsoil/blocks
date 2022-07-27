package com.blocks.common.data.mybatis.monitor.parserdata;

import com.blocks.common.data.mybatis.monitor.ChangeData;
import com.blocks.common.data.mybatis.monitor.core.MybatisInvocation;

import java.util.List;

/**
 * 数据解析
 *
 * @author luyanan
 * @since 2022/7/27
 **/
public interface ParseData {
    /**
     * 解析
     *
     * @param commandName       命令名称
     * @param mybatisInvocation 参数
     * @return java.util.List<com.blocks.common.data.mybatis.monitor.ChangeData>
     * @since 2022/7/27
     */
    List<ChangeData> parse(String commandName, MybatisInvocation mybatisInvocation) throws Throwable;
}
