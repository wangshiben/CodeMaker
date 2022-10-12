package com.Servlet;

import com.Entity.mailEntity;
import com.Service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MailServlet {
    @Autowired
    private MailService mailService;
    @RequestMapping("/mail")
    public void MailSend(String sendTo,String title,String words){
        mailEntity mailEntity=new mailEntity(sendTo,title,words,null);
        mailService.sendHtmlMail(mailEntity);
    }
}
