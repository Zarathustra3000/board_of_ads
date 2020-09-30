package com.board_of_ads.service.interfaces;

import java.util.Map;

public interface AuthYandexService {

    String getAuthURL();

    String getRequestBody(String code);

    String getToken(String body);

    Map<String, String> getUserData(String token);
}
