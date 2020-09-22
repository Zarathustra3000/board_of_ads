package com.board_of_ads.configs.auth;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class AuthVK {
    private final String clientId = "7604924";
    private final String clientSecret = "acpHC7p5T746jYx17yz1";
    private final String responseType = "code";

    private final String redirectURL = "http://localhost:8080/vk_auth";
    private final String authUrl = "http://oauth.vk.com/authorize";
    private final String tokenURL = "https://oauth.vk.com/access_token";
    private final String usersGetURL = "https://api.vk.com/method/users.get";

    private String scope = "email";
    private String display = "popup";
    private String fields = "photo_100";
    private String version = "5.124";


    public String getAuthURL() {
        return authUrl + "?"
                + "client_id=" + clientId
                + "&redirect_uri=" + redirectURL
                + "&scope=" + scope
                + "&display=" + display
                + "&response_type=" + responseType;
    }

    public String getAuthResponseURL(String code) {
        return tokenURL + "?"
                + "client_id=" + clientId
                + "&client_secret=" + clientSecret
                + "&redirect_uri=" + redirectURL
                + "&code=" + code;
    }

    public Map<String, String> getUserData(String authResponseUrl) {
        System.out.println(authResponseUrl);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(authResponseUrl, String.class);
        System.out.println(responseEntity.getBody());
        Object obj = null;
        try {
            obj = new JSONParser().parse(responseEntity.getBody());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = (JSONObject) obj;

        Map<String, String> userData = new HashMap<>();
        userData.put("access_token", (String) jsonObject.get("access_token"));
        userData.put("user_id", (String) jsonObject.get("user_id"));
        userData.put("email", (String) jsonObject.get("email"));

        System.out.println("TOKEN: " + jsonObject.get("access_token"));
        System.out.println("USER_ID: " + jsonObject.get("user_id"));
        System.out.println("EMAIL: " + jsonObject.get("email"));
        return userData;
    }

    public Map<String, String> getUserInfo(Map<String, String> userData) {
        String request = usersGetURL + "?"
                + "users_ids=" + userData.get("user_id")
                + "&fields=" + fields
                + "&v=" + version
                + "&access_token=" + userData.get("access_token");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(request, String.class);
        System.out.println(responseEntity.getBody());

        JSONObject jo = null;
        try {
            jo = (JSONObject) JSONValue.parseWithException(responseEntity.getBody());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = (JSONArray) jo.get("response");
        JSONObject dataArray = (JSONObject) jsonArray.get(0);

        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("first_name", (String) dataArray.get("first_name"));
        userInfo.put("last_name", (String) dataArray.get("last_name"));
        userInfo.put("avatar_link", (String) dataArray.get("photo_100"));
        System.out.println(userInfo.get("first_name"));
        System.out.println(userInfo.get("last_name"));
        System.out.println(userInfo.get("avatar_link"));

        return userInfo;
    }


}








//    public String createLink(String targetUrl, Map<String, String> params) {
//        StringBuilder sb = new StringBuilder(targetUrl);
//        sb.append("?");
//        for (Map.Entry<String, String> entry : params.entrySet()) {
//            sb.append(entry.getKey());
//            sb.append("=");
//            sb.append(entry.getValue());
//            sb.append("&");
//        }
//        return sb.substring(0, sb.length() - 1);
//    }