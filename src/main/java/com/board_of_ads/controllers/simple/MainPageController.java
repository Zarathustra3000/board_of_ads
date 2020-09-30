package com.board_of_ads.controllers.simple;

import com.board_of_ads.models.User;
import com.board_of_ads.service.interfaces.AuthService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@Controller
@AllArgsConstructor
public class MainPageController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final AuthService authService;

    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        return Collections.singletonMap("name", principal.getAttribute("name"));
    }

    @GetMapping("/")
    public String getMainPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute(user != null ? user : new User());
        return "main-page";
    }

    @GetMapping("/admin_page")
    public String adminPage(@AuthenticationPrincipal User user, Model model) {
        System.out.println(user.getAuthorities());
        model.addAttribute(user);
        return "admin_page";
    }


    @GetMapping("/vk_auth")
    public String vkAuth(@RequestParam(value = "code") String code) {
        return authService.vkAuth(code);
    }

    @GetMapping("/yandex_auth")
    public String yandexAuth(@RequestParam(value = "code") String code) {
        return authService.yandexAuth(code);
    }

    /** todo delete
     * Тестовый контроллер для проверки авторизации.
     * Если при переходе на /test вас перенаправило на главную страницу ВК, то вы авторизованы
     */
    @GetMapping("/test")
    public String aa() {
        return "redirect:http://vk.com";
    }
}
