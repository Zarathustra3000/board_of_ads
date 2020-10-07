package com.board_of_ads.controllers.simple;

import com.board_of_ads.models.User;
import com.board_of_ads.service.interfaces.MailService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class MainPageController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final MailService mailService;

    @GetMapping("/")
    public String getMainPage(@AuthenticationPrincipal() User user, Model model) {
        model.addAttribute("user", user != null ? user : new User());
        return "main-page";
    }

    @GetMapping("/admin_page")
    public String adminPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute(user);
        return "admin/admin_page";
    }

    @GetMapping("/new_post")
    public String addNewPost(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user != null ? user : new User());
        return "newpost-page";
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
