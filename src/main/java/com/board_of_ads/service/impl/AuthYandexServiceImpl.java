package com.board_of_ads.service.impl;

import com.board_of_ads.service.interfaces.AuthYandexService;
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
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@Setter
@ConfigurationProperties(value = "security.auth-yandex")
public class AuthYandexServiceImpl implements AuthYandexService {

    private String clientId;
    private String clientSecret;
    private String responseType;
    private String display;
    private String redirectURL;
    private String authURL;
    private String tokenURL;
    private String userInfoURL;

    /**
     * Метод для кнопки авторизации
     * @return ссылку для авторизации при переходе по которой, получим ссылку с
     *          аргументом code необходимым для метода getAuthResponse(String code).
     */
    @Override
    public String getAuthURL() {
        return authURL + "?"
                + "client_id=" + clientId
                + "&redirect_uri=" + redirectURL
                + "&display=" + display
                + "&response_type=" + responseType;
    }

    /**
     * @param code получаем из ссылки возвращаемой методом getAuthURL()
     * @return тело запроса, для получения access_token
     */
    @Override
    public String getRequestBody(String code) {
        return "grant_type=authorization_code"
                + "&client_id=" + clientId
                + "&client_secret=" + clientSecret
                + "&code=" + code;
    }

    /**
     * @param body получаем из метода getRequestBody(String code)
     * @return токен авторизации пользователя Yandex Passport
     */
    @Override
    public String getToken(String body) {
        HttpEntity<String> httpEntity = new HttpEntity<>(body);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> entity = restTemplate.exchange(tokenURL, HttpMethod.POST, httpEntity, String.class);
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

    /**
     * @param token получаем из метода getToken(String body)
     * @return Map с именем, фамилией, почтой и ссылкой на аватар пользователя (100x100px).
     */
    @Override
    public Map<String, String> getUserData(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "OAuth " + token);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(userInfoURL, HttpMethod.GET, httpEntity, String.class);
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
        userData.put("email", (String) jsonObject.get("default_email"));
        userData.put("avatar_link", "https://avatars.yandex.net/get-yapic/"
                + jsonObject.get("default_avatar_id") + "/islands-retina-50");
        return userData;
    }
}
