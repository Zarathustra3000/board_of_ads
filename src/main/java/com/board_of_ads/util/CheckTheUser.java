/**
 * Компонент в котором осуществляется логика проверки валидности полей пользователя
 * при регистрации, а так-же отсутствие пользователя в базе данных и последующей авторизацией
 * пользователя на сайте при успешной регистрации
 * Для проверки валидности введенных данных и последующего логирования при наличии ошибок
 * используется <b>bindingResultLogs</b>
 * Для авторизации и проверки наличия пользователя в базе данных используется <b>authorizationService</b>
 * Общение с базой данных происходит посредством <b>userService</b>
 * @autor Карпов Василий
 * @version 1.0
 * @data 13.10.2020
 */

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

    /**
     * Метод осуществляет проверку если приходит POST запрос на RestController
     * {@link com.board_of_ads.controllers.rest.UserRestController#Action(User, BindingResult)}
     */
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
