package com.Servlet;

import com.Entity.mailEntity;
import com.Resp.BaseRespones;
import com.Service.MailService;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class MailServlet {
    @Autowired
    private MailService mailService;
    @ResponseBody
    @RequestMapping("/mail")
    public BaseRespones<Boolean> MailSend(String sendto, @RequestParam(defaultValue = "代码生成器验证码") String  title , String words, HttpSession session){
        String code = UUID.randomUUID().toString();
        String[] split = code.split("-");
        words="您本次的验证码为:"+ split[3];
        session.setAttribute("code",split[3]);
        mailEntity mailEntity=new mailEntity(sendto,title,words,null);
        mailService.sendHtmlMail(mailEntity);
        return BaseRespones.success("发送邮件成功",true);
    }

}
