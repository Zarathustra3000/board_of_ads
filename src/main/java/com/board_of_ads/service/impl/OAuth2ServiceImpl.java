
package com.board_of_ads.service.impl;

import com.board_of_ads.models.Image;
import com.board_of_ads.models.User;
import com.board_of_ads.service.interfaces.OAuth2Service;
import com.board_of_ads.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class OAuth2ServiceImpl implements OAuth2Service {

    private final UserService userService;

    @Override
    public void auth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
            OAuth2User oAuth2User = token.getPrincipal();
            Map<String, Object> attributes = oAuth2User.getAttributes();
            User user = userService.getUserByEmail((String) attributes.get("email"));
            if (user != null) {
                return;
            }
            if (token.getAuthorizedClientRegistrationId().equals("google")) {
                user = new User();
                user.setAvatar(new Image(null, (String) attributes.get("picture")));
                user.setEmail((String) attributes.get("email"));
                user.setFirsName((String) attributes.get("given_name"));
                user.setLastName((String) attributes.get("family_name"));
                user.setEnable(true);
                user.setPassword((String) attributes.get("email"));
                userService.saveUser(user);
            } else if (token.getAuthorizedClientRegistrationId().equals("facebook")) {
                user = new User();
                user.setEmail((String) attributes.get("email"));
                user.setPassword((String) attributes.get("email"));
                String name = (String) attributes.get("name");
                String[] userData = name.split(" ");
                user.setFirsName(userData[0]);
                user.setLastName(userData[1]);
                user.setEnable(true);
                userService.saveUser(user);
            }
        }
    }
}