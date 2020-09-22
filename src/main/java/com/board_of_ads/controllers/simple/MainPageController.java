package com.board_of_ads.controllers.simple;

import com.board_of_ads.configs.auth.AuthVK;
import com.board_of_ads.model.User;
import com.board_of_ads.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@AllArgsConstructor
public class MainPageController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserService userService;

    @GetMapping("/")
    public String getMainPage() {
        return "main-page";
    }

    @GetMapping("/vk_auth")
    public String vkAuth(@RequestParam(value = "code") String code, Model model) {
        AuthVK authVK = new AuthVK();  //todo spring injections
        String response = authVK.getAuthResponseURL(code);
        Map<String, String> userData = authVK.getUserData(response);
        Map<String, String> userInfo = authVK.getUserInfo(userData);
        User user = userService.getUserByEmail(userData.get("email"));
        if (user != null) {
            //todo add
        }
            model.addAttribute("first_name", userInfo.get("first_name"));
            model.addAttribute("last_name", userInfo.get("last_name"));
            model.addAttribute("avatar_link", userInfo.get("avatar_link"));
            model.addAttribute("email", userData.get("email"));
            return null;
    }

}
