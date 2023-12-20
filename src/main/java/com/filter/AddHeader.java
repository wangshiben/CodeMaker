package com.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class AddHeader implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
//        response.addHeader("Access-Control-Allow-Origin","*");
//        System.out.println();
        int status = response.getStatus();
        if (status==404){
            response.setStatus(HttpStatus.OK.value());
            request.getRequestDispatcher("/").forward(request,response);
            return false;
        }
        log.info("全局拦截器拦截路径:{},方法{}",request.getRequestURI(),request.getMethod());
        return true;
    }
}
