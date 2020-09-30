package com.board_of_ads.service.interfaces;

import com.board_of_ads.models.User;

import java.util.Map;

public interface AuthService {

    String vkAuth(String code);

    String yandexAuth(String code);

    void login(User user);

    User init(Map<String, String> userData);
}
