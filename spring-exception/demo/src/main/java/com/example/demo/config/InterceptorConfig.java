package com.example.demo.config;

import com.example.demo.interceptor.TestIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author wh1507006
 * @date 2019/4/22 10:51
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TestIntercepter()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
