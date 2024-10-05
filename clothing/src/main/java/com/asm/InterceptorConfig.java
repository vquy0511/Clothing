package com.asm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.asm.interceptor.AuthInterceptor;
import com.asm.interceptor.GlobalInterceptor;
import com.asm.interceptor.OrderInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{
    
    @Autowired
    GlobalInterceptor globalInterceptor;

    @Autowired
    AuthInterceptor authInterceptor;

    @Autowired
    OrderInterceptor orderInter;
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(globalInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns("/rest/**");

        registry.addInterceptor(authInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns("/rest/**");

        registry.addInterceptor(orderInter)
        .addPathPatterns("/order/detail/**");


    }

}
