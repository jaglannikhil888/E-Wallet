package com.gfg.jbdl12majorproject.notfication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfg.jbdl12majorproject.userservice.entities.PaymentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;

@Service
public class NotificationManagerImpl implements NotificationManager{

    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    SimpleMailMessage simpleMailMessage;

    @Override
    @KafkaListener(topics = "notification", groupId = "notification")
    public void send(String message) throws JsonProcessingException, UnsupportedEncodingException {
        NotificationRequest notificationRequest
                = objectMapper.readValue(message,NotificationRequest.class);
        RestTemplate restTemplate = new RestTemplate();
        PaymentUser paymentUser =
                restTemplate.getForEntity("http://localhost:8080/user/".concat(notificationRequest.getUser()),
                        PaymentUser.class).getBody();

        simpleMailMessage.setSubject(notificationRequest.getType().name());
        simpleMailMessage.setText(notificationRequest.getMessage());
        InternetAddress internetAddress = new InternetAddress(paymentUser.getEmail(),"","UTF-8");
        simpleMailMessage.setTo(internetAddress.getAddress());
        simpleMailMessage.setFrom("geekstutorialemail2020@gmail.com");

        javaMailSender.send(simpleMailMessage);

    }
}
