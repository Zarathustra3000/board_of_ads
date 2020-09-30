package com.board_of_ads.service.interfaces;

import java.util.Map;

public interface AuthVkService {

    String getAuthURL();

    String getAuthResponseURL(String code);

    Map<String, String> getUserData(String authResponseUrl);

    Map<String, String> getUserData(Map<String, String> userData);
}
