package com.board_of_ads.controllers.simple;

import com.board_of_ads.configs.auth.Auth;
import com.board_of_ads.configs.auth.AuthVK;
import com.board_of_ads.model.User;
import com.board_of_ads.service.impl.AuthInstagramService;
import com.board_of_ads.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Map;

@Controller
@AllArgsConstructor
public class MainPageController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserService userService;
    private final Auth auth;
    private final AuthVK authVK;
    private final AuthInstagramService instaService;

    @GetMapping("/")
    public String getMainPage() {
        return "main-page";
    }


    @GetMapping("/vk_auth")
    public String vkAuth(@RequestParam(value = "code") String code, Model model) {
        String response = authVK.getAuthResponseURL(code);
        Map<String, String> userData = authVK.getUserData(response);
        userData = authVK.getUserInfo(userData);
        User user = userService.getUserByEmail(userData.get("email"));
        if (user != null) {
            auth.login(user);
            return "redirect:/";
        }
        user = new User();
        user.setEnable(true);
        user.setDataRegistration(LocalDateTime.now());
        user.setEmail(userData.get("email"));
        user.setFirsName(userData.get("first_name"));
        user.setLastName(userData.get("last_name"));
        user.setPassword(userData.get("email")); //todo create set password page (and phone)
        userService.saveUser(user);
        auth.login(user);
        return "redirect:/";
    }

    /** todo delete
     * Тестовый контроллер для проверки авторизации.
     * Если при переходе на /test вас перенаправило на главную страницу ВК, то вы авторизованы
     */
    @GetMapping("/test")
    public String aa() {
        return "redirect:http://vk.com";
    }

    @GetMapping("/auth/instagram")
    private String redirectToAuthorize() {
        return instaService.redirectToAuth();
    }

    @GetMapping("/auth/instagram/getcode")
    private String getToken(@RequestParam(value = "code") String code) {
        return instaService.authorization(code);
    }
}