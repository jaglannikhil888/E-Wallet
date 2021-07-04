package com.gfg.jbdl12majorproject.config;

import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfiguration {

    @Bean
    JavaMailSender javaMailSender(){
        JavaMailSenderImpl javaMailSender  = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(587);
        javaMailSender.setUsername("");
        javaMailSender.setPassword("");
        Properties properties = new Properties();
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.debug","true");
        javaMailSender.setJavaMailProperties(properties);
        return javaMailSender;
    }

    @Bean
    SimpleMailMessage simpleMailMessage(){
        return new SimpleMailMessage();
    }
}
