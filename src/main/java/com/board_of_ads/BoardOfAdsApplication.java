package com.board_of_ads;

import com.board_of_ads.configs.auth.AuthVK;
import com.board_of_ads.service.impl.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BoardOfAdsApplication {

//
//    @Autowired
//    private SendEmailService sendEmailService;

    public static void main(String[] args) {
        AuthVK authVK = new AuthVK();
        System.out.println(authVK.getAuthURL());
        SpringApplication.run(BoardOfAdsApplication.class, args);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void triggerWhenStarts(){
//        sendEmailService.sendEmail("ggg@gmail.com", "Hi there", "Test");
//    }
}
