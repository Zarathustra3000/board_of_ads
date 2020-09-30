package com.board_of_ads.service.interfaces;

public interface EmailService {

    void sendEmail(String to, String body, String topic);
}
