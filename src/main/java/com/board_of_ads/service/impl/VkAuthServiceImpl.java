package com.board_of_ads.service.impl;

import com.board_of_ads.configs.auth.Auth;
import com.board_of_ads.configs.auth.AuthVK;
import com.board_of_ads.model.User;
import com.board_of_ads.repository.UserRepository;
import com.board_of_ads.service.interfaces.VkAuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@AllArgsConstructor
public class VkAuthServiceImpl implements VkAuthService {

    private final AuthVK authVK;
    private final Auth auth;
    private final UserRepository userRepository;

    @Override
    public String vkAuth(String code) {
        String response = authVK.getAuthResponseURL(code);
        Map<String, String> userData = authVK.getUserData(response);
        userData = authVK.getUserInfo(userData);
        User user = userRepository.findByEmail(userData.get("email"));
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
        userRepository.save(user);
        auth.login(user);
        return "redirect:/";
    }
}
