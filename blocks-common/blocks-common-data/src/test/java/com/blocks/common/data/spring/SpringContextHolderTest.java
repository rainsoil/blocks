package com.blocks.common.data.spring;

import cn.hutool.core.lang.Assert;
import com.blocks.common.data.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringContextHolderTest {

    @Test
    public void getBean() {
        TestService testService = SpringContextHolder.getBean(TestService.class);
        Assert.notNull(testService);
    }
}