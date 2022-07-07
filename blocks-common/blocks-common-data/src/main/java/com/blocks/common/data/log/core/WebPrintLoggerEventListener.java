package com.blocks.common.data.log.core;

import com.blocks.common.data.log.config.LogProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * web打印 事件监听
 *
 * @author luyanan
 * @since 2022/7/3
 **/
@RequiredArgsConstructor
@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(value = LogProperties.PREFIX + ".print", havingValue = "true", matchIfMissing = false)
public class WebPrintLoggerEventListener extends AbstractLoggerParserHandler implements
        ApplicationListener<LoggerEvent> {

    private final ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public void onApplicationEvent(LoggerEvent loggerEvent) {


        if (null == loggerEvent || !(loggerEvent.getSource() instanceof LoggerEventDto)) {
            return;
        }
        LoggerEventDto loggerEventDto = (LoggerEventDto) loggerEvent.getSource();

        Map<String, Object> map = parser(loggerEventDto);

        StringBuffer sb = new StringBuffer();
        sb.append("\n[-----------------------------------").append("\n")
                .append("methodName:").append(map.get("methodName")).append("\n")
                .append("sourceIp:").append(map.get("sourceIp")).append("\n")
                .append("desp:").append(map.get("desp")).append("\n")
                .append("uri:").append(map.get("uri")).append("\n");
        if (null != map.get("requestParams")) {


            sb.append("requestParams:").append(objectMapper.writeValueAsString(map.get("requestParams"))).append("\n");
        }
        // 耗时
        if (null != map.get("startTime") && null != map.get("endTime")) {
            sb.append("timeConsuming:").append(((Long) map.get("endTime")) - ((Long) map.get("startTime"))).append("\n");
        }
        if (null != map.get("result")) {
            sb.append("result:").append(objectMapper.writeValueAsString(map.get("result"))).append("\n");
        }
        if (null != map.get("errorMsg")) {
            sb.append("errorMsg:").append(objectMapper.writeValueAsString(map.get("errorMsg"))).append("\n");
        }
        sb.append("------------------------------]");
        log.debug(sb.toString());
    }
}
