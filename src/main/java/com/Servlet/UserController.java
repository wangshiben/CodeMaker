package com.Servlet;

import com.Resp.BaseRespones;

import com.Util.SessionMapUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
@CrossOrigin
public class UserController {

    private Map<String, HttpSession>sessionConetext= SessionMapUtil.getMap();
    @PostMapping("/login")
    public BaseRespones<Boolean> Login(@RequestParam(value = "JSESSIONID",required = false) String sessionID,
                                       HttpSession session,@RequestBody String username,
                                       @RequestBody(required = false) String password){
//        response.setHeader("Access-Control-Allow-Origin","*");

        session = SessionMapUtil.getSession(sessionID,session);


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
            sessionConetext.put(session.getId(),session);
            return BaseRespones.success(session.getId(),true);
        }else {
            return BaseRespones.failed("登录失败",false);
        }
    }
    @PostMapping("/regesist")
    public BaseRespones<Boolean> regesist(@RequestParam("JSESSIONID") String sessionID, HttpSession session, @RequestBody String mailcode
            , @RequestBody(required = false) String username, @RequestBody(required = false)String password, @RequestBody(required = false) String email){
        log.info("传入jessionID:"+ sessionID);
        session = SessionMapUtil.getSession(sessionID,session);

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
        String mailSendTo = (String) session.getAttribute(mailcode);//获取的是邮箱
        if (mailSendTo!=null){
            if (mailSendTo.equals(email)) {
                return BaseRespones.success("注册成功", true);
            }else {
                return BaseRespones.failed("注册失败:验证码邮箱和注册邮箱不一致",false);
            }
        }
        return BaseRespones.failed("注册失败");
    }



}
