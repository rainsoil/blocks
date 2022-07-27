package com.blocks.common.data.mybatis.monitor.parserdata;

import com.blocks.common.data.mybatis.monitor.ChangeData;
import com.blocks.common.data.mybatis.monitor.core.MybatisInvocation;

import java.util.List;

/**
 * 解析
 *
 * @author luyanan
 * @since 2022/7/27
 **/
public class ParseContext implements ParseData {
    @Override
    public List<ChangeData> parse(String commandName, MybatisInvocation mybatisInvocation) throws Throwable {
        ParseData parseData = ParseFactory.getInstance().creator(commandName);
        return parseData.parse(commandName, mybatisInvocation);
    }


}
