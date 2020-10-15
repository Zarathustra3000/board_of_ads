package com.board_of_ads.controllers.rest;

import com.board_of_ads.models.User;
import com.board_of_ads.service.interfaces.AuthorizationService;
import com.board_of_ads.service.interfaces.UserService;
import com.board_of_ads.util.Error;
import com.board_of_ads.util.ErrorResponse;
import com.board_of_ads.util.Response;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
@Slf4j
public class UserRestController {

    private final UserService userService;
    private final AuthorizationService authorizationService;

    @GetMapping
    public Response<Principal> getUser(@AuthenticationPrincipal Principal user) {
        log.info("Use this default logger");
        return user != null
                ?  Response.ok(user)
                : new Response.ErrorBuilderImpl().code(401).text("No auth user").build();
    }

    @PostMapping ("/modal-reg")
    public Response<User> Action(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (userService.checkUserDataBeforeReg(user, bindingResult, log)) {
            authorizationService.login(user);
            return Response.ok(user);
        }
        return new ErrorResponse<>(new Error(204, "Incorrect Data"));
    }

}
