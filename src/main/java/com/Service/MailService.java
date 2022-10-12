package com.Service;

import com.Entity.mailEntity;
import org.springframework.stereotype.Service;


public interface MailService {
    public void sendHtmlMail(mailEntity mailRequest);
}
