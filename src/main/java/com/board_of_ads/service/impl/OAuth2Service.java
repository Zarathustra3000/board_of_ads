package com.board_of_ads.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OAuth2Service {

//    private UserService userService;

//    public void auth(OidcUser oidcUser) {
//        System.out.println("OAUTHSERVIICE");
//        System.out.println((String) oidcUser.getClaim("sub"));
//        if (oidcUser.getClaims().containsKey("sub")) {
//
//        }
//    }
//




//    public User facebookAuth() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println("AUTHEN");
//        System.out.println(authentication.getPrincipal());
//        System.out.println(authentication.getPrincipal().toString());
//        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
//        OAuth2User oAuth2User = token.getPrincipal();
//        Map<String, Object> attributes = oAuth2User.getAttributes();
//        System.out.println(attributes);
//        User user = userService.getUserByEmail((String) attributes.get("email"));
//        if (user != null) {
//            return user;
//        }
//        user = new User();
//        user.setEmail((String) attributes.get("email"));
//        user.setPassword((String) attributes.get("email"));
//        String name = (String) attributes.get("name");
//        String[] userData = name.split(" ");
//        user.setFirsName(userData[0]);
//        user.setLastName(userData[1]);
//        user.setEnable(true);
//        userService.saveUser(user);
//        return user;
//    }
//
//    public String googleAuth() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println("AUTHE111111111N");
//        System.out.println(authentication.getPrincipal());
//        System.out.println(authentication.getPrincipal().toString());
//
//        if (authentication.getPrincipal().toString().equals("anonymousUser")) {
//            return "redirect:/oauth2/authorization/google";
//        }
//        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
//        OAuth2User oAuth2User = token.getPrincipal();
//        Map<String, Object> attributes = oAuth2User.getAttributes();
//        System.out.println(attributes);
//        User user = userService.getUserByEmail((String) attributes.get("email"));
//        System.out.println("REGISTERED");
//        if (user != null) {
//            return "redirect:/aaaaaaaaaaaaaaaaaaa";
//        }
//        user = new User();
//        user.setAvatar(new Image(null, (String) attributes.get("picture")));
//        user.setEmail((String) attributes.get("email"));
//        user.setFirsName((String) attributes.get("given_name"));
//        user.setLastName((String) attributes.get("family_name"));
//        user.setEnable(true);
//        user.setPassword((String) attributes.get("email"));
//        userService.saveUser(user);
//        return "redirect:/aaaaaaaaaaaaaaaaaaaaaaaaa";
//    }

//    public Map<String, Object> getAttributes() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println("AUTHEN");
//        System.out.println(authentication.getPrincipal());
//        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
//        OAuth2User oAuth2User = token.getPrincipal();
//        Map<String, Object> attributes = oAuth2User.getAttributes();
//        return attributes;
//    }
}
