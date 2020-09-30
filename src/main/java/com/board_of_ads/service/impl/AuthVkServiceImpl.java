package com.board_of_ads.service.impl;

import com.board_of_ads.service.interfaces.AuthVkService;
import lombok.Setter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@Setter
@ConfigurationProperties(prefix="security.auth-vk")
public class AuthVkServiceImpl implements AuthVkService {

    private String clientId;
    private String clientSecret;
    private String responseType;
    private String redirectURL;
    private String authURL;
    private String tokenURL;
    private String usersGetURL;
    private String scope;
    private String display;
    private String fields;
    private String version;

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
                + "&scope=" + scope
                + "&display=" + display
                + "&response_type=" + responseType;
    }

    /**
     * @param code получаем из ссылки возвращаемой методом getAuthURL()
     * @return ссылку, для получения access_token, и данные пользователя VK
     */
    @Override
    public String getAuthResponseURL(String code) {
        System.out.println(clientId);
        System.out.println(clientSecret);
        return tokenURL + "?"
                + "client_id=" + clientId
                + "&client_secret=" + clientSecret
                + "&redirect_uri=" + redirectURL
                + "&code=" + code;
    }

    /**
     * @param authResponseUrl возвращается методом getAuthResponse(String code)
     * @return Map с access_token, user_id, и email пользователя.
     */
    @Override
    public Map<String, String> getUserData(String authResponseUrl) {
        Map<String, String> userData = new HashMap<>();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(authResponseUrl, String.class);
        Object obj = null;
        try {
            obj = new JSONParser().parse(responseEntity.getBody());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = (JSONObject) obj;
        userData.put("access_token", (String) jsonObject.get("access_token"));
        userData.put("user_id", jsonObject.get("user_id").toString());
        userData.put("email", (String) jsonObject.get("email"));
        return userData;
    }

    /**
     * @param userData возвращается методом getUserData(String authResponseUrl)
     * @return Map с именем, фамилией и ссылкой на аватар пользователя(100x100px)
     */
    @Override
    public Map<String, String> getUserData(Map<String, String> userData) {
        String request = usersGetURL + "?"
                + "users_ids=" + userData.get("user_id")
                + "&fields=" + fields
                + "&v=" + version
                + "&access_token=" + userData.get("access_token");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(request, String.class);
        JSONObject jo = null;
        try {
            jo = (JSONObject) JSONValue.parseWithException(responseEntity.getBody());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = (JSONArray) jo.get("response");
        JSONObject dataArray = (JSONObject) jsonArray.get(0);
        userData.put("first_name", (String) dataArray.get("first_name"));
        userData.put("last_name", (String) dataArray.get("last_name"));
        userData.put("avatar_link", (String) dataArray.get("photo_100"));
        return userData;
    }
}
