package com.board_of_ads.service.impl;

import com.board_of_ads.configs.auth.Auth;
import com.board_of_ads.model.User;
import com.board_of_ads.model.dto.InstagramTokenDto;
import com.board_of_ads.model.dto.InstagramUserDto;
import com.board_of_ads.repository.RoleRepository;
import com.board_of_ads.service.interfaces.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Set;

@Data
@Service
public class AuthInstagramService {

    private final RestTemplate restTemplate;
    private final Auth auth;
    private final UserService userService;
    private final RoleRepository roleRepository;

    public AuthInstagramService(
            RestTemplate restTemplate,
            Auth auth,
            UserService userService,
            RoleRepository roleRepository
    ) {
        this.restTemplate = restTemplate;
        this.auth = auth;
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @Value("${oath.insta.app_id}")
    private String appId;
    @Value("${oath.insta.app_secret}")
    private String appSecret;
    @Value("${oath.insta.redirect_url}")
    private String redirectUrl;
    @Value("${oath.insta.authorize_url}")
    private String authorizeUrl;
    @Value("${oath.insta.access_token_url}")
    private String accessTokenUrl;
    @Value("${oath.insta.graph_api_url}")
    private String graphApiUrl;
    @Value("${oath.insta.scope}")
    private String scope;

    private String getCodeUrl() {
        return authorizeUrl
                + "?app_id=" + appId
                + "&redirect_uri=" + redirectUrl
                + "&scope=" + scope
                + "&response_type=code";
    }

    private String getLongLivedTokenUrl(String token) {
        return graphApiUrl + "access_token"
                + "?grant_type=ig_exchange_token&client_secret=" + appSecret
                + "&access_token=" + token;
    }

    private String getUserInfoUrl(String id, String token) {
        return graphApiUrl + id
                + "?fields=id,username&access_token=" + token;
    }

    public String redirectToAuth() {
        return "redirect:" + getCodeUrl();
    }

    public String authorization(String code) {
        ResponseEntity<InstagramTokenDto> accessToken = getAccessToken(code);
        ResponseEntity<InstagramTokenDto> longLivedToken = getLongLivedToken(accessToken);
        ResponseEntity<InstagramUserDto> instagramUser = restTemplate.getForEntity(
                getUserInfoUrl(Objects.requireNonNull(accessToken.getBody()).getUser_id(), Objects.requireNonNull(longLivedToken.getBody()).getAccess_token()),
                InstagramUserDto.class);
        loginUser(instagramUser);
        return "redirect:/";
    }

    private void loginUser(ResponseEntity<InstagramUserDto> instagramUser) {
        User user = new User();
        String email = Objects.requireNonNull(instagramUser.getBody()).getId() + "@instagram.com";
        user.setEmail(email);
        user.setPassword(instagramUser.getBody().getId());
        user.setFirsName(instagramUser.getBody().getUsername());
        user.setRoles(Set.of(roleRepository.findRoleByName("USER")));
        if (userService.getUserByEmail(email) == null) {
            userService.saveUser(user);
        }
        auth.login(user);
    }

    private ResponseEntity<InstagramTokenDto> getAccessToken(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("app_id", appId);
        map.add("app_secret", appSecret);
        map.add("grant_type", "authorization_code");
        map.add("redirect_uri", redirectUrl);
        map.add("code", code);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        return restTemplate.postForEntity(accessTokenUrl, request, InstagramTokenDto.class);
    }

    private ResponseEntity<InstagramTokenDto> getLongLivedToken(ResponseEntity<InstagramTokenDto> tokenDto) {
        return restTemplate.getForEntity(
                getLongLivedTokenUrl(Objects.requireNonNull(tokenDto.getBody()).getAccess_token()),
                InstagramTokenDto.class
        );
    }
}