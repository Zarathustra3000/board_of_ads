package com.board_of_ads.service.impl;

import com.board_of_ads.configs.auth.Auth;
import com.board_of_ads.configs.auth.AuthMail;
import com.board_of_ads.configs.auth.AuthVK;
import com.board_of_ads.configs.auth.AuthYandex;
import com.board_of_ads.model.User;
import com.board_of_ads.repository.UserRepository;
import com.board_of_ads.service.interfaces.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private AuthVK authVK;
    private AuthYandex authYandex;
    private Auth auth;
    private AuthMail authMail;

    @Override
    public String vkAuth(String code) {
        String response = authVK.getAuthResponseURL(code);
        Map<String, String> userData = authVK.getUserData(response);
        userData = authVK.getUserData(userData);
        User user = auth.init(userData);
        auth.login(user);
        return "redirect:/";
    }

    @Override
    public String yandexAuth(String code) {
        String requestBody = authYandex.getRequestBody(code);
        String token = authYandex.getToken(requestBody);
        Map<String, String> userData = authYandex.getUserData(token);
        User user = auth.init(userData);
        auth.login(user);
        return "redirect:/";
    }

    @Override
    public String mailAuth(String code) {
        String response = authMail.getAuthResponseURL(code);
        String token = authMail.getToken(response);
        Map<String, String> userData = authMail.getUserData(token);
        User user = auth.init(userData);
        auth.login(user);
        return "redirect:/";
    }
}
