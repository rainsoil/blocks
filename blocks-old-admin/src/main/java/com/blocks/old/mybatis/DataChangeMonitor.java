package com.blocks.old.mybatis;

import com.blocks.common.data.mybatis.monitor.AbstractMonitor;
import com.blocks.common.data.mybatis.monitor.ChangeData;

import java.util.List;

/**
 * 数据监控
 *
 * @author luyanan
 * @since 2022/7/27
 **/
public class DataChangeMonitor extends AbstractMonitor {
    @Override
    public void listen(List<ChangeData> changeDataList) {
        System.out.println(changeDataList);
    }
}
