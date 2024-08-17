package com.sipc.clockin.utils;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Paths;
import java.util.UUID;


@Component
public class MailUtil {


    @Autowired
    private JavaMailSenderImpl mailSender;

    @Value("${spring.mail.username}")
    private String sender;


    public  String sendEmail(String mail) {
        //简单的邮件
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        //邮件标题
        mailMessage.setSubject("【天理打卡】平台验证码");
        //生成验证码
        String code = getVerificationCode();
        mailMessage.setText("您的TUT-Clockin平台验证码为: "+code);
        //发送人须和配置文件中的username相同
        mailMessage.setFrom(sender);
        //收件人
        mailMessage.setTo(mail);
        //发送
        mailSender.send(mailMessage);
        return code;
    }

    public  String sendEmail(String mail,String filename) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        //标题
        helper.setSubject("【天理打卡】平台验证码");
        //内容，可写css样式(第二参数设置为true)
        String code = getVerificationCode();
        helper.setText("您的TUT-Clockin平台验证码为: "+code);
        //文件
        String filepath = getSendFile();
        helper.addAttachment(filename, new File(filepath));
        //发件人
        helper.setFrom(sender);
        //收件人
        helper.setTo(mail);
        mailSender.send(mimeMessage);

        return code;
    }


    public static String getVerificationCode() {
        return UUID.randomUUID().toString().substring(0, 6);
    }

    public static String getSendFile(String filename){
        String path= Paths.get("src", "main", "resources", "static",filename).toString();
        return new File(path).getAbsolutePath();
    }
    public static String getSendFile(){
        String path= Paths.get("src", "main", "resources", "static").toString();
        return new File(path).getAbsolutePath();
    }
}