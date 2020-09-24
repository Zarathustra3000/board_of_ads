package com.board_of_ads;

import com.board_of_ads.configs.auth.AuthYandex;
import com.board_of_ads.service.impl.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class BoardOfAdsApplication {

//    @Autowired
//    private SendEmailService sendEmailService;

    public static void main(String[] args) {
        SpringApplication.run(BoardOfAdsApplication.class, args);
        AuthYandex authYandex = new AuthYandex();
        System.out.println(authYandex.getAuthURL());
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void triggerWhenStarts(){
//        sendEmailService.sendEmail("ggg@gmail.com", "Hi there", "Test");
//    }
}
