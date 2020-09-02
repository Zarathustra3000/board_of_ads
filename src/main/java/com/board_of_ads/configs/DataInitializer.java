package com.board_of_ads.configs;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@AllArgsConstructor
public class DataInitializer {

    @PostConstruct
    private void init() {

    }
}
