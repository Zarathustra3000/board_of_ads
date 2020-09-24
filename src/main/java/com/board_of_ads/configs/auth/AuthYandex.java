package com.board_of_ads.configs.auth;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class AuthYandex {
    private final String clientId = "2561bd17ecc1441d9c2461d780690cae";
    private final String clientSecret = "cc96fba5ab674d159b7d191215e1697b";
    private final String responseType = "code";
    private final String display = "popup";

    private final String redirectURL = "http://localhost:5556/yandex_auth";
    private final String authUrl = "https://oauth.yandex.ru/authorize";
    private final String tokenUrl = "https://oauth.yandex.ru/token";
    private final String userInfoUrl = "https://login.yandex.ru/info";

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

    public Map<String, String> getUserInfo(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.add("Authorization", "OAuth " + token);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(userInfoUrl, HttpMethod.GET, httpEntity, String.class);
        System.out.println(responseEntity.getBody());

        Object obj = null;
        try {
            obj = new JSONParser().parse(responseEntity.getBody());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = (JSONObject) obj;
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("first_name", (String) jsonObject.get("first_name"));
        userInfo.put("last_name", (String) jsonObject.get("last_name"));
        userInfo.put("email", (String) jsonObject.get("default_email"));
        userInfo.put("avatar_link", "https://avatars.yandex.net/get-yapic/"
                            + jsonObject.get("default_avatar_id") + "/islands-retina-50");
        return userInfo;
    }







}
