package com.Config;

import com.Resp.BaseRespones;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@RestControllerAdvice
@Slf4j
@ControllerAdvice
public class ExceptionConfig {
    @ExceptionHandler({SQLException.class})
    public BaseRespones<Boolean> IsConnect(){
        log.error("properties配置文件中连接失败,手动登录");
        return BaseRespones.failed("数据库连接失败:未设置数据库参数",false);
    }
    public BaseRespones<Boolean> DriverSuit(){
        log.error("未找到合适的DriverManager");
        return BaseRespones.failed("连接异常",false);
    }

    @ExceptionHandler(org.springframework.web.servlet.NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundError() {
        // 执行转发操作，将请求转发到 '/index'
        return "forward:/";
    }


}
