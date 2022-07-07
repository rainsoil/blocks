package com.blocks.common.data.log;

import cn.hutool.json.JSONUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * 日志测试
 *
 * @author luyanan
 * @since 2022/7/4
 **/
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class LogTest {


    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    @SneakyThrows
    @Test
    public void ignoreParam() {

        Map<String, String> param = new HashMap<>();
        param.put("name", "aaa");
        param.put("age", "2");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/log/test/ignoreParam").content(JSONUtil.toJsonStr(param)).contentType(MediaType.APPLICATION_JSON)).
                andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
        log.warn("返回结果:{}", mvcResult.getResponse().getContentAsString());
    }

    @SneakyThrows
    @Test
    public void ignoreResult() {

        Map<String, String> param = new HashMap<>();
        param.put("name", "aaa");
        param.put("age", "2");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/log/test/ignoreResult").content(JSONUtil.toJsonStr(param)).contentType(MediaType.APPLICATION_JSON)).
                andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
        log.warn("返回结果:{}", mvcResult.getResponse().getContentAsString());
    }


    @SneakyThrows
    @Test
    public void ignoreAll() {

        Map<String, String> param = new HashMap<>();
        param.put("name", "aaa");
        param.put("age", "2");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/log/test/ignoreAll").content(JSONUtil.toJsonStr(param)).contentType(MediaType.APPLICATION_JSON)).
                andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
        log.warn("返回结果:{}", mvcResult.getResponse().getContentAsString());
    }
}
