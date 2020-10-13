package com.board_of_ads.controllers.rest;

import com.board_of_ads.models.User;
import com.board_of_ads.service.interfaces.AuthorizationService;
import com.board_of_ads.service.interfaces.UserService;
import com.board_of_ads.util.BindingResultLogs;
import com.board_of_ads.util.CheckTheUser;
import com.board_of_ads.util.Error;
import com.board_of_ads.util.ErrorResponse;
import com.board_of_ads.util.Response;
import com.board_of_ads.util.SuccessResponse;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user/")
public class UserRestController {

    private final UserService userService;
    private final CheckTheUser checkTheUser;
    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);


    @GetMapping
    public Response<Principal> getUser(Principal user) {
        return user != null
                ? new SuccessResponse<>(user)
                :  new ErrorResponse<>(new Error(401, "No auth user"));
    }

    @PostMapping ("/modal-reg")
    public Response<User> Action(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (checkTheUser.checkUserDataBeforeReg(user, bindingResult, logger)) {
            return Response.ok(user);
        }
        return new ErrorResponse<>(new Error(204, "Incorrect Data"));
    }

}
