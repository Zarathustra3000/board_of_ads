package com.board_of_ads.controllers.simple;

import com.board_of_ads.configs.auth.Auth;
import com.board_of_ads.model.User;
import com.board_of_ads.service.interfaces.AuthService;
import com.board_of_ads.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class MainPageController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final AuthService authService;
    private final Auth auth;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    @GetMapping("/")
    public String getMainPage() {
        return "main-page";
    }

    @PostMapping("/auth")
    public ResponseEntity<User> create(@RequestBody User userAuth) {
        User user = userService.getUserByEmail(userAuth.getEmail());
        if (user != null) {
            if (passwordEncoder.matches(userAuth.getPassword(), user.getPassword())) {
                auth.login(user);
            }
        } else {
            throw new BadCredentialsException(userAuth.getEmail());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/vk_auth")
    public String vkAuth(@RequestParam(value = "code") String code, Model model) {
        return authService.vkAuth(code);
    }

    @GetMapping("/yandex_auth")
    public String yandexAuth(@RequestParam(value = "code") String code, Model model) {
        return authService.yandexAuth(code);
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
