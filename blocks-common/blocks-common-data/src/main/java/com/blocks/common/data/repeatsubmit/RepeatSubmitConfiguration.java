package com.blocks.common.data.repeatsubmit;

import com.blocks.common.data.repeatsubmit.interceptor.SameUrlDataInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RepeatSubmit 配置类
 *
 * @author luyanan
 * @since 2022/7/17
 **/
@Configuration
public class RepeatSubmitConfiguration {


    @Bean
    public SameUrlDataInterceptor sameUrlDataInterceptor() {
        return new SameUrlDataInterceptor();
    }


}
