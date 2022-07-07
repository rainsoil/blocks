package com.blocks.common.data.log.core;

import cn.hutool.extra.servlet.ServletUtil;
import com.blocks.common.data.log.annotation.IgnoreLogger;
import com.blocks.common.data.spring.SpringContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 日志解析器抽象类
 *
 * @author luyanan
 * @since 2022/7/3
 **/
public class AbstractLoggerParserHandler {

    /**
     * 解析
     *
     * @param loggerEventDto 日志事件响应
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @since 2022/7/3
     */
    public Map<String, Object> parser(LoggerEventDto loggerEventDto) {

        int status = 200;

        Map<String, Object> data = new HashMap<>();
        // 开始事件
        data.put("startTime", loggerEventDto.getStartTime());
        // 结束事件
        data.put("endTime", System.currentTimeMillis());
        MethodSignature methodSignature = (MethodSignature) loggerEventDto.getSignature();
        Method method = methodSignature.getMethod();
        IgnoreLogger ignoreLogger = method.getAnnotation(IgnoreLogger.class);

        // 不添加注解 或者 注解的类型不为ALL 或者不等于parade，
        addParam(loggerEventDto, data, methodSignature, ignoreLogger);

        // 返回结果
        if (null == ignoreLogger ||
                !ignoreLogger.type().equals(IgnoreLogger.Type.ALL)
                || ignoreLogger.type().equals(IgnoreLogger.Type.RESULT)) {
            // 返回结果
            data.put("result", loggerEventDto.getResult());
        }
        // 方法信息
        data.put("methodName", method.getDeclaringClass().getName() + "." + method.getName());
        if (null != loggerEventDto.getThrowable()) {
            status = 500;
            // 异常信息
            data.put("errorMsg", loggerEventDto.getThrowable().getLocalizedMessage());
        }
        // 状态
        data.put("status", status);
        // 获取介绍
        data.put("desp", getDesp(method));

        HttpServletRequest request =
                SpringContextHolder.getRequest();

        if (null != request) {
            // 来源ip
            data.put("sourceIp", ServletUtil.getClientIPByHeader(request));
            // 请求url
            data.put("uri", request.getRequestURI());

        }
        return data;
    }

    /**
     * 设置参数
     *
     * @param loggerEventDto  日志信息Vo类
     * @param data            数据
     * @param methodSignature 方法签名
     * @param ignoreLogger    忽略日志注解
     * @since 2022/7/5
     */
    private void addParam(LoggerEventDto loggerEventDto, Map<String, Object> data, MethodSignature methodSignature, IgnoreLogger ignoreLogger) {
        if (null != ignoreLogger) {
            if (ignoreLogger.type().equals(IgnoreLogger.Type.ALL) || ignoreLogger.type().equals(IgnoreLogger.Type.PARAMS)) {
                return;
            }

        }
        // 请求参数
        Map<String, Object> requestParams = getRequestParams(methodSignature, loggerEventDto.getArgs());
        data.put("requestParams", requestParams);
    }

    /**
     * 方法介绍分割线
     *
     * @since 2021/1/24
     */
    protected static final String DESP_DIVIDING_LINE = "-";

    /**
     * 方法介绍
     *
     * @param method 方法
     * @return java.lang  .String
     * @since 2021/8/22
     */
    protected String getDesp(Method method) {
        StringBuilder desp = new StringBuilder();
        if (method.getDeclaringClass().isAnnotationPresent(Api.class)) {
            String apiValue = method.getDeclaringClass().getAnnotation(Api.class).value();
            if (StringUtils.isEmpty(apiValue)) {
                //noinspection AliDeprecation
                apiValue = method.getDeclaringClass().getAnnotation(Api.class).value();
            }
            desp.append(apiValue).append(DESP_DIVIDING_LINE);
        }
        if (method.isAnnotationPresent(ApiOperation.class)) {
            desp.append(method.getAnnotation(ApiOperation.class).value());
        }
        if (desp.toString().endsWith(DESP_DIVIDING_LINE)) {
            desp.deleteCharAt(desp.length() - 1);
        }
        return desp.toString();
    }

    /**
     * 获取请求参数
     *
     * @param methodSignature 方法签名
     * @param args 参数
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @since 2022/7/5
     */
    protected Map<String, Object> getRequestParams(MethodSignature methodSignature, Object[] args) {
        Map<String, Object> requestParams = new HashMap<>(16);

        // 参数名
        String[] paramNames = methodSignature.getParameterNames();
        if (null == paramNames || paramNames.length == 0) {
            return new HashMap<>(16);
        }

        for (int i = 0; i < paramNames.length; i++) {
            Object value = args[i];
            if (null == value) {
                continue;
            }

            // 如果是文件对象
            if (value instanceof MultipartFile) {
                MultipartFile file = (MultipartFile) value;
                // 获取文件名
                value = file.getOriginalFilename();
            } else if (value instanceof ServletResponse || value instanceof ServletRequest) {

                continue;
            } else if (value instanceof Model || value.getClass().getName().startsWith("org.springframework")) {
                continue;
            }

            requestParams.put(paramNames[i], value);
        }
        return requestParams;
    }
}
