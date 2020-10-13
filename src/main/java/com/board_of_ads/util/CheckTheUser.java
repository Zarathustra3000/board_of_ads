package com.board_of_ads.util;

import com.board_of_ads.models.User;
import com.board_of_ads.service.interfaces.AuthorizationService;
import com.board_of_ads.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@AllArgsConstructor
@Component
public class CheckTheUser {

    private final AuthorizationService authorizationService;
    private final BindingResultLogs bindingResultLogs;
    private final UserService userService;

    public boolean checkUserDataBeforeReg(User user, BindingResult bindingResult, Logger logger) {
        if (authorizationService.isValid(user).equals("User not found!")
        && bindingResultLogs.checkUserFields(bindingResult, logger)) {
            userService.regUser(user);
            authorizationService.login(user);
            return true;
        }
        return false;
    }

}
