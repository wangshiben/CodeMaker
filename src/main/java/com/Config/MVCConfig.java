package com.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class MVCConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //配置允许跨域的路径
        registry.addMapping("/**")
                //配置允许访问的跨域资源的请求域名
                .allowCredentials(true)
                .allowedOriginPatterns("*")
                //配置允许访问该跨域资源服务器的请求方法
                .allowedMethods("*")
                //配置允许请求 头部head的访问
                .allowedHeaders("*");
    }
}
