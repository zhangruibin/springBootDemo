package com.example.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by zhangrui on 2017/11/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootJmsApplicationTests {
    @Test
    public void contextLoads() {
    }
    @Autowired
    private JavaMailSenderImpl mailSender;
    /**
     * 发送包含简单文本的邮件
     */

    @Test
    public void sendTxtMail() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 设置收件人，寄件人
        simpleMailMessage.setTo(new String[] {"251482784@qq.com"});
        simpleMailMessage.setFrom("635983562@qq.com");
        simpleMailMessage.setSubject("Spring Boot Mail 邮件测试【文本】");
        simpleMailMessage.setText("这里是一段简单文本。");
        // 发送邮件
        //发送邮件,未成功,应该是涉及到更深层的数据传输协议
        // TODO
        mailSender.send(simpleMailMessage);

        System.out.println("邮件已发送");
    }

}
