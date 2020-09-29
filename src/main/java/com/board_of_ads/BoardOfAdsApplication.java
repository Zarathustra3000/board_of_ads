package com.board_of_ads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.annotation.PreDestroy;

@SpringBootApplication
public class BoardOfAdsApplication {

    private static final Logger logger = LoggerFactory.getLogger(BoardOfAdsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BoardOfAdsApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    private void onStart() {
        logger.info("Successful application launch");
    }

}
