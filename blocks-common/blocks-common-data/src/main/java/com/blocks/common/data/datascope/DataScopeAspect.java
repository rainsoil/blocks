package com.blocks.common.data.datascope;

import com.blocks.common.data.datascope.annotaion.DataScope;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * 数据权限过滤拦截器
 *
 * @author luyanan
 * @since 2022/7/10
 **/
@RequiredArgsConstructor
@Aspect
public class DataScopeAspect {

    private final DataScopeHandler dataScopeHandler;


    @Before("@annotation(controllerDataScope)")
    public void doBefore(JoinPoint point, DataScope controllerDataScope) throws Throwable {
        dataScopeHandler.handler(point, controllerDataScope);
    }
}
