package com.board_of_ads.service.impl;

import com.board_of_ads.models.Image;
import com.board_of_ads.models.User;
import com.board_of_ads.service.interfaces.AuthService;
import com.board_of_ads.service.interfaces.AuthVkService;
import com.board_of_ads.service.interfaces.AuthYandexService;
import com.board_of_ads.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthVkService authVkService;
    private final AuthYandexService authYandexService;
    private final UserService userService;

    @Override
    public String vkAuth(String code) {
        String response = authVkService.getAuthResponseURL(code);
        Map<String, String> userData = authVkService.getUserData(response);
        userData = authVkService.getUserData(userData);
        User user = init(userData);
        login(user);
        return "redirect:/";
    }

    @Override
    public String yandexAuth(String code) {
        String requestBody = authYandexService.getRequestBody(code);
        String token = authYandexService.getToken(requestBody);
        Map<String, String> userData = authYandexService.getUserData(token);
        User user = init(userData);
        login(user);
        return "redirect:/";
    }

    /**
     * Метод для получения сессии пользователя
     */
    @Override
    public void login(User user) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    /**
     * Метод инициализации пользователя.
     * Если такой пользователь есть в базе данных, то он вернет его.
     * Если пользователя не существует, то он его создаст, добавит в БД и вернет.
     *
     * @param userData возвращается методом getUserData(...) в классах авторизаций.
     */
    @Override
    public User init(Map<String, String> userData) {
        User user = userService.getUserByEmail(userData.get("email"));
        if (user != null) {
            return user;
        }
        user = new User();
        user.setEnable(true);
        user.setDataRegistration(LocalDateTime.now());
        user.setEmail(userData.get("email"));
        user.setFirsName(userData.get("first_name"));
        user.setLastName(userData.get("last_name"));
        user.setAvatar(new Image(null, userData.get("avatar_link")));
        user.setPassword(userData.get("email")); //todo create set password page (and phone)
        userService.saveUser(user);
        return user;
    }
}
