package com.board_of_ads.configs.auth;

public class AuthYandex {
    private final String clientId = "2561bd17ecc1441d9c2461d780690cae";
    private final String responseType = "code";
    private final String display = "popup";
    private final String redirectURL = "http://localhost:5556/yandex_auth";

    private final String authUrl = "https://oauth.yandex.ru/authorize";

    public String getAuthURL() {
        return authUrl + "?"
                + "client_id=" + clientId
                + "&redirect_uri=" + redirectURL
                + "&display=" + display
                + "&response_type=" + responseType;
    }

    public String getToken(String code) {


        return "redirect:/";
    }







}
