package com.board_of_ads.controllers.simple;

import com.board_of_ads.configs.auth.AuthVK;
import com.board_of_ads.model.User;
import com.board_of_ads.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
@Controller
@AllArgsConstructor
public class MainPageController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserService userService;
    private final UserDetailsService userDetailsService;
    @GetMapping("/")
    public String getMainPage() {
        return "main-page";
    }



//    @GetMapping("/vk_auth")
//    public String vkAuth(@RequestParam(value = "code") String code, Model model) {
//        AuthVK authVK = new AuthVK();  //todo spring injections
//        String response = authVK.getAuthResponseURL(code);
//        Map<String, String> userData = authVK.getUserData(response);
//        Map<String, String> userInfo = authVK.getUserInfo(userData);
//        User user = userService.getUserByEmail(userData.get("email"));
//        if (user != null) {
//            userDetailsService.loadUserByUsername(user.getUsername());
//            System.out.println("USER: " + user.getUsername());
//            System.out.println("USER: " + user.getAuthorities());
//            return "redirect:/";
//        }
//            model.addAttribute("first_name", userInfo.get("first_name"));
//            model.addAttribute("last_name", userInfo.get("last_name"));
//            model.addAttribute("avatar_link", userInfo.get("avatar_link"));
//            model.addAttribute("email", userData.get("email"));
//            return "redirect:/";
//    }

    @GetMapping("/aa")
    public String aa() {
        return "redirect:http://vk.com";
    }
}
