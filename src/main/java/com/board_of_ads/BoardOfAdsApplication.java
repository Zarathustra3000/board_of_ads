package com.board_of_ads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.IOException;
import java.util.logging.LogManager;

@SpringBootApplication
public class BoardOfAdsApplication {

    private static final Logger logger = LoggerFactory.getLogger(BoardOfAdsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BoardOfAdsApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onStart() {
//        try {
//            LogManager.getLogManager().readConfiguration(
//                    BoardOfAdsApplication.class.getResourceAsStream("/logging.properties"));
//        } catch (IOException e) {
//            System.err.println("Could not setup logger configuration: " + e.toString());
//        }

        logger.info("Successful application launch");
    }

}
