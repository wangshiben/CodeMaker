package com.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {//添加跨域请求
        registry.addInterceptor(addHeader()).addPathPatterns("/**");
    }
    @Bean//将拦截器注入
    public AddHeader addHeader(){
        return new AddHeader();
    }
    @Bean
    public RestTemplate getTemple() {
        return new RestTemplate();
    }


}
