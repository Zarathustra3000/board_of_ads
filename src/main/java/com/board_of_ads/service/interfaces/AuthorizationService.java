package com.board_of_ads.service.interfaces;

import com.board_of_ads.models.User;

public interface AuthorizationService {

    boolean isValid(User user);

    void login(User user);
}
