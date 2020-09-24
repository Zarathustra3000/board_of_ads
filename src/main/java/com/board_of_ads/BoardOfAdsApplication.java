package com.board_of_ads;

import com.board_of_ads.configs.auth.AuthYandex;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BoardOfAdsApplication {

//    @Autowired
//    private SendEmailService sendEmailService;

    public static void main(String[] args) {
        SpringApplication.run(BoardOfAdsApplication.class, args);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void triggerWhenStarts(){
//        sendEmailService.sendEmail("ggg@gmail.com", "Hi there", "Test");
//    }
}
