package com.board_of_ads.service.impl;

import com.board_of_ads.models.Image;
import com.board_of_ads.models.User;
import com.board_of_ads.service.interfaces.MailService;
import com.board_of_ads.service.interfaces.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@Getter
@Setter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "security.auth_mail")

public class MailServiceImpl implements MailService {

    private final UserService userService;

    private String clientId;
    private String clientSecret;
    private String responseType;
    private String redirectURL;
    private String authUrl;
    private String tokenUrl;
    private String userInfoUrl;

    @Override
    public void auth(String code) {
        String requestBody = getAuthResponseURL(code);
        String token = getToken(requestBody);
        String sig = getSign(token);
        Map<String, String> userData = getUserData(token, sig);
        User user = init(userData);
        login(user);
    }

    @Override
    public String getAuthURL() {
        return authUrl + "?"
                + "client_id=" + clientId
                + "&response_type=" + responseType
                + "&redirect_uri=" + redirectURL;
    }

    @Override
    public String getAuthResponseURL(String code) {
        return tokenUrl + "?"
                + "client_id=" + clientId
                + "&client_secret=" + clientSecret
                + "grant_type=authorization_code&"
                + "&code=" + code
                + "&redirect_uri=" + redirectURL;
    }

    @Override
    public String getToken(String body) {
        HttpEntity<String> httpEntity = new HttpEntity<>(body);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> entity = restTemplate.exchange(tokenUrl, HttpMethod.POST, httpEntity, String.class);
        Object obj = null;
        try {
            obj = new JSONParser().parse(entity.getBody());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = (JSONObject) obj;
        String token = (String) jsonObject.get("access_token");
        return token;
    }


    public String getSign(String token) {
        return "hex_md5('app_id=" + clientId
                + "}method=users.getInfosecure=1session_key="
                + token + clientSecret + "')";
    }

    @Override
    public Map<String, String> getUserData(String token, String sig) {

        String request = userInfoUrl + "?"
                + "method=users.getInfo&app_id=" + clientId
                + "&session_key=" + token
                + "&sig=" + sig;


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "OAuth " + token);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(userInfoUrl, HttpMethod.GET, httpEntity, String.class);
        Object obj = null;
        try {
            obj = new JSONParser().parse(responseEntity.getBody());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = (JSONObject) obj;
        Map<String, String> userData = new HashMap<>();
        userData.put("first_name", (String) jsonObject.get("first_name"));
        userData.put("last_name", (String) jsonObject.get("last_name"));
        userData.put("pic_small", (String) jsonObject.get("pic_small"));
        return userData;
    }

    @Override
    public User init(Map<String, String> userData) {
        User user = userService.getUserByEmail(userData.get("email"));
        if (user != null) {
            return user;
        }
        user = new User();
        user.setEnable(true);
        user.setDataRegistration(LocalDateTime.now());
        user.setFirsName(userData.get("first_name"));
        user.setLastName(userData.get("last_name"));
        user.setAvatar(new Image(null, userData.get("pic_small")));
        userService.saveUser(user);
        return user;
    }

    @Override
    public void login(User user) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
