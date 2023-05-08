package com.Config;

import com.Resp.BaseRespones;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
@Slf4j
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

}
