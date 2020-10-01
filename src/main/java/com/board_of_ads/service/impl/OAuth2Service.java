package com.board_of_ads.service.impl;

import com.board_of_ads.models.Image;
import com.board_of_ads.models.User;
import com.board_of_ads.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@AllArgsConstructor
public class OAuth2Service {

    private UserService userService;

    public void facebookAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        OAuth2User user = token.getPrincipal();
        Map<String, Object> attributes = user.getAttributes();
        System.out.println(attributes);
    }

    public User googleAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken)authentication;
        OAuth2User oAuth2User = token.getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();
        System.out.println(attributes);
        User user = userService.getUserByEmail((String) attributes.get("email"));
        if (user != null) {
            return user;
        }
        user = new User();
        user.setAvatar(new Image(null, (String) attributes.get("picture")));
        user.setEmail((String) attributes.get("email"));
        user.setFirsName((String) attributes.get("given_name"));
        user.setLastName((String) attributes.get("family_name"));
        user.setEnable(true);
        user.setPassword((String) attributes.get("email"));
        userService.saveUser(user);
        return user;
    }
}
