package com.rich.config;

import com.rich.interceptor.loginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webInterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private loginCheckInterceptor logincheckinterceptor;//注入拦截器

    @Override
    //注册拦截器
    public void addInterceptors(InterceptorRegistry registry) {
        //addInterceptor是注册拦截器，addPathPatterns是拦截哪些路径，excludePathPatterns是排除哪些路径
       registry.addInterceptor(logincheckinterceptor).addPathPatterns("/**").excludePathPatterns("/login");
    }
}
