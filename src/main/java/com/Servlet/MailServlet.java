package com.Servlet;

import com.Entity.mailEntity;
import com.Resp.BaseRespones;
import com.Service.MailService;
import com.Util.SessionMapUtil;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

@Controller
@Slf4j
public class MailServlet {

    private Map<String, HttpSession> sessionMap= SessionMapUtil.getMap();
    @Autowired
    private MailService mailService;
    @ResponseBody
    @RequestMapping("/mail")
    public BaseRespones<Boolean> MailSend(String sendto, @RequestParam(defaultValue = "代码生成器验证码") String  title , String words, HttpSession session, HttpServletResponse response){
        log.info("sessionID:"+session.getId());//
//        sessionMap.put();//存入map
        sessionMap.put(session.getId(),session);
        log.info("sendto:"+sendto);
        Cookie cookie=new Cookie("JSESSIONID",session.getId());
        response.addCookie(cookie);
        String code = UUID.randomUUID().toString();
        String[] split = code.split("-");
        words="您本次的验证码为:"+ split[3];
        log.info(words);
        session.setAttribute(split[3],sendto);
        mailEntity mailEntity=new mailEntity(sendto,title,words,null);
        mailService.sendHtmlMail(mailEntity);
        return BaseRespones.success(session.getId(),true);
    }

}
