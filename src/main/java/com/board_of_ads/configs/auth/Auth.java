package com.board_of_ads.configs.auth;

import com.board_of_ads.model.Image;
import com.board_of_ads.model.User;
import com.board_of_ads.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Служебный класс для авторизации пользователя
 */
@Controller
@AllArgsConstructor
public class Auth {
    private final UserService userService;

    /**
     * Метод для получения сессии пользователя
     */
    public void login(User user) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    /**
     * Метод инициализации пользователя.
     * Если такой пользователь есть в базе данных, то он вернет его.
     * Если пользователя не существует, то он его создаст, добавит в БД и вернет.
     * @param userData возвращается методом getUserData(...) в классах авторизаций.
     */
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
