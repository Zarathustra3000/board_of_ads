package com.board_of_ads.configs.auth;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AuthMail {

    /*
    clientId и clientSecret получить можно будет только после того, как сайт окажется в сети
    После того, как сайт будет зарегестрирован на mail.ru будут получены все идентификаторы

    Описание процесса авторизации https://api.mail.ru/docs/guides/oauth/sites
     */


    private final String clientId = "XxXxXxXxXx";
    private final String clientSecret = "XxXxXxXxXx";
    private final String responseType = "code";

    private final String redirectURL = "http://localhost:5556/mail_auth";
    private final String authUrl = "https://connect.mail.ru/oauth/authorize";
    private final String tokenUrl = "https://connect.mail.ru/oauth/token";
    private final String userInfoUrl = "http://www.appsmail.ru/platform/api";

    public String getAuthURL() {
        return authUrl + "?"
                + "client_id=" + clientId
                + "&response_type=" + responseType
                + "&redirect_uri=" + redirectURL;
    }


    public String getAuthResponseURL(String code) {
        return tokenUrl + "?"
                + "client_id=" + clientId
                + "&client_secret=" + clientSecret
                + "grant_type=authorization_code&"
                + "&code=" + code
                + "&redirect_uri=" + redirectURL;

        /*
        В теле запроса должен получиться вот такой запрос:
        client_id={client_id}&client_secret={secret_key}&grant_type=
        authorization_code&code={code}&redirect_uri=http://mysite.com/mmlogin
         */

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


    public Map<String, String> getUserData(String token) {
        String request = userInfoUrl + "?"
                + "method=users.getInfo&"
                + "secure=1&"
                + "app_id=" + clientId
                + "&session_key=" + clientSecret
                + "&sig=hex_md5('app_id=" + clientId
                + "method=users.getInfosecure=1"
                + "session_key=" + token + clientSecret + "')";

        /*
        Должно получиться вот так:
        GET запрос->
         http://www.appsmail.ru/platform/api?method=users.getInfo&secure=1&app_id={client_id}&session_key={access_token}&sig={sign}
        sign — md5-подпись:
            sign=hex_md5('app_id={client_id}method=users.getInfosecure=1session_key={access_token}{secret_key}')
         */

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
        Map<String, String> userData = new HashMap<>();
        userData.put("first_name", (String) dataArray.get("first_name"));
        userData.put("last_name", (String) dataArray.get("last_name"));
        userData.put("email", (String) dataArray.get("email"));
        userData.put("pic_small", (String) dataArray.get("pic_small"));
        return userData;
    }
}