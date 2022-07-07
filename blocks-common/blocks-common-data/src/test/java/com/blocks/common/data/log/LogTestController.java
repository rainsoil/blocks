package com.blocks.common.data.log;

import com.blocks.common.data.log.annotation.IgnoreLogger;
import io.swagger.annotations.ApiOperation;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

/**
 * 日志测试
 *
 * @author luyanan
 * @since 2022/7/4
 **/
@RestController
@RequestMapping("log/test")
public class LogTestController {


    /**
     * 忽略参数
     *
     * @param map 参数
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @since 2022/7/4
     */
    @ApiOperation(value = "忽略参数")
    @IgnoreLogger(type = IgnoreLogger.Type.PARAMS)
    @PostMapping("ignoreParam")
    public Map<String, Object> ignoreParam(@RequestBody Map<String, Object> map) {
        return map;
    }

    /**
     * 忽略结果
     *
     * @param map 参数
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @since 2022/7/4
     */
    @ApiOperation(value = "忽略结果")
    @IgnoreLogger(type = IgnoreLogger.Type.RESULT)
    @PostMapping("ignoreResult")
    public Map<String, Object> ignoreResult(@RequestBody Map<String, Object> map) {
        return map;
    }


    /**
     * 忽略全部
     *
     * @param map 参数
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @since 2022/7/4
     */
    @ApiOperation(value = "忽略全部")
    @IgnoreLogger(type = IgnoreLogger.Type.ALL)
    @PostMapping("ignoreAll")
    public Map<String, Object> ignoreAll(@RequestBody Map<String, Object> map) {
        return map;
    }
}
