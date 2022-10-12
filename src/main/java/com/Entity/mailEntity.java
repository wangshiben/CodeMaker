package com.Entity;

import lombok.Data;

@Data
public class mailEntity {
    public mailEntity(String sendTo, String subject, String text, String filePath) {
        this.sendTo = sendTo;
        this.subject = subject;
        this.text = text;
        this.filePath = filePath;
    }

    public mailEntity() {
    }

    /**
     * 接收人
     */
    private String sendTo;

    /**
     *  邮件主题
     */
    private String subject;

    /**
     *  邮件内容
     */
    private String text;

    /**
     *  附件路径
     */
    private String filePath;
}
