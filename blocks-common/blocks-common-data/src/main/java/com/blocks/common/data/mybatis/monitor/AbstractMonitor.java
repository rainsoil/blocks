package com.blocks.common.data.mybatis.monitor;


import java.util.List;

/**
 * 监控抽象类
 *
 * @author luyanan
 * @since 2022/7/27
 **/
public abstract class AbstractMonitor {


    /**
     * 数据监听
     *
     * @param changeDataList 修改的数据
     * @since 2022/7/27
     */
    public abstract void listen(List<ChangeData> changeDataList);

}
