package com.board_of_ads.configs.auth;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class AuthYandex {
    private final String clientId = "2561bd17ecc1441d9c2461d780690cae";
    private final String clientSecret = "cc96fba5ab674d159b7d191215e1697b";
    private final String responseType = "code";
    private final String display = "popup";

    private final String redirectURL = "http://localhost:5556/yandex_auth";
    private final String authUrl = "https://oauth.yandex.ru/authorize";
    private final String tokenUrl = "https://oauth.yandex.ru/token";

    public String getAuthURL() {
        return authUrl + "?"
                + "client_id=" + clientId
                + "&redirect_uri=" + redirectURL
                + "&display=" + display
                + "&response_type=" + responseType;
    }

    public String getRequestBody(String code) {
        return "grant_type=authorization_code" +
                "&client_id=2561bd17ecc1441d9c2461d780690cae&" + //fixme
                "client_secret=cc96fba5ab674d159b7d191215e1697b&code=" + code;
    }

    public String getToken(String body) {
        HttpEntity<String> httpEntity = new HttpEntity<>(body);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> entity = restTemplate.exchange(tokenUrl, HttpMethod.POST, httpEntity, String.class);
        System.out.println(entity);

        return null
                ;
    }







}
