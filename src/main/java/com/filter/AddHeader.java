package com.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class AddHeader implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String header = request.getHeader("Access-Control-Allow-Origin");
//        if (header==null){
//            response.setHeader("Access-Control-Allow-Method","GET,POST,OPTIONS,PUT,DELETE");
//        response.setHeader("Access-Control-Allow-Origin","*");
//            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
//        }
        String requestURI = request.getRequestURI();
        log.info("全局拦截器拦截路径:"+requestURI);
        return true;
    }


}
