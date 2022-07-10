package com.blocks.common.data.datascope;

import com.blocks.common.data.datascope.annotaion.DataScope;
import lombok.Data;
import org.aspectj.lang.JoinPoint;

/**
 * 数据权限过滤处理器
 *
 * @author luyanan
 * @since 2022/7/10
 **/

public interface DataScopeHandler {


    /**
     * 数据权限过滤
     *
     * @param point               point
     * @param controllerDataScope 数据权限注解
     * @return void
     * @since 2022/7/10
     */
    void handler(JoinPoint point, DataScope controllerDataScope);
}
