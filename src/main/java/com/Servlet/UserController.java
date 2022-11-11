package com.Servlet;

import com.Resp.BaseRespones;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@Slf4j
@CrossOrigin
public class UserController {
    @PostMapping("/login")
    public BaseRespones<Boolean> Login(@RequestBody(required = false) String username, @RequestBody(required = false) String password, HttpServletResponse response){
//        response.setHeader("Access-Control-Allow-Origin","*");
        if (password==null){
            if (username!=null)
            {
                log.info(username);
                JSONObject parse =  JSON.parseObject(username);
                log.info("未接收到Response完整数据，进行解析");
                username = (String) parse.get("username");
                password = (String) parse.get("password");
            }else {
                username="anasasdd";
                password = "abcasd";
            }
        }
        log.info("username:"+username);
        log.info("password:"+password);

        if (username.equals("root")&&password.equals("123456")){
            return BaseRespones.success("登录成功");
        }else {
            return BaseRespones.failed("登录失败");
        }
    }
    @PostMapping("/regesist")
    public BaseRespones<Boolean> regesist(HttpSession session,@RequestBody String mailcode
            ,@RequestBody(required = false) String username,@RequestBody(required = false)String password,@RequestBody(required = false) String email){
        log.info("sessionID:"+session.getId());
        if (username==null||password==null){
            if (mailcode!=null){
                log.info("未接收到完整数据,进行解析");
                JSONObject jsonObject = JSON.parseObject(mailcode);
                mailcode= (String) jsonObject.get("mailcode");
                username=(String) jsonObject.get("username");
                email=(String)jsonObject.get("email");
                password=(String) jsonObject.get("password");
            }else {
                mailcode="1111";
                username="root";
                password="12311";
            }
        }
        log.info("email:"+email);
        log.info("mailcode:"+mailcode);
        log.info("username:"+username);
        log.info("password:"+password);
        String code = (String) session.getAttribute(mailcode);
        if (code!=null){
            if (code.equals(email)) {
                return BaseRespones.success("注册成功", true);
            }else {
                return BaseRespones.failed("注册失败:验证码邮箱和注册邮箱不一致",false);
            }
        }
        return BaseRespones.failed("注册失败");
    }



}
