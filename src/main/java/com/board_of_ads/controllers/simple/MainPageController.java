package com.board_of_ads.controllers.simple;

import com.board_of_ads.models.User;
import com.board_of_ads.service.interfaces.AuthService;
import com.board_of_ads.service.interfaces.VkService;
import com.board_of_ads.service.interfaces.YandexService;
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
    private final YandexService yandexService;
    private final VkService vkService;
    private final AuthService authService;

    @GetMapping("/")
    public String getMainPage(@AuthenticationPrincipal() User user, Model model) {
        authService.auth();
        model.addAttribute(user != null ? user : new User());
        return "main-page";
    }

    @GetMapping("/admin_page")
    public String adminPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute(user);
        return "admin/admin_page";
    }

    @GetMapping("/facebook_auth")
    public String facebookAuth() {
        return "redirect:/oauth2/authorization/facebook";
    }

    @GetMapping("/google_auth")
    public String googleAuth() {
        return "redirect:/oauth2/authorization/google";
    }

    @GetMapping("/vk_auth")
    public String vkAuth(@RequestParam(value = "code") String code, Model model) {
        vkService.auth(code);
        return "redirect:/";
    }

    @GetMapping("/yandex_auth")
    public String yandexAuth(@RequestParam(value = "code") String code, Model model) {
        yandexService.auth(code);
        return "redirect:/";
    }

    @GetMapping("/confirm/")
    public String confirmPassword() {
        return "main-page";
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
