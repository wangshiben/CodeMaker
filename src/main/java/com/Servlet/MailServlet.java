package com.Servlet;

import com.Entity.mailEntity;
import com.Resp.BaseRespones;
import com.Service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class MailServlet {
    @Autowired
    private MailService mailService;
    @ResponseBody
    @RequestMapping("/mail")
    public BaseRespones<Boolean> MailSend(String sendTo, String title, String words, HttpSession session){
        String code = UUID.randomUUID().toString();
        String[] split = code.split("-");
        words="您本次的验证码为:"+ split[3];
        session.setAttribute("code",split[3]);
        mailEntity mailEntity=new mailEntity(sendTo,title,words,null);
        mailService.sendHtmlMail(mailEntity);
        return BaseRespones.success("发送邮件成功",true);
    }
}
