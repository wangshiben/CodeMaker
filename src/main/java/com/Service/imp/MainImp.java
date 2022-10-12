package com.Service.imp;

import com.Entity.mailEntity;
import com.Service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;
@Slf4j
@Service
public class MainImp implements MailService {
    @Value("${spring.mail.username}")
    private String SendTo;
    @Autowired
    private JavaMailSender mailSender;
    @Override
    public void sendHtmlMail(mailEntity mailRequest) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper=new MimeMessageHelper(mimeMessage);
            //邮件发件人
            helper.setFrom(SendTo);
            //邮件收件人 1或多个
            helper.setTo(mailRequest.getSendTo().split(","));
            //邮件标题
            helper.setSubject(mailRequest.getSubject());
            //邮件内容
            helper.setText(mailRequest.getText(),true);
            //邮件发送时间
            helper.setSentDate(new Date());
            mailSender.send(mimeMessage);
            log.info("发送成功:{}->{}",mailRequest,mailRequest.getSendTo());
        } catch (MessagingException e) {

            e.printStackTrace();
        }
    }
}
