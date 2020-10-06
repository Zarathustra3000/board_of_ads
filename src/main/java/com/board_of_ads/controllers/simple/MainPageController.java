package com.board_of_ads.controllers.simple;

import com.board_of_ads.models.User;
import com.board_of_ads.service.interfaces.MailService;
import com.board_of_ads.service.interfaces.AuthorizationService;
import com.board_of_ads.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@AllArgsConstructor
public class MainPageController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final MailService mailService;
    private final AuthorizationService authorizationService;
    private final UserService userService;


    @GetMapping("/")
    public String getMainPage(@AuthenticationPrincipal() User user, Model model) {
        model.addAttribute("user", user != null ? user : new User());
        return "main-page";
    }

    @PostMapping("/auth")
    public ResponseEntity<User> authorization(@RequestBody User userAuth) {

        if (authorizationService.isValid(userAuth)) {
            userAuth = userService.getUserByEmail(userAuth.getEmail());
            authorizationService.login(userAuth);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/admin_page")
    public String adminPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute(user);
        return "admin/admin_page";
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
