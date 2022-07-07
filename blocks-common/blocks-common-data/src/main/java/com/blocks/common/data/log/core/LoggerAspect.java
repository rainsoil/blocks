package com.blocks.common.data.log.core;

import com.blocks.common.data.log.annotation.IgnoreLogger;
import com.blocks.common.data.spring.SpringContextHolder;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * 日志打印切面拦截
 *
 * @author luyanan
 * @since 2022/7/3
 **/
@AllArgsConstructor
@Aspect
@Slf4j
public class LoggerAspect {

    /**
     * 环绕切面
     *
     * @param proceedingJoinPoint proceedingJoinPoint
     * @param apiOperation        注解
     * @return java.lang.Object
     * @throws Throwable 异常
     * @since 2022/7/3
     */
    @Around("@annotation(apiOperation)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, ApiOperation apiOperation) throws Throwable {

        Object[] args =
                proceedingJoinPoint.getArgs();
        // 开始时间
        long startTime = System.currentTimeMillis();
        // 结果
        Object result = null;
        // 异常
        Throwable e = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            e = throwable;
            throwable.printStackTrace();
            throw throwable;
        } finally {
            MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
            Method method =
                    methodSignature.getMethod();
            IgnoreLogger ignoreLogger = method.getAnnotation(IgnoreLogger.class);
            if (isIgnore(ignoreLogger, IgnoreLogger.Type.PARAMS)) {
                args = null;
            }
            if (isIgnore(ignoreLogger, IgnoreLogger.Type.RESULT)) {
                result = null;
            }
            LoggerEventDto loggerEventDto = new LoggerEventDto()
                    .setEndTime(System.currentTimeMillis())
                    .setStartTime(startTime)
                    .setThrowable(e)
                    .setSignature(proceedingJoinPoint.getSignature())
                    .setResult(result)
                    .setArgs(args);
            // 发送事件
            SpringContextHolder.publishEvent(new LoggerEvent(loggerEventDto));

        }
        return result;
    }


    /**
     * 是否忽略
     *
     * @param ignoreLogger 忽略的注解
     * @param type         忽略的注解的类型
     * @return boolean
     * @since 2022/7/3
     */
    private boolean isIgnore(IgnoreLogger ignoreLogger, IgnoreLogger.Type type) {
        return null != ignoreLogger && (ignoreLogger.type().equals(type) ||
                ignoreLogger.type().equals(IgnoreLogger.Type.ALL));
    }

}
