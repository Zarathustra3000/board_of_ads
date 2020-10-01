package com.board_of_ads.controllers.simple;

import com.board_of_ads.models.User;
import com.board_of_ads.service.impl.AuthServiceImpl;
import com.board_of_ads.service.impl.OAuth2Service;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class MainPageController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final AuthServiceImpl authService; //todo
    private final OAuth2Service oAuth2Service;

//    @GetMapping("/user")
//    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
//        System.out.println(String.valueOf(principal.getAttribute("name")));
//        return Collections.singletonMap("name", principal.getAttribute("name"));
//    }

    @GetMapping("/")
    public String getMainPage(@AuthenticationPrincipal() User user, Model model) {
        authService.auth();
        model.addAttribute(user != null ? user : new User());
        return "main-page";
    }

//    @GetMapping("/q")
//    public String q() {
//        OAuth2Service oAuth2Service = new OAuth2Service();
//        oAuth2Service.googleAuth();
//        return "redirect:/";
//    }

    @GetMapping("/admin_page")
    public String adminPage(@AuthenticationPrincipal User user, Model model) {
        System.out.println(user.getAuthorities());
        model.addAttribute(user);
        return "admin_page";
    }

    @GetMapping("/facebook_auth")
    public String facebookAuth() {
//        oAuth2Service.facebookAuth();
        return "redirect:/oauth2/authorization/facebook";
    }

    @GetMapping("/google_auth")
    public String googleAuth() {
//        return oAuth2Service.googleAuth();
        return "redirect:/oauth2/authorization/google";
    }

    @GetMapping("/vk_auth")
    public String vkAuth(@RequestParam(value = "code") String code) {
//        return authService.vkAuth(code);
        return null;
    }

    @GetMapping("/yandex_auth")
    public String yandexAuth(@RequestParam(value = "code") String code) {
//        return authService.yandexAuth(code);
        return null;
    }

    /**
     * todo delete
     * Тестовый контроллер для проверки авторизации.
     * Если при переходе на /test вас перенаправило на главную страницу ВК, то вы авторизованы
     */
    @GetMapping("/test")
    public String aa() {
        return "redirect:http://vk.com";
    }
}
